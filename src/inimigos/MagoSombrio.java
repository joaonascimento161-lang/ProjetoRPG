package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class MagoSombrio extends Inimigo{
    private Random random = new Random();

    public MagoSombrio(){
        super("Mago Sombrio",100,10,120,70);
    }

    @Override
    public void usarHab(Personagem alvo){

        alvo.receberDano(35);

        System.out.println("Raio Sombrio! Dano: 35");
    }

    @Override
    public void realizarTurno(Personagem jogador){
        if(random.nextInt(100) <= 70){
            usarHab(jogador);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop(){
        Random random = new Random();

        if(random.nextInt(100) < 10){
            return new Armadura("Armadura encantada", 50);
        }
        return null;
    }
}