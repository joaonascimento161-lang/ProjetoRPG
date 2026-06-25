package personagens;

public class Curandeiro extends Personagem {

    private static final int CUSTO_MANA = 25;
    private static final int CURA       = 40;

    public Curandeiro() {
        super("Curandeiro", 95, 10);
    }

    @Override
    public void usarHab(Personagem alvo) {
        if (mana >= CUSTO_MANA) {
            mana -= CUSTO_MANA;
            curar(CURA);
            System.out.println("💚 Grande Cura! Vida restaurada: " + CURA
                    + " (" + vida + "/" + vidaMax + ")");
        } else {
            System.out.println("❌ Mana insuficiente! ("
                    + mana + "/" + CUSTO_MANA + " necessário)");
        }
    }
}