package com.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaEntityDataSerializer implements Serializer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private String encoding = "UTF8";
    private ObjectMapper mapper;

    @Override
    public void configure(Map map, boolean b) {
        mapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String s, Object data) {
        try {
            return mapper.writeValueAsString(data).getBytes(encoding);
        } catch (Exception e) {
            logger.error("My Deserializer error:", e);
        }
        return null;
    }

    @Override
    public void close() {

    }


}
