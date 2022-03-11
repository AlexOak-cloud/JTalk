package app.controllers;

import app.entity.Image;
import app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository<Image> imageRepository;


    @GetMapping("/my_images")
    public ModelAndView imageSet(){
        return new ModelAndView("/view/image/main.html");
    }

    @GetMapping("/new_image")
    public ModelAndView newImage(){
        return new ModelAndView("/view/image/new.html");
    }

    @PostMapping("/new_image")
    public ModelAndView newImagePost(@RequestBody File image){

        imageRepository.save(image);
        return new ModelAndView("redirect:/user/main");

    }
}
