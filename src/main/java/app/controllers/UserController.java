package app.controllers;

import app.entity.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/main")
    public ModelAndView userMainGet(){
        ModelAndView mav = new ModelAndView("/view/user/main.html");
        User authUser = userService.getAuthUser();
        mav.addObject("user",authUser);
        return mav;
    }

}
