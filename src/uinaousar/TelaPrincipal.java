package uinaousar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import personagens.Personagem;
import inimigos.*;
import save.SaveManager;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(Personagem jogador) {

        setTitle("Projeto RPG - Menu do Herói");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        PainelComFundo painelPrincipal = new PainelComFundo("src/image/map.jpg");
        painelPrincipal.setLayout(new BorderLayout(15, 15));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel cardStatus = new JPanel();
        cardStatus.setLayout(new BoxLayout(cardStatus, BoxLayout.Y_AXIS));
        cardStatus.setOpaque(true);
        cardStatus.setBackground(new Color(45, 30, 20, 220));
        cardStatus.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(139, 69, 19), 2),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        JLabel nome = new JLabel(jogador.getNome().toUpperCase());
        nome.setFont(new Font("Serif", Font.BOLD, 22));
        nome.setForeground(new Color(244, 228, 188));
        nome.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nivel = new JLabel("NÍVEL " + jogador.getNivel());
        nivel.setFont(new Font("Serif", Font.PLAIN, 14));
        nivel.setForeground(Color.LIGHT_GRAY);
        nivel.setAlignmentX(CENTER_ALIGNMENT);

        JProgressBar barraVida = new JProgressBar(0, jogador.getVidaMax());
        barraVida.setValue(jogador.getVida());
        barraVida.setString("HP: " + jogador.getVida() + " / " + jogador.getVidaMax());
        barraVida.setStringPainted(true);
        barraVida.setFont(new Font("SansSerif", Font.BOLD, 12));
        barraVida.setBackground(new Color(40, 40, 40));
        barraVida.setForeground(new Color(46, 139, 87));
        barraVida.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));

        JProgressBar barraMana = new JProgressBar(0, jogador.getManaMax());
        barraMana.setValue(jogador.getMana());
        barraMana.setString("MP: " + jogador.getMana() + " / " + jogador.getManaMax());
        barraMana.setStringPainted(true);
        barraMana.setFont(new Font("SansSerif", Font.BOLD, 12));
        barraMana.setBackground(new Color(40, 40, 40));
        barraMana.setForeground(new Color(30, 144, 255));
        barraMana.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));

        JLabel ouro = new JLabel("💲Ouro: " + jogador.getOuro());
        ouro.setFont(new Font("Serif", Font.BOLD, 16));
        ouro.setForeground(new Color(218, 165, 32));
        ouro.setAlignmentX(CENTER_ALIGNMENT);

        cardStatus.add(nome);
        cardStatus.add(Box.createVerticalStrut(4));
        cardStatus.add(nivel);
        cardStatus.add(Box.createVerticalStrut(15));
        cardStatus.add(barraVida);
        cardStatus.add(Box.createVerticalStrut(8));
        cardStatus.add(barraMana);
        cardStatus.add(Box.createVerticalStrut(12));
        cardStatus.add(ouro);

        JPanel painelCentral = new JPanel(new GridLayout(2, 1, 0, 15));
        painelCentral.setOpaque(false);

        JPanel painelAreas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painelAreas.setOpaque(true);
        painelAreas.setBackground(new Color(30, 20, 15, 180));
        painelAreas.setBorder(new LineBorder(new Color(100, 55, 20), 1));

        JButton btnFloresta = criarBotaoMenu("Floresta", new Color(34, 139, 34), Color.WHITE);
        JButton btnRuinas = criarBotaoMenu("Ruínas", new Color(210, 105, 30), Color.WHITE);
        JButton btnCaverna = criarBotaoMenu("Caverna", new Color(105, 105, 105), Color.WHITE);

        painelAreas.add(btnFloresta);
        painelAreas.add(btnRuinas);
        painelAreas.add(btnCaverna);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painelAcoes.setOpaque(false);

        JButton btnInventario = criarBotaoMenu("Inventário", new Color(100, 55, 20), Color.WHITE);
        JButton btnStatus = criarBotaoMenu("Status", new Color(100, 55, 20), Color.WHITE);
        JButton btnLoja = criarBotaoMenu("Loja", new Color(100, 55, 20), Color.WHITE);

        painelAcoes.add(btnInventario);
        painelAcoes.add(btnStatus);
        painelAcoes.add(btnLoja);

        painelCentral.add(painelAreas);
        painelCentral.add(painelAcoes);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setOpaque(false);
        JButton btnSalvar = criarBotaoMenu("💾 SALVAR E SAIR", new Color(139, 0, 0), Color.WHITE);
        painelInferior.add(btnSalvar);

        painelPrincipal.add(cardStatus, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);
        add(painelPrincipal);

        btnFloresta.addActionListener(e -> {
            Inimigo goblin = new Goblin();
            new TelaCombate(jogador, goblin, "src/image/FundoFloresta.jpg");
            dispose();
        });

        btnRuinas.addActionListener(e -> {
            Inimigo esqueleto = new Esqueleto();
            new TelaCombate(jogador, esqueleto, "src/image/FundoRuinas.jpg");
            dispose();
        });

        btnCaverna.addActionListener(e -> {
            Inimigo orc = new Orc();
            new TelaCombate(jogador, orc,"src/image/FundoCaverna.jpg");
            dispose();
        });

        btnLoja.addActionListener(e -> new TelaLoja(jogador));
        btnInventario.addActionListener(e -> new TelaInventario(jogador));
        btnStatus.addActionListener(e -> new TelaStatus(jogador));

        btnSalvar.addActionListener(e -> {
            SaveManager.salvar(jogador, false);
            dispose();
            new MenuPrincipal();
        });

        setVisible(true);
    }

    private JButton criarBotaoMenu(String texto, Color fundo, Color textoCor) {
        JButton btn = new JButton(texto.toUpperCase());
        btn.setFont(new Font("Serif", Font.BOLD, 13));
        btn.setBackground(fundo);
        btn.setForeground(textoCor);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(fundo.brighter(), 1),
            BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}