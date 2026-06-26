package Areas;

import java.util.Random;
import inimigos.*;

public class Ruinas extends Area {

    private static final int CHANCE_ESQUELETO_GIGANTE = 15;

    private Random random = new Random();

    public Ruinas() {
        super("Ruínas", 3);
    }

    @Override
    public Inimigo generateEnemie() {
        if (random.nextInt(100) < CHANCE_ESQUELETO_GIGANTE) {
            return new EsqueletoGigante();
        }
        return new Esqueleto();
    }
}