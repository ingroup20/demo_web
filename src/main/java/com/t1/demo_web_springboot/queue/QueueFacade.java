package com.t1.demo_web_springboot.queue;

import com.t1.demo_web_springboot.exceptions.EinvQueueException;
import org.springframework.amqp.AmqpException;


public interface QueueFacade {

    public void convertAndSend(String exchange, String routingKey, Object message);

    public void convertAndSend(String exchange, String routingKey, Object message, Integer messagePriority, Integer delayMillisecond);



}
