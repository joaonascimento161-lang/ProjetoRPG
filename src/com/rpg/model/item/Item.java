package com.rpg.model.item;

import com.rpg.model.personagem.Personagem;

public abstract class Item {
    protected String nome;

    public Item(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void usar(Personagem jogador);
}
