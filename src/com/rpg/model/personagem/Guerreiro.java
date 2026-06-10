package com.rpg.model.personagem;

public class Guerreiro extends Personagem {

    public Guerreiro(){
        super("Guerreiro", 120, 15);
    }
    
    @Override
    public void usarHab(Personagem alvo){

        if(mana >= 20){
            mana -= 20;
            
            int danoHab = dano + 10;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Golpe Devastador causou " + danoHab + " de dano");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
