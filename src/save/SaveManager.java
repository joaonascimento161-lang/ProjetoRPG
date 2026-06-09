package save;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import itens.Arma;
import itens.Armadura;
import personagens.Arqueiro;
import personagens.Assassino;
import personagens.Berserker;
import personagens.Curandeiro;
import personagens.Deus;
import personagens.Guerreiro;
import personagens.Mago;
import personagens.Paladino;
import personagens.Personagem;

public class SaveManager {
    private static final String ARQUIVO_SAVE = "save.txt";
    
    public static void salvar(Personagem jogador,boolean deusDesbloqueado){

        try{

            FileWriter writer = new FileWriter(ARQUIVO_SAVE);

            writer.write("Classe = " + jogador.getClass().getSimpleName() + "\n");

            writer.write("Nivel = " + jogador.getNivel() + "\n");

            writer.write("Xp = " + jogador.getXp() + "\n");

            writer.write("Ouro = " + jogador.getOuro() + "\n");

            writer.write("Arma = ");

            if(jogador.getArmaEquipada() != null){
                writer.write(jogador.getArmaEquipada().getNome());
            }else{
                writer.write("Nenhuma");}

            writer.write("\n");

            writer.write("Armadura = ");

            if(jogador.getArmaduraEquipada() != null){
                writer.write(jogador.getArmaduraEquipada().getNome());
            }else{
                writer.write("Nenhuma");}

            writer.write("\n");

            writer.write("DeusDesbloqueado = " + deusDesbloqueado + "\n");

            

            writer.close();

            System.out.println("Jogo salvo com sucesso!");
        }catch(IOException e){

            System.out.println("Erro ao salvar o jogo!");

            e.printStackTrace();

        }
    }

    public static Personagem carregar(){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SAVE));

            String classe = "";
            int nivel = 1;
            int xp = 0;
            int ouro = 0;

            String arma = "";
            String armadura = "";

            String linha;

            while ((linha = reader.readLine()) !=  null) {
                
                if(linha.startsWith("Classe = ")){
                    classe = linha.split(" = ")[1];
                }else if(linha.startsWith("Nivel = ")){
                    nivel = Integer.parseInt(linha.split(" = ")[1]);
                }else if(linha.startsWith("Xp = ")){
                    xp = Integer.parseInt(linha.split(" = ")[1]);
                }else if(linha.startsWith("Ouro = ")){
                    ouro = Integer.parseInt(linha.split(" = ")[1]);
                }else if(linha.startsWith("Arma = ")){
                    arma = linha.split(" = ")[1];
                }else if(linha.startsWith("Armadura = ")){
                    armadura = linha.split(" = ")[1];
                }
            }

            reader.close();

            Personagem jogador = null;

            switch (classe) {
                case "Guerreiro":
                    jogador = new Guerreiro();
                    break;

                case "Mago":
                    jogador = new Mago();
                    break;

                case "Arqueiro":
                    jogador = new Arqueiro();
                    break;

                case "Paladino":
                    jogador = new Paladino();
                    break;

                case "Assassino":
                    jogador = new Assassino();
                    break;

                case "Berserker":
                    jogador = new Berserker();
                    break;

                case "Curandeiro":
                    jogador = new Curandeiro();
                    break;

                case "Deus":
                    jogador = new Deus();
                    break;
            }

            if(jogador != null){

                jogador.setNivel(nivel);
                jogador.setXp(xp);
                jogador.adicionarOuro(ouro);

                if(arma.equals("Espada de ferro")){
                    jogador.equiparArma(new Arma("Espada de ferro", 10));
                }

                if(arma.equals("Machado osseo")){
                    jogador.equiparArma(new Arma("Machado osseo", 35));
                }

                if(arma.equals("Espada de aço")){
                    jogador.equiparArma(new Arma("Espada de aço", 15));
                }

                if(arma.equals("Espada enferrujada")){
                    jogador.equiparArma(new Arma("Espada enferrujada", 5));
                }

                if(arma.equals("Espada de osso")){
                    jogador.equiparArma(new Arma("Espada de osso", 20));
                }

                if(arma.equals("Matadora de dragões")){
                    jogador.equiparArma(new Arma("Matadora de dragões", 75));
                }

                if(armadura.equals("Armadura de couro")){
                    jogador.equiparArmadura(new Armadura("Armadura de couro", 15));
                }

                if(armadura.equals("Armadura do Rei Goblin")){
                    jogador.equiparArmadura(new Armadura("Armadura do Rei Goblin", 35));
                }

                if(armadura.equals("Armadura encantada")){
                    jogador.equiparArmadura(new Armadura("Armadura encantada", 50));
                }

                if(armadura.equals("Armadura de ferro")){
                    jogador.equiparArmadura(new Armadura("Armadura de ferro", 30));
                }
                
                System.out.println("Jogo carregado com sucesso");
            }

            return jogador;
        }catch(IOException e){
            System.out.println("Nenhum save encontrado");
            return null;
        }
    }

    public static boolean existeSave(){

        java.io.File arquivo = new java.io.File("save.txt");

        return arquivo.exists();
    }

    
}
