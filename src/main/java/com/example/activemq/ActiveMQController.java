package com.example.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/active")
public class ActiveMQController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public String messageSend(@PathVariable("message") String message) {
        jmsTemplate.convertAndSend("Queue",message);
        return "sendComment";
    }
}
