package app.repository;


import app.entity.Dialog;
import app.entity.Message;
import app.entity.User;
import app.services.UserService;
import app.utills.Direction;
import app.utills.FileUtil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;


@Repository
public class MsgRepository {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;

    public boolean saveMessage(Message msg, Dialog dialog){
        String writable = msg.getSender().getId() + "|" +
                msg.getDateTime() + "~" +
                msg.isRead() + "*" +
                msg.getContent() +
                "\n";
        try(BufferedOutputStream bos
                = new BufferedOutputStream(
                        new FileOutputStream(dialog.getFile(),true))){

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
        dialog.setFile(file);
        List<Message> msgs = extractMsgs(dialog);
        List<Integer> usersIdByFileName = getUsersIdByFileName(file);
        User sender = userService.getById(usersIdByFileName.get(0));
        User recipient = userService.getById(usersIdByFileName.get(1));
        dialog.setSender(sender);
        dialog.setRecipient(recipient);
        dialog.setMsgs(msgs);
        return dialog;
    }

    public List<Message> extractMsgs(Dialog dialog) {
        List<Message> rtnList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dialog.getFile()))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String content = line.substring(line.indexOf("*") + 1, line.length());
                String senderStr = line.substring(0, line.indexOf("|"));
                int senderId = Integer.parseInt(senderStr);
                User sender = userService.getById(senderId);
                String isReadStr = line.substring(line.indexOf("~") + 1, line.indexOf("*"));
                boolean isRead = Boolean.parseBoolean(isReadStr);
                String dateTimeStr = line.substring(line.indexOf("|") + 1, line.indexOf("~"));
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
                Message msg = new Message(content, sender, dateTime, isRead);
                if (msg.getContent() != null) {
                    rtnList.add(msg);
                }
            }
            return rtnList;
        } catch (IOException ex) {
            System.out.println("error MsgRepository.extractMsgs() method");
            ex.printStackTrace();
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

