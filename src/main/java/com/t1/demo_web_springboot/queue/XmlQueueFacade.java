package com.t1.demo_web_springboot.queue;

public interface XmlQueueFacade extends QueueFacade{

    //    void convertAndSendToPend(String exchange, String routingKey, String message) throws AmqpException, EinvQueueException;
//
//    void convertAndSendToTestTemplate(String exchange, String routingKey, Long message, String email) throws AmqpException, EinvQueueException;
//
//    void convertAndSendToMessageQueue(String exchange, String routingKey, String jsonString) throws AmqpException, EinvQueueException;

    public void convertAndSend(String exchange, String routingKey, Object message, String title1, String title2) ;
}
