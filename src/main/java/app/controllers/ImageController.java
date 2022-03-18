package app.controllers;

import app.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/my_images")
    public ModelAndView myImage() {
        return new ModelAndView("/view/image/main.html");
    }

    @GetMapping("/image/new")
    public @ResponseBody
    ModelAndView uploadImageGET() {
        return new ModelAndView("/view/image/new.html");
    }

    @PostMapping("/image/new")
    public @ResponseBody
    ModelAndView uploadImagePOST(@RequestParam("file") MultipartFile file) {
        imageService.save(file);
        return new ModelAndView("redirect:/user/main");
    }

    @GetMapping("/image/get_by_id/{id}")
    public ModelAndView getById(@PathVariable("id")int id){
        return new ModelAndView("/view/image/getById.html");
    }
}