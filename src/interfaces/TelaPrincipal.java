package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import personagens.Personagem;
import personagens.Adm;
import inimigos.*;
import save.SaveManager;

public class TelaPrincipal extends JFrame {

    private static final Color COR_FUNDO_CARD   = new Color(45, 30, 20, 220);
    private static final Color COR_BORDA        = new Color(139, 69, 19);
    private static final Color COR_TITULO       = new Color(244, 228, 188);
    private static final Color COR_SECUNDARIO   = new Color(180, 160, 130);
    private static final Color COR_VIDA         = new Color(46, 139, 87);
    private static final Color COR_MANA         = new Color(30, 144, 255);
    private static final Color COR_OURO         = new Color(218, 165, 32);
    private static final Font  FONTE_NOME       = new Font("Serif", Font.BOLD, 22);
    private static final Font  FONTE_NIVEL      = new Font("Serif", Font.PLAIN, 14);
    private static final Font  FONTE_BOTAO      = new Font("Serif", Font.BOLD, 13);

    private final Personagem jogador;

    public TelaPrincipal(Personagem jogador) {
        this.jogador = jogador;
        setTitle("Projeto RPG — " + jogador.getNome());
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
        painelPrincipal.add(criarCardStatus(), BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridLayout(2, 1, 0, 15));
        painelCentral.setOpaque(false);
        painelCentral.add(criarPainelAreas());
        painelCentral.add(criarPainelAcoes());
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(criarPainelInferior(), BorderLayout.SOUTH);
        add(painelPrincipal);
    }

    private JPanel criarCardStatus() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_FUNDO_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        JLabel lblNome = new JLabel(jogador.getNome().toUpperCase());
        lblNome.setFont(FONTE_NOME);
        lblNome.setForeground(COR_TITULO);
        lblNome.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblNivel = new JLabel("NÍVEL " + jogador.getNivel() + "   •   XP: " + jogador.getXp());
        lblNivel.setFont(FONTE_NIVEL);
        lblNivel.setForeground(COR_SECUNDARIO);
        lblNivel.setAlignmentX(CENTER_ALIGNMENT);

        boolean isAdm = jogador instanceof Adm;

        JProgressBar barraVida = isAdm
                ? criarBarraInfinita(new Color(200, 50, 50))
                : criarBarra(jogador.getVida(), jogador.getVidaMax(), COR_VIDA,
                "HP: " + jogador.getVida() + " / " + jogador.getVidaMax());

        JProgressBar barraMana = isAdm
                ? criarBarraInfinita(new Color(150, 0, 200))
                : criarBarra(jogador.getMana(), jogador.getManaMax(), COR_MANA,
                "MP: " + jogador.getMana() + " / " + jogador.getManaMax());

        JLabel lblOuro = new JLabel("💰  " + jogador.getOuro() + " ouro");
        lblOuro.setFont(new Font("Serif", Font.BOLD, 15));
        lblOuro.setForeground(COR_OURO);
        lblOuro.setAlignmentX(CENTER_ALIGNMENT);

