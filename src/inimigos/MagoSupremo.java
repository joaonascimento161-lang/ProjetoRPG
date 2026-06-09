package inimigos;

import personagens.*;
import itens.*;
public class MagoSupremo extends Inimigo{
    public MagoSupremo(){
        super("Mago Supremo",200,29,250, 150);
    }

    @Override
    public void realizarTurno(Personagem jogador){

        if(vida < 50){

            System.out.println("Mago supremo ativou seu feitiço supremo");

            jogador.receberDano(dano + 5);
        }else{
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
            return new Armadura("Armadura suprema",35);
    }
}    