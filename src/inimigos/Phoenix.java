package inimigos;

import itens.*;
import personagens.Personagem;
import java.util.Random;

public class Phoenix extends Inimigo {

    private static final int VIDA_MAX    = 900;
    private static final int VIDA_FURIA  = 200;
    private static final int BONUS_FURIA = 15;
    private static final int DANO_HAB    = 60;
    private static final int CHANCE_HAB  = 65; // %

    private Random random = new Random();

    public Phoenix() {
        super("Fernando a Phoenix", VIDA_MAX, 48, 1000, 550);
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

            if (jogador.estaVivo()) {
                usarHab(jogador);
            }
        } else {
            if (random.nextInt(100) < CHANCE_HAB) {
                usarHab(jogador);
            } else {
                atacar(jogador);
            }
        }
    }

    @Override
    public Item gerarDrop() {
        return EquipamentoFactory.criarArmadura("Pena do Fernando");
    }
}