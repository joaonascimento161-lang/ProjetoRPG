package uinaousar;

import javax.swing.*;

import personagens.Personagem;
import sistema.XPSystem;
import inimigos.Inimigo;
import itens.Item;

public class TelaCombate extends JFrame {

    public TelaCombate(Personagem jogador, Inimigo inimigo){

        setTitle("Combate");
        setSize(500,400);
        setLocationRelativeTo(null);


        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel lblJogador =
        new JLabel(jogador.getNome());

        JProgressBar barraVidaJogador =
        new JProgressBar(0, jogador.getVidaMax());

        lblJogador.setAlignmentX(CENTER_ALIGNMENT);

        barraVidaJogador.setString(jogador.getVida() + "/" + jogador.getVidaMax());
        barraVidaJogador.setStringPainted(true);

        JLabel lblMana =
        new JLabel("Mana");

        JProgressBar barraMana =
        new JProgressBar(0, 100);

        barraMana.setString(jogador.getMana() + "/" + jogador.getManaMax());
        barraMana.setStringPainted(true);

        JLabel lblInimigo =
        new JLabel(inimigo.getNome());

        JProgressBar barraVidaInimigo =
            new JProgressBar(0, inimigo.getVida());

            lblInimigo.setAlignmentX(CENTER_ALIGNMENT);

        barraVidaInimigo.setString(inimigo.getVida() + "/" + inimigo.getVidaMax());
        barraVidaInimigo.setStringPainted(true);

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
                
                    barraVidaJogador.setValue(jogador.getVida());

                    barraVidaInimigo.setValue(inimigo.getVida());

                    barraMana.setValue(jogador.getMana());
                
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

                    Item drop = inimigo.gerarDrop();

                    if(drop != null){

                        jogador.getInventario().adicionarItem(drop);

                        JOptionPane.showMessageDialog(
                            this,
                            "Item encontrado: " + drop.getNome()
                        );
                    }

                    dispose();
                
                        new TelaPrincipal(jogador);
                    }
                
                    if(!jogador.estaVivo()){
                
                        JOptionPane.showMessageDialog(
                            this,
                            "Você foi derrotado!"
                        );
                        
                        dispose();
                    }
                });

                painel.add(lblJogador);
                painel.add(barraVidaJogador);

                painel.add(lblMana);
                painel.add(barraMana);

                painel.add(Box.createVerticalStrut(15));

                painel.add(lblInimigo);
                painel.add(barraVidaInimigo);

                painel.add(Box.createVerticalStrut(15));

                painel.add(btnAtacar);

                painel.add(Box.createVerticalStrut(15));

                painel.add(new JScrollPane(historico));

        add(painel);

        setVisible(true);
    }
}