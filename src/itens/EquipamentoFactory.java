package itens;

public class EquipamentoFactory {

    public static Arma criarArma(String nome){

        switch(nome){

            case "Espada de ferro":
                return new Arma("Espada de ferro", 10);

            case "Machado osseo":
                return new Arma("Machado osseo", 35);

            case "Espada de aço":
                return new Arma("Espada de aço", 15);

            case "Espada de osso":
                return new Arma("Espada de osso", 20);

            case "Matadora de dragões":
                return new Arma("Matadora de dragões", 75);

            default:
                return null;
        }
    }

    public static Armadura criarArmadura(String nome){

        switch(nome){

            case "Armadura de couro":
                return new Armadura("Armadura de couro", 15);

            case "Armadura de ferro":
                return new Armadura("Armadura de ferro", 30);

            case "Armadura encantada":
                return new Armadura("Armadura encantada", 25);

            case "Armadura do Rei Goblin":
                return new Armadura("Armadura do Rei Goblin", 35);

            case "Armadura suprema":
                return new Armadura("Armadura suprema", 50);

            default:
                return null;
        }
    }
}