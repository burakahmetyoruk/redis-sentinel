package com.burak.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RedisSentinelConfig {

    private final SentinelNodeProperties sentinelNodeProperties;

    @Value("${spring.redis.sentinel.master}")
    private String sentinelMasterName;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration());
        return redisConnectionFactory;
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        return new RedisSentinelConfiguration(sentinelNodeProperties.getMaster(),new HashSet<>(sentinelNodeProperties.getNodes()));
    }

    @Bean
    public RedisTemplate<String, String> redisTemplateForString() {
        RedisTemplate<String, String> redisTemplateForString = new RedisTemplate<>();
        redisTemplateForString.setConnectionFactory(redisConnectionFactory());
        redisTemplateForString.setKeySerializer(jackson2JsonRedisSerializer());
        redisTemplateForString.setValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplateForString;
    }

    @Bean
    public RedisTemplate<String, List<String>> redisTemplateForList() {
        RedisTemplate<String, List<String>> redisTemplateForList = new RedisTemplate<>();
        redisTemplateForList.setConnectionFactory(redisConnectionFactory());
        redisTemplateForList.setKeySerializer(jackson2JsonRedisSerializer());
        redisTemplateForList.setValueSerializer(jackson2JsonRedisSerializerForList());
        return redisTemplateForList;
    }

    @Bean
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer(String.class);
    }

    @Bean
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializerForList() {
        return new Jackson2JsonRedisSerializer(List.class);
    }

}
