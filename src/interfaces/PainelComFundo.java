package interfaces;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelComFundo extends JPanel {

    private Image imagem;

    public PainelComFundo(String caminhoImagem) {
        String caminho = caminhoImagem
                .replace("src/", "")
                .replace("\\", "/");

        if (!caminho.startsWith("/")) {
            caminho = "/" + caminho;
        }

        URL url = getClass().getResource(caminho);

        if (url != null) {
            imagem = new ImageIcon(url).getImage();
        } else {
            imagem = new ImageIcon(caminhoImagem).getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagem != null) {
            g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
        }
    }
}