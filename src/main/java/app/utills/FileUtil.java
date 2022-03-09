package app.utills;

import app.entity.*;
import org.springframework.context.annotation.Bean;

import java.io.File;

public class FileUtil {

    public static final String PATH = "/home/alex_oak/IT/IdeaProjects/source/";

    @Bean
    public FileUtil getFileUtil(){
        return new FileUtil();
    }

    public File generateFile(User user, Image image){
        return new File(PATH + user.getName() + "/" + user.getId() + "/image/");
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
