package Areas;

import inimigos.*;

public class Vulcao extends Area {

    public Vulcao() {
        super("Vulcão", 10);
    }

    @Override
    public Inimigo generateEnemie() {
        return new Phoenix();
    }
}