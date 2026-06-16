package Areas;

import inimigos.*;

public class Caverna extends Area{
    
    public Caverna(){
        super("Caverna", 2);
    }

    @Override
    public Inimigo generateEnemie() {
        return new Orc();
    }
}