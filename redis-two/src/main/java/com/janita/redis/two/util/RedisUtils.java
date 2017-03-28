package com.janita.redis.two.util;

import java.util.Map;
import java.util.Set;

/**
 * Created by Janita on 2017-03-18 21:32
 * @see org.springframework.data.redis.connection.RedisStringCommands
 * @see org.springframework.data.redis.connection.RedisHashCommands
 * @see org.springframework.data.redis.connection.RedisSetCommands
 * @see org.springframework.data.redis.connection.RedisListCommands
 * @see org.springframework.data.redis.connection.RedisKeyCommands
 * @see org.springframework.data.redis.connection.RedisZSetCommands
 */

public class RedisUtils {
    /**
     * 删除某个key
     * @param template
     * @param key
     */
    public static void deleteKey(RedisUtilsTemplate template,String key){
        template.delete(key);
    }

    /**
     * 删除集合中所有的key
     * @param template
     * @param keys
     */
    public static void deleteKeys(RedisUtilsTemplate template, Set<String> keys){
        template.delete(keys);
    }

    /**
     * 为某个key设置过期时间
     * @param template
     * @param key
     * @param seconds
     */
    public static boolean setExpire(RedisUtilsTemplate template,String key,long seconds){
        return template.expire(key,seconds);
    }

    /**
     * 查询key的剩余时间
     * 以秒为单位,返回给定key的有效时间长，如果是-1则表示永远有效,当 key 不存在时，返回 -2 。
     * @param template
     * @param key
     * @return
     */
    public static long getExpireSeconds(RedisUtilsTemplate template,String key){
        return template.ttl(key);
    }

    /**
     * 取消key的过期时间,使其永久有效
     * @param template
     * @param key
     * @return
     */
    public static boolean persistKey(RedisUtilsTemplate template,String key){
        return template.persist(key);
    }

    /**
     * 判断key是否存在
     * @param template
     * @param key
     * @return
     */
    public static boolean isKeyExists(RedisUtilsTemplate template,String key){
        return template.isKeyExists(key);
    }

    /**
     * 回去key中存储数据的类型
     * @param template
     * @param key
     * @return
     */
    public static String getTypeOfKey(RedisUtilsTemplate template,String key){
        return template.type(key).code();
    }

    /**
     * 获取key的string值
     * @param template
     * @param key
     * @return
     */
    public static String getStringOfKey(RedisUtilsTemplate template ,String key){
        return template.get(key);
    }

    /**
     * 设置key的string值
     * @param template
     * @param key
     * @param value
     */
    public static void setKeyOfString(RedisUtilsTemplate template,String key,String value){
        template.set(key,value);
    }

    /**
     * 获取key的object值
     * @param template
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getObjectOfKey(RedisUtilsTemplate template,String key){
        return template.getObj(key);
    }

    /**
     * 为key设置object值
     * @param template
     * @param key
     * @param value
     * @param <T>
     */
    public static <T> void setKeyOfObject(RedisUtilsTemplate template,String key,T value){
        template.set(key,value);
    }

    /**
     * 把key的旧值替换为new string,返回old string
     * @param template
     * @param key
     * @param value
     * @return
     */
    public static String getSetString(RedisUtilsTemplate template,String key,String value){
        return template.getSetString(key,value);
    }

    /**
     * 把key的旧值替换为new object,返回旧old object
     * @param template
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T getSetObject(RedisUtilsTemplate template,String key,T value){
        return template.getSetObject(key,value);
    }

    /**
     * 为多个key设置他们的string value
     * @param template
     * @param pairs
     */
    public static void setManyKeysString(RedisUtilsTemplate template,Map<String,String> pairs){
        template.mSetString(pairs);
    }

    /**
     * 获取多个key的值
     * 返回map
     * @param template
     * @param keys
     * @return
     */
    public static Map<String,String> getManyKeysString(RedisUtilsTemplate template, Set<String> keys){
        return template.mget(keys);
    }

    /**
     * 为多个key设置他们的object value
     * @param template
     * @param pairs
     * @param <T>
     * @see org.springframework.data.redis.connection.RedisStringCommands
     */
    public static <T> void setManyKeysObject(RedisUtilsTemplate template,Map<String,T> pairs){
        template.mSetObject(pairs);
    }

    /**
     * 为多个key获取他们的object value
     * @param template
     * @param keys
     * @param <T>
     * @see org.springframework.data.redis.connection.RedisStringCommands
     * @return
     */
    public static <T> Map<String,T> getManyKeysObject(RedisUtilsTemplate template,Set<String> keys){
        return template.mgetObj(keys);
    }

    /**
     * 为key的string值的后面添加appendStr
     *
     * 若在key存入对象时,对此key调用此方法,则会导致
     * value无法反序列号为对象
     * 此方法只能用于key存入的是string
     *
     * @param template
     * @param key
     * @param appendStr
     * @see org.springframework.data.redis.connection.RedisStringCommands
     */
    public static Long append(RedisUtilsTemplate template,String key,String appendStr){
        return template.appendToStringValue(key,appendStr);
    }

    /****************string start***************/

    /****************hash start***************/

    /**
     * 把一个object设置到hash的key中
     * @param template
     * @param key
     * @param field
     * @param value
     * @param <T>
     * @see org.springframework.data.redis.connection.RedisHashCommands
     */
    public static <T> Boolean hSet(RedisUtilsTemplate template, String key, String field, T value){
        return template.hashSet(key,field,value);
    }

    /**
     * 从hash key中获取field所对应的value
     * @param template
     * @param key
     * @param field
     * @param <T>
     * @see org.springframework.data.redis.connection.RedisHashCommands
     * @return
     */
    public static <T> T hGet(RedisUtilsTemplate template, String key, String field){
        return template.hashGet(key,field);
    }

    /**
     * 删除key hash中某个field
     * @param template
     * @param key
     * @param field
     */
    public static Long hDelete(RedisUtilsTemplate template,String key,String field){
        return template.hashDelete(key,field);
    }

    /**
     * 判断某个hash中的field是否存在
     * @param template
     * @param key
     * @param field
     * @return
     */
    public static Boolean hExists(RedisUtilsTemplate template,String key,String field){
        return template.hashExists(key,field);
    }

    /**
     * 获取hash中的所有字段+值的集合
     * @param template
     * @param key
     * @param <T>
     * @return
     */
    public static <T> Map<String,T> hGetAll(RedisUtilsTemplate template,String key){
        return template.hashGetAll(key);
    }

    /**
     * 获取哈希表中的所有字段名。
     * @param template
     * @param key
     * @return
     */
    public static Set<String> hKeys(RedisUtilsTemplate template,String key){
        return template.hashKeys(key);
    }

    /**
     * 获取哈希表中字段的数量。
     * @param template
     * @param key
     * @return
     */
    public static Long hLen(RedisUtilsTemplate template,String key){
        return template.hashLen(key);
    }

    /**
     * 同时将多个 field-value (字段-值)对设置到哈希表中
     * @param template
     * @param key
     * @param pairs
     * @param <T>
     */
    public static <T> void hMSet(RedisUtilsTemplate template,String key,Map<String,T> pairs){
        template.hashMSet(key,pairs);
    }




    /****************hash end***************/
}
