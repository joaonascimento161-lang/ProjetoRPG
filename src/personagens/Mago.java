package personagens;

public class Mago extends Personagem {

    private static final int CUSTO_MANA  = 25;
    private static final int DANO_ESPECIAL = 40;

    public Mago() {
        super("Mago", 90, 12);
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            alvo.receberDano(DANO_ESPECIAL);
            System.out.println("🔥 Bola de Fogo! Dano causado: " + DANO_ESPECIAL
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}