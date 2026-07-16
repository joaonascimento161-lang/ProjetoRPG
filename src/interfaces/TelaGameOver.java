package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Timer;
import personagens.Personagem;
import inimigos.Inimigo;
import sistema.Missao;
import save.SaveManager;

import java.awt.*;
import java.awt.event.*;

/**
 * Tela de Game Over — exibida em tela cheia quando o jogador é derrotado.
 * Mostra um resumo da run (nível, ouro, missão) e oferece opções para
 * tentar novamente (carregando o último save), voltar ao menu ou sair.
 */
public class TelaGameOver extends JFrame {

    private static final Color COR_FUNDO_TOPO    = new Color(10, 4, 4);
    private static final Color COR_FUNDO_BASE    = new Color(35, 8, 8);
    private static final Color COR_BORDA         = new Color(120, 30, 30);
    private static final Color COR_TITULO        = new Color(210, 40, 40);
    private static final Color COR_SUBTITULO     = new Color(200, 170, 140);
    private static final Color COR_TEXTO         = new Color(220, 205, 190);
    private static final Color COR_TEXTO_FRACO   = new Color(150, 130, 120);
    private static final Font  FONTE_TITULO      = new Font("Serif", Font.BOLD, 54);
    private static final Font  FONTE_SUBTITULO   = new Font("Serif", Font.ITALIC, 18);
    private static final Font  FONTE_STAT_LABEL  = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font  FONTE_STAT_VALOR  = new Font("Serif", Font.BOLD, 16);
    private static final Font  FONTE_BOTAO       = new Font("Serif", Font.BOLD, 15);

    private PainelFundoDramatico painelFundo;

