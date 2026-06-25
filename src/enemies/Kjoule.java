package inimigos;

import itens.*;
import personagens.Personagem;

public class Kjoule extends Inimigo{
    public Kjoule(){
        super("KJoule", 7000,45,2500,4700);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 250){

            System.out.println("KJoule gerou estática");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Arma("Cabo óptico",300);
    }
}
