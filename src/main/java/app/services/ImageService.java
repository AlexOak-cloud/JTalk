package app.services;

import app.entity.User;
import app.repository.RepositoryCore;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService extends RepositoryCore {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private UserService userService;



    public void save(MultipartFile file) {
        String localMkdir = fileUtil.generateLocalPath(userService.getAuthUser()) + "image/";
        if(!file.isEmpty()) {
            super.save(localMkdir,file, SQLQuery.saveImage);
        } else {
            System.out.println("-> Empty_file");
        }
    }


    public List<String> getAllByUser(User user) {
        String localMkdir = fileUtil.generateLocalPath(user) + "image/";
        return super.getAllByUser(localMkdir);
    }
}
