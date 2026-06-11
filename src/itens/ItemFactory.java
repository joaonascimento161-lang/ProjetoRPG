package itens;

public class ItemFactory {
    
    public static Item criar(String nome){

        switch (nome) {
            case "Poção de Vida":
                return new PocaoVida();
            case "Poção de Mana":
                return new PocaoMana();
            case "Espada de ferro":
                return new Arma("Espada de ferro", 10);
            case "Espada de aço":
                return new Arma("Espada de aço", 15);
            case "Machado osseo":
                return new Arma("Machado osseo", 35);
            case "Espada enferrujada":
                return new Arma("Espada enferrujada", 5);
            case "Matadora de dragões":
                return new Arma("Espada de osso", 20);
            case "Armadura de couro":
                return new Armadura("Armadura de couro", 10);
            case "Armadura do Rei Goblin":
                return new Armadura("Armadura do Rei Goblin", 35);
            case "Armadura encantada":
                return new Armadura("Armadura encantada", 25);
            case "Armadura de ferro":
                return new Armadura("Armadura de ferro", 30);
            case "Armadura suprema":
                return new Armadura("Armadura suprema", 50);
            default:
                return null;
        }
    }
}
