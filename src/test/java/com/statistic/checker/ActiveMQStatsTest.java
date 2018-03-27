package com.statistic.checker;

import com.statistic.checker.configuration.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:test-stats.properties")
@Import(TestConfiguration.class)
@SpringBootTest
public class ActiveMQStatsTest {
    @Autowired
    private ActiveMQStats activeMQStats;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void shouldReturnNoOfMessageInQueue() throws JMSException {
        jmsTemplate.setDefaultDestinationName("some-topic");
        jmsTemplate.convertAndSend("some-message");
        long actualCount = activeMQStats.checkMessageCountOnAllBroker();
        assertEquals(actualCount, 1);
    }
}
