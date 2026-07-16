package inimigos;

import itens.*;
import personagens.Personagem;
import java.util.Random;

public class Kjoule extends Inimigo {

    private static final int VIDA_MAX     = 1200;
    private static final int VIDA_FURIA   = 300;
    private static final int BONUS_FURIA  = 12;
    private static final int DANO_HAB     = 55;
    private static final int CHANCE_HAB   = 55; // %

    private Random random = new Random();

    public Kjoule() {
        // Easter egg nível 35+ — o mais difícil do jogo fora do boss
        super("KJoule", VIDA_MAX, 42, 2000, 1200);
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
            System.out.println("⚡ KJoule sobrecarregou! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
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
        return EquipamentoFactory.criarArma("Cabo Óptico");
    }
}