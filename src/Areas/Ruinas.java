package Areas;

import java.util.Random;

import inimigos.*;

public class Ruinas extends Area{

    public Ruinas() {
        super("Ruínas",3);
    }

    @Override
    public Inimigo generateEnemie() {
        Random random = new Random();

        if(random.nextInt(100) < 15){
            return new EsqueletoGigante();

        }else{
            return new Esqueleto();
        }
    }
}
