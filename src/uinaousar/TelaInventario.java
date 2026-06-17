package uinaousar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import itens.Arma;
import itens.Armadura;
import itens.Item;
import personagens.Personagem;
import java.awt.*;

public class TelaInventario extends JFrame {

    private JLabel lblStatus;

    public TelaInventario(Personagem jogador) {
        setTitle("Inventário");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PainelComFundo painelPrincipal = new PainelComFundo("src/image/FundoInventario.png");
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblStatus = new JLabel(
            "HP: " + jogador.getVida() + "/" + jogador.getVidaMax() +
            " | MP: " + jogador.getMana() + "/" + jogador.getManaMax() +
            " | Ouro: " + jogador.getOuro()
        );
        lblStatus.setFont(new Font("Georgia", Font.BOLD, 14));
        lblStatus.setForeground(new Color(220, 200, 160));
        painelPrincipal.add(lblStatus, BorderLayout.NORTH);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (int i = 0; i < jogador.getInventario().tamanho(); i++) {
            Item item = jogador.getInventario().getItem(i);
            String icone = "📦 ";

            if (item instanceof Arma) {
                icone = "⚔ ";
            } else if (item instanceof Armadura) {
                icone = "🛡 ";
            }
            modelo.addElement(icone + item.getNome());
        }

        JList<String> listaItens = new JList<>(modelo);
        listaItens.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        listaItens.setBackground(new Color(40, 30, 20, 180)); 
        listaItens.setForeground(Color.WHITE);
        listaItens.setSelectionBackground(new Color(120, 90, 60));
        listaItens.setSelectionForeground(Color.YELLOW);

        JScrollPane scroll = new JScrollPane(listaItens);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        JTextArea descricao = new JTextArea(3, 20);
        descricao.setEditable(false);
        descricao.setWrapStyleWord(true);
        descricao.setLineWrap(true);
        descricao.setFont(new Font("Georgia", Font.ITALIC, 12));
        descricao.setBackground(new Color(50, 40, 30, 200));
        descricao.setForeground(new Color(200, 190, 180));
        descricao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 70, 50)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        listaItens.addListSelectionListener(e -> {
            int indice = listaItens.getSelectedIndex();
            if (indice >= 0) {
                Item item = jogador.getInventario().getItem(indice);
                descricao.setText("Item: " + item.getNome() + "\nTipo: " + item.getClass().getSimpleName());
            }
        });
        painelPrincipal.add(descricao, BorderLayout.SOUTH);

        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 0, 10));
        painelBotoes.setOpaque(false);

        JButton btnUsar = new JButton("Usar");
        JButton btnEquipar = new JButton("Equipar");
        JButton btnFechar = new JButton("Fechar");

        Dimension tamanhoBotao = new Dimension(100, 35);
        for (JButton btn : new JButton[]{btnUsar, btnEquipar, btnFechar}) {
            btn.setPreferredSize(tamanhoBotao);
            btn.setBackground(new Color(90, 65, 40));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Georgia", Font.BOLD, 12));
            painelBotoes.add(btn);
        }

        JPanel containerBotoes = new JPanel(new BorderLayout());
        containerBotoes.setOpaque(false);
        containerBotoes.setBorder(new EmptyBorder(0, 10, 0, 0));
        containerBotoes.add(painelBotoes, BorderLayout.NORTH);
        
        painelPrincipal.add(containerBotoes, BorderLayout.EAST);

        setContentPane(painelPrincipal);

        btnUsar.addActionListener(e -> {
            int indice = listaItens.getSelectedIndex();
            if (indice == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um item");
                return;
            }

            Item item = jogador.getInventario().getItem(indice);
            if (item instanceof Arma || item instanceof Armadura) {
                JOptionPane.showMessageDialog(this, "Use o botão Equipar");
                return;
            }

            item.usar(jogador);
            jogador.getInventario().removerItem(indice);
            modelo.remove(indice);
            atualizarStatus(jogador);
            JOptionPane.showMessageDialog(this, item.getNome() + " usado!");
        });

        btnEquipar.addActionListener(e -> {
            int indice = listaItens.getSelectedIndex();
            if (indice == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um item");
                return;
            }

            Item item = jogador.getInventario().getItem(indice);
            if (item instanceof Arma) {
                jogador.equiparArma((Arma) item);
                JOptionPane.showMessageDialog(this, item.getNome() + " equipada!");
            } else if (item instanceof Armadura) {
                jogador.equiparArmadura((Armadura) item);
                JOptionPane.showMessageDialog(this, item.getNome() + " equipada!");
            } else {
                JOptionPane.showMessageDialog(this, "Este item não pode ser equipado");
            }
            atualizarStatus(jogador);
        });

        btnFechar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void atualizarStatus(Personagem jogador) {
        lblStatus.setText(
            "HP: " + jogador.getVida() + "/" + jogador.getVidaMax() +
            " | MP: " + jogador.getMana() + "/" + jogador.getManaMax() +
            " | Ouro: " + jogador.getOuro()
        );
    }
}