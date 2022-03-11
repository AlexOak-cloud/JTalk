package app.services;

import app.entity.Image;
import app.entity.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    public static final String PATH = "/home/alex_oak/IT/IdeaProjects/source/";

    public Image getImageByFile(File file){
      Image image = new Image();
      image.setName(file.getName());
      return image;
    }

    public File generateFile(User user, File file){
        File rtnFile = new File(PATH + user.getName() + "/" + user.getId() + "/image/" + file.getName());
        try {
            if (!rtnFile.exists()) {
                boolean newFile = rtnFile.createNewFile();
                System.out.println(newFile);
            }
            return rtnFile;
        }catch (IOException exception){
            System.out.println(file.getPath());
            exception.printStackTrace();
            return new File("Path in error-file. Later Fixed");
        }
    }

}
