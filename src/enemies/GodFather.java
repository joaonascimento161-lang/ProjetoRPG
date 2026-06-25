package inimigos;

import itens.*;
import personagens.Personagem;

public class GodFather extends Inimigo{
    public GodFather(){
        super("Mafia", 6767,80,1500,800);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 6767){

            System.out.println("Mafia ficou pistola");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Arma("Pistolão do Mafia",250);
    }
}
