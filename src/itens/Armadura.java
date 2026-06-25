package itens;

import personagens.Personagem;

public class Armadura extends Equipamento {

    private int bonusVida;

    public Armadura(String nome, int bonusVida) {
        super(nome);
        this.bonusVida = bonusVida;
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.equiparArmadura(this);
        // mensagem já é exibida dentro de equiparArmadura()
    }

    public int getBonusVida() {
        return bonusVida;
    }
}