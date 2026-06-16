package uinaousar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import personagens.Personagem;

import java.awt.*;

public class TelaStatus extends JFrame {

    public TelaStatus(Personagem jogador){

        setTitle("Status do Personagem");
        setSize(450, 400);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(15,15,15,15));

        JLabel titulo = new JLabel("STATUS DO PERSONAGEM");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new GridLayout(0,2,10,10));

        painelInfo.add(new JLabel("Classe:"));
        painelInfo.add(new JLabel(jogador.getNome()));

        painelInfo.add(new JLabel("Nível:"));
        painelInfo.add(new JLabel(String.valueOf(jogador.getNivel())));

        painelInfo.add(new JLabel("XP:"));
        painelInfo.add(new JLabel(String.valueOf(jogador.getXp())));

        painelInfo.add(new JLabel("Ouro:"));
        painelInfo.add(new JLabel(String.valueOf(jogador.getOuro())));

        painelInfo.add(new JLabel("Dano:"));
        painelInfo.add(new JLabel(String.valueOf(jogador.getDano())));

        painelInfo.add(new JLabel("Arma Equipada:"));

        if(jogador.getArmaEquipada() != null){
            painelInfo.add(
                new JLabel(
                    jogador.getArmaEquipada().getNome()
                )
            );
        }else{
            painelInfo.add(new JLabel("Nenhuma"));
        }

        painelInfo.add(new JLabel("Armadura Equipada:"));

        if(jogador.getArmaduraEquipada() != null){
            painelInfo.add(
                new JLabel(
                    jogador.getArmaduraEquipada().getNome()
                )
            );
        }else{
            painelInfo.add(new JLabel("Nenhuma"));
        }

        painelPrincipal.add(painelInfo, BorderLayout.CENTER);

        JPanel painelBarras = new JPanel();
        painelBarras.setLayout(new BoxLayout(
            painelBarras,
            BoxLayout.Y_AXIS
        ));

        JLabel lblVida = new JLabel("Vida");

        JProgressBar barraVida =
            new JProgressBar(
                0,
                jogador.getVidaMax()
            );

        barraVida.setValue(jogador.getVida());
        barraVida.setString(
            jogador.getVida()
            + "/"
            + jogador.getVidaMax()
        );
        barraVida.setStringPainted(true);

        JLabel lblMana = new JLabel("Mana");

        JProgressBar barraMana =
            new JProgressBar(
                0,
                jogador.getManaMax()
            );

        barraMana.setValue(jogador.getMana());
        barraMana.setString(
            jogador.getMana()
            + "/"
            + jogador.getManaMax()
        );
        barraMana.setStringPainted(true);

        painelBarras.add(lblVida);
        painelBarras.add(barraVida);

        painelBarras.add(Box.createVerticalStrut(10));

        painelBarras.add(lblMana);
        painelBarras.add(barraMana);

        painelPrincipal.add(
            painelBarras,
            BorderLayout.SOUTH
        );

        add(painelPrincipal);

        setVisible(true);
    }
}