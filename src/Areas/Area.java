package Areas;

import inimigos.*;

public abstract class Area {

    private String nome;
    private int nivelMinimo;

    public Area(String nome, int nivelMinimo) {
        this.nome = nome;
        this.nivelMinimo = nivelMinimo;
    }

    public String getNome()      { return nome; }
    public int getNivelMinimo()  { return nivelMinimo; }

    public abstract Inimigo generateEnemie();
}