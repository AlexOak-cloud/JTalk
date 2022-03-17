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

    @Autowired
    private UserService userService;

    @Bean
    public FileUtil getFileUtil(){
        return new FileUtil();
    }

    public String generateUploadPath(User user){
        return path + "/" + user.getName() + "/" + user.getId() + "/";
    }

    public String generateLocalPath(User user){
        return  user.getName() + "/" + user.getId() + "/";
    }

    public String generatePathForImages(){
        User authUser = userService.getAuthUser();
        return "/" + authUser.getName() + "/" + authUser.getId() + "/image/";
    }

    public String generatePathForMusic(){
        User authUser = userService.getAuthUser();
        return "/" + authUser.getName() + "/" + authUser.getId() + "/music/";
    }

    public String generatePathForVideo(){
        User authUser = userService.getAuthUser();
        return "/" + authUser.getName() + "/" + authUser.getId() + "/video/";
    }

    public boolean checkExtensionForImage(MultipartFile file){
        return !getFileExtension(file).equals("jpeg") | !getFileExtension(file).equals("jpg");
    }

    public boolean checkExtensionForMusic(MultipartFile file){
        return !getFileExtension(file).equals("mp3") ;
    }


    public boolean checkExtensionForVideo(MultipartFile file){
        return !getFileExtension(file).equals("mp4");
    }

    public String getFileExtension(MultipartFile file){
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }
}
