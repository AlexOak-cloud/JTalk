package app.controllers;


import app.entity.Dialog;
import app.entity.Message;
import app.entity.User;
import app.repository.MsgRepository;
import app.services.UserService;
import app.utills.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/msg/get/{id}")
    public ModelAndView msgGET(@PathVariable("id")int id,
                               @ModelAttribute("message")Message message){
        User sender = userService.getAuthUser();
        User recipient = userService.getById(id);
        File dialogFile = msgRepository.generateFile(sender, recipient);
        if(!dialogFile.exists()){
            msgRepository.generateFile(sender,recipient);
        }
        Dialog dialog = msgRepository.getDialog(dialogFile);
        ModelAndView mav = new ModelAndView("/view/msg/msg.html");
        mav.addObject("recipient", recipient);
        mav.addObject("test", dialog.getPath());
        mav.addObject("sender", sender);
//        mav.addObject("dialog", dialog.getPath());
        mav.addObject("message",new Message());
        return mav;
    }


    @PostMapping("/msg/post/{id}")
    public ModelAndView msgPOST(@PathVariable("id")int id, Message message){
        User sender = userService.getAuthUser();
        User recipient = userService.getById(id);
        File senderFile = msgRepository.generateFile(sender,recipient);
        File recipientFile= msgRepository.generateFile(recipient, sender);
        message.setSender(userService.getAuthUser());
        message.setDateTime(LocalDateTime.now());
        message.setRead(false);
        msgRepository.saveMessage(message,senderFile);
        msgRepository.saveMessage(message,recipientFile);
        return new ModelAndView("redirect:/msg/get/" + id);
    }


}
