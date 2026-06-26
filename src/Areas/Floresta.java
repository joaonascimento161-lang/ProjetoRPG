package Areas;

import java.util.Random;
import inimigos.*;

public class Floresta extends Area {

    private static final int CHANCE_REI_GOBLIN = 15;

    private Random random = new Random();

    public Floresta() {
        super("Floresta", 1);
    }

    @Override
    public Inimigo generateEnemie() {
        if (random.nextInt(100) < CHANCE_REI_GOBLIN) {
            return new ReiGoblin();
        }
        return new Goblin();
    }
}