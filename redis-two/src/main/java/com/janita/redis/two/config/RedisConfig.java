package com.janita.redis.two.config;

import com.janita.redis.two.util.RedisUtilsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Janita on 2017/3/14 0014.
 * redis配置
 */
@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(env.getProperty("redis.user.host").trim());
        jedisConnectionFactory.setPort(Integer.parseInt(env.getProperty("redis.user.port").trim()));
        jedisConnectionFactory.setPassword(env.getProperty("redis.user.password").trim());
        jedisConnectionFactory.setDatabase(Integer.parseInt(env.getProperty("redis.user.database").trim()));
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

    /**
     * 注入的是自己封装的redis模板
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisUtilsTemplate redisTemplate(){
        RedisUtilsTemplate redisTemplate = new RedisUtilsTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

}
