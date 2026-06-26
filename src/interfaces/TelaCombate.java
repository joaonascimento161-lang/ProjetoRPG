package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import personagens.Personagem;
import personagens.Adm;
import sistema.XPSystem;
import inimigos.Inimigo;
import itens.Item;
import java.awt.*;
import java.awt.event.*;

public class TelaCombate extends JFrame {

    // ── Paleta centralizada ────────────────────────────────────────────────
    private static final Color COR_FUNDO_PAINEL   = new Color(20, 12, 8, 210);
    private static final Color COR_BORDA          = new Color(120, 70, 20);
    private static final Color COR_TEXTO_TITULO   = new Color(244, 228, 188);
    private static final Color COR_TEXTO_SECUNDARIO = new Color(180, 160, 130);
    private static final Color COR_VIDA           = new Color(46, 180, 90);
    private static final Color COR_VIDA_INIMIGO   = new Color(210, 50, 50);
    private static final Color COR_MANA           = new Color(30, 144, 255);
    private static final Color COR_BTN_ATAQUE     = new Color(160, 30, 30);
    private static final Color COR_BTN_HABILIDADE = new Color(60, 30, 120);
    private static final Color COR_BTN_INVENTARIO = new Color(30, 80, 50);
    private static final Font  FONTE_TITULO       = new Font("Serif", Font.BOLD, 15);
    private static final Font  FONTE_BARRA        = new Font("SansSerif", Font.BOLD, 11);
    private static final Font  FONTE_HISTORICO    = new Font("Monospaced", Font.PLAIN, 13);
    private static final Font  FONTE_BOTAO        = new Font("Serif", Font.BOLD, 14);

    // ── Componentes que precisam ser atualizados ───────────────────────────
    private JProgressBar barraVidaJogador;
    private JProgressBar barraMana;
    private JProgressBar barraVidaInimigo;
    private JLabel lblVidaJogador;
    private JLabel lblVidaInimigo;
    private JTextArea historico;

    public TelaCombate(Personagem jogador, Inimigo inimigo, String caminhoFundo) {
        setTitle("⚔ Combate — " + inimigo.getNome());
        setSize(720, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        PainelComFundo painelPrincipal = new PainelComFundo(caminhoFundo);
        painelPrincipal.setLayout(new BorderLayout(0, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        painelPrincipal.add(criarHud(jogador, inimigo), BorderLayout.NORTH);
        painelPrincipal.add(criarHistorico(), BorderLayout.CENTER);
        painelPrincipal.add(criarControles(jogador, inimigo), BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    // ── HUD: jogador (esquerda) | VS | inimigo (direita) ──────────────────
    private JPanel criarHud(Personagem jogador, Inimigo inimigo) {
        JPanel hud = new JPanel(new GridLayout(1, 3, 12, 0));
        hud.setOpaque(false);

        hud.add(criarCardPersonagem(jogador, true));
        hud.add(criarLabelVS());
        hud.add(criarCardPersonagem(inimigo, false));

        return hud;
    }

    private JPanel criarCardPersonagem(Object entidade, boolean isJogador) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_FUNDO_PAINEL);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));

        String nome;
        int vida, vidaMax, mana = 0, manaMax = 0;

        if (isJogador) {
            Personagem p = (Personagem) entidade;
            nome    = p.getNome().toUpperCase();
            vida    = p.getVida();
            vidaMax = p.getVidaMax();
            mana    = p.getMana();
            manaMax = p.getManaMax();
        } else {
            Inimigo e = (Inimigo) entidade;
            nome    = e.getNome().toUpperCase();
            vida    = e.getVida();
            vidaMax = e.getVidaMax();
        }

        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(FONTE_TITULO);
        lblNome.setForeground(COR_TEXTO_TITULO);
        lblNome.setAlignmentX(CENTER_ALIGNMENT);

        // Barra de vida
        JProgressBar bVida = (isJogador && entidade instanceof Adm)
                ? criarBarraInfinita(new Color(200, 50, 50))
                : criarBarra(vida, vidaMax, isJogador ? COR_VIDA : COR_VIDA_INIMIGO);
        bVida.setString((isJogador && entidade instanceof Adm) ? null : "HP  " + vida + " / " + vidaMax);

        // Label de vida com valor grande
        JLabel lblVida = new JLabel("❤  " + vida + " / " + vidaMax);
        lblVida.setFont(new Font("Serif", Font.PLAIN, 12));
        lblVida.setForeground(COR_TEXTO_SECUNDARIO);
        lblVida.setAlignmentX(CENTER_ALIGNMENT);

        card.add(lblNome);
        card.add(Box.createVerticalStrut(8));
        card.add(bVida);
        card.add(Box.createVerticalStrut(3));
        card.add(lblVida);

        if (isJogador) {
            JProgressBar bMana = (entidade instanceof Adm)
                    ? criarBarraInfinita(new Color(150, 0, 200))
                    : criarBarra(mana, manaMax, COR_MANA);
            bMana.setString((entidade instanceof Adm) ? null : "MP  " + mana + " / " + manaMax);

            JLabel lblMana = new JLabel("✦  " + mana + " / " + manaMax);
            lblMana.setFont(new Font("Serif", Font.PLAIN, 12));
            lblMana.setForeground(COR_TEXTO_SECUNDARIO);
            lblMana.setAlignmentX(CENTER_ALIGNMENT);

            card.add(Box.createVerticalStrut(6));
            card.add(bMana);
            card.add(Box.createVerticalStrut(3));
            card.add(lblMana);

            barraVidaJogador = bVida;
            barraMana        = bMana;
            lblVidaJogador   = lblVida;
        } else {
            barraVidaInimigo = bVida;
            lblVidaInimigo   = lblVida;
        }

        return card;
    }

    private JPanel criarLabelVS() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        JLabel vs = new JLabel("VS");
        vs.setFont(new Font("Serif", Font.BOLD, 28));
        vs.setForeground(new Color(200, 100, 30));
        p.add(vs);
        return p;
    }

