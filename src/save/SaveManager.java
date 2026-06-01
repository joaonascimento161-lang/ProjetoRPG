package save;

import java.beans.PersistenceDelegate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

            writer.write("DeusDesbloqueado = " + deusDesbloqueado + "\n");

            writer.close();

            System.out.println("Jogo salvo com sucesso!");
        }catch(IOException e){

            System.out.println("Erro ao salvar o jogo!");

            e.printStackTrace();
        }
    }

    public static void carregar(){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SAVE));

            String linha;

            System.out.println("\n----- DADOS SALVOS -----");

            while ((linha = reader.readLine()) != null) {
                
                System.out.println(linha);
            }

            reader.close();
        }catch(IOException e){

            System.out.println("Nenhum save encontrado");
        }
    }

    public static boolean existeSave(){

        java.io.File arquivo = new java.io.File("save.txt");

        return arquivo.exists();
    }
}
