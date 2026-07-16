package itens;

import audio.SomManager;
import personagens.Personagem;

public class PocaoMana extends Item {

    private static final int MANA_RESTAURADA = 30;

    public PocaoMana() {
        super("Poção de Mana");
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.ganharMana(MANA_RESTAURADA);
        System.out.println("💧 Poção de Mana usada! +" + MANA_RESTAURADA
                + " de mana (" + jogador.getMana() + "/" + jogador.getManaMax() + ")");
        SomManager.somCura();
    }
}