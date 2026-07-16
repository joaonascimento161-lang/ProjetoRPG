package inimigos;

import itens.*;
import personagens.Personagem;

public class GodFather extends Inimigo {

    private static final int VIDA_MAX    = 800;
    private static final int VIDA_FURIA  = 200;
    private static final int BONUS_FURIA = 8;
    private static final int DANO_HAB    = 45;

    public GodFather() {
        // Easter egg nível 20+ — mais difícil que PedroNeves (nível 15+)
        super("The GodFather", VIDA_MAX, 35, 1500, 750);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🔫 Rajada da Máfia! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("😤 A Máfia ficou pistola! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            // Alterna entre ataque normal e habilidade
            if (Math.random() < 0.5) {
                usarHab(jogador);
            } else {
                atacar(jogador);
            }
        }
    }

    @Override
    public Item gerarDrop() {
        return EquipamentoFactory.criarArma("Pistolão da Máfia");
    }
}