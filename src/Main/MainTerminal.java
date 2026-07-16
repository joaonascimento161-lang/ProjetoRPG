package Main;

import java.util.Scanner;

import personagens.*;
import inimigos.*;
import Areas.*;
import Areas.Area;
import itens.*;
import save.*;
import sistema.*;
import audio.SomManager;

public class MainTerminal {

    private static Scanner sc = new Scanner(System.in);
    private static Area areas[] = {
            new Floresta(),
            new Caverna(),
            new Ruinas(),
            new CasteloSombrio(),
            new CovilDragao(),
            new Vulcao(),
            new AlpesSuicos(),
            new MansaoMafia(),
            new MarEletrico()
    };
    private static final int[] NIVEIS_MINIMOS = {1, 2, 3, 5, 8, 10, 15, 20, 35};
    private static final String[] NOMES_AREAS = {
            "🌲Floresta",
            "⛰ Caverna",
            "🪨Ruínas",
            "🏰 Castelo Sombrio",
            "🐉 Covil do Dragão",
            "🌋 Vulcão",
            "🏔 Alpes Suíços",
            "🕴 Mansão Mafia",
            "⚡ Mar Elétrico"
    };

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int lerInt() {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }

    public static void iniciar() {
        int opcao;

        ConquistaManager.carregar();

        do {
            limparTela();
            System.out.println("╔════════════╗");
            System.out.println("║    RPG     ║");
            System.out.println("╠════════════╣");
            System.out.println("║1 Novo Jogo ║");
            System.out.println("║2 Continuar ║");
            System.out.println("║3 Sair      ║");
            System.out.println("╚════════════╝");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1: novoJogo();     break;
                case 2: continuarJogo(); break;
                case 3: System.out.println("Até a próxima! 👋"); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 3);

        sc.close();
    }

    private static void novoJogo() {
        Personagem jogador = escolherClasse();
        ConquistaManager.registrarNivel(jogador.getNivel());
        menuAreas(jogador);
        SaveManager.salvar(jogador, GameData.isDeusDesbloqueado());
        ConquistaManager.salvar();
        System.out.println("✅ Jogo salvo com sucesso!");
    }

    private static void continuarJogo() {
        if (!SaveManager.existeSave()) {
            System.out.println("Nenhum save encontrado.");
            return;
        }
        Personagem jogador = SaveManager.carregar();
        if (jogador != null) {
            System.out.println("✅ Save carregado! Bem-vindo de volta, " + jogador.getNome() + "!");
            ConquistaManager.registrarNivel(jogador.getNivel());
            ConquistaManager.registrarOuro(jogador.getOuro());
            menuAreas(jogador);
        }
    }

    private static Personagem escolherClasse() {
        limparTela();
        System.out.println("\n⚔️  Escolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Paladino");
        System.out.println("5 - Assassino");
        System.out.println("6 - Berserker");
        System.out.println("7 - Curandeiro");

        if (GameData.isDeusDesbloqueado()) {
            System.out.println("8 - ✨ Deus (desbloqueado!)");
        }

        System.out.println("0 - [RESTRITO]");
        System.out.print("Escolha: ");
        int escolha = lerInt();

        switch (escolha) {
            case 1: return new Guerreiro();
            case 2: return new Mago();
            case 3: return new Arqueiro();
            case 4: return new Paladino();
            case 5: return new Assassino();
            case 6: return new Berserker();
            case 7: return new Curandeiro();
            case 8:
                if (GameData.isDeusDesbloqueado()) return new Deus();
                System.out.println("Classe não disponível.");
                return new Guerreiro();
            case 0:
                return tentarLoginAdm();
            default:
                System.out.println("Classe inválida. Iniciando como Guerreiro.");
                return new Guerreiro();
        }
    }

