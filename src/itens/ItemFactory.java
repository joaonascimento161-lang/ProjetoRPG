package itens;

public class ItemFactory {

    public static Item criar(String nome) {
        if (nome == null) return null;

        switch (nome) {
            case "Poção de Vida": return new PocaoVida();
            case "Poção de Mana": return new PocaoMana();
            default:
                Item arma = EquipamentoFactory.criarArma(nome);
                if (arma != null) return arma;

                Item armadura = EquipamentoFactory.criarArmadura(nome);
                if (armadura != null) return armadura;

                System.out.println("⚠️ Item desconhecido: " + nome);
                return null;
        }
    }
}