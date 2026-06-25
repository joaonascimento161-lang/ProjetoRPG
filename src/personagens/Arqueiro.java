package personagens;

public class Arqueiro extends Personagem {

    private static final int CUSTO_MANA    = 20;
    private static final int DANO_ESPECIAL = 30;

    public Arqueiro() {
        super("Arqueiro", 100, 14);
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            alvo.receberDano(DANO_ESPECIAL);
            System.out.println("🏹 Chuva de Flechas! Dano causado: " + DANO_ESPECIAL
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}