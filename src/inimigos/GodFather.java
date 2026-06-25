package inimigos;

import itens.*;
import personagens.Personagem;

public class GodFather extends Inimigo {

    private static final int VIDA_MAX    = 6767;
    private static final int BONUS_FURIA = 5;
    private static final int DANO_HAB    = 100;

    public GodFather() {
        super("Mafia", VIDA_MAX, 80, 1500, 800);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🔫 Rajada da Máfia! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_MAX) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("😤 A Máfia ficou pistola! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Arma("Pistolão da Máfia", 250); // drop garantido 🔫
    }
}