package sistema;

import audio.SomManager;

public class GameData {

    private static boolean deusDesbloqueado = false;

    public static boolean isDeusDesbloqueado() {
        return deusDesbloqueado;
    }

    public static void setDeusDesbloqueado(boolean valor) {
        deusDesbloqueado = valor;
    }

    public static void desbloquearDeus() {
        if (deusDesbloqueado) return;

        deusDesbloqueado = true;
        SomManager.somConquista();

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║  ✨ CLASSE DEUS DESBLOQUEADA! ✨  ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  Você provou seu valor mortal.   ║");
        System.out.println("║  O poder dos deuses é seu agora. ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    public static void resetar() {
        deusDesbloqueado = false;
    }

    @Override
    public String toString() {
        return "GameData { deusDesbloqueado=" + deusDesbloqueado + " }";
    }
}