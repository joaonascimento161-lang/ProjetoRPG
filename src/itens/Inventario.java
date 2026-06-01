package itens;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;
    
    public Inventario(){
        itens = new ArrayList<>();
    }

    public void adicionarItem(Item item){
        itens.add(item);

        System.out.println(item.getNome() + " adicionado ao inventario");
    }

    public void removerItem(int indice){
        if(indice >= 0 && indice < itens.size()){
            itens.remove(indice);
        }
    }

    public Item getItem(int indice){
        if(indice >= 0 && indice < itens.size()){
            return itens.get(indice);
        }
        return null;
    }

    public void listarItens(){
        System.out.println("---- INVENTÁRIO ----");

        if(itens.isEmpty()){
            System.out.println("Inventário vazio");
            return;
        }
        for(int cont = 0; cont < itens.size(); cont++){
            System.out.println((cont + 1) + " - " + itens.get(cont).getNome());
        }
    }

    public boolean estaVazio(){
        return itens.isEmpty();
    }

    public int tamanho(){
        return itens.size();
    }
}
