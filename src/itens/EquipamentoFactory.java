package itens;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class EquipamentoFactory {

    private static final Map<String, Arma> ARMAS = new LinkedHashMap<>();
    private static final Map<String, Armadura> ARMADURAS = new LinkedHashMap<>();

    static {
        ARMAS.put("Espada de Ferro",      new Arma("Espada de Ferro", 10));
        ARMAS.put("Espada de Aço",        new Arma("Espada de Aço", 15));
        ARMAS.put("Espada de Osso",       new Arma("Espada de Osso", 20));
        ARMAS.put("Machado Ósseo",        new Arma("Machado Ósseo", 35));
        ARMAS.put("Matadora de Dragões",  new Arma("Matadora de Dragões", 75));

        ARMADURAS.put("Armadura de Couro",      new Armadura("Armadura de Couro", 15));
        ARMADURAS.put("Armadura de Ferro",      new Armadura("Armadura de Ferro", 30));
        ARMADURAS.put("Armadura Encantada",     new Armadura("Armadura Encantada", 25));
        ARMADURAS.put("Armadura do Rei Goblin", new Armadura("Armadura do Rei Goblin", 35));
        ARMADURAS.put("Armadura Suprema",       new Armadura("Armadura Suprema", 50));
    }

    public static Arma criarArma(String nome) {
        return ARMAS.get(nome);
    }

    public static Armadura criarArmadura(String nome) {
        return ARMADURAS.get(nome);
    }

    public static Map<String, Arma> listarArmas() {
        return Collections.unmodifiableMap(ARMAS);
    }

    public static Map<String, Armadura> listarArmaduras() {
        return Collections.unmodifiableMap(ARMADURAS);
    }
}