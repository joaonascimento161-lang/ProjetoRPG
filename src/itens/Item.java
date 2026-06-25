package itens;

import personagens.Personagem;

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