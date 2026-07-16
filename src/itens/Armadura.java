package itens;

import personagens.Personagem;

public class Armadura extends Equipamento {

    private int bonusVida;

    public Armadura(String nome, int bonusVida) {
        this(nome, bonusVida, Raridade.COMUM);
    }

    public Armadura(String nome, int bonusVida, Raridade raridade) {
        super(nome, raridade);
        this.bonusVida = bonusVida;
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.equiparArmadura(this);
    }

    public int getBonusVida() {
        return bonusVida;
    }
}
