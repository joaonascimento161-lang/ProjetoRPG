package personagens;

public class Arqueiro extends Personagem{

    public Arqueiro(String nome, int vida, int dano) {
        super("Arqueiro", 100, 14);
    }

    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 20){

            int danoEspecial = 30;

            alvo.receberDano(danoEspecial);
            mana -= 20;

            System.out.println("Chuva de flechas!");
            System.out.println("Dano causado: " + danoEspecial);
        }else{
            System.out.println("Mana insuficiente");
        }
    }
    
}
