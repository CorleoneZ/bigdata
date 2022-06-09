package com.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.RetriableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

public class MyProducer {
    private final static Logger logger = LoggerFactory.getLogger(MyProducer.class);

    private String TOPIC = "work_metric"; //kafka主题 把消息发布到这个主题 自定义
    protected static String IP = "10.11.40.201";//kafka 消息系统 IP
    protected static String PORT = "9092";   //kafka端口号

    private Properties properties = MyProperties.getProperties();//kafka消费者配置
    private static MyProducer producer = new MyProducer();

    public static MyProducer getInstance() {  //单例
        if (producer == null) {
            producer = new MyProducer();
        }
        return producer;
    }

    /*{"user_id":63401,"item_id":6244,"cat_id":143,"action":"pv","province":3,"ts":1573445919}*/
    public void send() {
        Producer<String, String> producer = new KafkaProducer<>(properties);
        KafkaData data = new KafkaData();
        data.setUser_id(63402);
        data.setItem_id(6244);
        data.setCat_id(144);
        data.setAction("pvs");
        data.setProvince(1);
        data.setTs(1573445919);
        ProducerRecord record = new ProducerRecord<String, KafkaData>(TOPIC, data);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    logger.info("kafka send successful");
                } else {
                    //对kafka可重试异常，消息第一次发送失败再次重试。
                    if (e instanceof RetriableException) {
                        //处理可重试异常
                        try {
                            logger.error("kafka send fail Retry sending.");
                            Thread.sleep(3000);
                            MyProducer.getInstance().send();
                        } catch (InterruptedException e1) {
                            logger.error("kafka error :", e1);
                        }
                    } else {//对于kafka来说，不可重试异常，抛出。
                        throw new KafkaException("kafka server message error.");
                    }
                }
            }
        });
        //关闭 以免造成资源泄露
        producer.close();
    }

    //内部类的使用
    class MyProperties {
        public static Properties getProperties() {
            Properties props = new Properties();
            //集群地址，多个服务器用 逗号 ","分隔
            props.put("bootstrap.servers", IP + ":" + PORT);
            //key 的序列化，此处以字符串为例，使用kafka已有的序列化类
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            //发送字符串可以使用StringSerializer这个序列化器
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            //使用自定义序列化器 发送自定义数据/json数据
            props.put("value.serializer", "com.kafka.producer.KafkaEntityDataSerializer");
            props.put("request.required.acks", "1");
            return props;
        }
    }

}