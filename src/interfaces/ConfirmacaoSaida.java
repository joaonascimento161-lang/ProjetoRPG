package interfaces;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public final class ConfirmacaoSaida {

    private ConfirmacaoSaida() { }

    public static void protegerFechamento(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (confirmar(frame)) {
                    System.exit(0);
                }
            }
        });
    }

    public static boolean confirmar(Component parent) {
        Window janela = SwingUtilities.getWindowAncestor(parent) != null
                ? SwingUtilities.getWindowAncestor(parent)
                : (parent instanceof Window ? (Window) parent : null);

        JDialog dialogo = new JDialog(janela, "Confirmar Saída", Dialog.ModalityType.APPLICATION_MODAL);
        dialogo.setUndecorated(true);
        dialogo.setSize(380, 200);
        dialogo.setLocationRelativeTo(janela);

        final boolean[] resultado = {false};

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(20, 12, 8));
        painel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(140, 30, 30), 2),
                BorderFactory.createEmptyBorder(24, 30, 20, 30)
        ));

        JLabel titulo = new JLabel("⚠  Sair do jogo?");
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(new Color(230, 200, 160));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mensagem = new JLabel("Qualquer progresso não salvo será perdido.");
        mensagem.setFont(new Font("Serif", Font.ITALIC, 13));
        mensagem.setForeground(new Color(180, 160, 130));
        mensagem.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(10));
        painel.add(mensagem);
        painel.add(Box.createVerticalStrut(24));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        botoes.setOpaque(false);
        botoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCancelar = criarBotao("Cancelar", new Color(60, 60, 60));
        JButton btnSair     = criarBotao("Sair", new Color(120, 25, 25));

        btnCancelar.addActionListener(e -> {
            resultado[0] = false;
            dialogo.dispose();
        });

        btnSair.addActionListener(e -> {
            resultado[0] = true;
            dialogo.dispose();
        });

        botoes.add(btnCancelar);
        botoes.add(btnSair);
        painel.add(botoes);

        dialogo.setContentPane(painel);
        dialogo.setVisible(true);

        return resultado[0];
    }

    private static JButton criarBotao(String texto, Color corFundo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Serif", Font.BOLD, 14));
        btn.setForeground(new Color(235, 220, 205));
        btn.setBackground(corFundo);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(corFundo.brighter(), 1),
                BorderFactory.createEmptyBorder(10, 22, 10, 22)
        ));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(corFundo.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(corFundo); }
        });
        return btn;
    }
}
