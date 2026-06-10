package com.rpg.model.personagem;

import java.util.Random;

public class Assassino extends Personagem{

    public Assassino(){
        super("Assassino", 80, 18);
    }
    
    @Override
    public void atacar(Personagem alvo){
        Random random = new Random();
        
        if(random.nextInt(100) < 25){
            int danoCritico = dano * 2;
            
            alvo.receberDano(danoCritico);
            
            System.out.println(nome + " CRÍTICO! causou " + danoCritico + " de dano");
        }else{
            alvo.receberDano(dano);
            
            System.out.println(nome + " causou " + dano + " de dano");
        }
        
        ganharMana(10);
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        if(mana >= 22){
            mana -= 22;
            
            int danoHab = dano + 12;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Ataque Sombrio causou " + danoHab + " de dano");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
