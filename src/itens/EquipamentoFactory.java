package itens;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class EquipamentoFactory {

    private static final Map<String, Arma> ARMAS = new LinkedHashMap<>();
    private static final Map<String, Armadura> ARMADURAS = new LinkedHashMap<>();

    static {
        // ── Armas (originais) ───────────────────────────────────────────────
        ARMAS.put("Espada de Ferro",      new Arma("Espada de Ferro",      10, Raridade.COMUM));
        ARMAS.put("Espada de Aço",        new Arma("Espada de Aço",        15, Raridade.COMUM));
        ARMAS.put("Espada de Osso",       new Arma("Espada de Osso",       20, Raridade.INCOMUM));
        ARMAS.put("Espada Enferrujada",   new Arma("Espada Enferrujada",    5, Raridade.COMUM));
        ARMAS.put("Machado Ósseo",        new Arma("Machado Ósseo",        28, Raridade.INCOMUM));
        ARMAS.put("Matadora de Dragões",  new Arma("Matadora de Dragões",  75, Raridade.LENDARIO));
        ARMAS.put("Pistolão da Máfia",    new Arma("Pistolão da Máfia",    55, Raridade.RARO));
        ARMAS.put("Cabo Óptico",          new Arma("Cabo Óptico",          75, Raridade.EPICO));

        // ── Armas raras novas ────────────────────────────────────────────────
        ARMAS.put("Lâmina do Vazio",         new Arma("Lâmina do Vazio",         42, Raridade.RARO));
        ARMAS.put("Adaga Sussurrante",       new Arma("Adaga Sussurrante",       35, Raridade.RARO));
        ARMAS.put("Cajado das Tempestades",  new Arma("Cajado das Tempestades",  60, Raridade.EPICO));
        ARMAS.put("Foice do Ceifador",       new Arma("Foice do Ceifador",       68, Raridade.EPICO));
        ARMAS.put("Excalibur Fragmentada",   new Arma("Excalibur Fragmentada",   90, Raridade.LENDARIO));
        ARMAS.put("Fúria de Fênix",          new Arma("Fúria de Fênix",          85, Raridade.LENDARIO));

        // ── Armaduras (originais) ────────────────────────────────────────────
        ARMADURAS.put("Armadura de Couro",       new Armadura("Armadura de Couro",       15, Raridade.COMUM));
        ARMADURAS.put("Armadura de Ferro",       new Armadura("Armadura de Ferro",       30, Raridade.COMUM));
        ARMADURAS.put("Armadura Encantada",      new Armadura("Armadura Encantada",      25, Raridade.INCOMUM));
        ARMADURAS.put("Armadura do Rei Goblin",  new Armadura("Armadura do Rei Goblin",   8, Raridade.INCOMUM));
        ARMADURAS.put("Armadura Suprema",        new Armadura("Armadura Suprema",        50, Raridade.RARO));
        ARMADURAS.put("Manto Sombrio",           new Armadura("Manto Sombrio",           22, Raridade.INCOMUM));
        ARMADURAS.put("Manto Supremo",           new Armadura("Manto Supremo",           30, Raridade.RARO));
        ARMADURAS.put("Pena do Fernando",        new Armadura("Pena do Fernando",        65, Raridade.EPICO));
        ARMADURAS.put("Livro Sagrado",           new Armadura("Livro Sagrado",           55, Raridade.RARO));
        ARMADURAS.put("Coroa do Rei Goblin",     new Armadura("Coroa do Rei Goblin",      8, Raridade.INCOMUM));

        // ── Armaduras raras novas ────────────────────────────────────────────
        ARMADURAS.put("Placas do Guardião Ancestral", new Armadura("Placas do Guardião Ancestral", 45, Raridade.RARO));
        ARMADURAS.put("Escamas de Dragão",            new Armadura("Escamas de Dragão",            80, Raridade.EPICO));
        ARMADURAS.put("Égide Celestial",              new Armadura("Égide Celestial",              70, Raridade.EPICO));
        ARMADURAS.put("Manto do Rei Eterno",          new Armadura("Manto do Rei Eterno",         100, Raridade.LENDARIO));
        ARMADURAS.put("Coração de Fênix",             new Armadura("Coração de Fênix",             95, Raridade.LENDARIO));
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

    public static int calcularPreco(Equipamento equipamento, int precoBase) {
        double bonus = (equipamento instanceof Arma)
                ? ((Arma) equipamento).getBonusDano()
                : ((Armadura) equipamento).getBonusVida();

        double preco = (precoBase + bonus * 2.5) * equipamento.getRaridade().getMultiplicadorPreco();
        return (int) (Math.round(preco / 5.0) * 5);
    }
}
