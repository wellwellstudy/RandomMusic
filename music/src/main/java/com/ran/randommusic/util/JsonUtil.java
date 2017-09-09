package com.ran.randommusic.util;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.util.List;

/**
 * Created by com on 17/9/5.
 */
public class JsonUtil {
    private static Logger logger = Logger.getLogger(JsonUtil.class);
    private static ObjectMapper om = new ObjectMapper();
    static{
        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
    }

    public static String getJsonStr(Object obj){
        String json = null;
        if(obj!=null){
            try{
                json = om.writeValueAsString(obj);
            }catch (IOException ex){
                logger.error(ex.getMessage(),ex);
            }
        }
        return json;
    }
    public static <T> T convertToObject(String json,Class<T> clazz){
        T obj = null;
        try{
            obj = om.readValue(json,clazz);
        }catch (IOException ex){
            logger.error(ex.getMessage(),ex);
        }
        return obj;
    }
    public static <T> List<T> convertToList(String json,Class<T> clazz){
        List<T> list = null;
        try{
            list = om.readValue(json,om.getTypeFactory().constructType(List.class,clazz));
        }catch (IOException ex){
            logger.error(ex.getMessage(),ex);
        }
        return list;
    }
}
