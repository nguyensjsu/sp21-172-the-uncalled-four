package com.example.springrabbitmqhelloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;
  
    public static String question;

    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
		this.question = question;
	};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        this.template.convertAndSend(queue.getName(), question);
        System.out.println(" [x] Sent '" + question + "'");
    }
}