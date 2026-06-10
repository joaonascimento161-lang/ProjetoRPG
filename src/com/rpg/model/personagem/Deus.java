package com.rpg.model.personagem;

import java.util.Random;

public class Deus extends Personagem {

    public Deus(){
        super("Deus", 150, 20);
        this.manaMax = 120;
        this.mana = 120;
    }
    
    @Override
    public void ganharMana(int valor){
        mana += valor + 5;
        
        if(mana > manaMax){
            mana = manaMax;
        }
    }
    
    @Override
    public void usarHab(Personagem alvo){
        
        Random random = new Random();
        
        int habRandom = random.nextInt(3);
        
        switch (habRandom) {
            case 0:
                julgamentoDivino(alvo);
                break;
            case 1:
                milagre();
                break;
            case 2:
                apocalipse(alvo);
                break;
        }
    }
    
    private void julgamentoDivino(Personagem alvo){
        if(mana >= 30){
            mana -= 30;
            
            int danoHab = dano + 15;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Julgamento Divino causou " + danoHab + " de dano");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
    
    private void milagre(){
        if(mana >= 35){
            mana -= 35;
            
            int cura = 60;
            
            curar(cura);
            
            System.out.println("Milagre restaurou " + cura + " de vida");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
    
    private void apocalipse(Personagem alvo){
        if(mana >= 40){
            mana -= 40;
            
            int danoHab = dano + 25;
            
            alvo.receberDano(danoHab);
            
            System.out.println("Apocalipse causou " + danoHab + " de dano devastador");
        }else{
            System.out.println("Mana insuficiente");
        }
    }
}
