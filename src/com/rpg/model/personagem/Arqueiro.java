package com.rpg.model.personagem;

public class Arqueiro extends Personagem{

    public Arqueiro(){
        super("Arqueiro", 100, 14);
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        if(mana >= 18){
            mana -= 18;
            
            int danoHab = dano + 9;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Chuva de Flechas causou " + danoHab + " de dano");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
