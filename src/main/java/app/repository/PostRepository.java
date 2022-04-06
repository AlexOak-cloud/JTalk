package app.repository;

import app.entity.Post;
import app.entity.User;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class PostRepository {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.SSS");

    public File generatePostFile(String localMkdir){
        File file = new File(uploadPath + "/" + localMkdir);
        if(!file.exists()){
            file.mkdirs();
        }
        String savableName = UUID.randomUUID() + ".txt";
        File rtnFile = new File(file + "/" + savableName);
        try{
            if(!rtnFile.exists()){
                rtnFile.createNewFile();
            }
            return rtnFile;
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Error -> PostRepository.generateDialogFile()  method");
            return new File(file + "/" + savableName);
        }
    }

    public boolean saveMessage(Post post, File file){
        String writable = post.getSender().getId() + "|" +
                post.getDateTime() + "~" +
                post.getContent() +
                "\n";
        try(BufferedOutputStream bos
                    = new BufferedOutputStream(
                new FileOutputStream(file,true))){

            bos.write(writable.getBytes(StandardCharsets.UTF_8));
            bos.flush();
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Error -> PostRepository.saveMessage()  method");
            return false;
        }
    }

    public List<Post> getAll(User user) {
        File file = new File(uploadPath + fileUtil.generatePathForPost(user));
        return extractPost(file);
    }



    public List<Post> extractPost(File mkdir){
        List<Post> rtnList = new ArrayList<>();
       try{
           for(File tmp : Objects.requireNonNull(mkdir.listFiles())) {

               byte[] bytes = Files.readAllBytes(Paths.get(tmp.getPath()));
               String line = new String(bytes, StandardCharsets.UTF_8);
               String senderStr = line.substring(line.indexOf(0), line.indexOf("|"));
               User sender = userService.getById(Integer.parseInt(senderStr));
               String dateTimeStr = line.substring(line.indexOf("|"), line.indexOf("~"));
               LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
               String content = line.substring(line.indexOf("*"), line.length());
               Post rtnPost = new Post(content,sender,dateTime);
               if (rtnPost.getContent() != null) {
                   rtnList.add(rtnPost);
               }
           }
            return rtnList;
        } catch (IOException e) {
            e.printStackTrace();
           System.out.println("error -> PostRepository.extractPost method");
            return Collections.emptyList();
        }
    }

    public boolean delete(File file){
        return file.delete();
    }
}
