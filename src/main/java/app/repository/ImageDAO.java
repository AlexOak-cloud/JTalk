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
       Image image = ImageIO.read(new URL("https://drive.google.com/file/d/1CnrRrZk_IVjW5iEscmNXvA5KI5rhbQLq/view?usp=sharing"))
       System.out.println(image);
   } catch (IOException ex){
       ex.printStackTrace();
   }
    }

  Вывести картинку на экран

}
