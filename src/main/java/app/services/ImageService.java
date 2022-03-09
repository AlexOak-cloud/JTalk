package app.services;


import app.entity.Image;
import app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageService {

    @Autowired
    private ImageRepository<Image> imageRepository;

    public Image getImageByFile(File file){
      Image image = new Image();
      image.setName(file.getName());
      return image;
    }


}
