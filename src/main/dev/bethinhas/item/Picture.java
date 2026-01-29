package main.dev.bethinhas.item;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.net.URL;

public class Picture extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    private String filename;

    public Picture(String name, String description, String filename) {
        super(name, description);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void showImage() {
        URL imageUrl = getClass().getResource("/main/resources/images/" + filename);

        if (imageUrl == null) {
            System.out.println("Erro: Imagem " + filename + " não encontrada.");
            return;
        }

        ImageIcon icon = new ImageIcon(imageUrl);

        Image image = icon.getImage();
        if (icon.getIconWidth() > 800 || icon.getIconHeight() > 600) {
            Image newimg = image.getScaledInstance(800, 600,  java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
        }

        JOptionPane.showMessageDialog(
                null,
                "",
                "Visualizando: " + this.getName(),
                JOptionPane.INFORMATION_MESSAGE,
                icon
        );
    }

    @Override
    public String toString() {
        return "Uma imagem de " + filename;
    }
}
