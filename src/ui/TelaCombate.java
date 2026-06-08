package ui;

import javax.swing.*;

import personagens.Personagem;
import sistema.XPSystem;
import inimigos.Inimigo;

public class TelaCombate extends JFrame {

    public TelaCombate(Personagem jogador, Inimigo inimigo){

        setTitle("Combate");
        setSize(500,400);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        JLabel lblJogador =
                new JLabel(jogador.getNome()
                + " HP: " + jogador.getVida());

        JLabel lblInimigo =
                new JLabel(inimigo.getNome()
                + " HP: " + inimigo.getVida());

        JButton btnAtacar =
                new JButton("Atacar");

                btnAtacar.addActionListener(e -> {

                    jogador.atacar(inimigo);
                
                    if(inimigo.estaVivo()){
                        inimigo.atacar(jogador);
                    }
                
                    lblJogador.setText(
                        jogador.getNome() + " HP: " + jogador.getVida()
                    );
                
                    lblInimigo.setText(
                        inimigo.getNome() + " HP: " + inimigo.getVida()
                    );
                
                    if(!inimigo.estaVivo()){
                
                        JOptionPane.showMessageDialog(
                            this,
                            "Você venceu!"
                        );

                        XPSystem.ganharXP(
                        jogador,
                        inimigo.getRecompensaXP()
                    );

                    jogador.adicionarOuro(
                        inimigo.getRecompensaOuro()
                    );

                    JOptionPane.showMessageDialog(
                        this,
                        "Vitória!\n" +
                        "XP: " + inimigo.getRecompensaXP() +
                        "\nOuro: " + inimigo.getRecompensaOuro()
                    );

                    dispose();
                
                        btnAtacar.setEnabled(false);
                    }
                
                    if(!jogador.estaVivo()){
                
                        JOptionPane.showMessageDialog(
                            this,
                            "Você foi derrotado!"
                        );
                
                        btnAtacar.setEnabled(false);
                    }
                });

        painel.add(lblJogador);
        painel.add(lblInimigo);
        painel.add(btnAtacar);

        add(painel);

        setVisible(true);
    }
}