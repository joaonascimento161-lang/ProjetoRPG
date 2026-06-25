package Areas;

import java.util.Random;
import inimigos.*;

public class CasteloSombrio extends Area {

    private static final int CHANCE_MAGO_SUPREMO = 15; // %

    private Random random = new Random();

    public CasteloSombrio() {
        super("Castelo Sombrio", 5);
    }

    @Override
    public Inimigo generateEnemie() {
        if (random.nextInt(100) < CHANCE_MAGO_SUPREMO) {
            return new MagoSupremo();
        }
        return new MagoSombrio();
    }
}