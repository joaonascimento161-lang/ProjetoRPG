package uinaousar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import itens.*;
import personagens.Personagem;
import java.awt.*;

public class TelaLoja extends JFrame {

    public TelaLoja(Personagem jogador) {
        setTitle("Loja");
        setSize(500, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PainelComFundo painelPrincipal = new PainelComFundo("src/image/FundoLoja.jpg");
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblOuro = new JLabel("Ouro Disponível: " + jogador.getOuro());
        lblOuro.setHorizontalAlignment(SwingConstants.CENTER);
        lblOuro.setFont(new Font("Georgia", Font.BOLD, 18));
        lblOuro.setForeground(new Color(230, 190, 100));
        lblOuro.setBorder(new EmptyBorder(0, 0, 15, 0));
        painelPrincipal.add(lblOuro, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 1, 0, 15));
        painelBotoes.setOpaque(false);

        JButton btnPocaoVida = new JButton("Poção de Vida (30 ouro)");
        JButton btnPocaoMana = new JButton("Poção de Mana (25 ouro)");
        JButton btnEspada = new JButton("Espada de Ferro (100 ouro)");
        JButton btnArmadura = new JButton("Armadura de Couro (150 ouro)");

        Font fonteBotao = new Font("Georgia", Font.BOLD, 14);
        Color corBotaoFundo = new Color(85, 60, 40);
        Color corTextoBotao = Color.WHITE;

        for (JButton btn : new JButton[]{btnPocaoVida, btnPocaoMana, btnEspada, btnArmadura}) {
            btn.setFont(fonteBotao);
            btn.setBackground(corBotaoFundo);
            btn.setForeground(corTextoBotao);
            btn.setFocusPainted(false);
            painelBotoes.add(btn);
        }

        btnPocaoVida.addActionListener(e -> {
            if (jogador.gastarOuro(30)) {
                jogador.getInventario().adicionarItem(new PocaoVida());
                lblOuro.setText("Ouro Disponível: " + jogador.getOuro());
                JOptionPane.showMessageDialog(this, "Poção de vida comprada");
            } else {
                JOptionPane.showMessageDialog(this, "Ouro insuficiente!");
            }
        });

        btnPocaoMana.addActionListener(e -> {
            if (jogador.gastarOuro(25)) {
                jogador.getInventario().adicionarItem(new PocaoMana());
                lblOuro.setText("Ouro Disponível: " + jogador.getOuro());
                JOptionPane.showMessageDialog(this, "Poção de mana comprada!");
            } else {
                JOptionPane.showMessageDialog(this, "Ouro insuficiente!");
            }
        });

        btnEspada.addActionListener(e -> {
            if (jogador.gastarOuro(100)) {
                jogador.getInventario().adicionarItem(new Arma("Espada de Ferro", 5));
                lblOuro.setText("Ouro Disponível: " + jogador.getOuro());
                JOptionPane.showMessageDialog(this, "Espada comprada!");
            } else {
                JOptionPane.showMessageDialog(this, "Ouro insuficiente!");
            }
        });

        btnArmadura.addActionListener(e -> {
            if (jogador.gastarOuro(150)) {
                jogador.getInventario().adicionarItem(new Armadura("Armadura de Couro", 20));
                lblOuro.setText("Ouro Disponível: " + jogador.getOuro());
                JOptionPane.showMessageDialog(this, "Armadura comprada!");
            } else {
                JOptionPane.showMessageDialog(this, "Ouro insuficiente!");
            }
        });

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Sair da Loja");
        btnFechar.setFont(new Font("Georgia", Font.BOLD, 12));
        btnFechar.setBackground(new Color(120, 40, 40));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.addActionListener(e -> dispose());

        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSul.setOpaque(false);
        painelSul.setBorder(new EmptyBorder(15, 0, 0, 0));
        painelSul.add(btnFechar);
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
        setVisible(true);
    }
}