        card.add(lblNome);
        card.add(Box.createVerticalStrut(2));
        card.add(lblNivel);
        card.add(Box.createVerticalStrut(12));
        card.add(barraVida);
        card.add(Box.createVerticalStrut(6));
        card.add(barraMana);
        card.add(Box.createVerticalStrut(10));
        card.add(lblOuro);
        return card;
    }

    private JPanel criarPainelAreas() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painel.setBackground(new Color(30, 20, 15, 180));
        painel.setBorder(new LineBorder(new Color(100, 55, 20), 1));

        List<AreaCaca> areas = List.of(
                new AreaCaca("🌲 Floresta",     1,  new Color(34, 139, 34),   Color.WHITE,     () -> new Goblin(),     "src/image/FundoFloresta.jpg"),
                new AreaCaca("🏚 Ruínas",       3,  new Color(210, 105, 30),  Color.WHITE,     () -> new Esqueleto(),  "src/image/FundoRuinas.jpg"),
                new AreaCaca("⛰ Caverna",       5,  new Color(105, 105, 105), Color.WHITE,     () -> new Orc(),        "src/image/FundoCaverna.jpg"),
                new AreaCaca("🌋 Vulcão",       10, new Color(205, 0, 0),     Color.WHITE,     () -> new Phoenix(),    "src/image/FundoVulcao.jpg"),
                new AreaCaca("🏔 Alpes Suíços", 15, new Color(70, 130, 180),  Color.WHITE,     () -> new PedroNeves(), "src/image/FundoAlpes.jpg"),
                new AreaCaca("🕴 Mansão Mafia", 20, new Color(102, 51, 0),    Color.WHITE,     () -> new GodFather(),  "src/image/FundoMafia.jpg"),
                new AreaCaca("⚡ Mar Elétrico", 35, new Color(180, 160, 0),   Color.DARK_GRAY, () -> new Kjoule(),     "src/image/Gemini_Generated_Image_w0uvyow0uvyow0uv.png")
        );

        for (AreaCaca area : areas) {
            boolean ok = jogador.getNivel() >= area.nivelMinimo;
            JButton btn = criarBotaoArea(area, ok);
            if (ok) btn.addActionListener(e -> iniciarCombate(area.criarInimigo(), area.cenario));
            painel.add(btn);
        }
        return painel;
    }

    private JButton criarBotaoArea(AreaCaca area, boolean desbloqueada) {
        String texto = desbloqueada ? area.nome : "🔒 Nível " + area.nivelMinimo;
        JButton btn = new JButton(texto.toUpperCase());
        btn.setFont(FONTE_BOTAO);
        btn.setFocusPainted(false);
        if (desbloqueada) {
            btn.setBackground(area.corFundo);
            btn.setForeground(area.corTexto);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(area.corFundo.brighter(), 1),
                    BorderFactory.createEmptyBorder(10, 18, 10, 18)
            ));
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { btn.setBackground(area.corFundo.brighter()); }
                @Override public void mouseExited(MouseEvent e)  { btn.setBackground(area.corFundo); }
            });
        } else {
            btn.setBackground(new Color(40, 30, 20));
            btn.setForeground(new Color(90, 70, 50));
            btn.setEnabled(false);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(60, 45, 25), 1),
                    BorderFactory.createEmptyBorder(10, 18, 10, 18)
            ));
        }
        return btn;
    }

    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painel.setOpaque(false);
        JButton btnInventario = criarBotaoMenu("🎒 Inventário");
        JButton btnStatus     = criarBotaoMenu("📊 Status");
        JButton btnLoja       = criarBotaoMenu("🛒 Loja");
        btnInventario.addActionListener(e -> new TelaInventario(jogador));
        btnStatus.addActionListener(e    -> new TelaStatus(jogador));
        btnLoja.addActionListener(e      -> new TelaLoja(jogador));
        painel.add(btnInventario);
        painel.add(btnStatus);
        painel.add(btnLoja);
        return painel;
    }

    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painel.setOpaque(false);
        JButton btn = new JButton("💾  SALVAR E SAIR");
        btn.setFont(FONTE_BOTAO);
        btn.setBackground(new Color(139, 0, 0));
        btn.setForeground(COR_TITULO);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 30, 30), 1),
                BorderFactory.createEmptyBorder(10, 24, 10, 24)
        ));
        btn.addActionListener(e -> { SaveManager.salvar(jogador, false); dispose(); new MenuPrincipal(); });
        painel.add(btn);
        return painel;
    }

    private void iniciarCombate(Inimigo inimigo, String cenario) {
        dispose();
        new TelaCombate(jogador, inimigo, cenario);
    }

    private JProgressBar criarBarra(int valor, int max, Color cor, String texto) {
        JProgressBar b = new JProgressBar(0, max);
        b.setValue(valor); b.setString(texto); b.setStringPainted(true);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setForeground(cor); b.setBackground(new Color(40, 40, 40));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        return b;
    }

    private JProgressBar criarBarraInfinita(Color cor) {
        JProgressBar b = new JProgressBar(0, 1) {
            @Override
            public String getString() {
                return "∞";
            }
        };
        b.setValue(1);
        b.setStringPainted(true);
        b.setFont(new Font("Serif", Font.BOLD, 16));
        b.setForeground(cor);
        b.setBackground(new Color(40, 40, 40));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        return b;
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto.toUpperCase());
        btn.setFont(FONTE_BOTAO);
        btn.setBackground(new Color(100, 55, 20));
        btn.setForeground(COR_TITULO);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(140, 80, 30), 1),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));
        btn.addMouseListener(new MouseAdapter() {
            final Color base = new Color(100, 55, 20);
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(base.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(base); }
        });
        return btn;
    }

    private static class AreaCaca {
        final String nome; final int nivelMinimo;
        final Color corFundo, corTexto;
        final java.util.function.Supplier<Inimigo> fabricaInimigo;
        final String cenario;
        AreaCaca(String nome, int nivelMinimo, Color corFundo, Color corTexto,
                 java.util.function.Supplier<Inimigo> fabricaInimigo, String cenario) {
            this.nome=nome; this.nivelMinimo=nivelMinimo; this.corFundo=corFundo;
            this.corTexto=corTexto; this.fabricaInimigo=fabricaInimigo; this.cenario=cenario;
        }
        Inimigo criarInimigo() { return fabricaInimigo.get(); }
    }
}