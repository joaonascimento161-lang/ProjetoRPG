package itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventario {

    private ArrayList<Item> itens;

    public Inventario() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        System.out.println("🎒 " + item.getNome() + " adicionado ao inventário!");
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }

    public Item getItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            return itens.get(indice);
        }
        return null;
    }

    public void listarItens() {
        System.out.println("\n🎒 ---- INVENTÁRIO ----");
        if (itens.isEmpty()) {
            System.out.println("  Inventário vazio.");
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            String exibicao = (item instanceof Equipamento)
                    ? ((Equipamento) item).getNomeFormatado()
                    : item.getNome();
            System.out.println("  " + (i + 1) + " - " + exibicao);
        }
        System.out.println("----------------------");
    }

    public boolean estaVazio()  { return itens.isEmpty(); }
    public int tamanho()        { return itens.size(); }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }
}