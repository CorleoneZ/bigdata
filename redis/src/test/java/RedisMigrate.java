import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * -------string--------
 * fht:workflow:id:%d:platId:%s:instance -> fht:workflow:id:%d:instance
 * fht:workflow:id:%d:platId:%s:elements -> fht:workflow:id:%d:elements
 * fht:workflow:platId:%s:element:id:%d:instance -> fht:workflow:element:id:%d:instance
 *
 * ---------set---------
 * fht:workflow:platId:%s:element:id:%d:start:audience -> fht:workflow:element:id:%d:start:audience
 *
 * --------hash---------
 * fht:workflow:platId:%s:element:id:%d:reached:openIds -> fht:workflow:element:id:%d:reached:openIds
 * fht:workflow:platId:%s:element:id:%d:reached:contactIds -> fht:workflow:element:id:%d:reached:contactIds
 */
public class RedisMigrate {

    private Jedis jedis_ppe;
    private Jedis jedis_local;

    @Before
    public void setJedis() {
        //连接ppe环境redis服务器
        jedis_ppe = new Jedis("10.11.20.112", 6380);
        jedis_ppe.auth("op03RhBk");

        //连接本地redis服务器
        jedis_local = new Jedis("127.0.0.1", 6379);
        jedis_local.auth("123456");

        System.out.println("连接服务成功");
    }

    /**
     * 按照key的类型分为string、set、hash三类
     * 分别对三类key 正则get到所有key-value 存入map
     * 遍历 map 对key进行字符串替换得到map_keyTrans；map & map_keyTrans数据写入redis-local
     * 遍历 map & map_keyTrans =》 map & map_keyTrans数据写入redis-local + map_keyTrans写入redis-ppe
     */
    //fht:workflow:platId:L8B3Q17P47:element:id:12063:instance
    public String transform(String key) {
        if (key.contains("platId")) {
            String temp = key.replaceFirst("platId:.*?:","");
            return temp;
        } else {
            return key;
        }
    }

    @Test
    public void doEvent() {
        Set<String> keys = jedis_ppe.keys("fht:workflow:id*:events");
        for (String oldKey : keys) {
            String newKey = transform(oldKey);
            System.out.println( oldKey + " : " + newKey);
            jedis_ppe.rename(oldKey,newKey);

        }
        System.out.println("key events size: " + keys.size());
    }

    @Test
    public void doTypeString() {
        Set<String> keys1 = jedis_ppe.keys("fht:workflow:platId:*:instance");
        Set<String> keys2 = jedis_ppe.keys("fht:workflow:id:*:platId:*:elements");
        for (String s : keys2) {
            System.out.println(s);
            jedis_ppe.del(s);
        }
        System.out.println("key instance size: " + keys1.size() + "   key elements size: " + keys2.size());

        /*Map<String,String> map = new HashMap<>();
        Map<String,String> map_keyTrans = new HashMap<>();

        // all type keys get save into map and transform
        allKeys.addAll(keys1);
        allKeys.addAll(keys2);
        for (String key : allKeys) {
            String value = jedis_ppe.get(key);
            map.put(key, value);

            String keyTrans = transform(key);
            map_keyTrans.put(keyTrans, value);
        }

        // print all type keys after transform
        for (String key : map_keyTrans.keySet()) {
            jedis_ppe.set(key, map_keyTrans.get(key));
            //jedis_local.set(key, map_keyTrans.get(key));
        }

        // save all into local redis server
        for (String key : map.keySet()) {
            jedis_local.set(key, map.get(key));
        }
        System.out.println(map.size());*/
    }

    @Test
    public void doTypeSet() {
        Set<String> keys = jedis_ppe.keys("fht:workflow:platId:*:audience");
        Set<String> newKey = jedis_ppe.keys("fht:workflow:*:audience");
        System.out.println("type set keys size: " + keys.size());
        //System.out.println("type set keys size: " + newKey.size());
        for (String s : keys) {
            System.out.println(s);
            jedis_ppe.del(s);
        }

        /*Map<String,Set<String>> map = new HashMap<>();
        Map<String,Set<String>> map_keyTrans = new HashMap<>();

        for (String key : keys) {
            Set<String> value = jedis_ppe.smembers(key);
            map.put(key, value);

            String keyTrans = transform(key);
            map_keyTrans.put(keyTrans, value);
        }

        for (String key : map_keyTrans.keySet()) {
            for (String valueStr : map_keyTrans.get(key)) {
                //jedis_local.sadd(key,valueStr);
                jedis_ppe.sadd(key,valueStr);
                //System.out.println("single value: " + valueStr);
            }
            //System.out.println("key = " + key);
        }
        System.out.println(map_keyTrans.size());

        for (String key : map.keySet()) {
            for (String valueStr : map.get(key)) {
                jedis_local.sadd(key,valueStr);
                //System.out.println("single value: " + valueStr);
            }
        }*/
    }
    @Test
    public void doTypeHash() {
        Set<String> allKeys = new HashSet<>();
        Set<String> keys1 = jedis_ppe.keys("fht:workflow:platId:*:openIds");
        //Set<String> keys2 = jedis_ppe.keys("fht:workflow:*:contactIds");
        //System.out.println("key openIds size: " + keys1.size() + "   key contactIds size: " + keys2.size());
        //jedis_ppe.rename("fht:workflow:element:id:45431:reached:openIds","fht:workflow:platId:7982e40a1309174f:element:id:45431:reached:openIds");
        //jedis_ppe.rename("fht:workflow:element:id:46492:reached:openIds","fht:workflow:platId:6587732166bc7bfc:element:id:46492:reached:openIds");
        for (String oldKey : keys1) {
            String newKey = transform(oldKey);
            System.out.println(newKey);
            //jedis_ppe.del(newKey);
            jedis_ppe.rename(oldKey,newKey);
            /*Map<String, String> map = jedis_ppe.hgetAll(oldKey);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jedis_ppe.hset(newKey,entry.getKey(),entry.getValue());
            }*/
        }


        /*for (String key : map.keySet()) {
            jedis_local.hmset(key, map.get(key));
        }*/
    }

}
