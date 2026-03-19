package com.groupeisi.commandeservice.config;
import org.springframework.cache.annotation.EnableCaching; import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.*; import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*; import java.time.Duration;
@Configuration @EnableCaching
public class RedisConfig {
    @Bean public RedisCacheManager cacheManager(RedisConnectionFactory cf) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(cf).cacheDefaults(config).build();
    }
}
