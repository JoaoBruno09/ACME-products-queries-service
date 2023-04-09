package com.isep.acme.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RpcClient {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RpcClient(ConnectionFactory connectionFactory) {
        this.rabbitTemplate = new RabbitTemplate(connectionFactory);
    }

    public String callRpc(String request) {
        String replyTo = "products_queries_queue";
        String correlationId = UUID.randomUUID().toString();

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setReplyTo(replyTo);
        messageProperties.setCorrelationId(correlationId);

        Message requestMessage = new Message(request.getBytes(), messageProperties);
        Message replyMessage = rabbitTemplate.sendAndReceive("rpc_exchange", "rpc_products_queue", requestMessage);

        return new String(replyMessage.getBody());
    }

}
