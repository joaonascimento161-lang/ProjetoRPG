package itens;

/**
 * Representa o nível de raridade de um equipamento.
 * Cada raridade possui uma cor ANSI (para o terminal), um símbolo
 * e um multiplicador de preço usado pela loja.
 */
public enum Raridade {

    COMUM     ("Comum",      "\u001B[37m", "⚪", 1.0),
    INCOMUM   ("Incomum",    "\u001B[32m", "🟢", 1.5),
    RARO      ("Raro",       "\u001B[34m", "🔵", 2.2),
    EPICO      ("Épico",      "\u001B[35m", "🟣", 3.2),
    LENDARIO  ("Lendário",   "\u001B[33m", "🟡", 4.5);

    private final String nome;
    private final String cor;
    private final String simbolo;
    private final double multiplicadorPreco;

    Raridade(String nome, String cor, String simbolo, double multiplicadorPreco) {
        this.nome = nome;
        this.cor = cor;
        this.simbolo = simbolo;
        this.multiplicadorPreco = multiplicadorPreco;
    }

    public String getNome() { return nome; }
    public String getCor() { return cor; }
    public String getSimbolo() { return simbolo; }
    public double getMultiplicadorPreco() { return multiplicadorPreco; }

    private static final String RESET = "\u001B[0m";

    /** Retorna o nome colorido pronto para impressão no terminal. */
    public String formatar(String texto) {
        return cor + simbolo + " " + texto + " (" + nome + ")" + RESET;
    }

    /** Retorna apenas o nome colorido da raridade, sem o item. */
    public String tag() {
        return cor + simbolo + " " + nome + RESET;
    }
}
