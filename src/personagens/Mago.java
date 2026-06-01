package personagens;

public class Mago extends Personagem{

    public Mago() {
        super("Mago", 90, 12);
    }

    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 25){

            int danoEspecial = 40;

            alvo.receberDano(danoEspecial);
            mana -= 25;

            System.out.println("Bola de fogo!");
            System.out.println("Dano causado: " + danoEspecial);
        }else{
            System.out.println("Mana insuficiente");
        }
    }
    
}
