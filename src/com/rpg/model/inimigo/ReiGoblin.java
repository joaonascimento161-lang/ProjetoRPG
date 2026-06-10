package com.rpg.model.inimigo;

import com.rpg.model.personagem.Personagem;

public class ReiGoblin extends Inimigo {
    public ReiGoblin() {
        super("Rei Goblin", 60, 10, 120, 70);
    }

    @Override
    public void usarHab(Personagem alvo) {
        int danoHab = dano + 7;
        alvo.receberDano(danoHab);
        System.out.println("Rei Goblin realizou um Grito de Guerra causando " + danoHab + " de dano");
    }
}
