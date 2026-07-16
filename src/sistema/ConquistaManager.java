package sistema;

import audio.SomManager;
import itens.Equipamento;
import itens.Raridade;

import java.io.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * Controla o progresso de conquistas do jogador. É um progresso "global"
 * (persistido em arquivo próprio, separado do save do personagem), assim
 * como {@link GameData}, valendo para todas as partidas jogadas.
 */
public class ConquistaManager {

    private static final String ARQUIVO = "conquistas.txt";

    private static final Set<ConquistaTipo> desbloqueadas = EnumSet.noneOf(ConquistaTipo.class);

    private static int totalInimigosDerrotados = 0;
    private static int maiorOuroAcumulado = 0;
    private static int maiorNivelAlcancado = 1;

    // -------- registro de eventos (chamados pelo resto do jogo) --------

    public static void registrarVitoria(String nomeInimigo, boolean vidaCriticaAoFinal) {
        totalInimigosDerrotados++;

        desbloquear(ConquistaTipo.PRIMEIRO_SANGUE);
        if (totalInimigosDerrotados >= 10)  desbloquear(ConquistaTipo.CACADOR_NOVATO);
        if (totalInimigosDerrotados >= 50)  desbloquear(ConquistaTipo.CACADOR_VETERANO);
        if (totalInimigosDerrotados >= 150) desbloquear(ConquistaTipo.LENDA_DOS_CAMPOS);

        if ("Dragão Ancestral".equals(nomeInimigo)) {
            desbloquear(ConquistaTipo.MATADOR_DE_DRAGAO);
        }
        if (vidaCriticaAoFinal) {
            desbloquear(ConquistaTipo.SOBREVIVENTE);
        }
    }

    public static void registrarNivel(int nivel) {
        maiorNivelAlcancado = Math.max(maiorNivelAlcancado, nivel);
        if (nivel >= 5)  desbloquear(ConquistaTipo.NIVEL_5);
        if (nivel >= 10) desbloquear(ConquistaTipo.NIVEL_10);
        if (nivel >= 20) desbloquear(ConquistaTipo.NIVEL_20);
    }

    public static void registrarOuro(int ouroAtual) {
        maiorOuroAcumulado = Math.max(maiorOuroAcumulado, ouroAtual);
        if (ouroAtual >= 500)  desbloquear(ConquistaTipo.RICO);
        if (ouroAtual >= 2000) desbloquear(ConquistaTipo.MAGNATA);
    }

    public static void registrarCompra(Equipamento equipamento) {
        Raridade r = equipamento.getRaridade();
        if (r == Raridade.RARO)     desbloquear(ConquistaTipo.COLECIONADOR_RARO);
        if (r == Raridade.EPICO)    { desbloquear(ConquistaTipo.COLECIONADOR_RARO); desbloquear(ConquistaTipo.COLECIONADOR_EPICO); }
        if (r == Raridade.LENDARIO) { desbloquear(ConquistaTipo.COLECIONADOR_RARO); desbloquear(ConquistaTipo.COLECIONADOR_EPICO); desbloquear(ConquistaTipo.LENDARIO); }
    }

    public static void registrarTodasAreasDesbloqueadas() {
        desbloquear(ConquistaTipo.EXPLORADOR);
    }

    public static void registrarMissaoCumprida() {
        desbloquear(ConquistaTipo.MISSAO_CUMPRIDA);
    }

    // -------- núcleo --------

    private static void desbloquear(ConquistaTipo tipo) {
        if (desbloqueadas.contains(tipo)) return;
        desbloqueadas.add(tipo);

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        🏆 NOVA CONQUISTA DESBLOQUEADA!    ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  %-41s║%n", tipo.getNome());
        System.out.printf( "║  %-41s║%n", tipo.getDescricao());
        System.out.println("╚══════════════════════════════════════════╝");

        SomManager.somConquista();
        salvar();
    }

    public static boolean isDesbloqueada(ConquistaTipo tipo) {
        return desbloqueadas.contains(tipo);
    }

    public static void mostrarConquistas() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║              🏆 CONQUISTAS                ║");
        System.out.println("╠══════════════════════════════════════════╣");
        int total = ConquistaTipo.values().length;
        System.out.printf( "║  Progresso: %d / %d                         ║%n", desbloqueadas.size(), total);
        System.out.println("╠══════════════════════════════════════════╣");

        for (ConquistaTipo tipo : ConquistaTipo.values()) {
            String status = desbloqueadas.contains(tipo) ? "✅" : "🔒";
            String nome = desbloqueadas.contains(tipo) ? tipo.getNome() : "??? (bloqueada)";
            System.out.println("  " + status + " " + nome);
            if (desbloqueadas.contains(tipo)) {
                System.out.println("      " + tipo.getDescricao());
            }
        }
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // -------- persistência --------

    public static void salvar() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            StringBuilder sb = new StringBuilder();
            for (ConquistaTipo tipo : desbloqueadas) {
                sb.append(tipo.name()).append(";");
            }
            writer.write("Desbloqueadas;" + sb + "\n");
            writer.write("TotalInimigosDerrotados = " + totalInimigosDerrotados + "\n");
            writer.write("MaiorOuroAcumulado = " + maiorOuroAcumulado + "\n");
            writer.write("MaiorNivelAlcancado = " + maiorNivelAlcancado + "\n");
        } catch (IOException e) {
            // Falha silenciosa: conquistas não são essenciais ao progresso do save principal.
        }
    }

    public static void carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Desbloqueadas;")) {
                    String[] partes = linha.split(";");
                    for (int i = 1; i < partes.length; i++) {
                        try {
                            desbloqueadas.add(ConquistaTipo.valueOf(partes[i].trim()));
                        } catch (IllegalArgumentException ignored) {
                            // conquista removida/renomeada em versão antiga do save; ignora
                        }
                    }
                } else if (linha.startsWith("TotalInimigosDerrotados = ")) {
                    totalInimigosDerrotados = Integer.parseInt(valor(linha));
                } else if (linha.startsWith("MaiorOuroAcumulado = ")) {
                    maiorOuroAcumulado = Integer.parseInt(valor(linha));
                } else if (linha.startsWith("MaiorNivelAlcancado = ")) {
                    maiorNivelAlcancado = Integer.parseInt(valor(linha));
                }
            }
        } catch (IOException e) {
            // Sem conquistas salvas ainda.
        }
    }

    private static String valor(String linha) {
        return linha.split(" = ", 2)[1].trim();
    }
}
