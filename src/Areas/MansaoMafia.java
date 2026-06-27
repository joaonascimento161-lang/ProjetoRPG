package Areas;

import inimigos.*;

public class MansaoMafia extends Area {

    public MansaoMafia() {
        super("Mansão Mafia", 20);
    }

    @Override
    public Inimigo generateEnemie() {
        return new GodFather();
    }
}