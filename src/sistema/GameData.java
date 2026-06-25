package sistema;

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