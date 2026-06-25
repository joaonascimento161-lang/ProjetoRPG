package personagens;

public class Paladino extends Personagem {

    private static final int CUSTO_MANA = 30;
    private static final int CURA = 30;

    public Paladino() {
        super("Paladino", 130, 12);
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            curar(CURA);
            System.out.println("✨ Luz Sagrada! Vida restaurada: " + CURA
                    + " (" + vida + "/" + vidaMax + ")");
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}