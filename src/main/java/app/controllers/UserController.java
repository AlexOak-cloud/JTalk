package app.controllers;

import app.entity.User;
import app.services.ImageService;
import app.services.MusicService;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/user/main")
    public ModelAndView userMainGet(){
        User authUser = userService.getAuthUser();
        ModelAndView mav = new ModelAndView("/view/user/main.html");
        mav.addObject("user",userService.getAuthUser());
        mav.addObject("allImages",imageService.getAllByUser(authUser));
        mav.addObject("allSongs",musicService.getAllByUser(authUser));
        mav.addObject("allVideos",videoService.getAllByUser(authUser));
        return mav;
    }
}
