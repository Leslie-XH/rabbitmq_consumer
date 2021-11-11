package com.wu.rabbitmq.work;

import com.rabbitmq.client.*;
import com.wu.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
/**
 * 两个消费者 默认是均分的
 * 设置根据消费者自动争抢须要：
 *  1、关闭自动确认 channel.basicConsume(QUEUE_NAME,false,consumer);
 *  2、添加手动确认代码 channel.basicAck(envelope.getDeliveryTag(), false);
 *   3、//消费掉一个消息后再去消费另一个  channel.basicQos(1);
 */
public class WorkConsumer2 {
    static final String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body："+new String(body));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false); //第二个参数是是否确认多个消息
            }
        };
//        channel.basicConsume(QUEUE_NAME,true,consumer);
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
