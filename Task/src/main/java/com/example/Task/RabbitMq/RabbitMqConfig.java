package com.example.Task.RabbitMq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
   /* @Value("${user.deleted.queue}")
    private String userDeletedQueueName;

    @Bean
    public Queue userDeletedQueue() {
        return new Queue(userDeletedQueueName, true);
    }*/
    /*@Bean
    Queue tasksQueue() {
        return new Queue("tasksQueue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("tasksExchange");
    }

    @Bean
    Binding binding(Queue tasksQueue, DirectExchange exchange) {
        return BindingBuilder.bind(tasksQueue).to(exchange).with("tasksRouting");
    }*/


    @Bean
    Queue deleteTasksQueue() {
        return new Queue("deleteTasksQueue", false);
    }

    @Bean
    Queue getTasksQueue() {
        return new Queue("getTasksQueue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("tasksExchange");
    }

    @Bean
    Binding deleteTasksbinding(Queue deleteTasksQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteTasksQueue).to(exchange).with("deleteTasksRouting");
    }

    @Bean
    Binding GetTasksbinding(Queue getTasksQueue, DirectExchange exchange) {
        return BindingBuilder.bind(getTasksQueue).to(exchange).with("getTasksRouting");
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate newRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setReplyTimeout(10000);
        rabbitTemplate.setReceiveTimeout(10000);
        return rabbitTemplate;
    }
}
