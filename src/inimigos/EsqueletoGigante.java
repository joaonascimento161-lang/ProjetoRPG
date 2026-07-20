package inimigos;

import itens.Arma;
import itens.EquipamentoFactory;
import itens.Item;
import personagens.Personagem;

public class EsqueletoGigante extends Inimigo {

    private static final int VIDA_FURIA   = 50;
    private static final int BONUS_FURIA  = 5;
    private static final int DANO_HAB     = 28;

    public EsqueletoGigante() {
        super("Esqueleto Gigante", 165, 22, 115, 75);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("💀 Esmagamento! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("💢 Esqueleto Gigante entrou em FÚRIA! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return EquipamentoFactory.criarArma("Machado Ósseo");
    }
}