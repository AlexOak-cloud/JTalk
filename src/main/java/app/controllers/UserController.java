package app.controllers;

import app.repository.ImageRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        File file = new File("/home/alex_oak/IT/IdeaProjects/source/asd/3/image/as.jpeg");
        return mav;
    }

}
