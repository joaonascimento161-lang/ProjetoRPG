package com.rpg.model.personagem;

import java.util.Random;

public class Berserker extends Personagem{

    public Berserker(){
        super("Berserker", 110, 16);
    }
    
    @Override
    public void atacar(Personagem alvo){
        Random random = new Random();
        
        if(random.nextInt(100) < 20){
            int danoRage = dano + 5;
            
            alvo.receberDano(danoRage);
            
            System.out.println(nome + " FÚRIA! causou " + danoRage + " de dano");
        }else{
            alvo.receberDano(dano);
            
            System.out.println(nome + " causou " + dano + " de dano");
        }
        
        ganharMana(10);
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        if(mana >= 20){
            mana -= 20;
            
            int danoHab = dano + 11;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Fúria Selvagem causou " + danoHab + " de dano");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
