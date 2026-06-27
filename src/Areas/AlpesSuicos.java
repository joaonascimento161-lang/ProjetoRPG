package Areas;

import inimigos.*;

public class AlpesSuicos extends Area {

    public AlpesSuicos() {
        super("Alpes Suíços", 15);
    }

    @Override
    public Inimigo generateEnemie() {
        return new PedroNeves();
    }
}