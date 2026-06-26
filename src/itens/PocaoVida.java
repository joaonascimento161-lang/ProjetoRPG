package itens;

import personagens.Personagem;

public class PocaoVida extends Item {

    private static final int CURA = 35;

    public PocaoVida() {
        super("Poção de Vida");
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.curar(CURA);
    }
}