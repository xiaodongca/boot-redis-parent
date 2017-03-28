package com.janita.redis.two;

import com.janita.redis.two.bean.Shop;
import com.janita.redis.two.bean.User;
import com.janita.redis.two.util.RedisUtils;
import com.janita.redis.two.util.RedisUtilsTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Janita on 2017/3/14 0014
 * reids-one
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTwoApplicationTests {

    @Resource(name = "redisTemplate")
    private RedisUtilsTemplate template;

    public void print(Object param){
        System.out.println("*******"+param);
    }
    private static final String KEY_STRING = "strKey";
    private static final String KEY_OBJECT = "objectKey";

    @Test
    public void testSetKeyOfString(){
        RedisUtils.setKeyOfString(template,KEY_STRING,"cxd");
    }

    @Test
    public void testGetStringOfKey(){
        print(RedisUtils.getStringOfKey(template,KEY_STRING));
    }

    @Test
    public void testSetObjectOfKey(){
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("朱晨剑");

        User user1 = new User();
        user1.setId(2);
        user1.setAge(18);
        user1.setName("朱晨利");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);

        RedisUtils.setKeyOfObject(template,KEY_OBJECT,list);
    }

    @Test
    public void testGetObjectOfKey(){
        print(RedisUtils.getObjectOfKey(template,KEY_OBJECT));



    }

    @Test
    public void testDeleteKey(){
        RedisUtils.deleteKey(template,KEY_OBJECT);
    }

    @Test
    public void testDeleteKeys(){
        Set<String> keys = new HashSet<>();
        keys.add(KEY_OBJECT);
        keys.add(KEY_STRING);
        RedisUtils.deleteKeys(template,keys);
    }

    @Test
    public void testIsKeyExists(){
        print(RedisUtils.isKeyExists(template,KEY_OBJECT));
    }

    @Test
    public void testExpire(){
        RedisUtils.setExpire(template,KEY_OBJECT,500);
    }

    @Test
    public void testGetExpire(){
        print(RedisUtils.getExpireSeconds(template,KEY_OBJECT));
    }

    @Test
    public void testPersist(){
        print(RedisUtils.persistKey(template,KEY_OBJECT));
    }

    @Test
    public void type(){
        print(RedisUtils.getTypeOfKey(template,KEY_OBJECT));
    }

    @Test
    public void getSetString(){
        print(RedisUtils.getSetString(template,"jan","new Jan"));
    }

    @Test
    public void getSetObject(){
        Shop old = new Shop();
        old.setId("1");
        old.setName("商店");
        old.setAddress("地址");
        old.setRemark("不备注");
        RedisUtils.setKeyOfObject(template,"shop",old);

        Shop ne = new Shop();
        ne.setRemark("bz");
        ne.setAddress("address");
        ne.setId("2");

        print(RedisUtils.getSetObject(template,"shop",ne));
        print(RedisUtils.getObjectOfKey(template,"shop"));

    }

    @Test
    public void mSetStr(){
        Map<String,String> map = new HashMap<>();
        map.put("a","aaaa");
        map.put("b","bbbbb");
        RedisUtils.setManyKeysString(template,map);

        print(RedisUtils.getManyKeysString(template,map.keySet()));
    }

    @Test
    public void mSetObj(){
        Shop old = new Shop();
        old.setId("1");
        old.setName("商店");
        old.setAddress("地址");
        old.setRemark("不备注");
        RedisUtils.setKeyOfObject(template,"shop",old);

        Shop ne = new Shop();
        ne.setRemark("bz");
        ne.setAddress("address");
        ne.setId("2");

        Map<String,Shop> map = new HashMap<>();
        map.put("s1",old);
        map.put("s2",ne);
        RedisUtils.setManyKeysObject(template,map);

        print(RedisUtils.getManyKeysObject(template,map.keySet()));
    }

    @Test
    public void append(){
        print(RedisUtils.append(template,"s1","append"));
        print(RedisUtils.getObjectOfKey(template,"s1"));
    }

    @Test
    public void hashSet(){
        Shop ne = new Shop();
        ne.setRemark("bz");
        ne.setAddress("address");
        ne.setId("2");

        print(RedisUtils.hSet(template,"hash","2",ne));
        print(RedisUtils.hSet(template,"hash","3","是的发生的发生的范范的"));
        print(RedisUtils.hGet(template,"hash","3"));


        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("朱晨剑");

        print(RedisUtils.hSet(template,"hash","4",user));
        print(RedisUtils.hGet(template,"hash","4"));

        RedisUtils.hSet(template,"hash","5",100);
        print(RedisUtils.hGet(template,"hash","5"));
    }

    @Test
    public void testHDel(){
//        RedisUtils.hDelete(template,"hash","5");
        print(RedisUtils.hExists(template,"hash","5"));
        print(RedisUtils.hGetAll(template,"hash"));
    }
    @Test
    public void testHGetAll(){
        RedisUtils.hSet(template,"hash","6","80");
        print(RedisUtils.hKeys(template,"hash"));
        print(RedisUtils.hLen(template,"hash"));
    }

    @Test
    public void hMSet(){
        Map<String,Object> map = new HashMap<>();
        map.put("1","dsfsdfsdf");
        map.put("2",new User());
        map.put("3","你好");

        RedisUtils.hMSet(template,"hash",map);

        print(RedisUtils.hGetAll(template,"hash"));
    }
}
