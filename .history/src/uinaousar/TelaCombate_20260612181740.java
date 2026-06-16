package uinaousar;

import javax.swing.*;

import personagens.Personagem;
import sistema.XPSystem;
import inimigos.Inimigo;
import itens.Item;
import java.awt.Color;

public class TelaCombate extends JFrame {

    private void atualizarBarras(
                Personagem jogador,
                Inimigo inimigo,
                JProgressBar barraVidaJogador,
                JProgressBar barraMana,
                JProgressBar barraVidaInimigo){
    
            barraVidaJogador.setValue(jogador.getVida());
    
            barraMana.setValue(jogador.getMana());
    
            barraVidaInimigo.setValue(inimigo.getVida());
    
            barraVidaJogador.setString(
                jogador.getVida() + "/" + jogador.getVidaMax()
            );
    
            barraMana.setString(
                jogador.getMana() + "/" + jogador.getManaMax()
            );
    
            barraVidaInimigo.setString(
                inimigo.getVida() + "/" + inimigo.getVidaMax()
            );
        }
    
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
        
        barraVidaJogador.setValue(jogador.getVida());
        barraVidaJogador.setString(jogador.getVida() + "/" + jogador.getVidaMax());
        barraVidaJogador.setStringPainted(true);

        JLabel lblMana =
        new JLabel("MP");

        JProgressBar barraMana =
        new JProgressBar(0, 100);

        barraMana.setValue(jogador.getMana());
        barraMana.setString(jogador.getMana() + "/" + jogador.getManaMax());
        barraMana.setStringPainted(true);

        JLabel lblInimigo =
        new JLabel(inimigo.getNome());

        JProgressBar barraVidaInimigo =
            new JProgressBar(0, inimigo.getVidaMax());

            lblInimigo.setAlignmentX(CENTER_ALIGNMENT);

        barraVidaInimigo.setValue(inimigo.getVida());
        barraVidaInimigo.setString(inimigo.getVida() + "/" + inimigo.getVidaMax());
        barraVidaInimigo.setStringPainted(true);

        JTextArea historico = new JTextArea(10, 30);

        historico.setEditable(false);

        historico.append("Combate iniciado!\n");

        JButton btnAtacar = new JButton("Atacar");

        JButton btnHabilidade = new JButton("Habilidade");

        JButton bntInventario = new JButton("Inventário");

            bntInventario.addActionListener(e -> {

                if(jogador.getInventario().estaVazio()){

                    JOptionPane.showMessageDialog(this, "Inventário vazio");

                    return;
                }

                String[] itens = new String[jogador.getInventario().tamanho()];

                for(int cont = 0; cont < itens.length; cont++){

                    itens[cont] = jogador.getInventario().getItem(cont).getNome();
                }

                String escolhido = (String) JOptionPane.showInputDialog(
                    this,
                    "Escolha um item:",
                    "Inventário",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    itens,
                    itens[0]
                );

                for(int cont = 0; cont < jogador.getInventario().tamanho(); cont++){

                    Item item = jogador.getInventario().getItem(cont);

                    if(item.getNome().equals(escolhido)){

                        item.usar(jogador);

                        jogador.getInventario().removerItem(cont);

                        break;
                    }
                }

            
            atualizarBarras(jogador, inimigo, barraVidaJogador, barraMana, barraVidaInimigo);

            });

                btnHabilidade.addActionListener(e -> {

                    jogador.usarHab(inimigo);

                    historico.append(
                        jogador.getNome()
                        + " usou sua habilidade em "
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

                    atualizarBarras(jogador, inimigo, barraVidaJogador, barraMana, barraVidaInimigo);

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
                
                    atualizarBarras(jogador, inimigo, barraVidaJogador, barraMana, barraVidaInimigo);
                    
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

                barraVidaJogador.setBackground(new Color(105,105,105));
                barraVidaJogador.setForeground(new Color(102,205,0));
                barraVidaInimigo.setBackground(new Color(105,105,105));
                barraVidaInimigo.setForeground(new Color(102,205,0));
                barraMana.setForeground(new Color(0,255,255));

                painel.add(lblJogador);
                painel.add(barraVidaJogador);

                painel.add(lblMana);
                painel.add(barraMana);

                painel.add(Box.createVerticalStrut(15));

                painel.add(lblInimigo);
                painel.add(barraVidaInimigo);

                painel.add(Box.createVerticalStrut(15));

                JPanel painelBotoes = new JPanel();

                painelBotoes.add(btnAtacar);
                painelBotoes.add(btnHabilidade);
                painelBotoes.add(bntInventario);

                painel.add(painelBotoes);

                painel.add(Box.createVerticalStrut(15));

                painel.add(new JScrollPane(historico));

        add(painel);

        setVisible(true);
    }

    //     
}