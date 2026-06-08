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

        progresso = 0;
        concluida = false;
    }

    public void registrarAbate(String inimigo){

        if(concluida){
            return;
        }

        if(inimigo.equals(inimigoAlvo)){

            progresso++;

            System.out.println("Missao: " + progresso + "/" + objetivo);

            if(progresso >= objetivo){
                concluida = true;

                System.out.println("MISSÃO CONCLUIDA");
            }
        }
    }

    public boolean isConcluida(){
        return concluida;
    }

    public int getRecompensaXP(){
        return recompensaXP;
    }

    public int getRecompensaOuro(){
        return recompensaOuro;
    }

    public String getNome(){
        return nome;
    }
}