package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDAO {

    public static void main(String[] args)  {
   try {
       Image image = ImageIO.read(new URL("https://static-cse.canva.com/blob/195615/paul-skorupskas-7KLa-xLbSXA-unsplash-2.jpg"));
       System.out.println(image);
       Graphics graphics = image.getGraphics();
       System.out.println(graphics);
   } catch (IOException ex){
       ex.printStackTrace();
   }
    }
    public static void showImage(Image image){
        Graphics graphics = image.getGraphics();
        System.out.println(graphics);
    }

}
