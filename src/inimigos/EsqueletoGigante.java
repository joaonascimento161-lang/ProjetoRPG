package inimigos;

import itens.Arma;
import itens.Item;
import personagens.Personagem;

public class EsqueletoGigante extends Inimigo{
    public EsqueletoGigante(){
        super("Esqueleto Gigante",175,22,215, 110);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 50){

            System.out.println("Esqueleto Gigante entrou em fúria");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
            return new Arma("Machado osseo",35);
    }
}