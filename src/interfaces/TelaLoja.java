package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import itens.*;
import personagens.Personagem;
import java.awt.*;
import java.awt.event.*;

public class TelaLoja extends JFrame {

    private static final Color COR_FUNDO_CARD  = new Color(30, 20, 12, 220);
    private static final Color COR_BORDA       = new Color(100, 65, 30);
    private static final Color COR_TITULO      = new Color(244, 228, 188);
    private static final Color COR_SECUNDARIO  = new Color(170, 145, 110);
    private static final Color COR_OURO        = new Color(220, 185, 50);
    private static final Color COR_BTN_COMPRAR = new Color(70, 110, 50);
    private static final Color COR_SEM_OURO    = new Color(140, 30, 30);
    private static final Font  FONTE_TITULO    = new Font("Serif", Font.BOLD, 18);
    private static final Font  FONTE_ITEM      = new Font("Serif", Font.BOLD, 14);
    private static final Font  FONTE_DESC      = new Font("Georgia", Font.ITALIC, 12);
    private static final Font  FONTE_PRECO     = new Font("Serif", Font.BOLD, 15);
    private static final Font  FONTE_BOTAO     = new Font("Georgia", Font.BOLD, 12);

    private JLabel lblOuro;

    public TelaLoja(Personagem jogador) {
        setTitle("Loja");
        setSize(560, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PainelComFundo fundo = new PainelComFundo("src/image/FundoLoja.jpg");
        fundo.setLayout(new BorderLayout(0, 12));
        fundo.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        fundo.add(criarHeader(jogador), BorderLayout.NORTH);
        fundo.add(criarGridItens(jogador), BorderLayout.CENTER);
        fundo.add(criarRodape(), BorderLayout.SOUTH);

        setContentPane(fundo);
        setVisible(true);
    }

    // ── Cabeçalho ─────────────────────────────────────────────────────────
    private JPanel criarHeader(Personagem jogador) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(28, 18, 10, 210));
        header.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(12, 18, 12, 18)
        ));

        JLabel titulo = new JLabel("🛒  LOJA DO AVENTUREIRO");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TITULO);

        lblOuro = new JLabel("💰  " + jogador.getOuro() + " ouro disponível");
        lblOuro.setFont(new Font("Serif", Font.BOLD, 14));
        lblOuro.setForeground(COR_OURO);

        header.add(titulo, BorderLayout.WEST);
        header.add(lblOuro, BorderLayout.EAST);
        return header;
    }

    // ── Grid de produtos ──────────────────────────────────────────────────
    private JPanel criarGridItens(Personagem jogador) {
        JPanel grid = new JPanel(new GridLayout(2, 2, 12, 12));
        grid.setOpaque(false);

        ProdutoLoja[] produtos = {
                new ProdutoLoja("Poção de Vida",     "🧪", "Restaura HP em combate",           30,  () -> new PocaoVida()),
                new ProdutoLoja("Poção de Mana",     "✦",  "Restaura MP para habilidades",      25,  () -> new PocaoMana()),
                new ProdutoLoja("Espada de Ferro",   "⚔",  "+5 de dano nos ataques",            100, () -> new Arma("Espada de Ferro", 5)),
                new ProdutoLoja("Armadura de Couro", "🛡",  "+20 de vida máxima",               150, () -> new Armadura("Armadura de Couro", 20)),
        };

        for (ProdutoLoja p : produtos) {
            grid.add(criarCardProduto(p, jogador));
        }

        return grid;
    }

    private JPanel criarCardProduto(ProdutoLoja produto, Personagem jogador) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_FUNDO_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));

        JLabel icone = new JLabel(produto.icone);
        icone.setFont(new Font("Serif", Font.PLAIN, 30));
        icone.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nome = new JLabel(produto.nome);
        nome.setFont(FONTE_ITEM);
        nome.setForeground(COR_TITULO);
        nome.setAlignmentX(CENTER_ALIGNMENT);

        JLabel desc = new JLabel(produto.descricao);
        desc.setFont(FONTE_DESC);
        desc.setForeground(COR_SECUNDARIO);
        desc.setAlignmentX(CENTER_ALIGNMENT);

        JLabel preco = new JLabel("💰 " + produto.preco + " ouro");
        preco.setFont(FONTE_PRECO);
        preco.setForeground(COR_OURO);
        preco.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setFont(FONTE_BOTAO);
        btnComprar.setBackground(COR_BTN_COMPRAR);
        btnComprar.setForeground(COR_TITULO);
        btnComprar.setFocusPainted(false);
        btnComprar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComprar.setAlignmentX(CENTER_ALIGNMENT);
        btnComprar.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BTN_COMPRAR.brighter(), 1),
                BorderFactory.createEmptyBorder(7, 20, 7, 20)
        ));
        btnComprar.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btnComprar.setBackground(COR_BTN_COMPRAR.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btnComprar.setBackground(COR_BTN_COMPRAR); }
        });

        btnComprar.addActionListener(e -> {
            if (jogador.gastarOuro(produto.preco)) {
                jogador.getInventario().adicionarItem(produto.fabricar.get());
                lblOuro.setText("💰  " + jogador.getOuro() + " ouro disponível");
                mostrarFeedback(card, "✔  Comprado!", new Color(60, 140, 60));
            } else {
                mostrarFeedback(card, "✖  Ouro insuficiente!", COR_SEM_OURO);
            }
        });

        card.add(icone);
        card.add(Box.createVerticalStrut(6));
        card.add(nome);
        card.add(Box.createVerticalStrut(4));
        card.add(desc);
        card.add(Box.createVerticalStrut(10));
        card.add(preco);
        card.add(Box.createVerticalStrut(10));
        card.add(btnComprar);

        return card;
    }

    // ── Feedback visual inline (sem JOptionPane) ──────────────────────────
    private void mostrarFeedback(JPanel card, String msg, Color cor) {
        JLabel feedback = new JLabel(msg);
        feedback.setFont(new Font("Serif", Font.BOLD, 12));
        feedback.setForeground(cor);
        feedback.setAlignmentX(CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(6));
        card.add(feedback);
        card.revalidate();
        card.repaint();

        // Remove a mensagem após 2 segundos
        Timer timer = new Timer(2000, ev -> {
            card.remove(feedback);
            card.revalidate();
            card.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private JPanel criarRodape() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setOpaque(false);

        JButton btnFechar = new JButton("Sair da Loja");
        btnFechar.setFont(FONTE_BOTAO);
        btnFechar.setBackground(new Color(100, 40, 20));
        btnFechar.setForeground(COR_TITULO);
        btnFechar.setFocusPainted(false);
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFechar.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(140, 60, 30), 1),
                BorderFactory.createEmptyBorder(9, 24, 9, 24)
        ));
        btnFechar.addActionListener(e -> dispose());
        p.add(btnFechar);
        return p;
    }

    // ── Classe interna ────────────────────────────────────────────────────
    private static class ProdutoLoja {
        final String nome, icone, descricao;
        final int preco;
        final java.util.function.Supplier<Item> fabricar;

        ProdutoLoja(String nome, String icone, String descricao, int preco,
                    java.util.function.Supplier<Item> fabricar) {
            this.nome=nome; this.icone=icone; this.descricao=descricao;
            this.preco=preco; this.fabricar=fabricar;
        }
    }
}