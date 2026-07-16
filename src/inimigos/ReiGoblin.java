package inimigos;

import itens.Armadura;
import itens.EquipamentoFactory;
import itens.Item;
import personagens.Personagem;

public class ReiGoblin extends Inimigo {

    private static final int VIDA_FURIA  = 30;
    private static final int BONUS_FURIA = 3;
    private static final int DANO_HAB    = 10;

    public ReiGoblin() {
        super("Rei Goblin", 75, 8, 75, 35);
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
        return EquipamentoFactory.criarArmadura("Coroa do Rei Goblin");
    }
}