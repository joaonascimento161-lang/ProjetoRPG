package personagens;

import itens.Inventario;
import itens.*;
import sistema.Missao;

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
    protected Arma armaEquipada;
    protected Armadura armaduraEquipada;
    protected Missao missaoAtual;

    
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

        System.out.println("\n---- EQUIPAMENTOS ----");

        if(armaEquipada != null){
            System.out.println("Arma: " + armaEquipada.getNome());
        }else{
            System.out.println("Arma: Nenhuma");
        }

        if(armaduraEquipada != null){
            System.out.println("Armadura: " + armaduraEquipada.getNome());
        }else{
            System.out.println("Armadura: Nenhuma");
        }
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
        this.xp = xp;
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

    public Missao getMissaoAtual(){
        return missaoAtual;
    }

    public void aceitarMissao(Missao missao){
        missaoAtual = missao;

        if(missao != null){
            System.out.println("Nova missão: " + missao.getNome());
        }
    }

    public Arma getArmaEquipada(){
        return armaEquipada;
    }

    public Armadura getArmaduraEquipada(){
        return armaduraEquipada;
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

        switch (nivel) {
            case 3:
                System.out.println("\n>>> Caverna desbloqueada");
                break;

            case 5:
                System.out.println("\n>>> Ruínas desbloqueada");
                break;
            case 8:
                System.out.println("\n>>> Castelo sombrio desbloqueada");
                break;
            case 10:
                System.out.println("\n>>> Covil do Dragão desbloqueada");
        }
    }

    public void equiparArma(Arma arma){
        
        if(armaEquipada != null){
            dano -= armaEquipada.getBonusDano();
        }

        armaEquipada = arma;

        dano += arma.getBonusDano();

        System.out.println(arma.getNome() + " equipada");
    }

    public void equiparArmadura(Armadura armadura){

        if(armaduraEquipada != null){
            vidaMax -= armaduraEquipada.getBonusVida();
        }

        armaduraEquipada = armadura;

        vidaMax += armadura.getBonusVida();

        System.out.println(armadura.getNome() + " equipada");
    }

    public void mostrarEquipamentos(){

        System.out.println("\n----- EQUIPAMENTOS -----");

        if(armaEquipada != null){

            System.out.println("Arma: " + armaEquipada.getNome());
        }else{

            System.out.println("Arma: Nenhuma");
        }

        if(armaduraEquipada != null){
            
            System.out.println("Armadura: " + armaduraEquipada.getNome());
        }else{
            System.out.println("Armadura: Nenhuma");
        }
    }
}