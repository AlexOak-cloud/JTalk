package app.repository;

import app.entity.User;
import app.services.UserService;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RepositoryCore implements SQLQuery {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private DataSource dataSource;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    public void save(String localMkdir, MultipartFile file){
            File savableFile = new File(uploadPath + "/" + localMkdir);
            if(!savableFile.exists()) {
                savableFile.mkdirs();
            }
            String savableFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String savablePath = localMkdir + savableFileName;
            try (Statement statement = dataSource.getConnection().createStatement();
                 BufferedOutputStream bos = new BufferedOutputStream(
                         new FileOutputStream(
                                 uploadPath  + "/" + savablePath)
                 )) {
                statement.executeUpdate(String.format(
                        SQLQuery.saveImage,
                        savableFileName,
                        savablePath,
                        LocalDateTime.now(),
                        userService.getAuthUser().getId()));
                byte[] bytes = file.getBytes();
                bos.write(bytes);
                bos.flush();

            } catch (SQLException | IOException ex) {
                System.out.println("-> Error in RepositoryCore.save()");
                ex.printStackTrace();
            }
    }

    public List<String> getAllByUser(String localMkdir) {
        List<String> paths = new ArrayList<>();
        File file = new File(uploadPath + "/" + localMkdir);
        if(!file.exists()){
            file.mkdirs();
        }
        for(File tmp : Objects.requireNonNull(file.listFiles())){
            paths.add(localMkdir +  tmp.getName());
        }
        return paths;
    }

    /** Returning path for looking file */
    public String getById(long id){
        String rtn = "File is Empty";
        try(Statement statement = dataSource.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(
                    String.format(SQLQuery.getImageById, id));
            while (resultSet.next()){
                rtn = resultSet.getString(1);
            }
            return rtn;
        }catch (SQLException ex){
            ex.printStackTrace();
            return rtn;
        }
    }


}


