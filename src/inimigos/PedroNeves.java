package inimigos;

import itens.Armadura;
import itens.Item;
import personagens.Personagem;

public class PedroNeves extends Inimigo {

    private static final int VIDA_MAX    = 2005;
    private static final int VIDA_FURIA  = 50;
    private static final int BONUS_FURIA = 5;
    private static final int DANO_HAB    = 70;

    public PedroNeves() {
        super("Pedro das Neves", VIDA_MAX, 55, 200, 180);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("📖 Sermão Devastador! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("😤 Pedro das Neves entrou em FÚRIA! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Armadura("Livro Sagrado", 35);
    }
}