package personagens;

public class Guerreiro extends Personagem {

    private static final int CUSTO_MANA    = 30;
    private static final int DANO_ESPECIAL = 45; // era 350.000.000 😅

    public Guerreiro() {
        super("Guerreiro", 120, 15);
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            alvo.receberDano(DANO_ESPECIAL);
            System.out.println("💥 Golpe Devastador! Dano causado: " + DANO_ESPECIAL
                    + " | HP do inimigo: " + alvo.getVida() + "/" + alvo.getVidaMax());
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}