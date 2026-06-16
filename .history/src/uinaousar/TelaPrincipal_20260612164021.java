package uinaousar;

import javax.swing.*;

import inimigos.Esqueleto;
import inimigos.Goblin;
import inimigos.Inimigo;
import personagens.Personagem;
import save.SaveManager;

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

        JButton btnFloresta = new JButton("Floresta");

        btnFloresta.addActionListener(e -> {
            System.out.println("Entrou na Floresta");
        
            Inimigo goblin = new Goblin();
            new TelaCombate(jogador, goblin);
            dispose();
        });

        JButton btnCaverna = new JButton("Caverna");

        btnCaverna.addActionListener(e ->{
            System.out.println("Entrou na Caverna");

            Inimigo esqueleto = new Esqueleto();
            new TelaCombate(jogador,esqueleto);
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
        btnCaverna.setAlignmentX(CENTER_ALIGNMENT);

        

        painel.add(Box.createVerticalStrut(20));

        painel.add(nome);
        painel.add(nivel);

        painel.add(vida);
        painel.add(barraVida);

        painel.add(mana);
        painel.add(barraMana);

        painel.add(ouro);

        painel.add(Box.createVerticalStrut(20));

        painel.add(btnFloresta);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnCaverna);
        painel.add(Box.createVerticalStrut(2));

        painel.add(btnLoja);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnInventario);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnStatus);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnSalvar);

        add(painel);

        setVisible(true);
    }
}