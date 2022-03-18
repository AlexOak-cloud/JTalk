package app.controllers;


import app.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MusicController {


    @Autowired
    private MusicService musicService;

    @GetMapping("/my_music")
    public ModelAndView myMusic() {
        return new ModelAndView("/view/music/list.html");
    }

    @GetMapping("/music/new")
    public @ResponseBody
    ModelAndView uploadMusicGET() {
        return new ModelAndView("/view/music/new.html");
    }

    @PostMapping("/music/new")
    public @ResponseBody
    ModelAndView uploadMusicPOST(@RequestParam("file") MultipartFile file) {
        musicService.save(file);
        return new ModelAndView("redirect:/user/main");
    }

    @GetMapping("/music/get_by_id/{id}")
    public ModelAndView getById(@PathVariable("id") int id) {
        return new ModelAndView("/view/music/getById.html");
    }
}