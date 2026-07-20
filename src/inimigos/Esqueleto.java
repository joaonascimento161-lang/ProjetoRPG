package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class Esqueleto extends Inimigo {

    private static final int CHANCE_HAB  = 40;
    private static final int DANO_HAB    = 26;
    private static final int CHANCE_DROP = 15;

    private Random random = new Random();

    public Esqueleto() {
        super("Esqueleto", 140, 18, 85, 45);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🦴 Golpe Ósseo! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
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
            return EquipamentoFactory.criarArma("Espada de Osso");
        }
        return null;
    }
}