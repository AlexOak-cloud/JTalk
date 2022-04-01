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

import java.io.File;
import java.time.LocalDateTime;

@Controller
public class MessageController {

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/msg")
    public ModelAndView msgGET(@ModelAttribute("msg") Message msg) {
        ModelAndView mav = new ModelAndView("/view/msg.html");
        mav.addObject("msg", new Message());
        return mav;
    }

    @PostMapping("/msg")
    public ModelAndView msgPOST(Message msg){
        User authUser = userService.getAuthUser();
        User recipient = userService.getById(3);
        msg.setSender(authUser);
        msg.setRecipient(recipient);
        msg.setRead(false);
        msg.setDateTime(LocalDateTime.now());
        File file =
                msgRepository.generateDialogFile(
                        authUser, recipient, fileUtil.generatePathForMessage());
        msgRepository.saveMessage(msg,file);
        return new ModelAndView("redirect:user/main");
    }
}
