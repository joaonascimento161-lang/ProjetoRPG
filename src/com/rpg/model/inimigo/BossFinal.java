package com.rpg.model.inimigo;

import com.rpg.model.personagem.Personagem;

public class BossFinal extends Inimigo {
    private int fase;

    public BossFinal() {
        super("Dragao Ancestral", 200, 12, 500, 300);
        this.fase = 1;
    }

    @Override
    public void usarHab(Personagem alvo) {
        int danoHab = dano + 8;
        alvo.receberDano(danoHab);
        System.out.println("Chama Infernal causou " + danoHab + " de dano");
    }
}
