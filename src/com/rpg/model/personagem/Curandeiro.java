package com.rpg.model.personagem;

public class Curandeiro extends Personagem{

    public Curandeiro(){
        super("Curandeiro", 95, 10);
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        if(mana >= 25){
            mana -= 25;
            
            int cura = 40;
            
            curar(cura);
            
            System.out.println("Grande Cura restaurou " + cura + " de vida");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
