package sistema;

public class Area {

    private String nome;
    private int nivelRecomendado;

    public Area(String nome, int nivelRecomendado){
        this.nome = nome;
        this.nivelRecomendado = nivelRecomendado;
    }

    public String getNome(){
        return nome;
    }

    public int getNivelMinimo(){
        return nivelRecomendado;
    }
}
