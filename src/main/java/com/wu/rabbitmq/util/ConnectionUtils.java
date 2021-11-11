package com.wu.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wu.rabbitmq.constant.MqConstant;

public class ConnectionUtils {
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(MqConstant.HOST);
        //端口
        factory.setPort(MqConstant.PORT);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername(MqConstant.USERNAME);
        factory.setPassword(MqConstant.PASSWORD);
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = ConnectionUtils.getConnection();
        System.out.println(con);

        con.close();
    }
}
