package app.services;

import app.entity.User;
import app.repository.RepositoryCore;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class VideoService extends RepositoryCore {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;


    public void save(MultipartFile file) {
        String localMkdir = fileUtil.generateLocalPath(userService.getAuthUser()) + "video/";
        if(!file.isEmpty()){
            super.save(localMkdir, file, SQLQuery.saveVideo);
        } else {
            System.out.println("-> File is Empty");
        }
    }

    public List<String> getAllByUser(User user) {
        String localMkdir =  fileUtil.generateLocalPath(user) + "video/" ;
        return super.getAllByUser(localMkdir);
    }
}
