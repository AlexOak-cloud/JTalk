package app.services;

import app.entity.User;
import app.repository.RepositoryMediaCore;
import app.utills.FileUtil;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService extends RepositoryMediaCore {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String path;

    @Value("${emerging.jpg}")
    private String emergingImg;


    public void save(MultipartFile file) {
        String localMkdir = fileUtil.generateLocalPath(userService.getAuthUser()) + "image/";
        if(!file.isEmpty()) {
            super.save(localMkdir,file, SQLQuery.saveImage);
        } else {
            System.out.println("-> Empty_file");
        }
    }

    public void saveMain(MultipartFile file){

        String localMkdir = fileUtil.generateLocalPath(userService.getAuthUser()) + "main_image/";
        if(!file.isEmpty()){
            super.save(localMkdir, file, SQLQuery.saveImage);
        } else {
            System.out.println("-> Empty_file");
        }
    }

    public String getMainByUser(User user){
        String localMkdir = fileUtil.generateLocalPath(user) + "main_image/";
        if(super.getAllByUser(localMkdir).isEmpty()) {
            return emergingImg;
        } else {
            return super.getAllByUser(localMkdir).get(0);
        }
    }


    public List<String> getAllByUser(User user) {
        String localMkdir = fileUtil.generateLocalPath(user) + "image/";
        return super.getAllByUser(localMkdir);
    }

    @Override
    public String getById(long id) {
        return super.getById(id);
    }
}
