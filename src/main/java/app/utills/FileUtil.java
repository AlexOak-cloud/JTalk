package app.utills;

import app.entity.*;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    @Value("${upload.path}")
    private String path;

    @Autowired
    private UserService userService;

    @Bean
    public FileUtil getFileUtil(){
        return new FileUtil();
    }

    public String generateUploadPath(User user,Direction direction){
        return  path + "/" + user.getName() + "/" + user.getId() + "/" + direction.toString() + "/";
    }
    public String generateLocalPath(User user, Direction direction){
        return  "/" + user.getName() + "/" + user.getId() + "/" + direction.toString() + "/";
    }

    public boolean checkExtensionForImage(MultipartFile file){
        return !getFileExtension(file).equals(".jpeg") | !getFileExtension(file).equals(".jpg");
    }

    public boolean checkExtensionForMusic(MultipartFile file){
        return !getFileExtension(file).equals(".mp3") ;
    }

    public File generateLocalDialogFile(User sender, User recipient){
        File file = new File(generateLocalPath(sender ,Direction.MSG));
        if(!file.exists()){
            file.mkdirs();
        }
        String savableName = sender.getId() + "_" + recipient.getId() + ".txt";
        File rtnFile = new File(file + "/" + savableName);
        try{
            if(!rtnFile.exists()){
                rtnFile.createNewFile();
            }
            return rtnFile;
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Error -> MsgRepository.generateDialogFile()  method");
            return new File(file + "/" + savableName);
        }
    }
    public File generateUploadDialogFile(User sender, User recipient){
        File file = new File(path + "/" + generateLocalPath(sender ,Direction.MSG));
        if(!file.exists()){
            file.mkdirs();
        }
        String savableName = sender.getId() + "_" + recipient.getId() + ".txt";
        File rtnFile = new File(file + "/" + savableName);
        try{
            if(!rtnFile.exists()){
                rtnFile.createNewFile();
            }
            return rtnFile;
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Error -> MsgRepository.generateDialogFile()  method");
            return new File(file + "/" + savableName);
        }
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
