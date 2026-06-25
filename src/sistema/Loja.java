package sistema;

import java.util.Scanner;

import itens.Arma;
import itens.Armadura;
import itens.PocaoMana;
import itens.PocaoVida;
import itens.Item;
import personagens.Personagem;

public class Loja {

    private static final int PRECO_POCAO_VIDA     = 20;
    private static final int PRECO_POCAO_MANA     = 25;
    private static final int PRECO_ESPADA_FERRO   = 50;
    private static final int PRECO_ESPADA_ACO     = 100;
    private static final int PRECO_ARMADURA_COURO = 50;
    private static final int PRECO_ARMADURA_FERRO = 100;

    private Scanner sc;

    public Loja() {}

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

    private void comprar(Personagem jogador, Item item, int preco, String nomeItem) {
        if (jogador.gastarOuro(preco)) {
            jogador.getInventario().adicionarItem(item);
            System.out.println("✅ " + nomeItem + " comprado(a)! 💰 Ouro restante: " + jogador.getOuro());
        } else {
            System.out.println("❌ Ouro insuficiente! Você tem " + jogador.getOuro() + " ouro.");
        }
    }

    public void abrir(Personagem jogador, Scanner sc) {
        this.sc = sc;
        int opcao;

        do {
            limparTela();

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         🏪 LOJA              ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.printf( "║  💰 Ouro: %-19d║%n", jogador.getOuro());
            System.out.println("╚══════════════════════════════╝");

            System.out.println("\n🧪 USÁVEIS");
            System.out.printf("  1 - Poção de Vida         %3d ouro%n", PRECO_POCAO_VIDA);
            System.out.printf("  2 - Poção de Mana         %3d ouro%n", PRECO_POCAO_MANA);

            System.out.println("\n⚔️  ARMAS");
            System.out.printf("  3 - Espada de Ferro (+10 dano)  %3d ouro%n", PRECO_ESPADA_FERRO);
            System.out.printf("  4 - Espada de Aço  (+15 dano)  %3d ouro%n", PRECO_ESPADA_ACO);

            System.out.println("\n🛡️  ARMADURAS");
            System.out.printf("  5 - Armadura de Couro (+15 vida) %3d ouro%n", PRECO_ARMADURA_COURO);
            System.out.printf("  6 - Armadura de Ferro (+30 vida) %3d ouro%n", PRECO_ARMADURA_FERRO);

            System.out.println("\n  0 - Sair da loja");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:
                    comprar(jogador, new PocaoVida(), PRECO_POCAO_VIDA, "Poção de Vida");
                    break;
                case 2:
                    comprar(jogador, new PocaoMana(), PRECO_POCAO_MANA, "Poção de Mana");
                    break;
                case 3:
                    comprar(jogador, new Arma("Espada de Ferro", 10), PRECO_ESPADA_FERRO, "Espada de Ferro");
                    break;
                case 4:
                    comprar(jogador, new Arma("Espada de Aço", 15), PRECO_ESPADA_ACO, "Espada de Aço");
                    break;
                case 5:
                    comprar(jogador, new Armadura("Armadura de Couro", 15), PRECO_ARMADURA_COURO, "Armadura de Couro");
                    break;
                case 6:
                    comprar(jogador, new Armadura("Armadura de Ferro", 30), PRECO_ARMADURA_FERRO, "Armadura de Ferro");
                    break;
                case 0:
                    System.out.println("👋 Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            }

        } while (opcao != 0);
    }
}