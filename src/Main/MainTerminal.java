    package main;

    import java.util.Random;
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

    public static void iniciar(){

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

        Personagem jogador = SaveManager.carregar();

        if(jogador != null){
            menuAreas(jogador);
        }
    }

    private static Personagem escolherClasse(){
        
        for(int cont = 0; cont < 20; cont++){
            System.out.println("\n\n");
        }

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

            for(int cont = 0; cont < 20; cont++){
                System.out.println("\n\n");
            }

            System.out.println("\n----- ÁREAS -----");

            for(int cont = 1; cont <= 5; cont++){

                if(areaLiberada(jogador, cont)){

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

            System.out.println("\n6 - Loja");
            System.out.println("7 - Status");
            System.out.println("8 - Inventario");
            System.out.println("\n0 - Salvar e Sair");
            
            System.out.println("Escolha: ");
            int escolha = sc.nextInt();

            for(int cont = 0; cont < 20; cont++){
                System.out.println("\n\n");
            }
            
            if(escolha == 6){
                Loja loja = new Loja();
                loja.abrir(jogador, sc);
            }else if(escolha == 7){
                jogador.mostrarStatus();
        
            }else if(escolha == 8){
                abrirInventario(jogador);
                
            }else if(escolha == 0){
                SaveManager.salvar(jogador, GameData.isDeusDesbloqueado());

                break;
            }else{

            if(escolha < 1 || escolha > areas.length){

                System.out.println("Área inválida.");
                continue;
            }

            iniciarArea(jogador, areas[escolha - 1]);
            }
            
        }
        
    }

    private static void iniciarArea(Personagem jogador, Area area){
        //area.getIcone()

        if(jogador.getNivel() < area.getNivelMinimo()){

            System.out.println("Você precisa estar no nivel " + area.getNivelMinimo() + " para entrar nesta aera");
            
            return;
        }

        Inimigo inimigo = area.generateEnemie();

        Combate combate = new Combate(sc);

        boolean venceu = combate.iniciar(jogador, inimigo);

        if(venceu){

            if(inimigo instanceof BossFinal){

                GameData.desbloquearDeus();

                System.out.println("CLASSE DEUS DESBLOQUEADA");

                SaveManager.salvar(jogador, venceu);
            }
        }
    }

   private static void abrirInventario(Personagem jogador){

        if(jogador.getInventario().estaVazio()){
            System.out.println("Inventario vazio");
            return;
        }

        jogador.getInventario().listarItens();

        System.out.println("Escolha um item:");
        int escolha = sc.nextInt() - 1;

        Item item = jogador.getInventario().getItem(escolha);

        if(item != null){
            item.usar(jogador);

            jogador.getInventario().removerItem(escolha);
        }
    }

    private static boolean areaLiberada(Personagem jogador, int area){
        switch (area) {
            case 1:
                return jogador.getNivel() >= 1;
            case 2:
                return jogador.getNivel() >= 3;
            case 3:
                return jogador.getNivel() >= 5;
            case 4:
                return jogador.getNivel() >= 8;
            case 5:
                return jogador.getNivel() >= 10;
            default:
                return false;
        }
    }
}