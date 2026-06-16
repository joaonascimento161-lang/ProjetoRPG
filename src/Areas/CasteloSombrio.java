package Areas;

import java.util.Random;

import inimigos.*;

public class CasteloSombrio extends Area{

    public CasteloSombrio() {
        super("Castelo Sombrio", 5);
    }

    @Override
    public Inimigo generateEnemie() {
        Random random = new Random();

        if(random.nextInt(100) < 15){
            return new MagoSupremo();
        }else{
            return new MagoSombrio();
        }
    }
}
