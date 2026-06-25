package sistema;

public class Progressao {

    private static final int TOTAL_AREAS = 5;
    private static final String[] NOMES_AREAS = {
            "Floresta",
            "Caverna",
            "Ruínas",
            "Castelo Sombrio",
            "Covil do Dragão"
    };

    private static int areaLiberada = 1;

    public static int getAreaLiberada() {
        return areaLiberada;
    }

    public static void setAreaLiberada(int area) {
        if (area >= 1 && area <= TOTAL_AREAS) {
            areaLiberada = area;
        }
    }

    public static void desbloquearProximaArea() {
        if (areaLiberada >= TOTAL_AREAS) {
            System.out.println("✅ Todas as áreas já estão desbloqueadas!");
            return;
        }

        areaLiberada++;

        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║   🗺️  NOVA ÁREA LIBERADA! ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  ➡️  " + NOMES_AREAS[areaLiberada - 1]);
        System.out.println("╚══════════════════════════╝");
    }

    public static boolean areaDisponivel(int area) {
        return area >= 1 && area <= areaLiberada;
    }
}