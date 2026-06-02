package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class Goblin extends Inimigo{
    private Random random = new Random();

    public Goblin(){
        super("Goblin",60,10,50,20);
    }

    @Override
    public void usarHab(Personagem alvo){

        alvo.receberDano(15);

        System.out.println("Pedrada do Goblin! Dano: 15");
    }

    @Override
    public void realizarTurno(Personagem jogador){
        if(random.nextInt(100) < 80){
            atacar(jogador);
        }else{
            usarHab(jogador);
        }
    }

    @Override
    public Item gerarDrop(){
        Random random = new Random();

        if(random.nextInt(100) < 20){
            return new Arma("Espada enferrujada", 5);
        }
        return null;
    }
}