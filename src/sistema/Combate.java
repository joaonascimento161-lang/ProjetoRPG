package sistema;

import audio.SomManager;
import inimigos.BossFinal;
import inimigos.Inimigo;
import itens.Item;
import personagens.Adm;
import personagens.Personagem;
import save.SaveManager;

import java.util.Scanner;

public class Combate {

    private Scanner sc;

    public Combate(Scanner sc) {
        this.sc = sc;
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private int lerInt() {
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

    // Barra normal com cor dinâmica
    public static String criarBarra(int atual, int maximo) {
        int tamanho = 20;
        int preenchido = (maximo > 0) ? (atual * tamanho) / maximo : 0;

        double porcentagem = (double) atual / maximo;
        String cor;
        if (porcentagem > 0.5) {
            cor = "\u001B[32m"; // verde
        } else if (porcentagem > 0.25) {
            cor = "\u001B[33m"; // amarelo
        } else {
            cor = "\u001B[31m"; // vermelho
        }

        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            barra.append(i < preenchido ? "█" : "-");
        }
        barra.append("]");

        return cor + barra + "\u001B[0m";
    }

    // Barra infinita para o Adm (roxa + símbolo ∞)
    private static String criarBarraInfinita() {
        return "\u001B[35m[████████████████████]\u001B[0m ∞";
    }

    public boolean iniciar(Personagem jogador, Inimigo inimigo) {
        limparTela();
        System.out.println("╔══════════════════════╗");
        System.out.println("║    ⚔️  COMBATE!      ║");
        System.out.println("╠══════════════════════╣");
        System.out.printf(" ║  %s VS %s%n", jogador.getNome(), inimigo.getNome());
        System.out.println("╚══════════════════════╝");

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            mostrarBarras(jogador, inimigo);

            boolean fugiu = turnoJogador(jogador, inimigo);

            if (fugiu) {
                System.out.println("🏃 Você fugiu do combate!");
                return false;
            }

            if (!inimigo.estaVivo()) break;

            turnoInimigo(jogador, inimigo);
        }

        return resolverFim(jogador, inimigo);
    }

    private void mostrarBarras(Personagem jogador, Inimigo inimigo) {
        System.out.println("\n════════════════════════════");

        System.out.println("👤 " + jogador.getNome());

        if (jogador instanceof Adm) {
            // Adm: barras infinitas em roxo
            System.out.println("  HP   " + criarBarraInfinita());
            System.out.println("  Mana " + criarBarraInfinita());
        } else {
            System.out.printf("  HP   %s %d/%d%n",
                    criarBarra(jogador.getVida(), jogador.getVidaMax()),
                    jogador.getVida(), jogador.getVidaMax());
            System.out.printf("  Mana %s %d/%d%n",
                    criarBarra(jogador.getMana(), jogador.getManaMax()),
                    jogador.getMana(), jogador.getManaMax());
        }

        System.out.println();

        System.out.println("👹 " + inimigo.getNome());
        System.out.printf("  HP   %s %d/%d%n",
                criarBarra(inimigo.getVida(), inimigo.getVidaMax()),
                inimigo.getVida(), inimigo.getVidaMax());

        System.out.println("════════════════════════════");
    }

    private boolean turnoJogador(Personagem jogador, Inimigo inimigo) {
        System.out.println("\n⚔️  SEU TURNO:");
        System.out.println("  1 - Atacar");
        System.out.println("  2 - Habilidade");
        System.out.println("  3 - Inventário");
        System.out.println("  4 - Fugir");
        System.out.print("Escolha: ");

        int opcao = lerInt();

        switch (opcao) {
            case 1: jogador.atacar(inimigo);    SomManager.somAtaque();     break;
            case 2: jogador.usarHab(inimigo);   SomManager.somHabilidade(); break;
            case 3: abrirInventario(jogador);   break;
            case 4: return true;
            default: System.out.println("Opção inválida. Turno perdido!"); SomManager.somErro();
        }

        return false;
    }

    private void turnoInimigo(Personagem jogador, Inimigo inimigo) {
        limparTela();
        mostrarBarras(jogador, inimigo);
        System.out.println("\n👹 TURNO DO INIMIGO:");
        inimigo.realizarTurno(jogador);
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine();
    }

    private boolean resolverFim(Personagem jogador, Inimigo inimigo) {
        limparTela();

        if (jogador.estaVivo()) {
            System.out.println("🏆 VITÓRIA!");
            System.out.println("Você derrotou " + inimigo.getNome() + "!");
            SomManager.somVitoria();

            boolean vidaCritica = jogador.getVida() <= jogador.getVidaMax() * 0.10;

            XPSystem.ganharXP(jogador, inimigo.getRecompensaXP());
            jogador.adicionarOuro(inimigo.getRecompensaOuro());
            System.out.println("💰 +" + inimigo.getRecompensaOuro() + " ouro");

            ConquistaManager.registrarVitoria(inimigo.getNome(), vidaCritica);
            ConquistaManager.registrarOuro(jogador.getOuro());

            resolverMissao(jogador, inimigo);
            resolverDrop(jogador, inimigo);

            if (inimigo instanceof BossFinal) {
                GameData.desbloquearDeus();
                SaveManager.salvar(jogador, true);
                System.out.println("✨ CLASSE DEUS DESBLOQUEADA!");
            }

            System.out.println("\nPressione Enter para continuar...");
            sc.nextLine();
            return true;
        }

        System.out.println("💀 DERROTA!");
        System.out.println("Você foi derrotado por " + inimigo.getNome() + ".");
        SomManager.somDerrota();
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine();
        return false;
    }

    private void resolverMissao(Personagem jogador, Inimigo inimigo) {
        if (jogador.getMissaoAtual() == null) return;

        jogador.getMissaoAtual().registrarAbate(inimigo.getNome());

        if (jogador.getMissaoAtual().isConcluida()) {
            System.out.println("\n🎯 MISSÃO CONCLUÍDA!");
            XPSystem.ganharXP(jogador, jogador.getMissaoAtual().getRecompensaXP());
            jogador.adicionarOuro(jogador.getMissaoAtual().getRecompensaOuro());
            System.out.println("💰 +" + jogador.getMissaoAtual().getRecompensaOuro() + " ouro (missão)");
            ConquistaManager.registrarMissaoCumprida();
            jogador.aceitarMissao(null);
        }
    }

    private void resolverDrop(Personagem jogador, Inimigo inimigo) {
        Item drop = inimigo.gerarDrop();
        if (drop != null) {
            jogador.getInventario().adicionarItem(drop);
            System.out.println("🎁 Item encontrado: " + drop.getNome());
        }
    }

    private void abrirInventario(Personagem jogador) {
        limparTela();

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