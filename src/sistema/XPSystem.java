package sistema;

import personagens.Personagem;

public class XPSystem {
    public static void ganharXP(Personagem jogador, int xpGanho){
        jogador.adicionarXP(xpGanho);
        System.out.println("\n" + xpGanho + " XP!");

        verificarLevelUp(jogador);
    }

    public static void verificarLevelUp(Personagem jogador){

        int xp = jogador.getXp();
        int nivel = jogador.getNivel();

        int xpNecessario = nivel * 100;

        while (xp >= xpNecessario) {
         
            jogador.subirNivel();

            nivel = jogador.getNivel();
            xpNecessario = nivel * 100;
        }
    }
}