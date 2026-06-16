package uinaousar;


import javax.swing.*;

import inimigos.Esqueleto;
import inimigos.Goblin;
import inimigos.Inimigo;
import inimigos.Orc;
import personagens.Personagem;
import save.SaveManager;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(Personagem jogador){

        setTitle("Projeto RPG");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();

        painel.setLayout(
            new BoxLayout(
                painel,
                BoxLayout.Y_AXIS
            )
        );

        JLabel nome = new JLabel("Classe: " + jogador.getNome());

        JLabel nivel = new JLabel("Nível: " + jogador.getNivel());

        JLabel vida = new JLabel("Vida: " + jogador.getVida());

        JProgressBar barraVida =
        new JProgressBar(
                0,
                jogador.getVidaMax()
        );

        barraVida.setValue(
                jogador.getVida()
        );

        barraVida.setStringPainted(true);

        JProgressBar barraMana =
        new JProgressBar(
            0,
            jogador.getManaMax()
        );

        barraMana.setValue(
            jogador.getMana()
        );

        barraMana.setStringPainted(true);

        barraMana.setString(
            jogador.getMana()
            + "/"
            + jogador.getManaMax()
        );

        barraVida.setString(
            jogador.getVida()
            + "/"
            + jogador.getVidaMax()
        );

        JLabel mana = new JLabel(
            "Mana: "
            + jogador.getMana()
            + "/"
            + jogador.getManaMax()
        );

        JLabel ouro = new JLabel("Ouro: " + jogador.getOuro());

        nome.setAlignmentX(CENTER_ALIGNMENT);
        nivel.setAlignmentX(CENTER_ALIGNMENT);
        vida.setAlignmentX(CENTER_ALIGNMENT);
        mana.setAlignmentX(CENTER_ALIGNMENT);
        ouro.setAlignmentX(CENTER_ALIGNMENT);

        

        JPanel painelAreas = new JPanel();

        JButton btnFloresta = new JButton("Floresta");
        JButton btnCaverna = new JButton("Caverna");
        JButton btnRuinas = new JButton("Ruínas");

        painelAreas.add(btnFloresta);
        painelAreas.add(btnCaverna);
        painelAreas.add(btnRuinas);

        btnFloresta.addActionListener(e -> {
            System.out.println("Entrou na Floresta");
        
            Inimigo goblin = new Goblin();
            new TelaCombate(jogador, goblin);
            dispose();
        });

        btnRuinas.addActionListener(e ->{
            System.out.println("Entrou nas Ruinas");

            Inimigo esqueleto = new Esqueleto();
            new TelaCombate(jogador,esqueleto);
            dispose();
        });

        btnCaverna.addActionListener(e ->{
            System.out.println("Entrou na Caverna");

            Inimigo orc = new Orc();
            new TelaCombate(jogador,orc);
            dispose();
        });

        JButton btnLoja = new JButton("Loja");

        btnLoja.addActionListener(e ->{
            new TelaLoja(jogador);
        });

        JButton btnInventario = new JButton("Inventário");

        btnInventario.addActionListener(e -> {
            new TelaInventario(jogador);
        });

        JButton btnStatus = new JButton("Status");

        btnStatus.addActionListener(e ->{
            JOptionPane.showMessageDialog(
                this,
                "Classe: " + jogador.getNome()
                + "\nNível: " + jogador.getNivel()
                + "\nVida: " + jogador.getVida()
                + "/" + jogador.getVidaMax()
                + "\nMana: " + jogador.getMana()
                + "/" + jogador.getManaMax()
                + "\nOuro: " + jogador.getOuro()
            );
        });

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e ->{
            SaveManager.salvar(jogador, false);
            dispose();

            new MenuPrincipal();
        });
        

        btnFloresta.setAlignmentX(CENTER_ALIGNMENT);
        btnLoja.setAlignmentX(CENTER_ALIGNMENT);
        btnInventario.setAlignmentX(CENTER_ALIGNMENT);
        btnStatus.setAlignmentX(CENTER_ALIGNMENT);
        btnSalvar.setAlignmentX(CENTER_ALIGNMENT);
        barraVida.setAlignmentX(CENTER_ALIGNMENT);
        barraMana.setAlignmentX(CENTER_ALIGNMENT);

        btnFloresta.setBackground(new Color(34, 139, 34));
        btnFloresta.setForeground(Color.BLACK);

        btnCaverna.setBackground(new Color(105, 105, 105));
        btnCaverna.setForeground(Color.WHITE);

        btnRuinas.setBackground(new Color(238, 118, 33));
        btnRuinas.setForeground(Color.BLACK);

        barraVida.setForeground(new Color(102,205,0));

        barraVida.setBackground(new Color(105,105,105));
        barraVida.setForeground(new Color(0, 100 0));
        barraVida.setBackground(new Color(105,105,105));
        barraVida.setForeground(new Color(102,205,0));
        barraMana.setForeground(new Color(0,255,255));
        barraMana.setBackground(new Color(105,105,105));
        

        painel.add(Box.createVerticalStrut(20));

        painel.add(nome);
        painel.add(nivel);

        painel.add(vida);
        painel.add(barraVida);

        painel.add(mana);
        painel.add(barraMana);

        painel.add(ouro);

        painel.add(Box.createVerticalStrut(20));

        painel.add(painelAreas);

        painel.add(btnInventario);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnStatus);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnSalvar);

        add(painel);

        setVisible(true);
    }
}