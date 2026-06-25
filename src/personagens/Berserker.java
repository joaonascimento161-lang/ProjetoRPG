package personagens;

public class Berserker extends Personagem {

    private static final int CUSTO_MANA    = 25;
    private static final int DANO_ESPECIAL = 45;
    private static final int BONUS_RAGE    = 15;

    public Berserker() {
        super("Berserker", 110, 16);
    }

    @Override
    public void atacar(Personagem alvo) {
        int danoFinal = dano;
        boolean emRage = vida <= vidaMax / 2;

        if (emRage) {
            danoFinal += BONUS_RAGE;
            System.out.println("🔴 RAGE ATIVADO! (HP baixo)");
        }

        alvo.receberDano(danoFinal);
        ganharMana(10);
        System.out.println("💢 Dano causado: " + danoFinal
                + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            alvo.receberDano(DANO_ESPECIAL);
            System.out.println("⚡ Fúria Selvagem! Dano causado: " + DANO_ESPECIAL
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}