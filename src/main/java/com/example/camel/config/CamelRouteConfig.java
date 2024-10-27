package com.example.camel.config;


import com.example.camel.route.CamelRoute;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class CamelRouteConfig {


//    @Bean
//    public CamelContext camelContext() {
//        DefaultCamelContext defaultCamelContext = new DefaultCamelContext();
//        defaultCamelContext.addRoute(new CamelRoute());
//        defaultCamelContext.addComponent("activemq", getActiveMQComponent());
//        return defaultCamelContext;
//    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(jacksonJmsMessageConverter());
        return template;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        factory.setUserName("admin");
        factory.setPassword("admin");
        return factory;
    }


    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    private ActiveMQComponent getActiveMQComponent() {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setBrokerURL("tcp://localhost:61616");
        activeMQComponent.setUsername("admin");
        activeMQComponent.setPassword("admin");
        activeMQComponent.setConcurrentConsumers(10);
        activeMQComponent.setIdleConsumerLimit(10);
        activeMQComponent.setMaxConcurrentConsumers(30);
        return activeMQComponent;
    }
}
