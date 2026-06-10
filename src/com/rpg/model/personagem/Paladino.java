package com.rpg.model.personagem;

public class Paladino extends Personagem{

    public Paladino(){
        super("Paladino", 130, 12);
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        if(mana >= 20){
            mana -= 20;
            
            int cura = 30;
            
            curar(cura);
            
            System.out.println("Luz Sagrada restaurou " + cura + " de vida");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
