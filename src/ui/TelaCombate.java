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
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel lblJogador =
                new JLabel(jogador.getNome()
                + " HP: " + jogador.getVida());

        JLabel lblInimigo =
                new JLabel(inimigo.getNome()
                + " HP: " + inimigo.getVida());

        JTextArea historico = new JTextArea(10, 30);

        historico.setEditable(false);

        historico.append("Combate iniciado!\n");

        JButton btnAtacar =
                new JButton("Atacar");

                btnAtacar.addActionListener(e -> {

                    jogador.atacar(inimigo);

                    historico.append(
                        jogador.getNome()
                        + " atacou "
                        + inimigo.getNome()
                        + "\n"
                    );
                
                    if(inimigo.estaVivo()){

                        inimigo.atacar(jogador);
                    
                        historico.append(
                            inimigo.getNome()
                            + " atacou "
                            + jogador.getNome()
                            + "\n"
                        );
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
                
                painel.add(Box.createVerticalStrut(10));
                
                painel.add(btnAtacar);
                
                painel.add(Box.createVerticalStrut(15));
                
                painel.add(new JScrollPane(historico));

        add(painel);

        setVisible(true);
    }
}