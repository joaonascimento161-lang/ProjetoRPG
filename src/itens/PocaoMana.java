package itens;

import personagens.Personagem;

public class PocaoMana extends Item{
    private int mana;

    public PocaoMana(String nome) {
        super("Poção de mana");
        this.mana = 30;
    }
    
    public void usar(Personagem jogador){
        jogador.ganharMana(mana);

        System.out.println("Você recuperou " + mana + " de mana!");
    }

    @Override
    public void usar(){
        
    }
}
