package itens;

import personagens.Personagem;

public abstract class Equipamento extends Item {

    protected Raridade raridade;

    public Equipamento(String nome) {
        this(nome, Raridade.COMUM);
    }

    public Equipamento(String nome, Raridade raridade) {
        super(nome);
        this.raridade = (raridade != null) ? raridade : Raridade.COMUM;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    /** Nome do item já formatado com cor/símbolo de raridade, pronto para exibição. */
    public String getNomeFormatado() {
        return raridade.formatar(nome);
    }

    @Override
    public abstract void usar(Personagem jogador);
}
