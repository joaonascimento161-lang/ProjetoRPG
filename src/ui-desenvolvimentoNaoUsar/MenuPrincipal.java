package ui;

import javax.swing.*;
import personagens.Personagem;
import personagens.Guerreiro;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal(){

        setTitle("Projeto RPG");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JButton btnNovoJogo = new JButton("Novo Jogo");
        JButton btnContinuar = new JButton("Continuar");
        JButton btnSair = new JButton("Sair");
    
        JPanel painel = new JPanel();
    
        painel.add(btnNovoJogo);
        painel.add(btnContinuar);
        painel.add(btnSair);
    
        add(painel);
    
        btnNovoJogo.addActionListener(e -> {

            JFrame telaClasse = new JFrame("Escolha sua Classe");
        
            telaClasse.setSize(400, 400);
            telaClasse.setLocationRelativeTo(null);
        
            JPanel painelClasse = new JPanel();
        
            JButton guerreiro = new JButton("Guerreiro");
            JButton mago = new JButton("Mago");
            JButton arqueiro = new JButton("Arqueiro");
        
            painelClasse.add(guerreiro);
            painelClasse.add(mago);
            painelClasse.add(arqueiro);

            guerreiro.addActionListener(e2 -> {

                Personagem jogador = new Guerreiro();
            
                new TelaPrincipal(jogador);
            
                telaClasse.dispose();
                dispose();
            });

            telaClasse.add(painelClasse);
        
            telaClasse.setVisible(true);
        });
    
        btnContinuar.addActionListener(e -> {
            System.out.println("Continuar clicado");
        });
    
        btnSair.addActionListener(e -> {
            System.exit(0);
        });
    
        setVisible(true);
    }
}