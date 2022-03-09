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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ImageRepository <T extends Image> implements SQLQuery, RepositoryCore <Image>{

    @Autowired
    private UserService userService;


    @Autowired
    private DataSource dataSource;

    @Override
    public void save(Image image) {
        FileUtil fu = new FileUtil();
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement(SQLQuery.saveImage)){
            File rtnFile = fu.generateFile(userService.getAuthUser(),image);
            ps.setString(1,image.getName());
            ps.setString(2,rtnFile.getPath());
            ps.setString(3,LocalDateTime.now().toString());
            ps.execute();
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
