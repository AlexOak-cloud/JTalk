package app.repository;

import app.entity.Image;
import app.entity.User;
import app.services.ImageService;
import app.services.UserService;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ImageRepository <T extends Image> implements SQLQuery, RepositoryCore <Image>{

    public static final String PATH = "/home/alex_oak/IT/IdeaProjects/source/";


    @Autowired
    private UserService userService;

    @Override
    public void save(File file) {
        FileUtil fileUtil = new FileUtil();
        try (FileOutputStream fos = new FileOutputStream(
                fileUtil.generateFile(
                         file))) {
            Files.copy(Paths.get(file.getPath()), fos);
        } catch (IOException ex) {
            ex.printStackTrace();
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
    public List<Image> getAllByUser(User user) {
        return null;
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public void deleteById(int id) {
    }
}
