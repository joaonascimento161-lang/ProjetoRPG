package uinaousar;

import javax.swing.*;
import personagens.Personagem;
import sistema.XPSystem;
import inimigos.Inimigo;
import itens.Item;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Insets;

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
    
    public TelaCombate(Personagem jogador, Inimigo inimigo, String caminhoFundo){

        setTitle("Combate - Arena");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(0, 0));
    
        PainelComFundo painelPrincipal = new PainelComFundo(caminhoFundo);
        painelPrincipal.setLayout(new BorderLayout(15, 15));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel painelHud = new JPanel();
        painelHud.setLayout(new BoxLayout(painelHud, BoxLayout.Y_AXIS));
        painelHud.setOpaque(false); 

        JLabel lblJogador = new JLabel(jogador.getNome().toUpperCase());
        lblJogador.setFont(new Font("Serif", Font.BOLD, 14));
        lblJogador.setForeground(Color.WHITE);
        lblJogador.setAlignmentX(CENTER_ALIGNMENT);

        JProgressBar barraVidaJogador = new JProgressBar(0, jogador.getVidaMax());
        barraVidaJogador.setValue(jogador.getVida());
        barraVidaJogador.setString(jogador.getVida() + "/" + jogador.getVidaMax());
        barraVidaJogador.setStringPainted(true);
        barraVidaJogador.setBackground(new Color(105,105,105));
        barraVidaJogador.setForeground(new Color(102,205,0));

        JProgressBar barraMana = new JProgressBar(0, jogador.getManaMax());
        barraMana.setValue(jogador.getMana());
        barraMana.setString(jogador.getMana() + "/" + jogador.getManaMax());
        barraMana.setStringPainted(true);
        barraMana.setBackground(new Color(105,105,105));
        barraMana.setForeground(new Color(0,255,255));

        JLabel lblInimigo = new JLabel(inimigo.getNome().toUpperCase());
        lblInimigo.setFont(new Font("Serif", Font.BOLD, 14));
        lblInimigo.setForeground(Color.WHITE);
        lblInimigo.setAlignmentX(CENTER_ALIGNMENT);

        JProgressBar barraVidaInimigo = new JProgressBar(0, inimigo.getVidaMax());
        barraVidaInimigo.setValue(inimigo.getVida());
        barraVidaInimigo.setString(inimigo.getVida() + "/" + inimigo.getVidaMax());
        barraVidaInimigo.setStringPainted(true);
        barraVidaInimigo.setBackground(new Color(105,105,105));
        barraVidaInimigo.setForeground(new Color(255, 0, 0));

        painelHud.add(lblJogador);
        painelHud.add(barraVidaJogador);
        painelHud.add(Box.createVerticalStrut(5));
        painelHud.add(barraMana);
        painelHud.add(Box.createVerticalStrut(10));
        painelHud.add(lblInimigo);
        painelHud.add(barraVidaInimigo);

        JTextArea historico = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 165));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        historico.setOpaque(false); 
        historico.setEditable(false);
        historico.setLineWrap(true);
        historico.setWrapStyleWord(true);
        historico.setFont(new Font("Monospaced", Font.PLAIN, 13));
        historico.setForeground(new Color(245, 245, 220)); 
        historico.setMargin(new Insets(10, 10, 10, 10));
        historico.append("⚔️ Combate iniciado!\n");
        
        JScrollPane scrollHistorico = new JScrollPane(historico);
        scrollHistorico.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50), 1));
        scrollHistorico.setOpaque(false);
        scrollHistorico.getViewport().setOpaque(false);

        JPanel painelControles = new JPanel();
        painelControles.setLayout(new BoxLayout(painelControles, BoxLayout.Y_AXIS));
        painelControles.setOpaque(false);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setOpaque(false);
        JButton btnAtacar = new JButton("Atacar");
        JButton btnHabilidade = new JButton("Habilidade");
        JButton bntInventario = new JButton("Inventário");

        painelBotoes.add(btnAtacar);
        painelBotoes.add(btnHabilidade);
        painelBotoes.add(bntInventario);
        painelControles.add(painelBotoes);

        painelPrincipal.add(painelHud, BorderLayout.NORTH);
        painelPrincipal.add(scrollHistorico, BorderLayout.CENTER);
        painelPrincipal.add(painelControles, BorderLayout.SOUTH);

        add(painelPrincipal);

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
                this, "Escolha um item:", "Inventário",
                JOptionPane.PLAIN_MESSAGE, null, itens, itens[0]
            );

            // CORREÇÃO: Variável alterada de 'chosen' para 'escolhido'
            if (escolhido != null) {
                for(int cont = 0; cont < jogador.getInventario().tamanho(); cont++){
                    Item item = jogador.getInventario().getItem(cont);
                    if(item.getNome().equals(escolhido)){
                        item.usar(jogador);
                        historico.append("✨ Você usou " + item.getNome() + "!\n");
                        jogador.getInventario().removerItem(cont);
                        break;
                    }
                }
                atualizarBarras(jogador, inimigo, barraVidaJogador, barraMana, barraVidaInimigo);
            }
        });

        btnHabilidade.addActionListener(e -> {
            jogador.usarHab(inimigo);
            historico.append("💥 " + jogador.getNome() + " usou sua habilidade em " + inimigo.getNome() + "\n");

            if(inimigo.estaVivo()){
                inimigo.atacar(jogador);
                historico.append("💢 " + inimigo.getNome() + " atacou " + jogador.getNome() + "\n");
            }

            atualizarBarras(jogador, inimigo, barraVidaJogador, barraMana, barraVidaInimigo);

            if(!inimigo.estaVivo()){
                XPSystem.ganharXP(jogador, inimigo.getRecompensaXP());
                jogador.adicionarOuro(inimigo.getRecompensaOuro());

                JOptionPane.showMessageDialog(this, "Vitória!\nXP: " + inimigo.getRecompensaXP() + "\nOuro: " + inimigo.getRecompensaOuro());

                Item drop = inimigo.gerarDrop();
                if(drop != null){
                    jogador.getInventario().adicionarItem(drop);
                    JOptionPane.showMessageDialog(this, "Item encontrado: " + drop.getNome());
                }
                dispose();
                new TelaPrincipal(jogador);
            } else if(!jogador.estaVivo()){
                JOptionPane.showMessageDialog(this, "Você foi derrotado!");
                dispose();
            }
        });

        btnAtacar.addActionListener(e -> {
            jogador.atacar(inimigo);
            historico.append("⚔️ " + jogador.getNome() + " atacou " + inimigo.getNome() + "\n");
        
            if(inimigo.estaVivo()){
                inimigo.atacar(jogador);
                historico.append("🩸 " + inimigo.getNome() + " atacou " + jogador.getNome() + "\n");
            }
        
            atualizarBarras(jogador, inimigo, barraVidaJogador, barraMana, barraVidaInimigo);
            
            if(!inimigo.estaVivo()){
                XPSystem.ganharXP(jogador, inimigo.getRecompensaXP());
                jogador.adicionarOuro(inimigo.getRecompensaOuro());

                JOptionPane.showMessageDialog(this, "Vitória!\nXP: " + inimigo.getRecompensaXP() + "\nOuro: " + inimigo.getRecompensaOuro());

                Item drop = inimigo.gerarDrop();
                if(drop != null){
                    jogador.getInventario().adicionarItem(drop);
                    JOptionPane.showMessageDialog(this, "Item encontrado: " + drop.getNome());
                }
                dispose();
                new TelaPrincipal(jogador);
            } else if(!jogador.estaVivo()){
                JOptionPane.showMessageDialog(this, "Você foi derrotado!");
                dispose();
            }
        });

        setVisible(true);
    }
}