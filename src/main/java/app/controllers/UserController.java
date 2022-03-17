package app.controllers;

import app.repository.ImageRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/user/main")
    public ModelAndView userMainGet(){
        ModelAndView mav = new ModelAndView("/view/user/main.html");
        mav.addObject("user",userService.getAuthUser());
        return mav;
    }

}
