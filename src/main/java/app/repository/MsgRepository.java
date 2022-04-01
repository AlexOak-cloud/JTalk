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
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.SSS");
//    example -> 2022-04-01T11:52:03.884

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
        String writable = msg.getSender().getId() + "|" +
                msg.getDateTime() + "~" +
                msg.isRead() + "*" +
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
        List<Message> msgs = extractMsgs(file);
        List<Integer> usersIdByFileName = getUsersIdByFileName(file);
        User sender = userService.getById(usersIdByFileName.get(0));
        User recipient = userService.getById(usersIdByFileName.get(1));
        dialog.setSender(sender);
        dialog.setRecipient(recipient);
        dialog.setPath(file.getPath());
        dialog.setMsgs(msgs);
        return dialog;
    }

    public List<Message> extractMsgs(File file){
        List<Message> rtnList = new ArrayList<>();
        try(BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file))){
          while(reader.ready()) {
              String line = reader.readLine();
              String content = line.substring(line.indexOf("*"), line.length());
              String senderStr = line.substring(line.indexOf(0), line.indexOf("|"));
              int senderId = Integer.parseInt(senderStr);
              User sender = userService.getById(senderId);
              String isReadStr = line.substring(line.indexOf("~"),line.indexOf("*"));
              boolean isRead = Boolean.parseBoolean(isReadStr);
              String dateTimeStr = line.substring(line.indexOf("|"),line.indexOf("~"));
              LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
              Message msg = new Message(content,sender, dateTime, isRead);
              if(msg.getContent() != null) {
                  rtnList.add(msg);
              }
          }
              return rtnList;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void updateMessage(Dialog dialog,Message message,Message newMessage){
        List<Message> msgs = dialog.getMsgs();
        for(Message tmp : msgs){
            if(tmp.equals(message)){
                deleteMessage(dialog,tmp);
                tmp.setContent(newMessage.getContent());
                break;
            }
        }
    }

    public void deleteMessage(Dialog dialog,Message message){
        List<Message> msgs = dialog.getMsgs();
        for(Message tmp : msgs){
            if(tmp.equals(message)){
                tmp.setContent(null);
                break;
            }
        }
    }
}

