package personagens;

public class Guerreiro extends Personagem{

    public Guerreiro(String nome, int vida, int dano) {
        super("Guerreiro", 120, 15);
    }

    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 30){
            int danoEspecial = 35;

            alvo.receberDano(danoEspecial);
            mana -= 30;

            System.out.println("Golpe devastador!");
            System.out.println("Dano causado: " + danoEspecial);

        }else{
            System.out.println("Mana insuficiente");
        }
    }
    
}
