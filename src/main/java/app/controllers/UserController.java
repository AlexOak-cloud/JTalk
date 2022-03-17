package app.controllers;

import app.repository.ImageRepository;
import app.services.ImageService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class UserController {

    @Value("${upload.path}")
    private String path;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/user/main")
    public ModelAndView userMainGet(){
        ModelAndView mav = new ModelAndView("/view/user/main.html");
        mav.addObject("user",userService.getAuthUser());
        mav.addObject("path",path);
        mav.addObject("test", "asd/3/image/as.jpeg");
        mav.addObject("allImages",imageService.getAllByUser(userService.getAuthUser()));
        return mav;
    }

}
