import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        jedis_ppe = new Jedis("10.11.40.220", 6379);

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
    public void doTypeString() {
        Set<String> allKeys = new HashSet<>();
        Set<String> keys1 = jedis_ppe.keys("fht:workflow:*:instance");
        Set<String> keys2 = jedis_ppe.keys("fht:workflow:*:elements");
        System.out.println("key instance size: " + keys1.size() + "   key elements size: " + keys2.size());

        Map<String,String> map = new HashMap<>();
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
            System.out.println("key = " + key);
        }
        System.out.println(map_keyTrans.size());

        // save all data into local redis server
        for (String key : map.keySet()) {
            jedis_local.set(key, map.get(key));
        }
    }

    @Test
    public void doTypeSet() {
        Set<String> keys = jedis_ppe.keys("fht:workflow:*:audience");
        System.out.println("type set keys size: " + keys.size());

        Map<String,Set<String>> map = new HashMap<>();
        Map<String,Set<String>> map_keyTrans = new HashMap<>();

        for (String key : keys) {
            Set<String> value = jedis_ppe.smembers(key);
            map.put(key, value);

            String keyTrans = transform(key);
            map_keyTrans.put(keyTrans, value);
        }

        for (String key : map_keyTrans.keySet()) {
            System.out.println("key = " + key);
        }
        System.out.println(map_keyTrans.size());

        for (String key : map.keySet()) {
            for (String valueStr : map.get(key)) {
                jedis_local.sadd(key,valueStr);
                System.out.println("single value: " + valueStr);
            }
        }
    }

    @Test
    public void doTypeHash() {
        Set<String> allKeys = new HashSet<>();
        Set<String> keys1 = jedis_ppe.keys("fht:workflow:*:openIds");
        Set<String> keys2 = jedis_ppe.keys("fht:workflow:*:contactIds");
        System.out.println("key openIds size: " + keys1.size() + "   key contactIds size: " + keys2.size());

        Map<String,Map<String,String>> map = new HashMap<>();
        Map<String,Map<String,String>> map_keyTrans = new HashMap<>();

        allKeys.addAll(keys1);
        allKeys.addAll(keys2);
        for (String key : allKeys) {
            Map<String,String> value = jedis_ppe.hgetAll(key);
            map.put(key, value);

            String keyTrans = transform(key);
            map_keyTrans.put(keyTrans, value);
        }

        for (String key : map_keyTrans.keySet()) {
            System.out.println("key = " + key);
            System.out.println(map_keyTrans.get(key));
        }
        System.out.println(map_keyTrans.size());

        for (String key : map.keySet()) {
            jedis_local.hmset(key, map.get(key));
        }
    }

}
