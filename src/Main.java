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

        menuAreas(jogador);

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

    private static void menuAreas(Personagem jogador){

        while (jogador.estaVivo()) {

            System.out.println("\n----- ÁREAS -----");

            for(int cont = 1; cont <= 5; cont++){

                if(cont <= Progressao.getAreaLiberada()){

                    switch (cont) {
                        case 1:
                            System.out.println("1 - Floresta");
                            break;
                        case 2:
                            System.out.println("2 - Caverna");
                            break;
                        case 3:
                            System.out.println("3 - Ruínas");
                            break;
                        case 4:
                            System.out.println("4 - Castelo sombrio");
                            break;
                        case 5:
                            System.out.println("5 - Covil do Dragão");
                            break;

                    }
                }else{

                    System.out.println(cont + " - Bloqueado");
                }
            }
            
            System.out.println("Escolha: ");
            int escolha = sc.nextInt();
            
            iniciarArea(jogador, escolha);
        }
    }

    private static void iniciarArea(Personagem jogador, int Area){

        if(Area > Progressao.getAreaLiberada()){
            System.out.println("Área bloqueada");

            return;
        }

        int nivelNecessario = 1;

        switch (Area) {
            case 1:
                nivelNecessario = 1;
                break;
            case 2:
                nivelNecessario = 3;
                break;
            case 3:
                nivelNecessario = 5;
                break;
            case 4:
                nivelNecessario = 8;
                break;
            case 5:
                nivelNecessario = 10;
                break;
        }

        if(jogador.getNivel() < nivelNecessario){

            System.out.println("Você precisa estar no nivel " + nivelNecessario + " para entrar nesta área");
            return;
        }

        Inimigo inimigo = null;

        switch (Area) {
            case 1:
                inimigo = new Goblin();
                break;
            case 2:
                inimigo = new Orc();
                break;
            case 3:
                inimigo = new Esqueleto();
                break;
            case 4:
                inimigo = new MagoSombrio();
                break;
            case 5:
                inimigo = new BossFinal();
                break;
                
            default:
                System.out.println("Área inválida");
                return;
        }

        Combate combate = new Combate(sc);

        boolean venceu = combate.iniciar(jogador, inimigo);

        if(venceu){
            Progressao.desbloquearProximaArea();

            if(inimigo instanceof BossFinal){

                GameData.desbloquearDeus();

                SaveManager.salvar(jogador, true);
            }
        }
    }
}