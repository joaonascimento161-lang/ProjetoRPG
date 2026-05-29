package personagens;

public class Deus extends Personagem{

    public Deus(String nome, int vida, int dano) {
        super("Deus", 150, 20);
    }

    @Override
    public void atacar(Personagem alvo){
        alvo.receberDano(dano);
        ganharMana(15);

        System.out.println("Ataque divino!");
        System.out.println("Mana +15");
    }

    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 100){
             alvo.receberDano(120);
             mana -= 100;

             System.out.println("APOCALIPSE!");
             System.out.println("Dano causado: 120");
        }else if(mana >= 40){
            alvo.receberDano(60);
            mana -= 40;

            System.out.println("Julgamento divino!");
            System.out.println("Dano causado: 60");
        }else if(mana >= 35){
            curar(50);
            mana -= 35;

            System.out.println("Milagre!");
            System.out.println("Vida restaurada: 50");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
