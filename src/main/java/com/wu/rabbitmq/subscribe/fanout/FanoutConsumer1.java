package com.wu.rabbitmq.subscribe.fanout;

import com.rabbitmq.client.*;
import com.wu.rabbitmq.util.ConnectionUtils;

import java.io.IOException;

public class FanoutConsumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        String queue1Name = "test_fanout_queue1";
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
                System.out.println("将日志信息打印到控制台.....");
            }
        };
        channel.basicConsume(queue1Name, true, consumer);
    }
}
