package itens;

import personagens.Personagem;

public class PocaoMana extends Item {

    private int mana;

    public PocaoMana() {
        super("Poção de Mana");
        this.mana = 30;
    }

    @Override
    public void usar(Personagem jogador) {

        jogador.ganharMana(mana);

        System.out.println("Você recuperou " + mana + " de mana!");
    }
}