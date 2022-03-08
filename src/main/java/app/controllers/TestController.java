package app.controllers;


import app.repository.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping("/hello")
    public ModelAndView testGet(){
        ModelAndView mav = new ModelAndView("/view/hello.html");
        mav.addObject("image", "/home/alex_oak/IT/IdeaProjects/Resorses/jIZJytzpnjQ.jpg");
        return mav;
    }
}
