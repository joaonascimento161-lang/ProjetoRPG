package itens;

import personagens.Personagem;

public class Arma extends Equipamento {

    private int bonusDano;

    public Arma(String nome, int bonusDano) {
        super(nome);
        this.bonusDano = bonusDano;
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.equiparArma(this);
        // mensagem já é exibida dentro de equiparArma()
    }

    public int getBonusDano() {
        return bonusDano;
    }
}