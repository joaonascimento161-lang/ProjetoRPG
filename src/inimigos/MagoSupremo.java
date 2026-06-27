package inimigos;

import personagens.Personagem;
import itens.*;

public class MagoSupremo extends Inimigo {

    private static final int VIDA_FURIA  = 60;
    private static final int BONUS_FURIA = 6;
    private static final int DANO_HAB    = 38;

    public MagoSupremo() {
        super("Mago Supremo", 255, 30, 200, 145);
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("✨ Feitiço Supremo! Dano: " + DANO_HAB
                + " | HP do jogador: " + alvo.getVida() + "/" + alvo.getVidaMax());
    }

    @Override
    public void realizarTurno(Personagem jogador) {
        if (vida < VIDA_FURIA) {
            int danoFurioso = dano + BONUS_FURIA;
            jogador.receberDano(danoFurioso);
            System.out.println("💥 Mago Supremo ativou Feitiço Supremo! Dano: " + danoFurioso
                    + " | HP do jogador: " + jogador.getVida() + "/" + jogador.getVidaMax());
        } else {
            atacar(jogador);
        }
    }

    @Override
    public Item gerarDrop() {
        return new Armadura("Manto Supremo", 30);
    }
}