package com.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 5:10
 * @Version 1.0
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * @Description
     * 缓存管理器,@Cacheable等注解生效
     * @Param [redisConnectionFactory]
     * @Author Jay.Jia
     * @Date 2020/5/17 7:08
     * @return org.springframework.cache.CacheManager
     **/
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //这种方式比较节省空间
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 配置序列化
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = config.serializeKeysWith(RedisSerializationContext.SerializationPair
                .fromSerializer(redisSerializer))
                .entryTtl(Duration.ofMinutes(2)) // 设置缓存有效期一分钟
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));


        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        // 配置连接工厂
//        template.setConnectionFactory(factory);
//        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
//        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        ObjectMapper om = new ObjectMapper();
//        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jacksonSeial.setObjectMapper(om);
//        // 值采用json序列化
//        template.setValueSerializer(jacksonSeial);
//        //使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        // 设置hash key 和value序列化模式
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(jacksonSeial);
//        template.afterPropertiesSet();
//        return template;
//    }

    /**
     * @Description 直接使用redisTemplate时的配置
     * @Param [factory]
     * @Author Jay.Jia
     * @Date 2020/5/17 7:09
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     **/
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 值采用json序列化
        template.setValueSerializer(stringRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringRedisSerializer);
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
