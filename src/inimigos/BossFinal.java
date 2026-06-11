package inimigos;
import java.util.Random;
import personagens.Personagem;
import itens.*;

public class BossFinal extends Inimigo{

    private Random random = new Random();

    public BossFinal() {
        super("Dragão Ancestral", 400, 30,500,300);
    }

    @Override
    public void usarHab(Personagem alvo){
        System.out.println("\nO Dragão Ancestral prepara uma habilidade!");

        if(random.nextInt(100) < 30){
            alvo.receberDano(50);

            System.out.println("CHAMA INFERNAL!");
            System.out.println("Dano causado: 50");
        }else{
            System.out.println("A habilidade falhou");
        }
    }

    @Override
    public void realizarTurno(Personagem jogador) {

        double porcentagemVida =
                (double) getVida() / 400.0;

        if (porcentagemVida > 0.5) {

            System.out.println("\n[FASE 1]");
            atacar(jogador);

        } else if (porcentagemVida > 0.25) {

            System.out.println("\n[FASE 2]");

            if (random.nextInt(100) < 50) {
                usarHab(jogador);
            } else {
                atacar(jogador);
            }

        } else {

            System.out.println("\n[FASE 3 - FÚRIA]");

            if (random.nextInt(100) < 70) {
                usarHab(jogador);
            } else {

                jogador.receberDano(35);

                System.out.println(
                        "Golpe Furioso! Dano: 35");
            }
        }
    }

    @Override 
    public Item gerarDrop(){

        return new Arma("Matadora de dragões", 75);
    }
}
