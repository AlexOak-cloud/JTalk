package app.controllers;

import app.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/my_images")
    public ModelAndView imageSet() {
        return new ModelAndView("/view/image/main.html");
    }

    @GetMapping("/new_image")
    public @ResponseBody
    ModelAndView newImageGet() {
        return new ModelAndView("/view/exemple.html");
    }

    @PostMapping("/new_image")
    public @ResponseBody
    ModelAndView newImagePost(@RequestParam("file") MultipartFile file) {
        imageService.save(file);
        return new ModelAndView("redirect:/user/main");
    }
}