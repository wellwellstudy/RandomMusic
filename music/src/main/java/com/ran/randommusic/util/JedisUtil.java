package com.ran.randommusic.util;

import org.apache.log4j.Logger;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by com on 17/7/30.
 */
public class JedisUtil {
    private static Logger logger = Logger.getLogger(JedisUtil.class);
    private static JedisPool pool; //线程池对象
    private static int MAX_IDLE=10;
    private static int MAX_WAIT=100000;
    private static int MAX_ACTIVE=50;
    private static boolean TEST_ON_BORROW = true;
    private static boolean TEST_ON_RETURN=true;
    private static String ADDR = "127.0.0.1";
    private static int PORT = 6379;
    private static int TIMEOUT = 10000;
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setTestOnReturn(TEST_ON_RETURN);
        pool = new JedisPool(config, ADDR, PORT, TIMEOUT);
    }
    public static Jedis getJedisResource(){
        try{
            if(pool!=null){
                return pool.getResource();
            }
            return null;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static String set(String key,Object object){
        Jedis client = getJedisResource();
        String objStr = JsonUtil.getJsonStr(object);
        return client.set(key,objStr);
    }

    public static String set(String key,Object object,int second){
        Jedis client = getJedisResource();
        String objStr = JsonUtil.getJsonStr(object);
        return client.setex(key,second,objStr);
    }

    public static Object get(String key,Class clazz){
        Jedis client = getJedisResource();
        String objStr = client.get(key);
        return JsonUtil.convertToObject(objStr,clazz);
    }

    public static void expire(String key,int second){
        Jedis client = getJedisResource();
        client.expire(key,second);
    }


    public static void main(String [] args){
        Jedis client = JedisUtil.getJedisResource();
        client.hset("family", "lbq", "65"); //同Redis命令行中的hset操作，如名为family的set不存在，则创建set并放入名为lbq的元素，值为65
        client.hset("family", "zjz", "62"); //Redis不支持int类型，如不传String则会报错。
        client.hset("family","hxr","18");
        logger.info(client.hmget("family", "lbq", "zjz"));

        Map<String,String> testMap1 = new HashMap<String,String>();
        testMap1.put("num1", "1"); //此处同上面，不能传非String型
        testMap1.put("num2", "15");
        testMap1.put("num3", "606");
        testMap1.put("num4", "1024");
        client.hmset("testMap1", testMap1); //传入整个map对象入redis
        logger.info(client.hmget("testMap1", "num1", "num2", "num3"));

        client.zadd("score",100,"com");
        client.zadd("score",60, "tu");
        client.zadd("score",88.8,"jian");
        client.zadd("score",60,"lin");
        client.zadd("score",99,"com");
        Set<Tuple> set = client.zrevrangeWithScores("score", 0, 5);
        for(Tuple s: set){
            logger.info(s.getElement() + ":" + s.getScore());
        }
        logger.info(client.zscore("score", "com"));
        logger.info(client.zrangeByScoreWithScores("score", 60, 99));
        logger.info(client.ping());


        client.set("key", "hello");
        logger.info(client.strlen("key"));
        long value = client.setnx("key","world");
        if(value == 0){
            logger.error("存在");
        }else if(value == 1 ){
            logger.error("不存在");
        }
        logger.info(client.get("key"));
        set("key",6);
        client.decrBy("key", 3);
    }

}
