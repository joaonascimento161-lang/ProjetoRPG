package uinaousar;

import java.awt.BorderLayout;

import javax.swing.*;

import inimigos.Esqueleto;
import inimigos.Goblin;
import inimigos.Inimigo;
import inimigos.Orc;
import personagens.Personagem;
import save.SaveManager;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(Personagem jogador) {

        setTitle("Projeto RPG");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Informações do personagem
        JLabel nome = new JLabel("Classe: " + jogador.getNome());
        JLabel nivel = new JLabel("Nível: " + jogador.getNivel());

        JLabel vida = new JLabel(
                "Vida: "
                + jogador.getVida()
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

        // Barra de vida
        JProgressBar barraVida =
                new JProgressBar(0, jogador.getVidaMax());

        barraVida.setValue(jogador.getVida());
        barraVida.setStringPainted(true);
        barraVida.setString(
                jogador.getVida()
                + "/"
                + jogador.getVidaMax()
        );

        // Barra de mana
        JProgressBar barraMana =
                new JProgressBar(0, jogador.getManaMax());

        barraMana.setValue(jogador.getMana());
        barraMana.setStringPainted(true);
        barraMana.setString(
                jogador.getMana()
                + "/"
                + jogador.getManaMax()
        );

        // Centralizar componentes
        nome.setAlignmentX(CENTER_ALIGNMENT);
        nivel.setAlignmentX(CENTER_ALIGNMENT);
        vida.setAlignmentX(CENTER_ALIGNMENT);
        mana.setAlignmentX(CENTER_ALIGNMENT);
        ouro.setAlignmentX(CENTER_ALIGNMENT);

        barraVida.setAlignmentX(CENTER_ALIGNMENT);
        barraMana.setAlignmentX(CENTER_ALIGNMENT);

        // Botões
        JButton btnFloresta = new JButton("Floresta");
        JButton btnCaverna = new JButton("Caverna");
        JButton btnRuinas = new JButton("Ruínas");
        JButton btnLoja = new JButton("Loja");
        JButton btnInventario = new JButton("Inventário");
        JButton btnStatus = new JButton("Status");
        JButton btnSalvar = new JButton("Salvar");

        btnFloresta.setAlignmentX(CENTER_ALIGNMENT);
        btnCaverna.setAlignmentX(CENTER_ALIGNMENT);
        btnRuinas.setAlignmentX(CENTER_ALIGNMENT);
        btnLoja.setAlignmentX(CENTER_ALIGNMENT);
        btnInventario.setAlignmentX(CENTER_ALIGNMENT);
        btnStatus.setAlignmentX(CENTER_ALIGNMENT);
        btnSalvar.setAlignmentX(CENTER_ALIGNMENT);

        // Eventos dos botões

        btnFloresta.addActionListener(e -> {
            Inimigo goblin = new Goblin();
            new TelaCombate(jogador, goblin);
            dispose();
        });

        btnCaverna.addActionListener(e -> {
            Inimigo orc = new Orc();
            new TelaCombate(jogador, orc);
            dispose();
        });

        btnRuinas.addActionListener(e -> {
            Inimigo esqueleto = new Esqueleto();
            new TelaCombate(jogador, esqueleto);
            dispose();
        });

        btnLoja.addActionListener(e -> {
            new TelaLoja(jogador);
        });

        btnInventario.addActionListener(e -> {
            new TelaInventario(jogador);
        });

        btnStatus.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Classe: " + jogador.getNome()
                    + "\nNível: " + jogador.getNivel()
                    + "\nVida: " + jogador.getVida()
                    + "/" + jogador.getVidaMax()
                    + "\nMana: " + jogador.getMana()
                    + "/" + jogador.getManaMax()
                    + "\nOuro: " + jogador.getOuro(),
                    "Status do Personagem",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        btnSalvar.addActionListener(e -> {
            SaveManager.salvar(jogador, false);

            JOptionPane.showMessageDialog(
                    this,
                    "Jogo salvo com sucesso!"
            );

            dispose();
            new MenuPrincipal();
        });

        // Adicionando componentes
        painel.add(Box.createVerticalStrut(15));

        painel.add(nome);
        painel.add(nivel);

        painel.add(Box.createVerticalStrut(10));

        painel.add(vida);
        painel.add(barraVida);

        painel.add(Box.createVerticalStrut(10));

        painel.add(mana);
        painel.add(barraMana);

        painel.add(Box.createVerticalStrut(10));

        painel.add(ouro);

        painel.add(Box.createVerticalStrut(20));

        painel.add(btnFloresta);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnCaverna);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnRuinas);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnLoja);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnInventario);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnStatus);
        painel.add(Box.createVerticalStrut(5));

        painel.add(btnSalvar);

        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }
}2