package personagens;

import java.util.Random;

public class Assassino extends Personagem{

    private Random random = new Random();

    public Assassino() {
        super("Assassino", 80, 18);
    }

    @Override
    public void atacar(Personagem alvo){

        int danoFinal = dano;

        if(random.nextInt(100) < 25){
            danoFinal *= 2;
            System.out.println("CRÍTICO!");
        }

        alvo.receberDano(danoFinal);
        ganharMana(10);
        System.out.println("Dano causado: " + danoFinal);
    }

    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 30){
            alvo.receberDano(50);
            mana -= 30;

            System.out.println("Ataque sombrio!");
            System.out.println("Dano causado: 50");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}