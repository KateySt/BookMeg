package com.example.activemq;

import com.example.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/active")
public class ActiveMQController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(value = "/send/{message}", produces = "text/html")
    public String messageSend(@PathVariable("message") String message) {
        jmsTemplate.convertAndSend("Queue",message);
        return "done";
    }
}
