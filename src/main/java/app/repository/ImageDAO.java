package app.repository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageDAO {

    public static void main(String[] args)  {
   try {
       Image image = ImageIO.read(new URL("https://www.google.com/imgres?imgurl=https%3A%2F%2Fstatic-cse.canva.com%2Fblob%2F195615%2Fpaul-skorupskas-7KLa-xLbSXA-unsplash-2.jpg&imgrefurl=https%3A%2F%2Fwww.canva.com%2Fru_ru%2Fobuchenie%2Fidei-dlya-foto%2F&tbnid=x-sEyD0QhXJ6QM&vet=12ahUKEwjdluS53Jr2AhUE_SoKHbJ3AqEQMygBegUIARDSAQ..i&docid=qv3bYgAKOmFK9M&w=1920&h=1280&q=%D1%84%D0%BE%D1%82%D0%BE&client=firefox-b-lm&ved=2ahUKEwjdluS53Jr2AhUE_SoKHbJ3AqEQMygBegUIARDSAQ"));
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
