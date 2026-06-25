package interfaces;

import javax.swing.*;
import java.awt.*;

public class PainelComFundo extends JPanel {

    private Image imagem;

    public PainelComFundo(String caminhoImagem) {

        imagem = new ImageIcon(caminhoImagem).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(
            imagem,
            0,
            0,
            getWidth(),
            getHeight(),
            this
        );

        g.setColor(
            new Color(
                0,
                0,
                0,
                0
            )
        );

        g.fillRect(
            0,
            0,
            getWidth(),
            getHeight()
        );
    }
}