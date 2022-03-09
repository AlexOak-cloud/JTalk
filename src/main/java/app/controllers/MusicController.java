package app.controllers;


import org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MusicController {

    @GetMapping("/music")
    public ModelAndView musicGet(){
        ModelAndView mav = new ModelAndView("/view/music/main.html");

        return mav;
    }
}
