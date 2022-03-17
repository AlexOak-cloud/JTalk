package app.utills;

import app.entity.*;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileUtil {

    @Value("${upload.path}")
    private String path;

    public static final String PATH = "/home/alex_oak/IT/IdeaProjects/source/";
    public static final String IMAGE = "/image";

    @Autowired
    private UserService userService;

    @Bean
    public FileUtil getFileUtil(){
        return new FileUtil();
    }

    public String generatePathForUser(User user){
        return PATH + user.getName() + "/" + user.getId();

    }

    public String generatePathForImages(){
        User authUser = userService.getAuthUser();
        return PATH + authUser.getName() + "/" + authUser.getId() + "/image/";
    }

    public boolean checkExtensionForImage(MultipartFile file){
        return !getFileExtension(file).equals("jpeg") | !getFileExtension(file).equals("jpg");
    }
    public String generatePathForMusic(){
        User authUser = userService.getAuthUser();
        return PATH + authUser.getName() + "/" + authUser.getId() + "/music/";
    }

    public boolean checkExtensionForMusic(MultipartFile file){
        return !getFileExtension(file).equals("mp3") ;
    }
    public String generatePathForVideo(){
        User authUser = userService.getAuthUser();
        return PATH + authUser.getName() + "/" + authUser.getId() + "/video/";
    }

    public boolean checkExtensionForVideo(MultipartFile file){
        return !getFileExtension(file).equals("mp4");
    }

    public String getFileExtension(MultipartFile file){
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }
}
