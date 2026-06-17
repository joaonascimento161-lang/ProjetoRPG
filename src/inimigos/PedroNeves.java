package inimigos;

import itens.Armadura;
import itens.Item;
import personagens.Personagem;

public class PedroNeves extends Inimigo{
    public PedroNeves(){
        super("PedroDasNeves",2005,55,200,180);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 50){

            System.out.println("Pedro das neves entrou em fúria");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Armadura("Livro sagrado",35);
    }
}
