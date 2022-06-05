package com.example.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {
    @JmsListener(destination = "Queue")
    public void processMessages(String message){
        System.out.println("Received: "+ message);
    }
}
