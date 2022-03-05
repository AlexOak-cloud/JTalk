package app.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping("/hello")
    public ModelAndView helloGet(){
        return new ModelAndView("/view/hello.html");
    }
}
