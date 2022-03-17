package app.services;

import app.entity.User;
import app.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService extends ImageRepository {

    @Override
    public void save(MultipartFile file) {
        if(!file.isEmpty()) {
            super.save(file);
        } else {
            System.out.println("-> Empty_file");
        }
    }

    @Override
    public List<String> getAllByUser(User user) {
        return super.getAllByUser(user);
    }
}
