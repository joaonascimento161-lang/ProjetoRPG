package itens;

import personagens.Personagem;

public class Armadura extends Equipamento{
    
    private int bonusVida;

    public Armadura(String nome,int bonusVida){
        super(nome);
        this.bonusVida = bonusVida;
    }

    @Override
    public void usar(Personagem jogador){

        jogador.aumentarVidaMax(bonusVida);

        System.out.println(nome + " equipada");
        System.out.println("+" + bonusVida + " de vida máxima");
    }
}