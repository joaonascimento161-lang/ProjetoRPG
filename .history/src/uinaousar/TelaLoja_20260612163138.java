package uinaousar;

import javax.swing.*;

import itens.*;
import personagens.Personagem;

public class TelaLoja extends JFrame {

    public TelaLoja(Personagem jogador){

        setTitle("Loja");
        setSize(500,400);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        painel.setLayout(
            new BoxLayout(
                painel,
                BoxLayout.Y_AXIS
            )
        );

        JLabel lblOuro =
            new JLabel(
                "Ouro: "
                + jogador.getOuro()
            );

        JButton btnEspada =
            new JButton(
                "Espada de Ferro (100 ouro)"
            );

        JButton btnArmadura =
            new JButton(
                "Armadura de Couro (150 ouro)"
            );

        JButton btnPocaoVida = new JButton("Poção de Vida (30 ouro)");

        btnPocaoVida.addActionListener(e -> {

            if(jogador.gastarOuro(30)){

                jogador.getInventario().adicionarItem(
                    new PocaoVida()
                );
            
                lblOuro.setText(
                    "Ouro: " + jogador.getOuro()
                );
            
            }else{
                JOptionPane.showMessageDialog(
                    this,
                    "Ouro insuficiente!"
                );
            }
        });

        JButton btnPocaoMana = new JButton("Poção de Mana (25 ouro)");

        btnPocaoMana.addActionListener(e -> {

            if(jogador.gastarOuro(30)){

                jogador.getInventario().adicionarItem(
                    new PocaoMana()
                );
            
                lblOuro.setText(
                    "Ouro: " + jogador.getOuro()
                );

                JOptionPane.showMessageDialog(btnPocaoMana, e);
            
            }else{
                JOptionPane.showMessageDialog(
                    this,
                    "Ouro insuficiente!"
                );
            }
        });

        btnEspada.addActionListener(e -> {

            if(jogador.gastarOuro(100)){
        
                jogador.getInventario().adicionarItem(
                    new Arma(
                        "Espada de Ferro",
                        5
                    )
                );
        
                lblOuro.setText(
                    "Ouro: " + jogador.getOuro()
                );
        
                JOptionPane.showMessageDialog(
                    this,
                    "Espada comprada!"
                );
            }
            else{
        
                JOptionPane.showMessageDialog(
                    this,
                    "Ouro insuficiente!"
                );
            }
        });

        btnArmadura.addActionListener(e -> {

            if(jogador.gastarOuro(150)){
        
                jogador.getInventario().adicionarItem(
                    new Armadura(
                        "Armadura de Couro",
                        20
                    )
                );
        
                lblOuro.setText(
                    "Ouro: " + jogador.getOuro()
                );
        
                JOptionPane.showMessageDialog(
                    this,
                    "Armadura comprada!"
                );
            }
            else{
        
                JOptionPane.showMessageDialog(
                    this,
                    "Ouro insuficiente!"
                );
            }
        });

        painel.add(lblOuro);

        painel.add(Box.createVerticalStrut(15));

        painel.add(btnPocaoVida);
        painel.add(btnPocaoMana);
        painel.add(btnEspada);
        painel.add(btnArmadura);

        add(painel);

        setVisible(true);
    }
}