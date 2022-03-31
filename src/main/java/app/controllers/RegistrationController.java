package app.controllers;

import app.entity.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public ModelAndView regisGet(@ModelAttribute("user")User user){
        ModelAndView mav = new ModelAndView("/view/registration.html");
        mav.addObject("user",new User());
        return mav; 
    }

    @PostMapping("/registration")
    public ModelAndView regisPost(User user){
        userService.saveUser(user);
        return new ModelAndView("redirect:/login");
    }
}
