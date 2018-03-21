package com.burak.redis.service;

import com.burak.redis.model.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> stringRedisTemplate;
    private final RedisTemplate<String,List<String>> listRedisTemplate;

    public String getStringFromRedis(String key) {
       return stringRedisTemplate.opsForValue().get(key);
    }

    public void addStringToRedis(KeyValue keyValue) {
        stringRedisTemplate.opsForValue().set(keyValue.getKey(), keyValue.getValue());
    }

    public List<String> getListFromRedis(String key) {
        return listRedisTemplate.opsForValue().get(key);
    }

    public void addListToRedis(String key, List<String> list) {
        listRedisTemplate.opsForValue().set(key, list);
    }
}
