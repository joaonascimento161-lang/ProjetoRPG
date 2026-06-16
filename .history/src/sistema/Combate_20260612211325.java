package sistema;

import inimigos.BossFinal;
import inimigos.Inimigo;
import itens.Item;
import personagens.Personagem;
import save.SaveManager;

import java.util.Scanner;

public class Combate {

        private Scanner sc;
    
    public Combate(Scanner sc){
        this.sc = sc;
    }

    public static String criarBarra(int atual, int maximo){

        int tamanho = 20;

        int preenchido = (atual * tamanho) / maximo;

        String barra = "[";

        System.out.println();

        for(int i = 0; i < tamanho; i++){

            if(i < preenchido){
                barra += "█";
            }else{
                barra += "-";
            }
        }

        barra += "]";

        return barra;
    }

    public boolean iniciar(Personagem jogador, Inimigo inimigo){

        System.out.println("\n------------------");
        System.out.println("COMBATE INICIADO");
        System.out.println(jogador.getNome() + " VS " + inimigo.getNome());
        System.out.println("------------------");

        while (jogador.estaVivo() && inimigo.estaVivo()) {

            System.out.println("\n====================");

            System.out.println(jogador.getNome());

            System.out.println(
                "HP   "
                + criarBarra(
                    jogador.getVida(),
                    jogador.getVidaMax()
                )
                + " "
                + jogador.getVida()
                + "/"
                + jogador.getVidaMax()
            );

            System.out.println(
                "Mana "
                + criarBarra(
                    jogador.getMana(),
                    jogador.getManaMax()
                )
                + " "
                + jogador.getMana()
                + "/"
                + jogador.getManaMax()
            );

            System.out.println();

            System.out.println(inimigo.getNome());

            System.out.println(
                "HP   "
                + criarBarra(
                    inimigo.getVida(),
                    inimigo.getVidaMax()
                )
                + " "
                + inimigo.getVida()
                + "/"
                + inimigo.getVidaMax()
            );

            System.out.println("====================");

            turnoJogador(jogador, inimigo);

            if(!inimigo.estaVivo()){
                break;
            }

            turnoInimigo(jogador, inimigo);
        }

        if(jogador.estaVivo()){
            System.out.println("\nVITÓRIA!");

            XPSystem.ganharXP(jogador, inimigo.getRecompensaXP());

            jogador.adicionarOuro(inimigo.getRecompensaOuro());

            if(jogador.getMissaoAtual() != null){

                jogador.getMissaoAtual().registrarAbate(inimigo.getNome());

                if(jogador.getMissaoAtual().isConcluida()){

                    System.out.println("RECOMPENSA DA MISSÃO");

                    XPSystem.ganharXP(jogador, jogador.getMissaoAtual().getRecompensaXP());

                    jogador.adicionarOuro(jogador.getMissaoAtual().getRecompensaOuro());

                    System.out.println("+" + jogador.getMissaoAtual().getRecompensaOuro() + " ouro");

                    jogador.aceitarMissao(null);
                }
            }

            Item drop = inimigo.gerarDrop();

            if(drop != null){

                jogador.getInventario().adicionarItem(drop);

                System.out.println("Item encontrado: " + drop.getNome());
            }
            System.out.println("+" + inimigo.getRecompensaOuro() + " ouro");

            if(inimigo instanceof BossFinal){
                GameData.desbloquearDeus();

                SaveManager.salvar(jogador, true);
            }

            return true;
        }

        System.out.println("\nDERROTA!");
        return false;
    }

    private void turnoJogador(Personagem jogador, Inimigo inimigo){
        System.out.println("\n---- SEU TURNO ----");

        System.out.println("1 - Atk");
        System.out.println("2 - Hab");
        System.out.println("3 - Inv");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:

                jogador.atacar(inimigo);
                break;
            case 2:

                jogador.usarHab(inimigo);
                break;
            case 3:
                abrirInventario(jogador);
                break;

            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private void turnoInimigo(Personagem jogador, Inimigo inimigo){
        System.out.println("\n---- TURNO DO INIMIGO ----");

        inimigo.realizarTurno(jogador);
    }

    private void abrirInventario(Personagem jogador){

        if(jogador.getInventario().estaVazio()){
            System.out.println("Inventário vazio");

            return;
        }

        jogador.getInventario().listarItens();

        System.out.println("Escolha um item: ");

        int escolha = sc.nextInt() - 1;

        Item item = jogador.getInventario().getItem(escolha);

        if(item != null){
            
            item.usar(jogador);

            jogador.getInventario().removerItem(escolha);
        }
    }

    private void mostrarStatus(Personagem jogador, Inimigo inimigo){

        System.out.println("\n---- STATUS ----");

        System.out.println(jogador.getNome() + " | Vida: " +  jogador.getVida() + " | Mana: " + jogador.getMana());

        System.out.println(inimigo.getNome() + " | Vida: " + inimigo.getVida());

        System.out.println("-----------------");
    }
}
