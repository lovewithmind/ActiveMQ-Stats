package com.statistic.checker.configuration;


import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.plugin.StatisticsBrokerPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public BrokerService broker(@Value("${spring.activemq.broker-url}") String uri) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector(uri);
        brokerService.setPlugins(new BrokerPlugin[]{new StatisticsBrokerPlugin()});
        brokerService.setDestinations(new ActiveMQDestination[]{new ActiveMQQueue("ActiveMQ.DLQ")});
        return brokerService;
    }

}
