package save;

import java.io.*;
import java.util.*;

import personagens.*;
import itens.*;
import sistema.GameData;

public class SaveManager {

    private static final String ARQUIVO_SAVE = "save.txt";

    public static void salvar(Personagem jogador, boolean deusDesbloqueado) {
        try (FileWriter writer = new FileWriter(ARQUIVO_SAVE)) {

            writer.write("Classe = "    + jogador.getClass().getSimpleName() + "\n");
            writer.write("Nivel = "     + jogador.getNivel() + "\n");
            writer.write("Xp = "        + jogador.getXp() + "\n");
            writer.write("Vida = "      + jogador.getVida() + "\n");
            writer.write("Mana = "      + jogador.getMana() + "\n");
            writer.write("Ouro = "      + jogador.getOuro() + "\n");

            writer.write("Arma = ");
            writer.write(jogador.getArmaEquipada() != null
                    ? jogador.getArmaEquipada().getNome() : "Nenhuma");
            writer.write("\n");

            writer.write("Armadura = ");
            writer.write(jogador.getArmaduraEquipada() != null
                    ? jogador.getArmaduraEquipada().getNome() : "Nenhuma");
            writer.write("\n");

            writer.write("Inventario;");
            if (jogador.getInventario().tamanho() == 0) {
                writer.write("Vazio");
            } else {
                for (int i = 0; i < jogador.getInventario().tamanho(); i++) {
                    writer.write(jogador.getInventario().getItem(i).getNome() + ";");
                }
            }
            writer.write("\n");

            writer.write("DeusDesbloqueado = " + deusDesbloqueado + "\n");

            System.out.println("✅ Jogo salvo com sucesso!");

        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar o jogo!");
            e.printStackTrace();
        }
    }

    public static Personagem carregar() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SAVE))) {

            String classe = "";
            int nivel = 1, xp = 0, ouro = 0, vida = 0, mana = 0;
            String arma = "Nenhuma", armadura = "Nenhuma";
            boolean deusDesbloqueado = false;
            List<String> inventario = new ArrayList<>();

            String linha;
            while ((linha = reader.readLine()) != null) {
                if      (linha.startsWith("Classe = "))           classe  = valor(linha);
                else if (linha.startsWith("Nivel = "))            nivel   = Integer.parseInt(valor(linha));
                else if (linha.startsWith("Xp = "))               xp      = Integer.parseInt(valor(linha));
                else if (linha.startsWith("Vida = "))             vida    = Integer.parseInt(valor(linha));
                else if (linha.startsWith("Mana = "))             mana    = Integer.parseInt(valor(linha));
                else if (linha.startsWith("Ouro = "))             ouro    = Integer.parseInt(valor(linha));
                else if (linha.startsWith("Arma = "))             arma    = valor(linha);
                else if (linha.startsWith("Armadura = "))         armadura = valor(linha);
                else if (linha.startsWith("DeusDesbloqueado = ")) deusDesbloqueado = Boolean.parseBoolean(valor(linha));
                else if (linha.startsWith("Inventario;"))         inventario = carregarInventario(linha);
            }

            Personagem jogador = criarPersonagem(classe);
            if (jogador == null) {
                System.out.println("❌ Classe corrompida. Impossível carregar.");
                return null;
            }

            jogador.carregarNivel(nivel);
            jogador.setXp(xp);
            jogador.adicionarOuro(ouro);
            jogador.setVida(vida);
            jogador.setMana(mana);

            // Equipa arma usando ItemFactory
            if (!arma.equals("Nenhuma")) {
                Item armaObj = ItemFactory.criar(arma);
                if (armaObj instanceof Arma) jogador.equiparArma((Arma) armaObj);
                else System.out.println("⚠️ Arma desconhecida no save: " + arma);
            }

            // Equipa armadura usando ItemFactory
            if (!armadura.equals("Nenhuma")) {
                Item armaduraObj = ItemFactory.criar(armadura);
                if (armaduraObj instanceof Armadura) jogador.equiparArmadura((Armadura) armaduraObj);
                else System.out.println("⚠️ Armadura desconhecida no save: " + armadura);
            }

            // Restaura inventário usando ItemFactory
            for (String nomeItem : inventario) {
                Item item = ItemFactory.criar(nomeItem);
                if (item != null) jogador.getInventario().adicionarItem(item);
                else System.out.println("⚠️ Item desconhecido no save: " + nomeItem);
            }

            GameData.setDeusDesbloqueado(deusDesbloqueado);

            System.out.println("✅ Jogo carregado! Bem-vindo de volta, " + jogador.getNome() + "!");
            return jogador;

        } catch (IOException e) {
            System.out.println("Nenhum save encontrado.");
            return null;
        }
    }

    public static boolean existeSave() {
        return new File(ARQUIVO_SAVE).exists();
    }

    private static String valor(String linha) {
        return linha.split(" = ", 2)[1].trim();
    }

    private static List<String> carregarInventario(String linha) {
        List<String> itens = new ArrayList<>();
        String[] partes = linha.split(";");
        for (int i = 1; i < partes.length; i++) {
            if (!partes[i].equals("Vazio") && !partes[i].isBlank()) {
                itens.add(partes[i]);
            }
        }
        return itens;
    }

    private static Personagem criarPersonagem(String classe) {
        switch (classe) {
            case "Guerreiro":  return new Guerreiro();
            case "Mago":       return new Mago();
            case "Arqueiro":   return new Arqueiro();
            case "Paladino":   return new Paladino();
            case "Assassino":  return new Assassino();
            case "Berserker":  return new Berserker();
            case "Curandeiro": return new Curandeiro();
            case "Deus":       return new Deus();
            case "ADM":        return new Adm();
            default:           return null;
        }
    }
}