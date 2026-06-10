package com.rpg.model.personagem;

public class Mago extends Personagem{

    public Mago(){
        super("Mago", 90, 12);
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        if(mana >= 15){
            mana -= 15;
            
            int danoHab = dano + 8;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Bola de Fogo causou " + danoHab + " de dano");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
