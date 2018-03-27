package com.statistic.checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ActiveMQStats {
    @Autowired
    private final JmsTemplate jmsTemplate;

    private static String ACTIVEMQ_STATISTICS_ALL_BROKER = "ActiveMQ.Statistics.Broker";
    private static String ACTIVEMQ_DESTINATION = "ActiveMQ.Statistics.Destination.";

    public ActiveMQStats(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Long checkMessageCountOnAllBroker() throws JMSException {
        MapMessage mapMessage = (MapMessage) getStats(ACTIVEMQ_STATISTICS_ALL_BROKER);
        return mapMessage.getLong("size");
    }

    public Long checkMessageCountOnDestination(String destination) throws JMSException {
        String statsDestination = String.format(ACTIVEMQ_DESTINATION + "{}", destination);
        MapMessage mapMessage = (MapMessage) getStats(statsDestination);
        return mapMessage.getLong("size");
    }

    public Message getStats(String destination) {
        return jmsTemplate.sendAndReceive(destination, Session::createMessage);
    }
}

