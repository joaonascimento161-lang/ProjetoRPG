package inimigos;

import java.util.Random;

import itens.Armadura;
import itens.Item;
import personagens.Personagem;

public class ReiGoblin extends Inimigo{
    public ReiGoblin(){
        super("Rei Goblin",120,18,150,80);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 50){

            System.out.println("Rei Goblin entrou em fúria");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
            return new Armadura("Armadura do Rei Goblin",35);
    }
}
