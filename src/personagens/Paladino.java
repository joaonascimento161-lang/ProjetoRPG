package personagens;

public class Paladino extends Personagem{

    public Paladino() {
        super("Paladino", 130, 12);
    }
    
    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 30){

            curar(30);
            mana -= 30;

            System.out.println("Luz sagrada!");
            System.out.println("Vida restaurada: 30");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
