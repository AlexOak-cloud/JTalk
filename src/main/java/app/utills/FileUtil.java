package app.utills;

import app.entity.*;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static final String PATH = "/home/alex_oak/IT/IdeaProjects/source/";

    @Bean
    public FileUtil getFileUtil(){
        return new FileUtil();
    }

    public File generateFile(User user, File file){
        File rtnFile = new File(PATH + user.getName() + "/" + user.getId() + "/image/" + file.getName());
        try {
            if (!rtnFile.exists()) {
                boolean newFile = rtnFile.createNewFile();
                System.out.println(newFile);
            }
            return file;
        }catch (IOException exception){
            exception.printStackTrace();
            System.out.println(file.getPath());
            return new File("Path in error-file. Later Fixed");
        }

    }
    public File generateFile(User user, Music music){
        return new File(PATH + user.getName() + "/" + user.getId() + "/music/");
    }

    public File generateFile(User user, Video video){
        return new File(PATH + user.getName() + "/" + user.getId() + "/video/");
    }
    public File generateFile(User user, Post post){
        return new File(PATH + user.getName() + "/" + user.getId() + "/post/");
    }
}
