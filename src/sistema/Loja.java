package sistema;

import java.util.Scanner;

import itens.Arma;
import itens.Armadura;
import itens.PocaoMana;
import itens.PocaoVida;
import personagens.Personagem;

public class Loja {
    
    private static final int PRECO_POCAO_VIDA = 20;
    private static final int PRECO_POCAO_MANA = 25;

    private static final int PRECO_ESPADA_FERRO = 50;
    private static final int PRECO_ESPADA_ACO = 100;

    private static final int PRECO_ARMADURA_COURO = 50;
    private static final int PRECO_ARMADURA_FERRO = 100;

    public void abrir(Personagem jogador, Scanner sc){
        int opcao;

        do{

            System.out.println("---- LOJA ----");
            System.out.println("Ouro: " + jogador.getOuro());

            System.out.println("\n----- USAVEIS -----");
            System.out.println("1 - Poção de vida (20 Ouros)");
            System.out.println("2 - Poção de mana (25 de ouros)");

            System.out.println("\n---- ARMAS ----");
            System.out.println("3 - Espada de ferro (+10 dano) (50 Ouro)");
            System.out.println("4 - Espada de aço (+15 dano) (100 Ouro)");

            System.out.println("\n---- ARMADURAS ----");
            System.out.println("5 - Armadura de couro (+15 vida) (50 Ouro)");
            System.out.println("6 - Armadura de ferro (+30 vida) (100 Ouro)");

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
                case 3:
                    if(jogador.gastarOuro(PRECO_ESPADA_FERRO)){
                        jogador.getInventario().adicionarItem(new Arma("Espada de ferro", 10));

                        System.out.println("Espada comprada");
                    }else{
                        System.out.println("Ouro insuficiente");
                    }
                    break;
                case 4:
                    if(jogador.gastarOuro(PRECO_ESPADA_ACO)){
                        jogador.getInventario().adicionarItem(new Arma("Espada de aço", 15));

                        System.out.println("Espada comprada");
                    }else{
                        System.out.println("Ouro insuficiente");
                    }
                    break;
                case 5:
                    if(jogador.gastarOuro(PRECO_ARMADURA_COURO)){
                        jogador.getInventario().adicionarItem(new Armadura("Armadura de couro", 15));

                        System.out.println("Armadura comprada");
                    }else{
                        System.out.println("Ouro insuficiente");
                    }
                    break;
                case 6:
                    if(jogador.gastarOuro(PRECO_ARMADURA_FERRO)){
                        jogador.getInventario().adicionarItem(new Arma("Armadura de ferro", 30));

                        System.out.println("Armadura comprada");
                    }else{
                        System.out.println("Ouro insuficiente");
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