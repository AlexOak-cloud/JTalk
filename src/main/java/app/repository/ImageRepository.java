package app.repository;

import app.entity.User;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class ImageRepository extends RepositoryCore{

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;



    public void save(MultipartFile file) {
        String localMkdir = fileUtil.generateLocalPath(userService.getAuthUser()) + "image/";
        super.save(localMkdir, file);
    }


    public List<String> getAllByUser(User user) {
        String localMkdir =  fileUtil.generateLocalPath(user) + "image/" ;
        return super.getAllByUser(localMkdir);
    }


}
