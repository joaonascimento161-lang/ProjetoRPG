package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import personagens.Personagem;
import java.awt.*;

public class TelaStatus extends JFrame {

    private static final Color COR_FUNDO_CARD = new Color(20, 12, 6, 170);   // escuro mas transparente
    private static final Color COR_TITULO     = new Color(255, 245, 210);    // branco quente
    private static final Color COR_SECAO      = new Color(255, 210, 80);     // dourado vivo
    private static final Color COR_LABEL      = new Color(220, 200, 160);    // bege claro
    private static final Color COR_VALOR      = new Color(255, 250, 230);    // quase branco
    private static final Color COR_OURO       = new Color(255, 200, 40);
    private static final Color COR_BORDA      = new Color(160, 100, 30);
    private static final Color COR_VIDA       = new Color(180, 40, 40);
    private static final Color COR_MANA       = new Color(40, 100, 200);
    private static final Font  FONTE_TITULO   = new Font("Serif", Font.BOLD, 20);
    private static final Font  FONTE_SECAO    = new Font("Serif", Font.BOLD, 13);
    private static final Font  FONTE_LABEL    = new Font("Georgia", Font.BOLD, 13);
    private static final Font  FONTE_VALOR    = new Font("Georgia", Font.PLAIN, 13);

    public TelaStatus(Personagem jogador) {
        setTitle("Status — " + jogador.getNome());
        setSize(680, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PainelComFundo fundo = new PainelComFundo("src/image/madeira.jpg");
        fundo.setLayout(new BorderLayout(0, 12));
        fundo.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        fundo.add(criarHeader(jogador), BorderLayout.NORTH);
        fundo.add(criarCorpo(jogador), BorderLayout.CENTER);
        fundo.add(criarRodape(), BorderLayout.SOUTH);

        setContentPane(fundo);
        setVisible(true);
    }

    private JPanel criarHeader(Personagem jogador) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COR_FUNDO_CARD);
        header.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(12, 18, 12, 18)
        ));

        JLabel titulo = new JLabel("📊  STATUS DO PERSONAGEM");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TITULO);

        JLabel classe = new JLabel(jogador.getNome().toUpperCase() + "  •  NÍVEL " + jogador.getNivel());
        classe.setFont(new Font("Serif", Font.PLAIN, 13));
        classe.setForeground(COR_LABEL);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(classe);

        header.add(textos, BorderLayout.WEST);
        return header;
    }

    private JPanel criarCorpo(Personagem jogador) {
        JPanel corpo = new JPanel(new GridLayout(1, 2, 14, 0));
        corpo.setOpaque(false);
        corpo.add(criarCardAtributos(jogador));
        corpo.add(criarCardEquipamentos(jogador));
        return corpo;
    }

    private JPanel criarCardAtributos(Personagem jogador) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_FUNDO_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));

        adicionarSecao(card, "⚔  ATRIBUTOS");
        adicionarLinha(card, "Classe",    jogador.getNome());
        adicionarLinha(card, "Nível",     String.valueOf(jogador.getNivel()));
        adicionarLinha(card, "XP",        String.valueOf(jogador.getXp()));
        adicionarLinha(card, "Dano base", String.valueOf(jogador.getDano()));

        JPanel linhaOuro = new JPanel(new BorderLayout());
        linhaOuro.setOpaque(false);
        linhaOuro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        JLabel lblOuro = new JLabel("Ouro");
        lblOuro.setFont(FONTE_LABEL);
        lblOuro.setForeground(COR_LABEL);
        JLabel valOuro = new JLabel(jogador.getOuro() + " 💰");
        valOuro.setFont(new Font("Georgia", Font.BOLD, 13));
        valOuro.setForeground(COR_OURO);
        linhaOuro.add(lblOuro, BorderLayout.WEST);
        linhaOuro.add(valOuro, BorderLayout.EAST);
        card.add(linhaOuro);
        card.add(Box.createVerticalStrut(16));

        adicionarSecao(card, "❤  VITALIDADE");
        card.add(criarBarraLabel("Vida", jogador.getVida(), jogador.getVidaMax(), COR_VIDA));
        card.add(Box.createVerticalStrut(8));
        card.add(criarBarraLabel("Mana", jogador.getMana(), jogador.getManaMax(), COR_MANA));
        card.add(Box.createVerticalGlue());

        return card;
    }

    private JPanel criarCardEquipamentos(Personagem jogador) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_FUNDO_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));

        adicionarSecao(card, "🛡  EQUIPAMENTOS");

        String nomeArma     = (jogador.getArmaEquipada()     != null) ? jogador.getArmaEquipada().getNome()     : "Nenhuma";
        String nomeArmadura = (jogador.getArmaduraEquipada() != null) ? jogador.getArmaduraEquipada().getNome() : "Nenhuma";

        adicionarEquipamento(card, "⚔  Arma equipada",     nomeArma,     jogador.getArmaEquipada()     != null);
        adicionarEquipamento(card, "🛡  Armadura equipada", nomeArmadura, jogador.getArmaduraEquipada() != null);

        card.add(Box.createVerticalStrut(16));
        adicionarSecao(card, "📜  RESUMO");

        JTextArea resumo = new JTextArea(resumoPersonagem(jogador));
        resumo.setAlignmentX(CENTER_ALIGNMENT);
        resumo.setEditable(false);
        resumo.setLineWrap(true);
        resumo.setWrapStyleWord(true);
        resumo.setFont(new Font("Georgia", Font.ITALIC, 12));
        resumo.setForeground(COR_VALOR);
        resumo.setBackground(new Color(15, 10, 5, 150));
        resumo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        resumo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        card.add(resumo);
        card.add(Box.createVerticalGlue());

        return card;
    }

    private JPanel criarRodape() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setOpaque(false);
        JButton btn = new JButton("Fechar");
        btn.setFont(new Font("Georgia", Font.BOLD, 12));
        btn.setBackground(new Color(80, 50, 20));
        btn.setForeground(COR_TITULO);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(8, 28, 8, 28)
        ));
        btn.addActionListener(e -> dispose());
        p.add(btn);
        return p;
    }

    private void adicionarSecao(JPanel painel, String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_SECAO);
        label.setForeground(COR_SECAO);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        painel.add(label);

        JSeparator sep = new JSeparator();
        sep.setForeground(COR_BORDA);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        painel.add(sep);
        painel.add(Box.createVerticalStrut(8));
    }

    private void adicionarLinha(JPanel painel, String label, String valor) {
        JPanel linha = new JPanel(new BorderLayout());
        linha.setOpaque(false);
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(FONTE_LABEL);
        lblLabel.setForeground(COR_LABEL);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(FONTE_VALOR);
        lblValor.setForeground(COR_VALOR);

        linha.add(lblLabel, BorderLayout.WEST);
        linha.add(lblValor, BorderLayout.EAST);
        painel.add(linha);
        painel.add(Box.createVerticalStrut(4));
    }

    private void adicionarEquipamento(JPanel painel, String label, String valor, boolean equipado) {
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBackground(new Color(15, 10, 5, 140));
        bloco.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(equipado ? COR_BORDA : new Color(100, 70, 25), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        bloco.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Georgia", Font.BOLD, 11));
        lblLabel.setForeground(COR_LABEL);
        lblLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Georgia", Font.PLAIN, 13));
        lblValor.setForeground(equipado ? COR_TITULO : new Color(180, 150, 100));
        lblValor.setAlignmentX(CENTER_ALIGNMENT);

        bloco.add(lblLabel);
        bloco.add(Box.createVerticalStrut(3));
        bloco.add(lblValor);

        painel.add(bloco);
        painel.add(Box.createVerticalStrut(8));
    }

    private JPanel criarBarraLabel(String nome, int valor, int max, Color cor) {
        JPanel p = new JPanel(new BorderLayout(0, 3));
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));

        JLabel lbl = new JLabel(nome);
        lbl.setFont(new Font("Georgia", Font.BOLD, 11));
        lbl.setForeground(COR_LABEL);

        JProgressBar barra = new JProgressBar(0, max);
        barra.setValue(valor);
        barra.setString(valor + " / " + max);
        barra.setStringPainted(true);
        barra.setFont(new Font("SansSerif", Font.BOLD, 11));
        barra.setForeground(cor);
        barra.setBackground(new Color(40, 25, 10));

        p.add(lbl, BorderLayout.NORTH);
        p.add(barra, BorderLayout.CENTER);
        return p;
    }

    private String resumoPersonagem(Personagem jogador) {
        int pct = (jogador.getVidaMax() > 0) ? (jogador.getVida() * 100 / jogador.getVidaMax()) : 0;
        if (pct > 75) return jogador.getNome() + " está em ótima forma, pronto para batalha.";
        if (pct > 40) return jogador.getNome() + " tem ferimentos leves. Use uma poção antes do próximo combate.";
        return "⚠  " + jogador.getNome() + " está em estado crítico! Use poções antes de combater.";
    }
}