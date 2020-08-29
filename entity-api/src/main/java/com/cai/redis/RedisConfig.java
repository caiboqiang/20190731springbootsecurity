package com.cai.redis;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {
	
	@Autowired
	 private RedisConnectionFactory redisConnectionFactory;
		@Bean
		public Redisson redisson(){
			Config config = new Config ( );
			config.useSingleServer().setAddress( "http://123.57.108.75:6379" );
			config.useSingleServer().setDatabase ( 0 );
			config.useSingleServer().setConnectionMinimumIdleSize(1);
			config.useSingleServer ().setPassword ( "123456" );
			return (Redisson)Redisson.create (config);
		}

	    /**
	     * 实例化 RedisTemplate 对象
	     * 
	     */
	    @Bean
	    public RedisTemplate<String, Object> functionDomainRedisTemplate() {
	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	        this.initRedisTemplate(redisTemplate, redisConnectionFactory);
	        return redisTemplate;
	    }

	    /**
	     * 序列化设置
	     */
	    private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
	        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
	        redisTemplate.setConnectionFactory(factory);
	    }

	    @Bean
	    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
	        return redisTemplate.opsForHash();
	    }

	    @Bean
	    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
	        return redisTemplate.opsForValue();
	    }

	    @Bean
	    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
	        return redisTemplate.opsForList();
	    }

	    @Bean
	    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
	        return redisTemplate.opsForSet();
	    }

	    @Bean
	    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
	        return redisTemplate.opsForZSet();
	    }
}
