package texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {

    private BufferedImage texture;

    public Texture(String path) {
        try {
            this.texture = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("An error occurred while texture loading");
        }
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
