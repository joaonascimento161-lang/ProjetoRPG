package itens;

import personagens.Personagem;

public class Arma extends Equipamento {

    private int bonusDano;

    public Arma(String nome, int bonusDano) {
        this(nome, bonusDano, Raridade.COMUM);
    }

    public Arma(String nome, int bonusDano, Raridade raridade) {
        super(nome, raridade);
        this.bonusDano = bonusDano;
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.equiparArma(this);
    }

    public int getBonusDano() {
        return bonusDano;
    }
}
