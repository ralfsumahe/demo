package com.example.demo;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testHash(){
        redisTemplate.delete("hashTest");

        Map<String,String> datas = ImmutableMap.of("00:01", "1","00:02","2","00:03","3");
        redisTemplate.opsForHash().putAll("hashTest", datas);
        redisTemplate.opsForHash().put("hashTest", "00:05", "5");

        Map<String,Integer> map = redisTemplate.opsForHash().entries("hashTest");


        assertThat(map, hasEntry("00:01","1"));
        assertThat(map, hasEntry("00:02","2"));
        assertThat(map, hasEntry("00:03","3"));

        assertThat(map, hasEntry("00:05", "5"));

        redisTemplate.expire("hashTest", 10*60, TimeUnit.SECONDS);

        System.out.println(map);




    }

    public static void main(String[] args) {
        ImmutableMap map = ImmutableMap.of("1","1","2","2");
        map.keySet();
        System.out.println(map.keySet());
        List list = (List)map.keySet().stream().collect(Collectors.toList());
//        System.out.println(list);
    }
}
