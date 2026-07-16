package sistema;

import audio.SomManager;
import personagens.Personagem;

public class XPSystem {

    public static void ganharXP(Personagem jogador, int xpGanho) {
        jogador.adicionarXP(xpGanho);
        System.out.println("\n✨ +" + xpGanho + " XP!");

        verificarLevelUp(jogador);

        mostrarProgressoXP(jogador);
    }

    public static void verificarLevelUp(Personagem jogador) {
        int xp = jogador.getXp();
        int nivel = jogador.getNivel();
        int xpNecessario = nivel * 100;

        while (xp >= xpNecessario) {
            jogador.subirNivel();

            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║   ⬆️  LEVEL UP!           ║");
            System.out.printf( "║   Nível %d → %d!%n", nivel, jogador.getNivel());
            System.out.println("╚══════════════════════════╝");
            SomManager.somLevelUp();

            nivel = jogador.getNivel();
            xpNecessario = nivel * 100;
            xp = jogador.getXp();

            ConquistaManager.registrarNivel(nivel);
        }
    }

    private static void mostrarProgressoXP(Personagem jogador) {
        int xp = jogador.getXp();
        int xpNecessario = jogador.getNivel() * 100;

        System.out.println("📊 XP: " + criarBarraXP(xp, xpNecessario)
                + " " + xp + "/" + xpNecessario);
    }

    private static String criarBarraXP(int xp, int xpNecessario) {
        int tamanho = 15;
        int preenchido = (xpNecessario > 0) ? (xp * tamanho) / xpNecessario : 0;

        StringBuilder barra = new StringBuilder("\u001B[36m["); // ciano
        for (int i = 0; i < tamanho; i++) {
            barra.append(i < preenchido ? "█" : "-");
        }
        barra.append("]\u001B[0m");
        return barra.toString();
    }
}