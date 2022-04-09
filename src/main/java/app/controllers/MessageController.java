package app.controllers;


import app.entity.Dialog;
import app.entity.Message;
import app.entity.User;
import app.repository.MsgRepository;
import app.services.MsgService;
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
    private MsgService msgService;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/msg/get/{id}")
    public ModelAndView msgGET(@PathVariable("id")int id,
                               @ModelAttribute("message")Message message){
        User sender = userService.getAuthUser();
        User recipient = userService.getById(id);
        File dialogFile = fileUtil.generateLocalDialogFile(sender, recipient);
        Dialog dialog = msgService.getDialog(dialogFile);
        ModelAndView mav = new ModelAndView("/view/msg/msg.html");
        mav.addObject("msgs",dialog.getMsgs().isEmpty());
        mav.addObject("recipient", recipient);
        mav.addObject("test", dialog.getFile().isFile());
        mav.addObject("sender", sender);
        mav.addObject("message",new Message());
        return mav;
    }


    @PostMapping("/msg/post/{id}")
    public ModelAndView msgPOST(@PathVariable("id")int id, Message message){
        User sender = userService.getAuthUser();
        User recipient = userService.getById(id);
        File senderFile = fileUtil.generateUploadDialogFile(sender,recipient);
        File recipientFile= fileUtil.generateUploadDialogFile(recipient, sender);
        message.setSender(userService.getAuthUser());
        message.setDateTime(LocalDateTime.now());
        message.setRead(false);
        msgService.saveMessage(message,senderFile);
        msgService.saveMessage(message,recipientFile);
        return new ModelAndView("redirect:/msg/get/" + id);
    }


}
