package main.item;

import java.awt.image.BufferedImage;
import java.io.Serial;

public class Picture extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    private BufferedImage image;

    public Picture(String name, String description, BufferedImage image) {
        super(name, description);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
