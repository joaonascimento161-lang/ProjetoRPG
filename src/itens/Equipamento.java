package itens;

import personagens.Personagem;

public abstract class Equipamento extends Item {

    public Equipamento(String nome) {
        super(nome);
    }

    @Override
    public abstract void usar(Personagem jogador);
}