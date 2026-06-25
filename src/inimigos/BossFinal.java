package inimigos;

import java.util.Random;
import personagens.Personagem;
import itens.*;

public class BossFinal extends Inimigo {

    private static final int VIDA_MAX         = 400;
    private static final int CHANCE_CHAMA     = 30; // %
    private static final int DANO_CHAMA       = 50;
    private static final int DANO_GOLPE_FURIOSO = 35;

    private Random random = new Random();

    public BossFinal() {
        super("Dragão Ancestral", VIDA_MAX, 30, 500, 300);
    }

    @Override
    public void usarHab(Personagem alvo) {
        System.out.println("\n🐉 O Dragão Ancestral prepara uma habilidade!");

        if (random.nextInt(100) < CHANCE_CHAMA) {
            alvo.receberDano(DANO_CHAMA);
            System.out.println("🔥 CHAMA INFERNAL! Dano: " + DANO_CHAMA
                    + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
        } else {
            System.out.println("💨 O ataque do Dragão foi desviado!");
        }
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        double porcentagemVida = (double) getVida() / VIDA_MAX;

        if (porcentagemVida > 0.5) {
            System.out.println("\n🟢 [FASE 1 - Dragão Controlado]");
            atacar(jogador);

        } else if (porcentagemVida > 0.25) {
            System.out.println("\n🟡 [FASE 2 - Dragão Enfurecido]");
            if (random.nextInt(100) < 50) {
                usarHab(jogador);
            } else {
                atacar(jogador);
            }

        } else {
            System.out.println("\n🔴 [FASE 3 - FÚRIA ANCESTRAL]");
            if (random.nextInt(100) < 70) {
                usarHab(jogador);
            } else {
                jogador.receberDano(DANO_GOLPE_FURIOSO);
                System.out.println("💥 Golpe Furioso! Dano: " + DANO_GOLPE_FURIOSO
                        + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
            }
        }
    }

    @Override
    public Item gerarDrop() {
        return new Arma("Matadora de Dragões", 75); // drop garantido!
    }
}