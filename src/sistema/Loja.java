package sistema;

import java.util.Scanner;

import itens.PocaoMana;
import itens.PocaoVida;
import personagens.Personagem;

public class Loja {
    
    private static final int PRECO_POCAO_VIDA = 20;
    private static final int PRECO_POCAO_MANA = 25;

    public void abrir(Personagem jogador, Scanner sc){
        int opcao;

        do{

            System.out.println("---- LOJA ----");
            System.out.println("Ouro: " + jogador.getOuro());

            System.out.println("\n1 - Poção de vida (20 Ouros)");
            System.out.println("2 - Poção de mana (25 de ouros)");
            System.out.println("0 - Sair");

            System.out.println("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:

                    if(jogador.gastarOuro(PRECO_POCAO_VIDA)){

                        jogador.getInventario().adicionarItem(new PocaoVida());

                        System.out.println("Poção de vida comprada!");
                    }else {
                        System.out.println("Ouro insuficiente!");
                    }
                    break;

                case 2:
                    if(jogador.gastarOuro(PRECO_POCAO_MANA)){

                        jogador.getInventario().adicionarItem(new PocaoMana());

                        System.out.println("Poção de mana comprada!");
                    }else{
                        System.out.println("Ouro insuficiente!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo da loja...");
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }while(opcao != 0);
    }
}