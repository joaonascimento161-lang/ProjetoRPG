package save;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import personagens.*;
import itens.*;

public class SaveManager {
    private static final String ARQUIVO_SAVE = "save.txt";
    
    public static void salvar(Personagem jogador,boolean deusDesbloqueado){

        try{

            FileWriter writer = new FileWriter(ARQUIVO_SAVE);

            writer.write("Classe = " + jogador.getClass().getSimpleName() + "\n");

            writer.write("Nivel = " + jogador.getNivel() + "\n");

            writer.write("Xp = " + jogador.getXp() + "\n");

            writer.write("Vida = " + jogador.getVida() + "\n");

            writer.write("Mana = " + jogador.getMana() + "\n");

            writer.write("Ouro = " + jogador.getOuro() + "\n");

            writer.write("Inventario;");

            if(jogador.getInventario().tamanho() == 0){
                writer.write("Vazio");
            }

            for(int i = 0; i < jogador.getInventario().tamanho(); i++){

                Item item = jogador.getInventario().getItem(i);
            
                writer.write(item.getNome() + ";");
            }

            writer.write("\n");

            writer.write("Arma = ");

            if(jogador.getArmaEquipada() != null){
                writer.write(jogador.getArmaEquipada().getNome());
            }else{
                writer.write("Nenhuma");
            }

            writer.write("\n");

            writer.write("Armadura = ");

            if(jogador.getArmaduraEquipada() != null){
                writer.write(jogador.getArmaduraEquipada().getNome());
            }else{
                writer.write("Nenhuma");
            }

            writer.write("\n");

            writer.write("DeusDesbloqueado = " + deusDesbloqueado + "\n");

            writer.close();

            System.out.println("Jogo salvo com sucesso!****");
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
            int vida = 0;
            int mana = 0;

            ArrayList<String> inventario = new ArrayList<>();

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
                }else if(linha.startsWith("Vida = ")){
                    vida = Integer.parseInt(linha.split(" = ")[1]);
                }else if(linha.startsWith("Mana = ")){
                    mana = Integer.parseInt(linha.split(" = ")[1]);
                }else if(linha.startsWith("Ouro = ")){
                    ouro = Integer.parseInt(linha.split(" = ")[1]);
                }else if(linha.startsWith("Arma = ")){
                    arma = linha.split(" = ")[1];
                }else if(linha.startsWith("Armadura = ")){
                    armadura = linha.split(" = ")[1];
                }else if(linha.startsWith("Inventario;")){

                    String[] pegarTamanho = linha.split(";");
                
                    for(int i = 1; i < pegarTamanho.length; i++){
                
                        if(!pegarTamanho[i].equals("Vazio")){
                            inventario.add(pegarTamanho[i]);
                        }
                    }
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

                jogador.carregarNivel(nivel);

                jogador.setXp(xp);

                jogador.adicionarOuro(ouro);

                if(arma.equals("Espada de ferro")){
                    jogador.equiparArma(new Arma("Espada de ferro", 10));
                }else if(arma.equals("Espada de aço")){
                    jogador.equiparArma(new Arma("Espada de aço", 15));
                }else if(arma.equals("Espada de enferrujada")){
                    jogador.equiparArma(new Arma("Espada enferrujada", 5));
                }else if(arma.equals("Espada de osso")){
                    jogador.equiparArma(new Arma("Espada de osso", 20));
                }else if(arma.equals("Matadora de dragões")){
                    jogador.equiparArma(new Arma("Matadora de dragões", 55));
                }else if(!arma.equals("Nenhuma")){
                    System.out.println("Arma equipada corrompida");
                }

                if(armadura.equals("Armadura de couro")){
                    jogador.equiparArmadura(new Armadura("Armadura de couro", 15));
                }else if(armadura.equals("Armadura encantada")){
                    jogador.equiparArmadura(new Armadura("Armadura encantada", 50));
                }else if(armadura.equals("Armadura de ferro")){
                    jogador.equiparArmadura(new Armadura("Armadura de ferro", 30));
                }else if(!armadura.equals("Nenhuma")){
                    System.out.println("Armadura equipada corrompida");
                }
                
                System.out.println("Jogo carregado com sucesso");
            }else{
                System.out.println("Classe corrompida. Impossível iniciar");
            }

            return jogador;
        }catch(IOException e){
            System.out.println("Nenhum save encontrado");
            return null;
        }
    }

    public static boolean existeSave(){

        java.io.File arquivo = new java.io.File(ARQUIVO_SAVE);

        return arquivo.exists();
    }
}
