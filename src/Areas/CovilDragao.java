package Areas;

import inimigos.*;

public class CovilDragao extends Area {

    public CovilDragao() {
        super("Covil do Dragão", 8);
    }

    @Override
    public Inimigo generateEnemie() {
        return new BossFinal();
    }
}