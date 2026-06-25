package personagens;

import java.util.Random;

public class Assassino extends Personagem {

    private static final int CHANCE_CRITICO  = 25; // %
    private static final int CUSTO_MANA      = 30;
    private static final int DANO_ESPECIAL   = 50;

    private Random random = new Random();

    public Assassino() {
        super("Assassino", 80, 18);
    }

    @Override
    public void atacar(Personagem alvo) {
        int danoFinal = dano;
        boolean critico = random.nextInt(100) < CHANCE_CRITICO;

        if (critico) {
            danoFinal *= 2;
            System.out.println("💀 CRÍTICO! (x2)");
        }

        alvo.receberDano(danoFinal);
        ganharMana(10);
        System.out.println("🗡️  Dano causado: " + danoFinal
                + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            alvo.receberDano(DANO_ESPECIAL);
            System.out.println("🌑 Ataque Sombrio! Dano causado: " + DANO_ESPECIAL
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}