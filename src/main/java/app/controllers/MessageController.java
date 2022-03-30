package app.controllers;


import app.entity.Message;
import app.entity.User;
import app.repository.MsgRepository;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageReceivingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

    @Autowired
    private MsgRepository dialogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/message/{id}")
    public ModelAndView messageGET(@RequestParam("id")int id,
                                   @ModelAttribute("message")Message message){
        ModelAndView mav = new ModelAndView("/view/message.html");
        mav.addObject("recipient",userService.getById(id));
        mav.addObject("message", new Message());
        return mav;
    }

    @PostMapping("/message/{id}")
    public ModelAndView messagePOST(@RequestParam("id")int id, Message message){
        dialogRepository.save(message,userService.getById(id));
        return new ModelAndView("redirect:/user/main");
    }

//    @MessageMapping("/change-message")
//    @SendTo("/topic/activity")
//    public Message change(Message message){
//        return null;
//    }
}
