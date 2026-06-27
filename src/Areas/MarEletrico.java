package Areas;

import inimigos.*;

public class MarEletrico extends Area {

    public MarEletrico() {
        super("Mar Elétrico", 35);
    }

    @Override
    public Inimigo generateEnemie() {
        return new Kjoule();
    }
}