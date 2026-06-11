package itens;

import personagens.Personagem;

public class PocaoVida extends Item {

    private int cura;

    public PocaoVida() {
        super("Poção de Vida");
        this.cura = 35;
    }

    @Override
    public void usar(Personagem jogador) {

        jogador.curar(cura);

        System.out.println("Você recuperou " + cura + " de vida!");
    }
}