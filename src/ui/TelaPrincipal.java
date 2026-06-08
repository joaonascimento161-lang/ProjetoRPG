package ui;

import javax.swing.*;

import inimigos.Goblin;
import inimigos.Inimigo;
import personagens.Personagem;
import inimigos.Goblin;
import inimigos.Inimigo;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(Personagem jogador){

        setTitle("Projeto RPG");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel nome = new JLabel("Classe: " + jogador.getNome());

        JLabel nivel = new JLabel("Nível: " + jogador.getNivel());

        JLabel vida = new JLabel("Vida: " + jogador.getVida());

        JLabel mana = new JLabel("Mana: " + jogador.getMana());

        JLabel ouro = new JLabel("Ouro: " + jogador.getOuro());

        JButton btnFloresta = new JButton("Floresta");

        btnFloresta.addActionListener(e -> {

            System.out.println("Entrou na Floresta");
        
        });

        btnFloresta.addActionListener(e -> {

        Inimigo goblin = new Goblin();

        new TelaCombate(jogador, goblin);

        });

        JButton btnLoja = new JButton("Loja");

        JButton btnInventario = new JButton("Inventário");

        JButton btnStatus = new JButton("Status");

        JButton btnSalvar = new JButton("Salvar");

        painel.add(nome);
        painel.add(nivel);
        painel.add(vida);
        painel.add(mana);
        painel.add(ouro);

        painel.add(btnFloresta);
        painel.add(btnLoja);
        painel.add(btnInventario);
        painel.add(btnStatus);
        painel.add(btnSalvar);

        add(painel);

        setVisible(true);
    }
}