package com.burak.redis.controller;

import com.burak.redis.model.KeyValue;
import com.burak.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @GetMapping(value = "/get/string/{key}")
    public @ResponseBody ResponseEntity<String> getStringFromRedis(@PathVariable String key) {
        String value =  redisService.getStringFromRedis(key);

        return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/add/string")
    public @ResponseBody ResponseEntity<String> addStringToRedis(@RequestBody KeyValue keyValue) {
        redisService.addStringToRedis(keyValue);
        return ResponseEntity.ok("Success");
    }

    @GetMapping(value = "/get/list/{key}")
    public @ResponseBody ResponseEntity<List<String>> getListFromRedis(@PathVariable String key) {
        List<String> list = redisService.getListFromRedis(key);
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "add/list/{key}")
    public @ResponseBody ResponseEntity<String> addListToRedis(@PathVariable String key, @RequestBody List<String> stringList) {
        redisService.addListToRedis(key, stringList);
        return ResponseEntity.ok("Success");
    }
}
