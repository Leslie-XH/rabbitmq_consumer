package com.wu.rabbitmq.subscribe.topic;

import com.rabbitmq.client.*;
import com.wu.rabbitmq.util.ConnectionUtils;

import java.io.IOException;

public class TopicConsumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        String queue1Name = "test_topic_queue1";
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body："+new String(body));
                System.out.println("将日志信息存储到数据库.....");
            }
        };
        channel.basicConsume(queue1Name,true,consumer);
    }
}
