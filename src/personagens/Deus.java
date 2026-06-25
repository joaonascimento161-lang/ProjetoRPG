package personagens;

public class Deus extends Personagem {

    private static final int CUSTO_APOCALIPSE     = 100;
    private static final int DANO_APOCALIPSE      = 120;

    private static final int CUSTO_JULGAMENTO     = 40;
    private static final int DANO_JULGAMENTO      = 60;

    private static final int CUSTO_MILAGRE        = 35;
    private static final int CURA_MILAGRE         = 50;

    public Deus() {
        super("Deus", 150, 20);
    }

    @Override
    public void atacar(Personagem alvo) {
        alvo.receberDano(dano);
        ganharMana(15);
        System.out.println("✨ Ataque Divino! " + dano + " de dano | Mana +15 (" + mana + "/" + manaMax + ")");
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_APOCALIPSE) {
            mana -= CUSTO_APOCALIPSE;
            alvo.receberDano(DANO_APOCALIPSE);
            System.out.println("☄️  APOCALIPSE! Dano causado: " + DANO_APOCALIPSE
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());

        } else if (mana >= CUSTO_JULGAMENTO) {
            mana -= CUSTO_JULGAMENTO;
            alvo.receberDano(DANO_JULGAMENTO);
            System.out.println("⚖️  Julgamento Divino! Dano causado: " + DANO_JULGAMENTO
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());

        } else if (mana >= CUSTO_MILAGRE) {
            mana -= CUSTO_MILAGRE;
            curar(CURA_MILAGRE);
            System.out.println("💫 Milagre! Vida restaurada: " + CURA_MILAGRE
                    + " (" + vida + "/" + vidaMax + ")");

        } else {
            System.out.println("❌ Mana insuficiente! (mínimo " + CUSTO_MILAGRE + " necessário)");
        }
    }
}