package uinaousar;

import javax.swing.*;
import java.awt.*;

import personagens.*;
import save.SaveManager;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PainelComFundo painelPrincipal =
            new PainelComFundo("src/image/madeira.jpg");

        painelPrincipal.setOpaque(true);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        ImageIcon icon =
                new ImageIcon("src/image/image.png");

        Image img =
                icon.getImage().getScaledInstance(
                        400,
                        250,
                        Image.SCALE_SMOOTH
                );

        JLabel logo =
                new JLabel(
                        new ImageIcon(img)
                );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JButton btnNovoJogo =
                criarBotao("NOVO JOGO");

        JButton btnContinuar =
                criarBotao("CONTINUAR");

        JButton btnSair =
                criarBotao("SAIR");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setOpaque(false);
        painelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelBotoes.add(btnNovoJogo);
        painelBotoes.add(btnContinuar);
        painelBotoes.add(btnSair);

        painelPrincipal.add(Box.createVerticalGlue());

        painelPrincipal.add(logo);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        painelPrincipal.add(painelBotoes);

        painelPrincipal.add(Box.createVerticalGlue());

        JLabel versao =
                new JLabel("Versão 1.0");

        versao.setForeground(
                Color.LIGHT_GRAY
        );

        versao.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painelPrincipal.add(versao);

        painelPrincipal.add(
                Box.createVerticalStrut(15)
        );

        add(painelPrincipal);

        btnNovoJogo.addActionListener(e -> {

            JFrame telaClasse = new JFrame("Escolha sua Classe");
            telaClasse.setSize(1000, 562); 
            telaClasse.setLocationRelativeTo(null);
            telaClasse.setResizable(false);
        
            PainelComFundo painelClasse = new PainelComFundo("src/image/mesaClasses.png");
            painelClasse.setLayout(null); 
        
            JButton btnGuerreiro = criarBotaoInvisivel();
            JButton btnMago = criarBotaoInvisivel();
            JButton btnArqueiro = criarBotaoInvisivel();
            JButton btnAssassino = criarBotaoInvisivel();
        
            btnGuerreiro.setBounds(30, 350, 280, 180);
            btnMago.setBounds(330, 380, 220, 150);
            btnArqueiro.setBounds(560, 350, 230, 180);
            btnAssassino.setBounds(790, 390, 190, 140);
        
            painelClasse.add(btnGuerreiro);
            painelClasse.add(btnMago);
            painelClasse.add(btnArqueiro);
            painelClasse.add(btnAssassino);
        
            btnGuerreiro.addActionListener(e2 -> {
                new TelaPrincipal(new Guerreiro());
                telaClasse.dispose();
                dispose();
            });
        
            btnMago.addActionListener(e2 -> {
                new TelaPrincipal(new Mago());
                telaClasse.dispose();
                dispose();
            });
        
            btnArqueiro.addActionListener(e2 -> {
                new TelaPrincipal(new Arqueiro());
                telaClasse.dispose();
                dispose();
            });
        
            btnAssassino.addActionListener(e2 -> {
                new TelaPrincipal(new Assassino());
                telaClasse.dispose();
                dispose();
            });
        
            telaClasse.add(painelClasse);
            telaClasse.setVisible(true);
        });

        btnContinuar.addActionListener(e -> {
            if (!SaveManager.existeSave()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum save encontrado!"
                );
                return;
            }

            Personagem jogador = SaveManager.carregar();
            if (jogador != null) {
                new TelaPrincipal(jogador);
                dispose();
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        setVisible(true); 
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Serif", Font.BOLD, 20));
        btn.setBackground(new Color(100, 55, 20));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(200, 55));
        btn.setPreferredSize(new Dimension(200, 55));
        return btn;
    }

    private JButton criarBotaoInvisivel() {
        JButton btn = new JButton();
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}