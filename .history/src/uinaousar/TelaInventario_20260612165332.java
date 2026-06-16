package uinaousar;

import javax.swing.*;

import itens.Arma;
import itens.Armadura;
import itens.Item;
import personagens.Personagem;

import java.awt.*;
import java.awt.event.*;

public class TelaInventario extends JFrame {

private JLabel lblStatus;

public TelaInventario(Personagem jogador){

        setTitle("Inventário");
        setSize(500,400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        lblStatus = new JLabel(
            "HP: "
            + jogador.getVida()
            + "/"
            + jogador.getVidaMax()
            + " | MP: "
            + jogador.getMana()
            + "/"
            + jogador.getManaMax()
            + " | Ouro: "
            + jogador.getOuro()
        );

        add(lblStatus, BorderLayout.NORTH);

        DefaultListModel<String> modelo =
                new DefaultListModel<>();

        for(int i = 0; i < jogador.getInventario().tamanho(); i++){

            Item item =
                    jogador.getInventario().getItem(i);

            String icone = "📦 ";

            if(item instanceof Arma){
                icone = "⚔ ";
            }
            else if(item instanceof Armadura){
                icone = "🛡 ";
            }

            modelo.addElement(
                icone + item.getNome()
            );
        }

        JList<String> listaItens =
                new JList<>(modelo);

        JScrollPane scroll =
                new JScrollPane(listaItens);

        add(scroll, BorderLayout.CENTER);

        JTextArea descricao =
                new JTextArea(4,20);

        descricao.setEditable(false);

        listaItens.addListSelectionListener(e -> {

            int indice =
                    listaItens.getSelectedIndex();

            if(indice >= 0){

                Item item =
                        jogador.getInventario()
                        .getItem(indice);

                descricao.setText(
                    "Item: "
                    + item.getNome()
                );
            }
        });

        add(descricao, BorderLayout.SOUTH);

        JPanel painelBotoes =
                new JPanel();

        JButton btnUsar =
                new JButton("Usar");

        JButton btnEquipar =
                new JButton("Equipar");

        JButton btnFechar =
                new JButton("Fechar");

        painelBotoes.add(btnEquipar);
        painelBotoes.add(btnFechar);  painelBotoes.add(btnUsar);
      

        add(painelBotoes, BorderLayout.EAST);

        btnUsar.addActionListener(e -> {

            int indice =
                    listaItens.getSelectedIndex();

            if(indice == -1){

                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um item"
                );

                return;
            }

            Item item =
                    jogador.getInventario()
                    .getItem(indice);

            if(item instanceof Arma ||
            item instanceof Armadura){

                JOptionPane.showMessageDialog(
                    this,
                    "Use o botão Equipar"
                );

                return;
            }

            item.usar(jogador);

            jogador.getInventario()
                    .removerItem(indice);

            modelo.remove(indice);

            atualizarStatus(jogador);

            JOptionPane.showMessageDialog(
                this,
                item.getNome() + " usado!"
            );
        });

        btnEquipar.addActionListener(e -> {

            int indice =
                    listaItens.getSelectedIndex();

            if(indice == -1){

                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um item"
                );

                return;
            }

            Item item =
                    jogador.getInventario()
                    .getItem(indice);

            if(item instanceof Arma){

                jogador.equiparArma(
                    (Arma)item
                );

                JOptionPane.showMessageDialog(
                    this,
                    item.getNome()
                    + " equipada!"
                );
            }
            else if(item instanceof Armadura){

                jogador.equiparArmadura(
                    (Armadura)item
                );

                JOptionPane.showMessageDialog(
                    this,
                    item.getNome()
                    + " equipada!"
                );
            }
            else{

                JOptionPane.showMessageDialog(
                    this,
                    "Este item não pode ser equipado"
                );
            }

            atualizarStatus(jogador);
        });

        btnFechar.addActionListener(e -> {

            dispose();

        });

        setVisible(true);
    }

    private void atualizarStatus(
            Personagem jogador){

        lblStatus.setText(
            "HP: "
            + jogador.getVida()
            + "/"
            + jogador.getVidaMax()
            + " | MP: "
            + jogador.getMana()
            + "/"
            + jogador.getManaMax()
            + " | Ouro: "
            + jogador.getOuro()
        );
    }
}