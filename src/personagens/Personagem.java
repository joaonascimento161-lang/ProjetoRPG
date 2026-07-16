package personagens;

import audio.SomManager;
import itens.*;
import sistema.Combate;
import sistema.ConquistaManager;
import sistema.Missao;

public abstract class Personagem {

    protected Inventario inventario;
    protected String nome;
    protected int vida;
    protected int vidaMax;
    protected int dano;
    protected int mana;
    protected int manaMax;
    protected int nivel;
    protected int xp;
    protected int ouro;
    protected Arma armaEquipada;
    protected Armadura armaduraEquipada;
    protected Missao missaoAtual;

    public Personagem(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.dano = dano;
        this.mana = 0;
        this.manaMax = 100;
        this.nivel = 1;
        this.xp = 0;
        this.ouro = 0;
        this.inventario = new Inventario();
    }

    // -------- COMBATE --------

    public void atacar(Personagem alvo) {
        alvo.receberDano(dano);
        ganharMana(10);
        System.out.println("⚔️  " + nome + " causou " + dano + " de dano!");
    }

    public void receberDano(int danoRecebido) {
        vida = Math.max(0, vida - danoRecebido);
    }

    public void curar(int valor) {
        vida = Math.min(vidaMax, vida + valor);
        System.out.println("💚 " + nome + " recuperou " + valor + " de vida! (" + vida + "/" + vidaMax + ")");
    }

    public void ganharMana(int valor) {
        mana = Math.min(manaMax, mana + valor);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public abstract void usarHab(Personagem alvo);

    // -------- PROGRESSÃO --------

    public void subirNivel() {
        nivel++;
        int vidaAntes = vidaMax;
        int danoAntes = dano;

        vidaMax += 10;
        vida = vidaMax;
        dano += 2;

        SomManager.somLevelUp();
        ConquistaManager.registrarNivel(nivel);

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        ⬆️  LEVEL UP!          ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf( "║  Nível: %d%n", nivel);
        System.out.printf( "║  ❤️  Vida:  %d → %d%n", vidaAntes, vidaMax);
        System.out.printf( "║  ⚔️  Dano:  %d → %d%n", danoAntes, dano);
        System.out.println("╚══════════════════════════════╝");

        String areaDesbloqueada = null;
        switch (nivel) {
            case 3:  areaDesbloqueada = "Caverna"; break;
            case 5:  areaDesbloqueada = "Ruínas"; break;
            case 8:  areaDesbloqueada = "Castelo Sombrio"; break;
            case 10: areaDesbloqueada = "Covil do Dragão"; break;
        }
        if (areaDesbloqueada != null) {
            System.out.println("🗺️  Nova área desbloqueada: " + areaDesbloqueada + "!");
        }
    }

    public void adicionarXP(int valor) {
        this.xp += valor;
        int xpNecessario =  this.nivel * 100;

        while (this.xp >= xpNecessario){
            this.xp -= xpNecessario;
            subirNivel();
            xpNecessario = this.nivel * 100;
        }
    }

    public void carregarNivel(int nivel) {
        this.nivel = nivel;
        vidaMax += (nivel - 1) * 10;
        dano    += (nivel - 1) * 2;
        vida = vidaMax;
    }

    // -------- EQUIPAMENTOS --------

    public void equiparArma(Arma arma) {
        if (armaEquipada != null) dano -= armaEquipada.getBonusDano();
        armaEquipada = arma;
        dano += arma.getBonusDano();
        System.out.println("⚔️  " + arma.getNome() + " equipada! (+" + arma.getBonusDano() + " dano)");
    }

    public void equiparArmadura(Armadura armadura) {
        if (armaduraEquipada != null) vidaMax -= armaduraEquipada.getBonusVida();
        armaduraEquipada = armadura;
        vidaMax += armadura.getBonusVida();
        System.out.println("🛡️  " + armadura.getNome() + " equipada! (+" + armadura.getBonusVida() + " vida)");
    }

    // -------- OURO --------

    public boolean gastarOuro(int valor) {
        if (ouro >= valor) { ouro -= valor; return true; }
        return false;
    }

    public void adicionarOuro(int valor) { ouro += valor; }

    // -------- MISSÃO --------

    public void aceitarMissao(Missao missao) {
        missaoAtual = missao;
        if (missao != null) System.out.println("📋 Nova missão aceita: " + missao.getNome());
    }

    // -------- STATUS --------

    public void mostrarStatus() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.printf( "║  👤 %-29s║%n", nome + " (Nível " + nivel + ")");
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf("║  ❤️  HP   %s %d/%d%n", Combate.criarBarra(vida, vidaMax), vida, vidaMax);
        System.out.printf("║  💧 Mana %s %d/%d%n", Combate.criarBarra(mana, manaMax), mana, manaMax);
        int xpNecessario = nivel * 100;
        System.out.printf("║  ✨ XP   %s %d/%d%n", Combate.criarBarra(xp, xpNecessario), xp, xpNecessario);
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf("║  ⚔️  Dano:  %-23d║%n", dano);
        System.out.printf("║  💰 Ouro:  %-23d║%n", ouro);
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf("║  ⚔️  Arma:     %-20s║%n", armaEquipada != null ? armaEquipada.getNome() : "Nenhuma");
        System.out.printf("║  🛡️  Armadura: %-20s║%n", armaduraEquipada != null ? armaduraEquipada.getNome() : "Nenhuma");
        if (missaoAtual != null) {
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  " + missaoAtual.toString());
        }
        System.out.println("╚══════════════════════════════════╝");
    }

    // -------- GETTERS / SETTERS --------

    public String getNome()               { return nome; }
    public int getVida()                  { return vida; }
    public int getVidaMax()               { return vidaMax; }
    public int getMana()                  { return mana; }
    public int getManaMax()               { return manaMax; }
    public int getDano()                  { return dano; }
    public int getNivel()                 { return nivel; }
    public int getXp()                    { return xp; }
    public int getOuro()                  { return ouro; }
    public Inventario getInventario()     { return inventario; }
    public Arma getArmaEquipada()         { return armaEquipada; }
    public Armadura getArmaduraEquipada() { return armaduraEquipada; }
    public Missao getMissaoAtual()        { return missaoAtual; }

    public void setXp(int xp)            { this.xp = xp; }
    public void setNivel(int nivel)      { this.nivel = nivel; }
    public void aumentarVidaMax(int v)   { vidaMax += v; vida = vidaMax; }
    public void aumentarDano(int v)      { dano += v; }
    public void setVida(int vida)        { this.vida = Math.max(0, Math.min(vida, vidaMax)); }
    public void setMana(int mana)        { this.mana = Math.max(0, Math.min(mana, manaMax)); }
}