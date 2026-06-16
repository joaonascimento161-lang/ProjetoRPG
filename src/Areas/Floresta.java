package Areas;

import java.util.Random;

import inimigos.*;

public class Floresta extends Area{
    
    public Floresta(){
        super("Floresta", 1);
    }

    @Override
    public Inimigo generateEnemie() {
        Random random = new Random();

        if(random.nextInt(100) < 15){

            return new ReiGoblin();

        }else{

            return new Goblin();
        }
    }
}
