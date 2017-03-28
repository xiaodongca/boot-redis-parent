package com.janita.redis.one;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by Janita on 2017/3/14 0014
 * reids-one
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisOneApplicationTests {

    @Autowired
    private StringRedisTemplate template;

    @Test
    public void testRedis(){
        ValueOperations<String,String> operations = template.opsForValue();
        operations.set("id", UUID.randomUUID().toString());
    }
}
