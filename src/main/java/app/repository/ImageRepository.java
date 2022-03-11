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
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ImageRepository <T extends Image> implements SQLQuery, RepositoryCore <Image>{

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;


    @Autowired
    private DataSource dataSource;

    @Override
    public void save(File file) {
        try(Statement statement = dataSource.getConnection().createStatement()){
            File rtnFile = imageService.generateFile(userService.getAuthUser(),file);
            statement.executeUpdate(String.format(SQLQuery.saveImage,
                    rtnFile.getName(),
                    rtnFile.getPath(),
                    LocalDateTime.now()));
        }catch (SQLException ex){
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
