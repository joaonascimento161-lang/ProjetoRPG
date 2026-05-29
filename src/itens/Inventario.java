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

    public void removerItem(Item item){
        
    }
}
