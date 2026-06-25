package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

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

    @Override
    public Item gerarDrop(){
        Random random = new Random();

        if(random.nextInt(100)< 10){
            return new Arma("Espada de osso", 20);
        }
        return null;
    }
}