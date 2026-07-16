package sistema;

public enum ConquistaTipo {

    PRIMEIRO_SANGUE   ("🩸 Primeiro Sangue",     "Vença seu primeiro combate."),
    CACADOR_NOVATO    ("🗡️ Caçador Novato",      "Derrote 10 inimigos."),
    CACADOR_VETERANO  ("⚔️ Caçador Veterano",    "Derrote 50 inimigos."),
    LENDA_DOS_CAMPOS  ("🏹 Lenda dos Campos",    "Derrote 150 inimigos."),
    NIVEL_5           ("⭐ Promissor",            "Alcance o nível 5."),
    NIVEL_10          ("🌟 Veterano",             "Alcance o nível 10."),
    NIVEL_20          ("✨ Herói Lendário",       "Alcance o nível 20."),
    MATADOR_DE_DRAGAO ("🐉 Matador de Dragões",  "Derrote o Dragão Ancestral."),
    RICO              ("💰 Bolso Cheio",         "Acumule 500 de ouro."),
    MAGNATA           ("👑 Magnata",              "Acumule 2000 de ouro."),
    COLECIONADOR_RARO ("🔵 Colecionador",        "Compre um equipamento Raro ou superior."),
    COLECIONADOR_EPICO("🟣 Caçador de Tesouros", "Compre um equipamento Épico."),
    LENDARIO          ("🟡 Toque Lendário",      "Compre um equipamento Lendário."),
    EXPLORADOR        ("🗺️ Explorador",          "Desbloqueie todas as áreas."),
    SOBREVIVENTE      ("❤️ Sobrevivente",        "Vença um combate com menos de 10% de vida."),
    MISSAO_CUMPRIDA   ("🎯 Missão Cumprida",     "Complete sua primeira missão.");

    private final String nome;
    private final String descricao;

    ConquistaTipo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
}
