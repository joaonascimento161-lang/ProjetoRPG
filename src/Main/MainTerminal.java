package Main;

import java.util.Scanner;

import personagens.*;
import inimigos.*;
import Areas.*;
import Areas.Area;
import itens.*;
import save.*;
import sistema.*;

public class MainTerminal {

    private static Scanner sc = new Scanner(System.in);
    private static Area areas[] = {new Floresta(), new Caverna(), new Ruinas(), new CasteloSombrio(), new CovilDragao()};
    private static final int[] NIVEIS_MINIMOS = {1, 3, 5, 8, 10};

    // Limpa o terminal de verdade
    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Leitura segura de inteiro (evita crash com entrada inválida)
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
                case 1:
                    novoJogo();
                    break;
                case 2:
                    continuarJogo();
                    break;
                case 3:
                    System.out.println("Até a próxima! 👋");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);

        sc.close();
    }

    private static void novoJogo() {
        Personagem jogador = escolherClasse();
        menuAreas(jogador);
        SaveManager.salvar(jogador, GameData.isDeusDesbloqueado());
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
            java.util.Arrays.fill(senhaChar, ' '); // limpa da memória após uso
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
        String[] nomesAreas = {"Floresta", "Caverna", "Ruínas", "Castelo Sombrio", "Covil do Dragão"};

        while (jogador.estaVivo()) {
            limparTela();

            System.out.println("\n🗺️  ----- ÁREAS -----");

            for (int i = 0; i < areas.length; i++) {
                if (jogador.getNivel() >= NIVEIS_MINIMOS[i]) {
                    System.out.println((i + 1) + " - " + nomesAreas[i]);
                } else {
                    System.out.println((i + 1) + " - 🔒 Bloqueado (Nível " + NIVEIS_MINIMOS[i] + ")");
                }
            }

            System.out.println("\n6 - 🏪 Loja");
            System.out.println("7 - 📊 Status");
            System.out.println("8 - 🎒 Inventário");
            System.out.println("0 - 💾 Salvar e Sair");
            System.out.print("Escolha: ");

            int escolha = lerInt();
            limparTela();

            switch (escolha) {
                case 0:
                    SaveManager.salvar(jogador, GameData.isDeusDesbloqueado());
                    System.out.println("✅ Jogo salvo!");
                    return;
                case 6:
                    new Loja().abrir(jogador, sc);
                    break;
                case 7:
                    jogador.mostrarStatus();
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                    break;
                case 8:
                    abrirInventario(jogador);
                    break;
                default:
                    if (escolha >= 1 && escolha <= areas.length) {
                        iniciarArea(jogador, areas[escolha - 1]);
                    } else {
                        System.out.println("Área inválida.");
                    }
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

        if (escolha == -1) return; // cancelou

        Item item = jogador.getInventario().getItem(escolha);

        if (item != null) {
            item.usar(jogador);
            jogador.getInventario().removerItem(escolha);
        } else {
            System.out.println("Item inválido.");
        }
    }
}