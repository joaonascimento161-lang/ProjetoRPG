package com.rpg.model.inimigo;

import com.rpg.model.personagem.Personagem;
import com.rpg.model.item.Item;

public class Inimigo extends Personagem {
    private int recompensaXP;
    private int recompensaOuro;

    public Inimigo(String nome, int vida, int dano, int recompensaXP, int recompensaOuro) {
        super(nome, vida, dano);
        this.recompensaXP = recompensaXP;
        this.recompensaOuro = recompensaOuro;
    }

    @Override
    public void usarHab(Personagem alvo) {
        System.out.println(nome + " nao possui habilidade especial");
    }
    
    public void realizarTurno(Personagem jogador) {
        atacar(jogador);
    }
    
    public int getRecompensaXP() {
        return recompensaXP;
    }

    public int getRecompensaOuro() {
        return recompensaOuro;
    }

    public Item gerarDrop() {
        return null;
    }
}
