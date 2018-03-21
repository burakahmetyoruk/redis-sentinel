package com.burak.redis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.sentinel")
@Getter
@Setter
public class SentinelNodeProperties {

    private String master;

    private List<String> nodes;
}
