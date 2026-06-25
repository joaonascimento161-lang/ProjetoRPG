package sistema;

public class Missao {

    private String nome;
    private String inimigoAlvo;
    private int objetivo;
    private int progresso;
    private int recompensaXP;
    private int recompensaOuro;
    private boolean concluida;

    public Missao(String nome, String inimigoAlvo, int objetivo, int recompensaXP, int recompensaOuro) {
        this.nome = nome;
        this.inimigoAlvo = inimigoAlvo;
        this.objetivo = objetivo;
        this.recompensaXP = recompensaXP;
        this.recompensaOuro = recompensaOuro;
        this.progresso = 0;
        this.concluida = false;
    }

    public void registrarAbate(String inimigo) {
        if (concluida) return;

        if (inimigo.equals(inimigoAlvo)) {
            progresso++;

            System.out.println("📋 Missão [" + nome + "]: " + progresso + "/" + objetivo
                    + " " + criarBarraProgresso());

            if (progresso >= objetivo) {
                concluida = true;
                System.out.println("🎯 MISSÃO CONCLUÍDA: " + nome + "!");
            }
        }
    }

    private String criarBarraProgresso() {
        int tamanho = 10;
        int preenchido = (progresso * tamanho) / objetivo;

        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            barra.append(i < preenchido ? "█" : "-");
        }
        barra.append("]");
        return barra.toString();
    }

    @Override
    public String toString() {
        if (concluida) {
            return "✅ [CONCLUÍDA] " + nome;
        }
        return "📋 " + nome + " — Derrotar " + inimigoAlvo
                + " (" + progresso + "/" + objetivo + ") "
                + criarBarraProgresso()
                + " | Recompensa: " + recompensaXP + " XP, " + recompensaOuro + " ouro";
    }

    public boolean isConcluida()   { return concluida; }
    public int getRecompensaXP()   { return recompensaXP; }
    public int getRecompensaOuro() { return recompensaOuro; }
    public String getNome()        { return nome; }
    public int getProgresso()      { return progresso; }
    public int getObjetivo()       { return objetivo; }
    public String getInimigoAlvo() { return inimigoAlvo; }
}