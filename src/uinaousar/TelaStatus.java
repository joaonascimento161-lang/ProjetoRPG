package uinaousar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import personagens.Personagem;
import java.awt.*;

public class TelaStatus extends JFrame {

    public TelaStatus(Personagem jogador) {
        setTitle("Status do Personagem");
        setSize(550, 420); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PainelComFundo painelPrincipal = new PainelComFundo("src/image/FundoStatus.jpg");
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(25, 40, 25, 40)); 

        JLabel titulo = new JLabel("STATUS DO PERSONAGEM");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 18));
        titulo.setForeground(new Color(60, 40, 20)); 
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelColunas = new JPanel(new GridLayout(1, 2, 40, 0)); 
        painelColunas.setOpaque(false);

        JPanel paginaEsquerda = new JPanel(new GridLayout(5, 2, 5, 10));
        paginaEsquerda.setOpaque(false);

        Font fonteTexto = new Font("Georgia", Font.BOLD, 13);
        Color corTinta = new Color(50, 35, 20);

        adicionarLabelItem(paginaEsquerda, "Classe:", fonteTexto, corTinta);
        adicionarLabelItem(paginaEsquerda, jogador.getNome(), fonteTexto, corTinta);

        adicionarLabelItem(paginaEsquerda, "Nível:", fonteTexto, corTinta);
        adicionarLabelItem(paginaEsquerda, String.valueOf(jogador.getNivel()), fonteTexto, corTinta);

        adicionarLabelItem(paginaEsquerda, "XP:", fonteTexto, corTinta);
        adicionarLabelItem(paginaEsquerda, String.valueOf(jogador.getXp()), fonteTexto, corTinta);

        adicionarLabelItem(paginaEsquerda, "Ouro:", fonteTexto, corTinta);
        adicionarLabelItem(paginaEsquerda, String.valueOf(jogador.getOuro()), fonteTexto, corTinta);

        adicionarLabelItem(paginaEsquerda, "Dano:", fonteTexto, corTinta);
        adicionarLabelItem(paginaEsquerda, String.valueOf(jogador.getDano()), fonteTexto, corTinta);

        painelColunas.add(paginaEsquerda);

        JPanel paginaDireita = new JPanel();
        paginaDireita.setLayout(new BoxLayout(paginaDireita, BoxLayout.Y_AXIS));
        paginaDireita.setOpaque(false);

        JLabel lblArmaTitulo = new JLabel("Arma Equipada:");
        lblArmaTitulo.setFont(fonteTexto);
        lblArmaTitulo.setForeground(corTinta);
        paginaDireita.add(lblArmaTitulo);

        String nomeArma = (jogador.getArmaEquipada() != null) ? jogador.getArmaEquipada().getNome() : "Nenhuma";
        JLabel lblArmaValor = new JLabel(nomeArma);
        lblArmaValor.setFont(new Font("Georgia", Font.ITALIC, 13));
        lblArmaValor.setForeground(new Color(100, 70, 40));
        lblArmaValor.setBorder(new EmptyBorder(2, 0, 10, 0));
        paginaDireita.add(lblArmaValor);

        JLabel lblArmaduraTitulo = new JLabel("Armadura Equipada:");
        lblArmaduraTitulo.setFont(fonteTexto);
        lblArmaduraTitulo.setForeground(corTinta);
        paginaDireita.add(lblArmaduraTitulo);

        String nomeArmadura = (jogador.getArmaduraEquipada() != null) ? jogador.getArmaduraEquipada().getNome() : "Nenhuma";
        JLabel lblArmaduraValor = new JLabel(nomeArmadura);
        lblArmaduraValor.setFont(new Font("Georgia", Font.ITALIC, 13));
        lblArmaduraValor.setForeground(new Color(100, 70, 40));
        lblArmaduraValor.setBorder(new EmptyBorder(2, 0, 15, 0));
        paginaDireita.add(lblArmaduraValor);

        JLabel lblVida = new JLabel("Vida");
        lblVida.setFont(fonteTexto);
        lblVida.setForeground(corTinta);
        paginaDireita.add(lblVida);

        JProgressBar barraVida = new JProgressBar(0, jogador.getVidaMax());
        barraVida.setValue(jogador.getVida());
        barraVida.setString(jogador.getVida() + "/" + jogador.getVidaMax());
        barraVida.setStringPainted(true);
        barraVida.setForeground(new Color(160, 40, 40)); 
        barraVida.setBackground(new Color(210, 190, 160));
        paginaDireita.add(barraVida);

        paginaDireita.add(Box.createVerticalStrut(10));

        JLabel lblMana = new JLabel("Mana");
        lblMana.setFont(fonteTexto);
        lblMana.setForeground(corTinta);
        paginaDireita.add(lblMana);

        JProgressBar barraMana = new JProgressBar(0, jogador.getManaMax());
        barraMana.setValue(jogador.getMana());
        barraMana.setString(jogador.getMana() + "/" + jogador.getManaMax());
        barraMana.setStringPainted(true);
        barraMana.setForeground(new Color(40, 80, 160)); 
        barraMana.setBackground(new Color(210, 190, 160));
        paginaDireita.add(barraMana);

        painelColunas.add(paginaDireita);

        painelPrincipal.add(painelColunas, BorderLayout.CENTER);

        setContentPane(painelPrincipal);

        setVisible(true);
    }

    private void adicionarLabelItem(JPanel painel, String texto, Font fonte, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        label.setForeground(cor);
        painel.add(label);
    }
}