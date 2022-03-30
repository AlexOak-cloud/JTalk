package app.repository;


import app.entity.Message;
import app.entity.User;
import app.services.UserService;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Repository
public class MsgRepository {

    @Value("${upload.path}")
    private String uploadPath;


    public File generateDialogFile(User sender, User recipient, String localMkdir){
        File file = new File(uploadPath + "/" + localMkdir);
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

    public boolean saveMessage(Message msg, File file){
        String writable = msg.getSender().getId() + "| " +
                msg.getDateTime() + "| " +
                msg.isRead() + ": " +
                msg.getContent() ;
        try(BufferedOutputStream bos
                = new BufferedOutputStream(
                        new FileOutputStream(file,true))){

            bos.write(writable.getBytes(StandardCharsets.UTF_8));
            bos.flush();
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Error -> MsgRepository.saveMessage()  method");
            return false;
        }
    }
}
