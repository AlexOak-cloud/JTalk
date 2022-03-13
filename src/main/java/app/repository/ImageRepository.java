package app.repository;

import app.entity.Image;
import app.entity.User;
import app.services.UserService;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

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
                        LocalDateTime.now()));
                byte[] bytes = file.getBytes();
                bos.write(bytes);
                bos.flush();

            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }else {
            System.out.println("error in ImageRepository.save method");
        }
    }

    @Override
    public Image getById(long id) {

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
