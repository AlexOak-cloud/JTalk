package app.utills;

import app.entity.*;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

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
        return  path + "/" + user.getName() + "/" + user.getId() + "/";
    }

    public String generateLocalPath(User user){
        return "/" + user.getName() + "/" + user.getId() + "/";
    }

    public String generateLocalMsg(User user){
        return "/" + user.getName() + "/" + user.getId() + "/msg/";
    }

    public String generatePathForImages(){
        User authUser = userService.getAuthUser();
        return "/" + authUser.getName() + "/" + authUser.getId() + "/image/";
    }

    public String generatePathForMusic(){
        User authUser = userService.getAuthUser();
        return "/" + authUser.getName() + "/" + authUser.getId() + "/music/";
    }

    public String generatePathForMessage(){
        User authUser = userService.getAuthUser();
        return "/" + authUser.getName() + "/" + authUser.getId() + "/msg/";
    }

    public String generatePathForPost(User user){
        return "/" + user.getName() + "/" + user.getId() + "/post/";
    }


    public boolean checkExtensionForImage(MultipartFile file){
        return !getFileExtension(file).equals(".jpeg") | !getFileExtension(file).equals(".jpg");
    }

    public boolean checkExtensionForMusic(MultipartFile file){
        return !getFileExtension(file).equals(".mp3") ;
    }


    public boolean checkExtensionForVideo(MultipartFile file){
        return !getFileExtension(file).equals(".mp4");
    }

    public String getFileExtension(MultipartFile file){
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    public String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String deleteIllegalSymbol(String forFix){
        StringBuilder sb = new StringBuilder(forFix);
        for (int i = 0; i <sb.length() ; i++) {
            if(sb.charAt(i) == '\''){
                sb.deleteCharAt(i);
         }
        }
        return sb.toString();
    }
}
