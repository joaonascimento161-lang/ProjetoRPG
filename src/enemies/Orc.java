package inimigos;

import java.util.Random;

import itens.Armadura;
import itens.Item;
import personagens.Personagem;

public class Orc extends Inimigo{

    public Orc(){
        super("Orc",120,18,100,50);
    }

    @Override
    public void usarHab(Personagem alvo){

        alvo.receberDano(30);

        System.out.println("Machadada brutal! Dano: 30");
    }

    @Override
    public void realizarTurno(Personagem jogador){
        if(jogador.getVida() <= 30){
            usarHab(jogador);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        Random random = new Random();

        if(random.nextInt(100) < 20) {
            return new Armadura("Armadura de Couro",15);
        }
        return null;
    }
}