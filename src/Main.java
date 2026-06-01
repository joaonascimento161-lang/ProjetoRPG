import java.util.Scanner;

import personagens.*;
import inimigos.*;
import save.*;
import sistema.*;

public class Main {
    
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        int opcao;

        do{

            System.out.println("\n---- RPG ----");
            System.out.println("1 - Novo jogo");
            System.out.println("2 - Continuar");
            System.out.println("3 - Sair");

            System.out.println("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    novoJogo();
                    break;
                case 2:
                    continaurJogo();
                    break;
                case 3:
                    System.out.println("Até a próxima");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while (opcao != 3);
        
        sc.close();
    }

    private static void novoJogo(){

        Personagem jogador = escolherClasse();

        Goblin goblin = new Goblin();

        Combate combate = new Combate(sc);

        combate.iniciar(jogador, goblin);

        SaveManager.salvar(jogador, GameData.isDeusDesbloqueado());
    }

    private static void continaurJogo(){

        if(!SaveManager.existeSave()){

            System.out.println("Nenhum save encontrado. ");

            return;
        }

        SaveManager.carregar();
    }

    private static Personagem escolherClasse(){

        System.out.println("\nEscolha sua classe:");

        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Paladino");
        System.out.println("5 - Assasino");
        System.out.println("6 - Berserker");
        System.out.println("7 - Curandeiro");

        if(GameData.isDeusDesbloqueado()){

            System.out.println("8 - Deus");
        }

        int escolha = sc.nextInt();

        switch (escolha) {
            case 1:
                return new Guerreiro();
            case 2:
                return new Mago();
            case 3:
                return new Arqueiro();
            case 4:
                return new Paladino();
            case 5:
                return new Assassino();
            case 6:
                return new Berserker();
            case 7:
                return new Curandeiro();
            case 8:
                if(GameData.isDeusDesbloqueado()){
                    return new Deus();
                }
            default:

                System.out.println("Classe inválida.");
                return new Guerreiro();
        }
    }
}
