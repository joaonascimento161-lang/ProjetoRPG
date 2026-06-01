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
            
            jogador.setNivel(nivel + 1);

            jogador.aumentarVidaMax(10);

            jogador.aumentarDano(2);

            System.out.println("\n------------------");
            System.out.println("LEVEL UP!");
            System.out.println("Nível: " + jogador.getNivel());
            System.out.println("Vida máxima +10");
            System.out.println("Dano +2");
            System.out.println("------------------");

            nivel = jogador.getNivel();
            xpNecessario = nivel * 100;
        }
    }
}