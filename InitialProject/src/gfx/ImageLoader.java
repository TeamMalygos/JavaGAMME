package gfx;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <h3>Image Loader</h3>
 * 
 * <p>ImageLoader loads the resources from resource folders
 * relative to the class</p>
 * 
 * @author G_ANGELOV
 *
 */
public class ImageLoader {
	
    public static BufferedImage loadImage(String path){

        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
