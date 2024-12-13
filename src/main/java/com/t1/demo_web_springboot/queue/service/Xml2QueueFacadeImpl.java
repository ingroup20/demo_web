package com.t1.demo_web_springboot.queue.service;

import com.t1.demo_web_springboot.queue.QueueFacade;
import com.t1.demo_web_springboot.queue.XmlQueueFacade;
import com.t1.demo_web_springboot.queue.config.QueueCondition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service("Xml2QueueFacade")
@Conditional(QueueCondition.class)
public class Xml2QueueFacadeImpl implements XmlQueueFacade {
    protected final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void convertAndSend(String exchange, String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    @Override
    public void convertAndSend(String exchange, String routingKey, Object message, Integer messagePriority, Integer delayMillisecond) {
        amqpTemplate.convertAndSend(
                exchange,
                routingKey,
                message,
                message1 -> {
                    message1.getMessageProperties().setDelayLong(Long.valueOf(delayMillisecond));
                    message1.getMessageProperties().setPriority(messagePriority);
                    return message1;
                });
    }

    @Override
    public void convertAndSend(String exchange, String routingKey, Object message, String title1, String title2) {
        amqpTemplate.convertAndSend(
                exchange,
                routingKey,
                message,
                message1 -> {
                    message1.getMessageProperties().setHeader("title1", title1);
                    message1.getMessageProperties().setHeader("title2", title2);
                    return message1;
                });
    }
}
