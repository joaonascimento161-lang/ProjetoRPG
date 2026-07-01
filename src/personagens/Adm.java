package personagens;

public class Adm extends Personagem {

    private static final String SENHA = "minhasenha";
    private static final int DANO_HAB = 999999;

    public Adm() {
        super("ADM", 999999, 999);
    }

    public static Adm tentarCriar(String senhaTentativa) {
        if (senhaTentativa.equals(SENHA)) {
            return new Adm();
        }
        System.out.println("❌ Senha incorreta.");
        return null;
    }

    @Override
    public void usarHab(Personagem alvo) {
        alvo.receberDano(DANO_HAB);
        System.out.println("💀 BANNED! Dano: " + DANO_HAB);
    }

    @Override
    public void mostrarStatus() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        STATUS — ADM          ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  Vida  : [██████████] ∞      ║");
        System.out.println("║  Mana  : [██████████] ∞      ║");
        System.out.println("║  Dano  : ∞                   ║");
        System.out.println("║  Nível : ∞                   ║");
        System.out.println("╚══════════════════════════════╝");
    }
}