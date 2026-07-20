package inimigos;

import itens.Armadura;
import itens.EquipamentoFactory;
import itens.Item;
import personagens.Personagem;
import java.util.Random;

public class PedroNeves extends Inimigo {

    private static final int VIDA_MAX    = 1100;
    private static final int VIDA_FURIA  = 250;
    private static final int BONUS_FURIA = 10;
    private static final int DANO_HAB    = 52;
    private static final int CHANCE_HAB  = 55;

    private Random random = new Random();

    public PedroNeves() {
        super("Pedro das Neves", VIDA_MAX, 42, 1200, 620);
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
            if (random.nextInt(100) < CHANCE_HAB) {
                usarHab(jogador);
            } else {
                atacar(jogador);
            }
        }
    }

    @Override
    public Item gerarDrop() {
        return EquipamentoFactory.criarArmadura("Livro Sagrado");
    }
}