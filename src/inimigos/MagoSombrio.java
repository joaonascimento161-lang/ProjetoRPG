package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class MagoSombrio extends Inimigo {

    private static final int CHANCE_HAB  = 70; // %
    private static final int DANO_HAB    = 35;
    private static final int CHANCE_DROP = 10; // %

    private Random random = new Random();

    public MagoSombrio() {
        super("Mago Sombrio", 100, 10, 120, 70);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🌑 Raio Sombrio! Dano: " + DANO_HAB
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
            return new Armadura("Armadura Encantada", 25);
        }
        return null;
    }
}