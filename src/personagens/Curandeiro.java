package personagens;

public class Curandeiro extends Personagem{

    public Curandeiro(String nome, int vida, int dano) {
        super("Curandeiro", 95, 10);
    }

    @Override
    public void usarHab(Personagem alvo){
        if(mana >= 25){

            curar(40);
            mana -= 25;

            System.out.println("Grande cura!");
            System.out.println("Vida restaurada: 40");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
    
}
