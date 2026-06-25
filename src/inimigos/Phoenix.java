package inimigos;

import itens.*;
import personagens.Personagem;

public class Phoenix extends Inimigo {

    private static final int VIDA_MAX    = 1500;
    private static final int VIDA_FURIA  = 50;
    private static final int BONUS_FURIA = 5;
    private static final int DANO_HAB    = 65;

    public Phoenix() {
        super("Fernando a Phoenix", VIDA_MAX, 45, 250, 320);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🔥 Chama da Fênix! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("🔥 Fernando a Phoenix entrou em FÚRIA! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Armadura("Pena do Fernando", 250); // drop garantido 🪶
    }
}