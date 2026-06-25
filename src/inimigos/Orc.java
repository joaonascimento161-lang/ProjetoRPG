package inimigos;

import java.util.Random;
import itens.Armadura;
import itens.Item;
import personagens.Personagem;

public class Orc extends Inimigo {

    private static final int VIDA_ALVO_CRITICA = 30; // ataca forte quando jogador está baixo
    private static final int DANO_HAB          = 30;
    private static final int CHANCE_DROP       = 20; // %

    private Random random = new Random();

    public Orc() {
        super("Orc", 120, 18, 100, 50);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("🪓 Machadada Brutal! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (jogador.getVida() <= VIDA_ALVO_CRITICA) {
            System.out.println("😈 O Orc fareja sangue!");
            usarHab(jogador);
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        if (random.nextInt(100) < CHANCE_DROP) {
            return new Armadura("Armadura de Couro", 15);
        }
        return null;
    }
}