    public TelaGameOver(Personagem jogador, Inimigo inimigo) {
        setTitle("Game Over");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        ConfirmacaoSaida.protegerFechamento(this);
        setResizable(false);

        painelFundo = new PainelFundoDramatico();
        painelFundo.setLayout(new GridBagLayout());

        JPanel conteudo = new JPanel();
        conteudo.setOpaque(false);
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));

        // ── Título ──
        JLabel caveira = new JLabel("☠");
        caveira.setFont(new Font("Serif", Font.PLAIN, 46));
        caveira.setForeground(COR_TITULO);
        caveira.setAlignmentX(CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("VOCÊ MORREU");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TITULO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Derrotado por " + inimigo.getNome());
        subtitulo.setFont(FONTE_SUBTITULO);
        subtitulo.setForeground(COR_SUBTITULO);
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);

        conteudo.add(caveira);
        conteudo.add(Box.createVerticalStrut(6));
        conteudo.add(titulo);
        conteudo.add(Box.createVerticalStrut(8));
        conteudo.add(subtitulo);
        conteudo.add(Box.createVerticalStrut(34));

        // ── Painel de estatísticas ──
        conteudo.add(criarPainelStats(jogador));
        conteudo.add(Box.createVerticalStrut(38));

        // ── Botões ──
        conteudo.add(criarPainelBotoes(jogador));

        GridBagConstraints gbc = new GridBagConstraints();
        painelFundo.add(conteudo, gbc);

        add(painelFundo);
        setVisible(true);

        iniciarFadeIn();
    }

    // ── Painel central com estatísticas da partida ─────────────────────────
    private JPanel criarPainelStats(Personagem jogador) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(0, 0, 0, 120));
        painel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        ));
        painel.setAlignmentX(CENTER_ALIGNMENT);
        painel.setMaximumSize(new Dimension(460, 220));

        adicionarLinha(painel, "Personagem", jogador.getNome() + "  (Nível " + jogador.getNivel() + ")");
        adicionarLinha(painel, "Ouro acumulado", "💰 " + jogador.getOuro());

        Missao missao = jogador.getMissaoAtual();
        if (missao != null) {
            String progresso = missao.getProgresso() + "/" + missao.getObjetivo();
            adicionarLinha(painel, "Missão em andamento", missao.getNome() + "  (" + progresso + ")");
        } else {
            adicionarLinha(painel, "Missão em andamento", "Nenhuma");
        }

        return painel;
    }

    private void adicionarLinha(JPanel painel, String label, String valor) {
        JPanel linha = new JPanel(new BorderLayout());
        linha.setOpaque(false);
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        linha.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(FONTE_STAT_LABEL);
        lblLabel.setForeground(COR_TEXTO_FRACO);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(FONTE_STAT_VALOR);
        lblValor.setForeground(COR_TEXTO);

        linha.add(lblLabel, BorderLayout.WEST);
        linha.add(lblValor, BorderLayout.EAST);
        painel.add(linha);
    }

    // ── Botões de ação ──────────────────────────────────────────────────────
    private JPanel criarPainelBotoes(Personagem jogador) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        painel.setOpaque(false);
        painel.setAlignmentX(CENTER_ALIGNMENT);

        boolean temSave = SaveManager.existeSave();

        JButton btnTentarNovamente = criarBotao("🔄  TENTAR NOVAMENTE", new Color(90, 55, 15), temSave);
        JButton btnMenu            = criarBotao("🏠  MENU PRINCIPAL", new Color(60, 60, 60), true);
        JButton btnSair            = criarBotao("✕  SAIR DO JOGO", new Color(100, 20, 20), true);

        btnTentarNovamente.setToolTipText(temSave ? null : "Nenhum save encontrado");

        btnTentarNovamente.addActionListener(e -> {
            Personagem carregado = SaveManager.carregar();
            dispose();
            if (carregado != null) {
                new TelaPrincipal(carregado);
            } else {
                new MenuPrincipal();
            }
        });

        btnMenu.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        btnSair.addActionListener(e -> {
            if (ConfirmacaoSaida.confirmar(this)) {
                System.exit(0);
            }
        });

        painel.add(btnTentarNovamente);
        painel.add(btnMenu);
        painel.add(btnSair);

        return painel;
    }

    private JButton criarBotao(String texto, Color corFundo, boolean habilitado) {
        JButton btn = new JButton(texto);
        btn.setFont(FONTE_BOTAO);
        btn.setForeground(habilitado ? new Color(235, 220, 205) : new Color(120, 110, 100));
        btn.setBackground(corFundo);
        btn.setFocusPainted(false);
        btn.setEnabled(habilitado);
        btn.setCursor(new Cursor(habilitado ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(corFundo.brighter(), 1),
                BorderFactory.createEmptyBorder(12, 18, 12, 18)
        ));

        if (habilitado) {
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { btn.setBackground(corFundo.brighter()); }
                @Override public void mouseExited(MouseEvent e)  { btn.setBackground(corFundo); }
            });
        }

        return btn;
    }

    // ── Efeito de fade-in dramático ao abrir a tela ────────────────────────
    private void iniciarFadeIn() {
        painelFundo.setAlphaEscurecimento(1f);
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            float alpha = 1f;
            @Override public void actionPerformed(ActionEvent e) {
                alpha -= 0.05f;
                if (alpha <= 0f) {
                    alpha = 0f;
                    timer.stop();
                }
                painelFundo.setAlphaEscurecimento(alpha);
            }
        });
        timer.start();
    }

    // ── Painel de fundo: gradiente vinho/preto com vinheta ─────────────────
    private static class PainelFundoDramatico extends JPanel {
        private float alphaEscurecimento = 0f;

        void setAlphaEscurecimento(float alpha) {
            this.alphaEscurecimento = alpha;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            GradientPaint fundo = new GradientPaint(0, 0, COR_FUNDO_TOPO, 0, h, COR_FUNDO_BASE);
            g2.setPaint(fundo);
            g2.fillRect(0, 0, w, h);

            // Vinheta radial escura nas bordas
            RadialGradientPaint vinheta = new RadialGradientPaint(
                    new Point(w / 2, h / 2), Math.max(w, h) * 0.7f,
                    new float[]{0f, 1f},
                    new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 180)}
            );
            g2.setPaint(vinheta);
            g2.fillRect(0, 0, w, h);

            if (alphaEscurecimento > 0f) {
                g2.setColor(new Color(0, 0, 0, (int) (alphaEscurecimento * 255)));
                g2.fillRect(0, 0, w, h);
            }
        }
    }
}
