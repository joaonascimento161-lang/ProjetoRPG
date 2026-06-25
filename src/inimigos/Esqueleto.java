package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class Esqueleto extends Inimigo {

    private static final int CHANCE_HAB  = 40; // %
    private static final int DANO_HAB    = 20;
    private static final int CHANCE_DROP = 10; // %

    private Random random = new Random();

    public Esqueleto() {
        super("Esqueleto", 90, 12, 80, 30);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🦴 Golpe Ósseo! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());

        // TODO: implementar efeito real de sangramento
        // ex: causar X de dano por turno pelos próximos N turnos
        System.out.println("🩸 Sangramento aplicado!");
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
            return new Arma("Espada de Osso", 20);
        }
        return null;
    }
}