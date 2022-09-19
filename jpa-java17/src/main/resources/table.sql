insert into iparllay.fht_scrm_event(`plat_id` ,`event_id` ,`open_id` ,`event_type` ,`event_content` ,`raw_data` ,`event_source` ,`event_time` ) values ('1','1','1','1','1','1','1',1656398515126)
insert into iparllay.fht_scrm_event(`raw_data`) values ('\u003cxml\u003e\u003cToUserName\u003e\u003c![CDATA[gh_831878ebae0c]]\u003e\u003c/ToUserName\u003e\n\u003cFromUserName\u003e\u003c![CDATA[okO8Gt9em5q1e7A2lTmoG9ENukKk]]\u003e\u003c/FromUserName\u003e\n\u003cCreateTime\u003e1656397796\u003c/CreateTime\u003e\n\u003cMsgType\u003e\u003c![CDATA[event]]\u003e\u003c/MsgType\u003e\n\u003cEvent\u003e\u003c![CDATA[TEMPLATESENDJOBFINISH]]\u003e\u003c/Event\u003e\n\u003cMsgID\u003e2462988005503303684\u003c/MsgID\u003e\n\u003cStatus\u003e\u003c![CDATA[success]]\u003e\u003c/Status\u003e\n\u003c/xml\u003e')

CREATE TABLE iparllay.fht_scrm_event(`plat_id` String,`event_id` String,`open_id` String,`event_type` String,`user_id` String,`union_id` String,`raw_data` String,`event_source` String,`seq_id` Int64,`owner` String,`agent_id` Int32,`form_id` String,`customer_id` String,`contact_id` String, `business_id` Int32,`event_content` String,`event_time` Int64,`event_value` String,`event_row` String,`staff_id` Int32,`event_property1` String,`event_property2` String,`event_property3` String)ENGINE = ReplacingMergeTree(event_time) ORDER BY event_id
CREATE MATERIALIZED VIEW iparllay.fht_scrm_event_source_consumer TO iparllay.fht_scrm_event(`plat_id` String,`event_id` String,`open_id` String,`event_type` String,`event_content` String,`raw_data` String,`event_source` String,`event_time` String) AS SELECT JSONExtractString(msg, 'platId') AS plat_id,JSONExtractString(msg, 'eventId') AS event_id,JSONExtractString(msg, 'openId') AS open_id,JSONExtractString(msg, 'eventType') AS event_type,JSONExtractString(msg, 'content') AS event_content,JSONExtractString(msg, 'rawData') AS raw_data,JSONExtractString(msg, 'eventSource') AS event_source,JSONExtractString(msg, 'createTimeMS') AS event_time FROM iparllay.fht_scrm_event_source;
CREATE TABLE iparllay.fht_scrm_event_source(`msg` String)ENGINE = Kafka SETTINGS kafka_broker_list = '10.11.40.202:9092', kafka_topic_list = 'wechatEvent', kafka_group_name = 'report-clickhouse-cmgroup5', kafka_format = 'JSONAsString'

CREATE TABLE iparllay.event_test(`event_time` String)ENGINE = MergeTree
CREATE TABLE iparllay.event_test_source(`msg` String)ENGINE = Kafka SETTINGS kafka_broker_list = '10.11.40.202:9092', kafka_topic_list = 'wechatEvent', kafka_group_name = 'test-event8', kafka_format = 'JSONAsString'
CREATE MATERIALIZED VIEW iparllay.event_test_source_consumer TO iparllay.event_test(`event_time` String) AS SELECT JSONExtractString(msg, 'createTimeMS') AS event_time FROM iparllay.event_test_source


Hibernate: select wechatuser0_.plat_id as plat_id1_0_, wechatuser0_.gender as gender2_0_, wechatuser0_.head_image_url as head_ima3_0_, wechatuser0_.language_id as language4_0_, wechatuser0_.nickname as nickname5_0_, wechatuser0_.open_id as open_id6_0_, wechatuser0_.user_id as user_id7_0_ from wechat_user wechatuser0_ where wechatuser0_.open_id=?
WechatUser(platId=12, userId=12, openId=12, nickname=12, gender=12, languageId=12, headImageUrl=12)
2022-06-29 17:39:59.083  INFO 20945 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'persistenceUnit'
2022-06-29 17:39:59.083  INFO 20945 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2022-06-29 17:39:59.086  INFO 20945 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0