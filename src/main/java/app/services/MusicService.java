package app.services;


import app.entity.User;
import app.repository.RepositoryMediaCore;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MusicService extends RepositoryMediaCore {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public String getById(long id) {
        return super.getById(id);
    }

    public void save(MultipartFile file) {
        String localMkdir = fileUtil.generateLocalPath(userService.getAuthUser()) + "music/";
        if(!file.isEmpty()){
            super.save(localMkdir, file, SQLQuery.saveMusic);
        } else {
            System.out.println("-> File is Empty");
        }
    }


    public List<String> getAllByUser(User user) {
        String localMkdir =  fileUtil.generateLocalPath(user) + "music/" ;
        return super.getAllByUser(localMkdir);
    }
}
