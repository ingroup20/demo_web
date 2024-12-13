package com.t1.demo_web_springboot;

import com.t1.demo_web_springboot.queue.XmlQueueFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;

public class QueueTest {

    @Qualifier("XmlQueueFacade")
    XmlQueueFacade queueFacade;

    @Qualifier("Xml2QueueFacade")
    XmlQueueFacade queueFacade2;

    @Test
    public void testXmlQueueFacade() {
        queueFacade.convertAndSend("exchange", "routingKey", "message", "title1", "title2");
    }

    @Test
    public void testXml2QueueFacade() {
        queueFacade2.convertAndSend("exchange", "routingKey", "message", "title1", "title2");
    }
}
