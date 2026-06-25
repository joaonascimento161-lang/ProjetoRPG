package inimigos;

import itens.*;
import personagens.Personagem;

public class Phoenix extends Inimigo{
    public Phoenix(){
        super("Fernando a Phoenix", 1500,45,250,320);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 50){

            System.out.println("Mafia ficou pistola");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Armadura("Pena do Fernando",250);
    }
}
