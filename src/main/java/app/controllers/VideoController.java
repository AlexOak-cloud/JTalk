package app.controllers;

import app.services.MusicService;
import app.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/my_video")
    public ModelAndView myVideo() {
        return new ModelAndView("/view/video/list.html");
    }

    @GetMapping("/video/new")
    public @ResponseBody
    ModelAndView uploadVideoGET() {
        return new ModelAndView("/view/video/new.html");
    }

    @PostMapping("/video/new")
    public @ResponseBody
    ModelAndView uploadVideoPOST(@RequestParam("file") MultipartFile file) {
        videoService.save(file);
        return new ModelAndView("redirect:/user/main");
    }

    @GetMapping("/video/get_by_id/{id}")
    public ModelAndView getById(@PathVariable("id") int id) {
        return new ModelAndView("/view/video/getById.html");
    }

}
