package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class Goblin extends Inimigo {

    private static final int CHANCE_HAB      = 20;
    private static final int DANO_HAB        = 15;
    private static final int CHANCE_DROP     = 20;

    private Random random = new Random();

    public Goblin() {
        super("Goblin", 60, 10, 50, 20);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🪨 Pedrada do Goblin! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (random.nextInt(100) < CHANCE_HAB) {
            usarHab(jogador);
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        if (random.nextInt(100) < CHANCE_DROP) {
            return new Arma("Espada Enferrujada", 5);
        }
        return null;
    }
}