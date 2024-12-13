package com.t1.demo_web_springboot.queue.service;

import com.t1.demo_web_springboot.queue.QueueFacade;
import com.t1.demo_web_springboot.queue.XmlQueueFacade;
import com.t1.demo_web_springboot.queue.config.QueueCondition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("XmlQueueFacade")
@Conditional(QueueCondition.class)
public class XmlQueueFacadeImpl implements XmlQueueFacade {
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
                exchange
                , routingKey
                , message
                , new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        if (delayMillisecond != null) {
                            message.getMessageProperties().setDelayLong(Long.valueOf(delayMillisecond));
                        }
                        if (messagePriority != null) {
                            message.getMessageProperties().setPriority(messagePriority);
                        }
                        return message;
                    }
                });
    }

    @Override
    public void convertAndSend(String exchange, String routingKey, Object message, String title1, String title2) {
        logger.info("Insert message to rabbit Queue");

        amqpTemplate.convertAndSend(
                exchange
                , routingKey
                , message
                , new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setTimestamp(new Date());
                        message.getMessageProperties().setHeader("title1", title1);
                        message.getMessageProperties().setHeader("title2", title2);
                        return message;
                    }
                }
        );

        System.out.println("End of Insert message");
    }


}
