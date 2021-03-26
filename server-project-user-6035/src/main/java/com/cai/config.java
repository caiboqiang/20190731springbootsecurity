package com.cai;

import com.cai.redis.RedisService;
//import org.redisson.Redisson;
//import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
//    @Bean
//    public Redisson redisson(){
//        Config config = new Config ( );
//        config.useSingleServer().setAddress( "http://39.100.91.195:6379" );//39.100.91.195:6379 123.57.108.75:6379
//        config.useSingleServer().setDatabase ( 0 );
//        config.useSingleServer().setConnectionMinimumIdleSize(1);
//        config.useSingleServer ().setPassword ( "wuhan123456@" ); //wuhan123456@   123456
//        return (Redisson)Redisson.create (config);
//    }
    @Bean
    public RedisService redisService(){
        return new RedisService();
    }
}
