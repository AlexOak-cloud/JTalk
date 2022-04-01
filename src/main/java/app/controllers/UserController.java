package app.controllers;

import app.entity.User;
import app.repository.MsgRepository;
import app.services.ImageService;
import app.services.MusicService;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private MsgRepository msgRepository;


    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/user/main")
    public ModelAndView userMainGet(){
        User authUser = userService.getAuthUser();
        ModelAndView mav = new ModelAndView("/view/user/main.html");
        mav.addObject("user",authUser);
        mav.addObject("avatar",imageService.getMainByUser(authUser));
        mav.addObject("images",imageService.getAllByUser(authUser));
        mav.addObject("songs",musicService.getAllByUser(authUser));
        File file = new File(
                "/home/alex_oak/IT/IdeaProjects/source/users/Sasha/2/msg/2_3.txt"
        );
        mav.addObject("boolean",file.isFile());
        mav.addObject("list",msgRepository.extractMsgs(file));
        return mav;
    }
}
