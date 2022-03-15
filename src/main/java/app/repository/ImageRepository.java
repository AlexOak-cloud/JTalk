package app.repository;

import app.entity.Image;
import app.entity.User;
import app.services.UserService;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ImageRepository <T extends Image> implements SQLQuery, RepositoryCore <Image>{

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public void save(MultipartFile file) {
        if(fileUtil.checkExtensionForImage(file)) {
            File savableFile = new File(fileUtil.generatePathForImages());
            savableFile.mkdirs();
            try (Statement statement = dataSource.getConnection().createStatement();
                 BufferedOutputStream bos = new BufferedOutputStream(
                         new FileOutputStream(savableFile + "/" + file.getOriginalFilename())
                 )) {
                statement.executeUpdate(String.format(
                        SQLQuery.saveImage,
                        file.getOriginalFilename(),
                        savableFile + "/" + file.getOriginalFilename(),
                        LocalDateTime.now(),
                        userService.getAuthUser().getId()));
                byte[] bytes = file.getBytes();
                bos.write(bytes);
                bos.flush();

            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }else {
            System.out.println("error in ImageRepository.save()");
        }
    }

    public byte[] getOne(File file){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            BufferedImage bi = ImageIO.read(file);
            ImageIO.write(bi, "jpeg", baos);
            baos.flush();
            byte[] bytes = baos.toByteArray();
            return bytes;
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Image getById(long id) {
        return null;
    }

    @Override
    public Image getById(int id) {
        return null;
    }

    @Override
    public List<String> getAllByUser(User user) {
       return null;
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public void deleteById(int id) {
    }
}
