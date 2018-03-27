package com.statistic;

import com.statistic.checker.ActiveMQStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private final ActiveMQStats activeMQStats;

    @Autowired
    public Application(ActiveMQStats activeMQStats) {
        this.activeMQStats = activeMQStats;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(false).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        long count = activeMQStats.checkMessageCountOnAllBroker();
        LOGGER.info("Number of Message on ActiveMq is {}", count);
    }
}
