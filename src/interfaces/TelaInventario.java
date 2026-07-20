package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import itens.Arma;
import itens.Armadura;
import itens.Item;
import personagens.Personagem;
import java.awt.*;
import java.awt.event.*;

public class TelaInventario extends JFrame {

    private static final Color COR_BORDA       = new Color(100, 65, 30);
    private static final Color COR_TITULO      = new Color(244, 228, 188);
    private static final Color COR_SECUNDARIO  = new Color(170, 145, 110);
    private static final Color COR_SELECAO     = new Color(120, 80, 30);
    private static final Font  FONTE_TITULO    = new Font("Serif", Font.BOLD, 18);
    private static final Font  FONTE_ITEM      = new Font("Serif", Font.PLAIN, 13);
    private static final Font  FONTE_BOTAO     = new Font("Georgia", Font.BOLD, 12);

    private JLabel lblStatus;
    private JTextArea areaDescricao;

    public TelaInventario(Personagem jogador) {
        setTitle("Inventário — " + jogador.getNome());
        setSize(620, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PainelComFundo fundo = new PainelComFundo("src/image/FundoInventario.png");
        fundo.setLayout(new BorderLayout(10, 10));
        fundo.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        fundo.add(criarHeader(jogador), BorderLayout.NORTH);
        fundo.add(criarPainelCentral(jogador), BorderLayout.CENTER);
        fundo.add(criarPainelSul(), BorderLayout.SOUTH);

        setContentPane(fundo);
        setVisible(true);
    }

    private JPanel criarHeader(Personagem jogador) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 20, 12, 200));
        header.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(10, 16, 10, 16)
        ));

        JLabel titulo = new JLabel("🎒  INVENTÁRIO");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TITULO);

        lblStatus = new JLabel(textoStatus(jogador));
        lblStatus.setFont(new Font("Georgia", Font.PLAIN, 12));
        lblStatus.setForeground(COR_SECUNDARIO);

        header.add(titulo, BorderLayout.WEST);
        header.add(lblStatus, BorderLayout.EAST);
        return header;
    }

    private JPanel criarPainelCentral(Personagem jogador) {
        JPanel painel = new JPanel(new BorderLayout(10, 0));
        painel.setOpaque(false);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (int i = 0; i < jogador.getInventario().tamanho(); i++) {
            Item item = jogador.getInventario().getItem(i);
            modelo.addElement(iconeItem(item) + "  " + item.getNome());
        }

        JList<String> lista = new JList<>(modelo);
        lista.setFont(FONTE_ITEM);
        lista.setBackground(new Color(30, 20, 12, 200));
        lista.setForeground(COR_TITULO);
        lista.setSelectionBackground(COR_SELECAO);
        lista.setSelectionForeground(new Color(255, 230, 150));
        lista.setFixedCellHeight(36);
        lista.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(new LineBorder(COR_BORDA, 1));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setPreferredSize(new Dimension(300, 0));

        JPanel direito = new JPanel(new BorderLayout(0, 10));
        direito.setOpaque(false);

        areaDescricao = new JTextArea("Selecione um item para ver os detalhes.");
        areaDescricao.setEditable(false);
        areaDescricao.setLineWrap(true);
        areaDescricao.setWrapStyleWord(true);
        areaDescricao.setFont(new Font("Georgia", Font.ITALIC, 13));
        areaDescricao.setBackground(new Color(30, 20, 12, 200));
        areaDescricao.setForeground(COR_SECUNDARIO);
        areaDescricao.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        lista.addListSelectionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx >= 0) {
                Item item = jogador.getInventario().getItem(idx);
                areaDescricao.setText(descricaoItem(item));
            }
        });

        JPanel botoes = new JPanel(new GridLayout(3, 1, 0, 8));
        botoes.setOpaque(false);

        JButton btnUsar    = criarBotao("Usar",    new Color(50, 100, 50));
        JButton btnEquipar = criarBotao("Equipar", new Color(60, 50, 120));
        JButton btnFechar  = criarBotao("Fechar",  new Color(100, 40, 20));

        botoes.add(btnUsar);
        botoes.add(btnEquipar);
        botoes.add(btnFechar);

        direito.add(areaDescricao, BorderLayout.CENTER);
        direito.add(botoes, BorderLayout.SOUTH);

        painel.add(scroll, BorderLayout.CENTER);
        painel.add(direito, BorderLayout.EAST);

        btnUsar.addActionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx == -1) { aviso("Selecione um item."); return; }
            Item item = jogador.getInventario().getItem(idx);
            if (item instanceof Arma || item instanceof Armadura) {
                aviso("Use o botão Equipar para este item."); return;
            }
            item.usar(jogador);
            jogador.getInventario().removerItem(idx);
            modelo.remove(idx);
            atualizarStatus(jogador);
            areaDescricao.setText("✔  " + item.getNome() + " utilizado!");
        });

        btnEquipar.addActionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx == -1) { aviso("Selecione um item."); return; }
            Item item = jogador.getInventario().getItem(idx);
            if (item instanceof Arma) {
                jogador.equiparArma((Arma) item);
                areaDescricao.setText("⚔  " + item.getNome() + " equipada!");
            } else if (item instanceof Armadura) {
                jogador.equiparArmadura((Armadura) item);
                areaDescricao.setText("🛡  " + item.getNome() + " equipada!");
            } else {
                aviso("Este item não pode ser equipado."); return;
            }
            atualizarStatus(jogador);
        });

        btnFechar.addActionListener(e -> dispose());

        return painel;
    }

    private JPanel criarPainelSul() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        if (lblStatus != null) {
            JLabel dica = new JLabel("Dica: equipamentos aumentam seus atributos permanentemente.");
            dica.setFont(new Font("Georgia", Font.ITALIC, 11));
            dica.setForeground(new Color(130, 105, 70));
            p.add(dica);
        }
        return p;
    }

    private String iconeItem(Item item) {
        if (item instanceof Arma)     return "⚔";
        if (item instanceof Armadura) return "🛡";
        return "🧪";
    }

    private String descricaoItem(Item item) {
        StringBuilder sb = new StringBuilder();
        sb.append(iconeItem(item)).append("  ").append(item.getNome()).append("\n\n");
        sb.append("Tipo: ").append(item.getClass().getSimpleName()).append("\n");
        if (item instanceof Arma) {
            sb.append("Bônus de dano: +").append(((Arma) item).getBonusDano());
        } else if (item instanceof Armadura) {
            sb.append("Bônus de vida: +").append(((Armadura) item).getBonusVida());
        } else {
            sb.append("Item consumível. Use em combate ou no inventário.");
        }
        return sb.toString();
    }

    private String textoStatus(Personagem jogador) {
        return "HP: " + jogador.getVida() + "/" + jogador.getVidaMax()
                + "   MP: " + jogador.getMana() + "/" + jogador.getManaMax()
                + "   💰 " + jogador.getOuro();
    }

    private void atualizarStatus(Personagem jogador) {
        lblStatus.setText(textoStatus(jogador));
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(FONTE_BOTAO);
        btn.setBackground(cor);
        btn.setForeground(COR_TITULO);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(cor.brighter(), 1),
                BorderFactory.createEmptyBorder(8, 14, 8, 14)
        ));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(cor.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(cor); }
        });
        return btn;
    }

    private void aviso(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Inventário", JOptionPane.PLAIN_MESSAGE);
    }
}