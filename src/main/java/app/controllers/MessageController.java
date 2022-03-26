package app.controllers;


import app.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message getMessage(Message message){
        System.out.println(message);
        return message;
    }
}