    private static Personagem tentarLoginAdm() {
        limparTela();
        System.out.println("╔══════════════════════╗");
        System.out.println("║   ACESSO RESTRITO    ║");
        System.out.println("╚══════════════════════╝");

        String senha;
        java.io.Console console = System.console();
        if (console != null) {
            char[] senhaChar = console.readPassword("🔑 Senha: ");
            senha = new String(senhaChar);
            java.util.Arrays.fill(senhaChar, ' ');
        } else {
            System.out.print("🔑 Senha: ");
            senha = sc.nextLine();
        }

        Personagem adm = Adm.tentarCriar(senha);

        if (adm != null) {
            limparTela();
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║  ✅ ACESSO CONCEDIDO         ║");
            System.out.println("║  Bem-vindo, Administrador.   ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.println("\nPressione Enter para continuar...");
            sc.nextLine();
            return adm;
        }

        limparTela();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║  ❌ ACESSO NEGADO             ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("Iniciando como Guerreiro.");
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine();
        return new Guerreiro();
    }

    private static void menuAreas(Personagem jogador) {
        while (jogador.estaVivo()) {
            limparTela();
            System.out.println("\n🗺️  ----- ÁREAS -----");

            for (int i = 0; i < areas.length; i++) {
                if (jogador.getNivel() >= NIVEIS_MINIMOS[i]) {
                    System.out.println((i + 1) + " - " + NOMES_AREAS[i]);
                } else {
                    System.out.println((i + 1) + " - 🔒 Bloqueado (Nível " + NIVEIS_MINIMOS[i] + ")");
                }
            }

            System.out.println("\n" + (areas.length + 1) + " - 🏪 Loja");
            System.out.println((areas.length + 2) + " - 📊 Status");
            System.out.println((areas.length + 3) + " - 🎒 Inventário");
            System.out.println((areas.length + 4) + " - 🏆 Conquistas");
            System.out.println((areas.length + 5) + " - " + (SomManager.isAtivo() ? "🔊 Som: LIGADO" : "🔇 Som: DESLIGADO") + " (alternar)");
            System.out.println("0 - 💾 Salvar e Sair");
            System.out.print("Escolha: ");

            int escolha = lerInt();
            limparTela();

            if (escolha == 0) {
                SaveManager.salvar(jogador, GameData.isDeusDesbloqueado());
                ConquistaManager.salvar();
                System.out.println("✅ Jogo salvo!");
                return;
            } else if (escolha == areas.length + 1) {
                new Loja().abrir(jogador, sc);
            } else if (escolha == areas.length + 2) {
                jogador.mostrarStatus();
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            } else if (escolha == areas.length + 3) {
                abrirInventario(jogador);
            } else if (escolha == areas.length + 4) {
                ConquistaManager.mostrarConquistas();
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            } else if (escolha == areas.length + 5) {
                SomManager.alternar();
                System.out.println(SomManager.isAtivo() ? "🔊 Som ligado!" : "🔇 Som desligado!");
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            } else if (escolha >= 1 && escolha <= areas.length) {
                iniciarArea(jogador, areas[escolha - 1]);
                if (jogador.getNivel() >= NIVEIS_MINIMOS[areas.length - 1]) {
                    ConquistaManager.registrarTodasAreasDesbloqueadas();
                }
            } else {
                System.out.println("Área inválida.");
                SomManager.somErro();
            }
        }

        System.out.println("\n💀 Você morreu. Fim de jogo.");
    }

    private static void iniciarArea(Personagem jogador, Area area) {
        if (jogador.getNivel() < area.getNivelMinimo()) {
            System.out.println("⚠️  Você precisa estar no nível " + area.getNivelMinimo() + " para entrar nesta área.");
            System.out.println("Pressione Enter para continuar...");
            sc.nextLine();
            return;
        }

        Inimigo inimigo = area.generateEnemie();
        Combate combate = new Combate(sc);
        boolean venceu = combate.iniciar(jogador, inimigo);

        if (venceu && inimigo instanceof BossFinal) {
            GameData.desbloquearDeus();
            System.out.println("🏆 CLASSE DEUS DESBLOQUEADA!");
            SaveManager.salvar(jogador, true);
        }
    }

    private static void abrirInventario(Personagem jogador) {
        if (jogador.getInventario().estaVazio()) {
            System.out.println("🎒 Inventário vazio.");
            return;
        }

        jogador.getInventario().listarItens();
        System.out.println("0 - Cancelar");
        System.out.print("Escolha um item: ");
        int escolha = lerInt() - 1;

        if (escolha == -1) return;

        Item item = jogador.getInventario().getItem(escolha);
        if (item != null) {
            item.usar(jogador);
            jogador.getInventario().removerItem(escolha);
        } else {
            System.out.println("Item inválido.");
        }
    }
}