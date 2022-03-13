package app.controllers;

import app.entity.Image;
import app.repository.ImageRepository;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository<Image> imageRepository;

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
        imageRepository.save(file);
        return new ModelAndView("redirect:/user/main");
    }


    //        try {
//            File rtnFile = new File(fileUtil.generatePathForImages());
//            rtnFile.mkdirs();
//            byte[] bytes = file.getBytes();
//            BufferedOutputStream bos =
//                    new BufferedOutputStream(new FileOutputStream(rtnFile + "/"+ file.getOriginalFilename()));
//            bos.write(bytes);
//            bos.flush();
//            bos.close();
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }

}