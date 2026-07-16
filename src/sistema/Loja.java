package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import audio.SomManager;
import itens.Arma;
import itens.Armadura;
import itens.Equipamento;
import itens.EquipamentoFactory;
import itens.Item;
import itens.PocaoMana;
import itens.PocaoVida;
import personagens.Personagem;

/**
 * Loja avançada: vende consumíveis e todos os equipamentos cadastrados na
 * EquipamentoFactory (incluindo os raros/épicos/lendários), com preço
 * calculado dinamicamente pela raridade, além de permitir vender itens do
 * inventário por ouro.
 */
public class Loja {

    private static final int PRECO_POCAO_VIDA = 20;
    private static final int PRECO_POCAO_MANA = 25;
    private static final int PRECO_BASE_ARMA = 20;
    private static final int PRECO_BASE_ARMADURA = 20;

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

    private void pausar() {
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine();
    }

    public void abrir(Personagem jogador, Scanner sc) {
        this.sc = sc;
        int opcao;

        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          🏪 LOJA AVANÇADA             ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.printf( "║  💰 Ouro: %-28d║%n", jogador.getOuro());
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1 - 🧪 Usáveis                      ║");
            System.out.println("║  2 - ⚔️  Armas                        ║");
            System.out.println("║  3 - 🛡️  Armaduras                    ║");
            System.out.println("║  4 - 💵 Vender itens                 ║");
            System.out.println("║  0 - Sair da loja                    ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1: menuUsaveis(jogador);   break;
                case 2: menuArmas(jogador);     break;
                case 3: menuArmaduras(jogador); break;
                case 4: menuVender(jogador);    break;
                case 0: System.out.println("👋 Até logo!"); break;
                default: System.out.println("Opção inválida."); SomManager.somErro();
            }

        } while (opcao != 0);
    }

    // -------- USAVEIS --------

    private void menuUsaveis(Personagem jogador) {
        int opcao;
        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║             🧪 USÁVEIS                ║");
            System.out.printf( "║  💰 Ouro: %-28d║%n", jogador.getOuro());
            System.out.println("╠══════════════════════════════════════╣");
            System.out.printf("║  1 - Poção de Vida     %3d ouro       ║%n", PRECO_POCAO_VIDA);
            System.out.printf("║  2 - Poção de Mana     %3d ouro       ║%n", PRECO_POCAO_MANA);
            System.out.println("║  0 - Voltar                          ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha: ");

            opcao = lerInt();
            switch (opcao) {
                case 1: comprarItem(jogador, new PocaoVida(), PRECO_POCAO_VIDA, "Poção de Vida"); pausar(); break;
                case 2: comprarItem(jogador, new PocaoMana(), PRECO_POCAO_MANA, "Poção de Mana"); pausar(); break;
                case 0: break;
                default: System.out.println("Opção inválida."); SomManager.somErro(); pausar();
            }
        } while (opcao != 0);
    }

    // -------- ARMAS --------

    private void menuArmas(Personagem jogador) {
        List<Arma> armas = new ArrayList<>(EquipamentoFactory.listarArmas().values());
        int opcao;
        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                  ⚔️  ARMAS                        ║");
            System.out.printf( "║  💰 Ouro: %-40d║%n", jogador.getOuro());
            System.out.println("╠══════════════════════════════════════════════════╣");

            for (int i = 0; i < armas.size(); i++) {
                Arma a = armas.get(i);
                int preco = EquipamentoFactory.calcularPreco(a, PRECO_BASE_ARMA);
                System.out.printf("  %2d - %-45s %4d ouro%n",
                        i + 1, a.getRaridade().formatar(a.getNome() + " (+" + a.getBonusDano() + " dano)"), preco);
            }
            System.out.println("\n   0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInt();
            if (opcao >= 1 && opcao <= armas.size()) {
                Arma escolhida = armas.get(opcao - 1);
                int preco = EquipamentoFactory.calcularPreco(escolhida, PRECO_BASE_ARMA);
                comprarEquipamento(jogador, novaArma(escolhida), preco);
                pausar();
            } else if (opcao != 0) {
                System.out.println("Opção inválida.");
                SomManager.somErro();
                pausar();
            }
        } while (opcao != 0);
    }

    // -------- ARMADURAS --------

    private void menuArmaduras(Personagem jogador) {
        List<Armadura> armaduras = new ArrayList<>(EquipamentoFactory.listarArmaduras().values());
        int opcao;
        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                 🛡️  ARMADURAS                     ║");
            System.out.printf( "║  💰 Ouro: %-40d║%n", jogador.getOuro());
            System.out.println("╠══════════════════════════════════════════════════╣");

            for (int i = 0; i < armaduras.size(); i++) {
                Armadura a = armaduras.get(i);
                int preco = EquipamentoFactory.calcularPreco(a, PRECO_BASE_ARMADURA);
                System.out.printf("  %2d - %-45s %4d ouro%n",
                        i + 1, a.getRaridade().formatar(a.getNome() + " (+" + a.getBonusVida() + " vida)"), preco);
            }
            System.out.println("\n   0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInt();
            if (opcao >= 1 && opcao <= armaduras.size()) {
                Armadura escolhida = armaduras.get(opcao - 1);
                int preco = EquipamentoFactory.calcularPreco(escolhida, PRECO_BASE_ARMADURA);
                comprarEquipamento(jogador, novaArmadura(escolhida), preco);
                pausar();
            } else if (opcao != 0) {
                System.out.println("Opção inválida.");
                SomManager.somErro();
                pausar();
            }
        } while (opcao != 0);
    }

    // -------- VENDER --------

    private void menuVender(Personagem jogador) {
        limparTela();
        if (jogador.getInventario().estaVazio()) {
            System.out.println("🎒 Seu inventário está vazio. Nada para vender.");
            pausar();
            return;
        }

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║               💵 VENDER ITENS                     ║");
        System.out.println("╠══════════════════════════════════════════════════╣");

        List<Item> itens = jogador.getInventario().getItens();
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            int precoVenda = calcularPrecoVenda(item);
            String nomeExibicao = (item instanceof Equipamento)
                    ? ((Equipamento) item).getNomeFormatado()
                    : item.getNome();
            System.out.printf("  %2d - %-45s %4d ouro%n", i + 1, nomeExibicao, precoVenda);
        }
        System.out.println("\n   0 - Voltar");
        System.out.print("Escolha o item para vender: ");

        int escolha = lerInt() - 1;
        if (escolha == -1) return;

        Item item = jogador.getInventario().getItem(escolha);
        if (item == null) {
            System.out.println("Item inválido.");
            SomManager.somErro();
            pausar();
            return;
        }

        int precoVenda = calcularPrecoVenda(item);
        jogador.getInventario().removerItem(escolha);
        jogador.adicionarOuro(precoVenda);
        System.out.println("✅ " + item.getNome() + " vendido por " + precoVenda + " ouro!");
        SomManager.somCompra();
        pausar();
    }

    private int calcularPrecoVenda(Item item) {
        if (item instanceof Arma) {
            return EquipamentoFactory.calcularPreco((Arma) item, PRECO_BASE_ARMA) / 2;
        }
        if (item instanceof Armadura) {
            return EquipamentoFactory.calcularPreco((Armadura) item, PRECO_BASE_ARMADURA) / 2;
        }
        if (item instanceof PocaoVida) return PRECO_POCAO_VIDA / 2;
        if (item instanceof PocaoMana) return PRECO_POCAO_MANA / 2;
        return 5;
    }

    // -------- utilitarios de compra --------

    private void comprarItem(Personagem jogador, Item item, int preco, String nomeItem) {
        if (jogador.gastarOuro(preco)) {
            jogador.getInventario().adicionarItem(item);
            System.out.println("✅ " + nomeItem + " comprado(a)! 💰 Ouro restante: " + jogador.getOuro());
            SomManager.somCompra();
        } else {
            System.out.println("❌ Ouro insuficiente! Você tem " + jogador.getOuro() + " ouro.");
            SomManager.somErro();
        }
    }

    private void comprarEquipamento(Personagem jogador, Equipamento equipamento, int preco) {
        if (jogador.gastarOuro(preco)) {
            jogador.getInventario().adicionarItem(equipamento);
            System.out.println("✅ " + equipamento.getNomeFormatado() + " comprado! 💰 Ouro restante: " + jogador.getOuro());
            SomManager.somCompra();
            ConquistaManager.registrarCompra(equipamento);
        } else {
            System.out.println("❌ Ouro insuficiente! Você tem " + jogador.getOuro() + " ouro (precisa de " + preco + ").");
            SomManager.somErro();
        }
    }

    // Cria uma nova instancia independente do item de catalogo (evita compartilhar a mesma referencia entre compras)
    private Arma novaArma(Arma modelo) {
        return new Arma(modelo.getNome(), modelo.getBonusDano(), modelo.getRaridade());
    }

    private Armadura novaArmadura(Armadura modelo) {
        return new Armadura(modelo.getNome(), modelo.getBonusVida(), modelo.getRaridade());
    }
}
