package itens;

import personagens.Personagem;

public class PocaoVida extends Item{
    private int cura;
    
    public PocaoVida(){
        super("Poção de vida");
        this.cura = 25;
    }

    public void usar(Personagem jogador){
        jogador.curar(cura);

        System.out.println("Você recuperou " + cura + " de vida!");
    }

    @Override
    public void usar(){
        
    }
}
