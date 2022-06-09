package com.kafka.producer;

public class Test {

    public static void main(String[] args) {
        MyProducer producer = MyProducer.getInstance();
        producer.send();
    }

    /**
     * CREATE TABLE kafka_work_metric_src (
     *     user_id UInt64 COMMENT '用户id',
     *     item_id UInt64 COMMENT '商品id',
     *     cat_id UInt16  COMMENT '品类id',
     *     action String  COMMENT '行为',
     *     province UInt8 COMMENT '省份id',
     *     ts UInt64      COMMENT '时间戳'
     *   ) ENGINE = Kafka()
     *     SETTINGS
     *     kafka_broker_list = '10.11.40.201:9092',
     *     kafka_topic_list = 'work_metric',
     *     kafka_group_name = 'group-work_metric',
     *     kafka_format = 'JSONEachRow'
     *
     *
     *     CREATE TABLE kafka_work_metric (
     *     user_id UInt64 COMMENT '用户id',
     *     item_id UInt64 COMMENT '商品id',
     *     cat_id UInt16  COMMENT '品类id',
     *     action String  COMMENT '行为',
     *     province UInt8 COMMENT '省份id',
     *     ts UInt64      COMMENT '时间戳'
     *   ) ENGINE = MergeTree()
     *     ORDER BY user_id
     *
     *     CREATE MATERIALIZED VIEW work_metric_consumer TO kafka_work_metric
     *     AS SELECT * FROM kafka_work_metric_src ;
     *
     *     select * from kafka_work_metric
     *
     * ;
     */
}
