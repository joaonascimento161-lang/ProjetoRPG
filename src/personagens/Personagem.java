package personagens;

import itens.Inventario;

public abstract class Personagem {
    protected Inventario inventario;
    protected String nome;
    protected int vida;
    protected int vidaMax;
    protected int dano;
    protected int mana;
    protected int nivel;
    protected int xp;
    protected int ouro;

    
    public Inventario getInventario(){
        return inventario;
    }
    
    public Personagem(String nome, int vida, int dano){
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.dano = dano;
        this.mana = 0;
        this.nivel = 1;
        this.xp = 0;
        this.ouro = 0;
        
        inventario = new Inventario();
    }
    
    public void atacar(Personagem alvo){
        alvo.receberDano(dano);
        ganharMana(10);
        
        System.out.println(nome + " causou " + dano + " de dano");
    }
    
    public void receberDano(int danoRecebido){
        vida -= danoRecebido;
        
        if(vida < 0){
            vida = 0;
        }
    }
    
    public void curar(int valor){
        vida += valor;
        
        if(vida > vidaMax){
            vida = vidaMax;
        }
    }
    
    public void ganharMana(int valor){
        mana += valor;
    }
    
    public boolean estaVivo(){
        return vida > 0;
    }
    
    public void mostrarStatus(){
        System.out.println("----" + nome + "----");
        System.out.println("Vida: " + vida + "|" + vidaMax);
        System.out.println("Mana: " + mana);
        System.out.println("Nivel: " + nivel);
        System.out.println("XP: " + xp);
        System.out.println("Ouro: " + ouro);
    }
    
    public abstract void usarHab(Personagem alvo);
    
    public String getNome(){
        return nome;
    }
    
    public int getVida(){
        return vida;
    }
    
    public int getMana(){
        return mana;
    }
    
    public int getDano(){
        return dano;
    }
    
    public int getNivel(){
        return nivel;
    }

    public void setXp(int xp){
        this.nivel = xp;
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public void aumentarVidaMax(int valor){
        vidaMax += valor;
        vida = vidaMax;
    }

    public void aumentarDano(int valor){
        dano += valor;
    }
    
    public void adicionarXP(int valor){
        xp += valor;
    }
    
    public boolean gastarOuro(int valor){
        if(ouro >= valor){
            ouro -=  valor;
            return true;
        }

        return false;
    }

    public void adicionarOuro(int valor){
        ouro += valor;
    }
    
    public int getXp(){
        return xp;
    }

    public int getOuro(){
        return ouro;
    }

    public void subirNivel(){
        nivel++;

        vidaMax += 10;
        vida = vidaMax;

        dano += 2;

        System.out.println("\n----------------");
        System.out.println("LEVEL UP!");
        System.out.println("Novo nivel: " + nivel);
        System.out.println("Vida máxima: " + vidaMax);
        System.out.println("Dano: " + dano);
        System.out.println("----------------");
    }
}
