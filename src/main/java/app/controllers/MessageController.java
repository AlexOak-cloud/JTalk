package app.controllers;


import app.entity.Dialog;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.time.LocalDateTime;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MsgRepository msgRepository;

    @GetMapping("/msg/{id}")
    public ModelAndView msgGET(@PathVariable("id")int id,
                               @ModelAttribute("message")Message message){
        User sender = userService.getAuthUser();
        User recipient = userService.getById(id);
        File dialogFile = msgRepository.getFile(sender, recipient);
        Dialog dialog = msgRepository.getDialog(dialogFile);
        ModelAndView mav = new ModelAndView("/view/msg/msg.html");
        mav.addObject("recipient", recipient);
        mav.addObject("sender", sender);
        mav.addObject("dialog", dialog);
        mav.addObject("message",new Message());
        return mav;
    }

    @PostMapping("/msg/{id}")
    private ModelAndView msgPOST(Message message){
        File savable = msgRepository.saveMessage()
    }


}