    // ── Histórico ──────────────────────────────────────────────────────────
    private JScrollPane criarHistorico() {
        historico = new JTextArea() {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 160));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        historico.setOpaque(false);
        historico.setEditable(false);
        historico.setLineWrap(true);
        historico.setWrapStyleWord(true);
        historico.setFont(FONTE_HISTORICO);
        historico.setForeground(new Color(235, 225, 200));
        historico.setMargin(new Insets(12, 12, 12, 12));
        historico.append("═══════════════════════════════\n");
        historico.append("  ⚔  Combate iniciado!\n");
        historico.append("═══════════════════════════════\n");

        JScrollPane scroll = new JScrollPane(historico);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        return scroll;
    }

    // ── Painel de botões ───────────────────────────────────────────────────
    private JPanel criarControles(Personagem jogador, Inimigo inimigo) {
        JPanel painel = new JPanel(new GridLayout(1, 3, 12, 0));
        painel.setOpaque(false);

        JButton btnAtacar     = criarBotaoAcao("⚔  ATACAR",      COR_BTN_ATAQUE);
        JButton btnHabilidade = criarBotaoAcao("✦  HABILIDADE",   COR_BTN_HABILIDADE);
        JButton btnInventario = criarBotaoAcao("🎒  INVENTÁRIO",  COR_BTN_INVENTARIO);

        painel.add(btnAtacar);
        painel.add(btnHabilidade);
        painel.add(btnInventario);

        // ── Atacar ──
        btnAtacar.addActionListener(e -> {
            jogador.atacar(inimigo);
            log("⚔  " + jogador.getNome() + " atacou " + inimigo.getNome() + "!");

            if (inimigo.estaVivo()) {
                inimigo.atacar(jogador);
                log("🩸 " + inimigo.getNome() + " contra-atacou!");
            }

            atualizarBarras(jogador, inimigo);
            verificarFimDeCombate(jogador, inimigo);
        });

        // ── Habilidade ──
        btnHabilidade.addActionListener(e -> {
            if (jogador.getMana() <= 0) {
                log("✖  Mana insuficiente!");
                return;
            }
            jogador.usarHab(inimigo);
            log("💥 " + jogador.getNome() + " usou sua habilidade especial!");

            if (inimigo.estaVivo()) {
                inimigo.atacar(jogador);
                log("🩸 " + inimigo.getNome() + " contra-atacou!");
            }

            atualizarBarras(jogador, inimigo);
            verificarFimDeCombate(jogador, inimigo);
        });

        // ── Inventário ──
        btnInventario.addActionListener(e -> {
            if (jogador.getInventario().estaVazio()) {
                log("✖  Inventário vazio!");
                return;
            }

            String[] itens = new String[jogador.getInventario().tamanho()];
            for (int i = 0; i < itens.length; i++)
                itens[i] = jogador.getInventario().getItem(i).getNome();

            String escolhido = (String) JOptionPane.showInputDialog(
                    this, "Escolha um item:", "Inventário",
                    JOptionPane.PLAIN_MESSAGE, null, itens, itens[0]
            );

            if (escolhido != null) {
                for (int i = 0; i < jogador.getInventario().tamanho(); i++) {
                    Item item = jogador.getInventario().getItem(i);
                    if (item.getNome().equals(escolhido)) {
                        item.usar(jogador);
                        log("✨ Você usou " + item.getNome() + "!");
                        jogador.getInventario().removerItem(i);
                        break;
                    }
                }
                atualizarBarras(jogador, inimigo);
            }
        });

        return painel;
    }

    // ── Helpers visuais ────────────────────────────────────────────────────
    private JProgressBar criarBarra(int valor, int max, Color cor) {
        JProgressBar barra = new JProgressBar(0, max);
        barra.setValue(valor);
        barra.setStringPainted(true);
        barra.setFont(FONTE_BARRA);
        barra.setForeground(cor);
        barra.setBackground(new Color(30, 20, 15));
        barra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        barra.setBorder(BorderFactory.createLineBorder(new Color(80, 50, 20), 1));
        return barra;
    }

    private JProgressBar criarBarraInfinita(Color cor) {
        JProgressBar barra = new JProgressBar(0, 1) {
            @Override
            public String getString() {
                return "∞";
            }
        };
        barra.setValue(1);
        barra.setStringPainted(true);
        barra.setFont(new Font("Serif", Font.BOLD, 16));
        barra.setForeground(cor);
        barra.setBackground(new Color(30, 20, 15));
        barra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        barra.setBorder(BorderFactory.createLineBorder(new Color(80, 50, 20), 1));
        return barra;
    }

    private JButton criarBotaoAcao(String texto, Color corFundo) {
        JButton btn = new JButton(texto);
        btn.setFont(FONTE_BOTAO);
        btn.setBackground(corFundo);
        btn.setForeground(COR_TEXTO_TITULO);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(corFundo.brighter(), 1),
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btn.setBackground(corFundo.brighter());
            }
            @Override public void mouseExited(MouseEvent e) {
                btn.setBackground(corFundo);
            }
        });

        return btn;
    }

    // ── Lógica ────────────────────────────────────────────────────────────
    private void atualizarBarras(Personagem jogador, Inimigo inimigo) {
        barraVidaJogador.setValue(jogador.getVida());
        barraVidaJogador.setString("HP  " + jogador.getVida() + " / " + jogador.getVidaMax());
        lblVidaJogador.setText("❤  " + jogador.getVida() + " / " + jogador.getVidaMax());

        barraMana.setValue(jogador.getMana());
        barraMana.setString("MP  " + jogador.getMana() + " / " + jogador.getManaMax());

        barraVidaInimigo.setValue(inimigo.getVida());
        barraVidaInimigo.setString("HP  " + inimigo.getVida() + " / " + inimigo.getVidaMax());
        lblVidaInimigo.setText("❤  " + inimigo.getVida() + " / " + inimigo.getVidaMax());

        // Barra de vida vermelha quando crítico (< 25%)
        double pctJogador = (double) jogador.getVida() / jogador.getVidaMax();
        barraVidaJogador.setForeground(pctJogador < 0.25 ? new Color(220, 50, 50) : COR_VIDA);
    }

    private void verificarFimDeCombate(Personagem jogador, Inimigo inimigo) {
        if (!inimigo.estaVivo()) {
            XPSystem.ganharXP(jogador, inimigo.getRecompensaXP());
            jogador.adicionarOuro(inimigo.getRecompensaOuro());

            Item drop = inimigo.gerarDrop();
            if (drop != null) {
                jogador.getInventario().adicionarItem(drop);
            }

            mostrarTelaResultado(true, jogador, inimigo, drop);

        } else if (!jogador.estaVivo()) {
            mostrarTelaResultado(false, jogador, inimigo, null);
        }
    }

    // ── Tela de resultado customizada (substitui JOptionPane) ──────────────
    private void mostrarTelaResultado(boolean vitoria, Personagem jogador,
                                      Inimigo inimigo, Item drop) {
        JDialog dialogo = new JDialog(this, true);
        dialogo.setUndecorated(true);
        dialogo.setSize(380, 260);
        dialogo.setLocationRelativeTo(this);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(20, 12, 8));
        painel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(vitoria ? new Color(180, 140, 30) : new Color(140, 30, 30), 2),
                BorderFactory.createEmptyBorder(28, 32, 24, 32)
        ));

        // Título
        JLabel titulo = new JLabel(vitoria ? "⚔  VITÓRIA!" : "☠  DERROTA");
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(vitoria ? new Color(240, 200, 60) : new Color(220, 80, 80));
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        // Separador
        JSeparator sep = new JSeparator();
        sep.setForeground(COR_BORDA);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(16));
        painel.add(sep);
        painel.add(Box.createVerticalStrut(14));

        if (vitoria) {
            adicionarLinhaResultado(painel, "XP ganho",   "+" + inimigo.getRecompensaXP(),  new Color(100, 220, 120));
            adicionarLinhaResultado(painel, "Ouro ganho", "+" + inimigo.getRecompensaOuro(), new Color(220, 190, 60));
            if (drop != null) {
                adicionarLinhaResultado(painel, "Item obtido", drop.getNome(), new Color(160, 120, 240));
            }
        } else {
            JLabel msg = new JLabel("Você foi derrotado em batalha...");
            msg.setFont(new Font("Serif", Font.ITALIC, 14));
            msg.setForeground(COR_TEXTO_SECUNDARIO);
            msg.setAlignmentX(CENTER_ALIGNMENT);
            painel.add(msg);
        }

        painel.add(Box.createVerticalStrut(20));

        // Botão de continuar
        JButton btnContinuar = new JButton(vitoria ? "Continuar" : "Voltar ao Menu");
        btnContinuar.setFont(FONTE_BOTAO);
        btnContinuar.setBackground(vitoria ? new Color(80, 60, 20) : new Color(80, 20, 20));
        btnContinuar.setForeground(COR_TEXTO_TITULO);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuar.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        btnContinuar.setAlignmentX(CENTER_ALIGNMENT);
        btnContinuar.addActionListener(e -> {
            dialogo.dispose();
            dispose();
            if (vitoria) {
                new TelaPrincipal(jogador);
            } else {
                new MenuPrincipal();
            }
        });

        painel.add(btnContinuar);
        dialogo.setContentPane(painel);
        dialogo.setVisible(true);
    }

    private void adicionarLinhaResultado(JPanel painel, String label,
                                         String valor, Color corValor) {
        JPanel linha = new JPanel(new BorderLayout());
        linha.setOpaque(false);
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        lblLabel.setForeground(COR_TEXTO_SECUNDARIO);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Serif", Font.BOLD, 14));
        lblValor.setForeground(corValor);

        linha.add(lblLabel, BorderLayout.WEST);
        linha.add(lblValor, BorderLayout.EAST);
        painel.add(linha);
        painel.add(Box.createVerticalStrut(6));
    }

    private void log(String mensagem) {
        historico.append(mensagem + "\n");
        historico.setCaretPosition(historico.getDocument().getLength());
    }
}