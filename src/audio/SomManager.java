package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
public class SomManager {

    private static volatile boolean ativo = true;
    private static final float SAMPLE_RATE = 44100f;

    public static boolean isAtivo() { return ativo; }
    public static void setAtivo(boolean valor) { ativo = valor; }
    public static void alternar() { ativo = !ativo; }

    // -------- efeitos prontos --------

    public static void somAtaque()     { tocarAssincrono(new int[]{220}, new int[]{70}, 0.25); }
    public static void somHabilidade() { tocarAssincrono(new int[]{440, 660}, new int[]{80, 120}, 0.25); }
    public static void somCura()       { tocarAssincrono(new int[]{523, 659, 784}, new int[]{70, 70, 120}, 0.2); }
    public static void somCompra()     { tocarAssincrono(new int[]{392, 523}, new int[]{70, 100}, 0.2); }
    public static void somErro()       { tocarAssincrono(new int[]{160}, new int[]{150}, 0.25); }
    public static void somLevelUp()    { tocarAssincrono(new int[]{523, 659, 784, 1046}, new int[]{90, 90, 90, 180}, 0.28); }
    public static void somVitoria()    { tocarAssincrono(new int[]{659, 784, 987, 1318}, new int[]{100, 100, 100, 260}, 0.3); }
    public static void somDerrota()    { tocarAssincrono(new int[]{300, 250, 200, 150}, new int[]{150, 150, 150, 300}, 0.3); }
    public static void somConquista()  { tocarAssincrono(new int[]{784, 987, 1318, 1568}, new int[]{80, 80, 80, 220}, 0.3); }

    private static void tocarAssincrono(int[] frequencias, int[] duracoesMs, double volume) {
        if (!ativo) return;
        Thread t = new Thread(() -> tocarSequencia(frequencias, duracoesMs, volume));
        t.setDaemon(true);
        t.start();
    }

    private static void tocarSequencia(int[] frequencias, int[] duracoesMs, double volume) {
        try {
            AudioFormat formato = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
            SourceDataLine linha = AudioSystem.getSourceDataLine(formato);
            linha.open(formato);
            linha.start();

            for (int i = 0; i < frequencias.length; i++) {
                byte[] buffer = gerarTom(frequencias[i], duracoesMs[i], volume);
                linha.write(buffer, 0, buffer.length);
            }

            linha.drain();
            linha.close();
        } catch (Exception e) {
        }
    }

    private static byte[] gerarTom(int frequenciaHz, int duracaoMs, double volume) {
        int totalAmostras = (int) (SAMPLE_RATE * duracaoMs / 1000.0);
        byte[] buffer = new byte[totalAmostras];

        for (int i = 0; i < totalAmostras; i++) {
            double angulo = 2.0 * Math.PI * i * frequenciaHz / SAMPLE_RATE;
            double fade = Math.min(1.0, (totalAmostras - i) / (double) Math.max(1, totalAmostras / 6));
            double amostra = Math.sin(angulo) * volume * fade;
            buffer[i] = (byte) (amostra * 127);
        }
        return buffer;
    }
}
