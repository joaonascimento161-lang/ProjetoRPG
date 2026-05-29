package inimigos;

import java.util.Random;
import personagens.Personagem;

public class Esqueleto extends Inimigo{
    private Random random = new Random();

    public Esqueleto(){
        super("Esqueleto",90,12,80,30);
    }

    @Override
    public void usarHab(Personagem alvo){

        alvo.receberDano(20);

        System.out.println("Golpe ósseo Dano: 20");

        System.out.println("Sangramento aplicado!");
    }

    @Override
    public void realizarTurno(Personagem jogador){
        if(random.nextInt(100) <  40){
            usarHab(jogador);
        }else{
            atacar(jogador);
        }
    }
}