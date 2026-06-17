package uinaousar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import personagens.Personagem;
import inimigos.*;
import save.SaveManager;

public class TelaPrincipal extends JFrame {

    private final Personagem jogador;
    private JPanel painelAreas;
    private JPanel painelAcoes;

    public TelaPrincipal(Personagem jogador) {
        this.jogador = jogador;
        
        // Configurações básicas da janela
        setTitle("Projeto RPG - Menu do Herói");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        PainelComFundo painelPrincipal = new PainelComFundo("src/image/map.jpg");
        painelPrincipal.setLayout(new BorderLayout(15, 15));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Card de Status do Jogador (Topo)
        JPanel cardStatus = criarCardStatus();
        painelPrincipal.add(cardStatus, BorderLayout.NORTH);

        // 2. Painel Central (Áreas de Caça + Ações)
        JPanel painelCentral = new JPanel(new GridLayout(2, 1, 0, 15));
        painelCentral.setOpaque(false);

        criarPainelAreas();
        criarPainelAcoes();

        painelCentral.add(painelAreas);
        painelCentral.add(painelAcoes);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // 3. Painel Inferior (Botão Salvar)
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setOpaque(false);
        
        JButton btnSalvar = criarBotaoMenu("💾 SALVAR E SAIR", new Color(139, 0, 0), Color.WHITE);
        btnSalvar.addActionListener(e -> {
            SaveManager.salvar(jogador, false);
            dispose();
            new MenuPrincipal();
        });
        
        painelInferior.add(btnSalvar);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarCardStatus() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(true);
        card.setBackground(new Color(45, 30, 20, 220));
        card.setBorder(BorderFactory.createCompoundBorder(
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

        card.add(nome);
        card.add(Box.createVerticalStrut(4));
        card.add(nivel);
        card.add(Box.createVerticalStrut(15));
        card.add(barraVida);
        card.add(Box.createVerticalStrut(8));
        card.add(barraMana);
        card.add(Box.createVerticalStrut(12));
        card.add(ouro);

        return card;
    }

    private void criarPainelAreas() {
        painelAreas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painelAreas.setOpaque(true);
        painelAreas.setBackground(new Color(30, 20, 15, 180));
        painelAreas.setBorder(new LineBorder(new Color(100, 55, 20), 1));

        // Áreas Iniciais
        JButton btnFloresta = criarBotaoMenu("Floresta", new Color(34, 139, 34), Color.WHITE);
        btnFloresta.addActionListener(e -> iniciarCombate(new Goblin(), "src/image/FundoFloresta.jpg"));
        
        JButton btnRuinas = criarBotaoMenu("Ruínas", new Color(210, 105, 30), Color.WHITE);
        btnRuinas.addActionListener(e -> iniciarCombate(new Esqueleto(), "src/image/FundoRuinas.jpg"));
        
        JButton btnCaverna = criarBotaoMenu("Caverna", new Color(105, 105, 105), Color.WHITE);
        btnCaverna.addActionListener(e -> iniciarCombate(new Orc(), "src/image/FundoCaverna.jpg"));

        painelAreas.add(btnFloresta);
        painelAreas.add(btnRuinas);
        painelAreas.add(btnCaverna);

        // Desbloqueios por Nível (Ordenados do menor nível para o maior)
        if (jogador.getNivel() >= 10) {
            JButton btnVulcao = criarBotaoMenu("Vulcão", new Color(205, 0, 0), Color.WHITE);
            btnVulcao.addActionListener(e -> iniciarCombate(new Phoenix(), "src/image/FundoVulcao.jpg"));
            painelAreas.add(btnVulcao);
        }

        if (jogador.getNivel() >= 15) {
            JButton btnAlpes = criarBotaoMenu("Alpes Suíços", new Color(70, 130, 180), Color.WHITE);
            btnAlpes.addActionListener(e -> iniciarCombate(new PedroNeves(), "src/image/FundoAlpes.jpg"));
            painelAreas.add(btnAlpes);
        }

        if (jogador.getNivel() >= 20) {
            JButton btnMansao = criarBotaoMenu("Mansão Mafia", new Color(102, 51, 0), Color.WHITE);
            btnMansao.addActionListener(e -> iniciarCombate(new GodFather(), "src/image/FundoMafia.jpg"));
            painelAreas.add(btnMansao);
        }

        if (jogador.getNivel() >= 35) {
            JButton btnEletrico = criarBotaoMenu("Mar elétrico", new Color(255, 255, 0), Color.DARK_GRAY);
            btnEletrico.addActionListener(e -> iniciarCombate(new Kjoule(), "src/image/Gemini_Generated_Image_w0uvyow0uvyow0uv.png"));
            painelAreas.add(btnEletrico);
        }
    }

    private void criarPainelAcoes() {
        painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painelAcoes.setOpaque(false);

        JButton btnInventario = criarBotaoMenu("Inventário", new Color(100, 55, 20), Color.WHITE);
        btnInventario.addActionListener(e -> new TelaInventario(jogador));

        JButton btnStatus = criarBotaoMenu("Status", new Color(100, 55, 20), Color.WHITE);
        btnStatus.addActionListener(e -> new TelaStatus(jogador));

        JButton btnLoja = criarBotaoMenu("Loja", new Color(100, 55, 20), Color.WHITE);
        btnLoja.addActionListener(e -> new TelaLoja(jogador));

        painelAcoes.add(btnInventario);
        painelAcoes.add(btnStatus);
        painelAcoes.add(btnLoja);
    }

    private void iniciarCombate(Inimigo inimigo, String caminhoCenario) {
        new TelaCombate(jogador, inimigo, caminhoCenario);
        dispose();
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