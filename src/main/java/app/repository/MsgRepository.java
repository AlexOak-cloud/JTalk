package app.repository;


import app.entity.Dialog;
import app.entity.Message;
import app.entity.User;
import app.services.UserService;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@Repository
public class MsgRepository {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;


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
                msg.getContent() +
                "\n";
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

    public List<Integer> getUsersIdByFileName(File file){
        List<Integer> rtn = new ArrayList<>();
        String senderFromFileName =
                file.getName().substring(
                        0,
                        file.getName().indexOf("_"));
        String recipientFromFileName =
                file.getName().substring(
                        file.getName().indexOf("_") + 1,
                        file.getName().indexOf("."));
        rtn.add(Integer.parseInt(senderFromFileName));
        rtn.add(Integer.parseInt(recipientFromFileName));
        return rtn;
    }



    public Dialog getDialog(File file){
        Dialog dialog = new Dialog();
        List<Message> msgs = new ArrayList<>();
        List<Integer> usersIdByFileName = getUsersIdByFileName(file);
        User sender = userService.getById(usersIdByFileName.get(0));
        User recipient = userService.getById(usersIdByFileName.get(1));
        dialog.setSender(sender);
        dialog.setRecipient(recipient);
        dialog.setPath(file.getPath());




    }

    public List<Message> extractMsg(File file){
        List<Message> rtnList = new ArrayList<>();
        try(BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file))){
            while (reader.readLine() != null){
                Stream<String> lines = reader.lines();
                lines.forEach();
            }

            return rtnList;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
