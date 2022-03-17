package app.repository;

import app.entity.User;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MusicRepository extends RepositoryCore {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;

    public void save(String localMkdir, MultipartFile file) {
        super.save(localMkdir, file);
    }


    public List<String> getAllByUser(User user) {
        String localMkdir = "/" + fileUtil.generateLocalPath(user) + "music/" ;
        return super.getAllByUser(localMkdir);
    }
}
