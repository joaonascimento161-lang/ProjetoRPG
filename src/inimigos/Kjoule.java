package inimigos;

import itens.*;
import personagens.Personagem;

public class Kjoule extends Inimigo {

    private static final int VIDA_MAX    = 7000;
    private static final int VIDA_FURIA  = 250;
    private static final int BONUS_FURIA = 5;
    private static final int DANO_HAB    = 60;

    public Kjoule() {
        super("KJoule", VIDA_MAX, 45, 2500, 4700);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("⚡ Descarga Elétrica! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("⚡ KJoule gerou estática! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Arma("Cabo Óptico", 300); // drop garantido ⚡
    }
}