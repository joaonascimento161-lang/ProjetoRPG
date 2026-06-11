package personagens;

public class Berserker extends Personagem{

    public Berserker() {
        super("Berserker", 110, 16);
    }

    @Override
    public void atacar(Personagem alvo){
        int danoFinal = dano;

        if(vida <= vidaMax /2){
            danoFinal += 15;
        }

        alvo.receberDano(danoFinal);
        ganharMana(10);
        System.out.println("Dano causado: " + danoFinal);
    }

    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 25){

            alvo.receberDano(45);
            mana -= 25;

            System.out.println("Fúria selvagem!");
            System.out.println("Dano causando: 45");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
