package inimigos;

import itens.Armadura;
import itens.Item;
import personagens.Personagem;

public class ReiGoblin extends Inimigo {

    private static final int VIDA_FURIA  = 50;
    private static final int BONUS_FURIA = 5;
    private static final int DANO_HAB    = 25;

    public ReiGoblin() {
        super("Rei Goblin", 120, 18, 150, 80);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("👑 Golpe Real! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("👑 Rei Goblin entrou em FÚRIA! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Armadura("Armadura do Rei Goblin", 35); // drop garantido 👑
    }
}