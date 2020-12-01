package com.cai.controller;

import com.cai.redis.RedisService;
import com.cai.utilEntity.FasterJsonTool;
import com.cai.utilEntity.MessageBox;
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 用redis实现分布式锁 保证数据唯一性
 */
@RestController()
@RequestMapping(value = "/redisLock")
public class RedisLockController {

    private final String LOCK = "LOCK";

    @Autowired
    RedisTemplate redisTemplate;
//    @Autowired
//    Redisson redisson;

    @Autowired
    private RedisService redisService;
//    @RequestMapping(value = "getRedisLock")
//    public Callable<MessageBox> getRedisLock(){
//        Callable<MessageBox> callable = new Callable<MessageBox>(){
//            @Override
//            public MessageBox call() throws Exception {
//
//                //TODO 1.生成一个钥匙 就用
////                String lock = UUID.randomUUID ().toString ();
////                //TODO  在保证出错释放锁
////                Boolean ifAbsent = redisTemplate.opsForValue ().setIfAbsent (LOCK,lock,30l, TimeUnit.SECONDS );
//                //TODO 获取锁 分布式锁 用redisson
//                RLock rLock = redisson.getLock (LOCK);
//                //TODO 加锁
//                rLock.lock();
//                try {
//                //TODO 业务
//
//                }catch (Exception e){
//                    e.printStackTrace ();
//                }finally {
//                    //TODO 释放锁
//                    rLock.unlock ();
////                    Object object = redisService.get (LOCK);
////                    if(object != null){
////                        String lockStr = (String)object;
////                        if(lockStr.equals (lock)){
////                            redisService.remove (LOCK);
////                        }
////                    }
//                }
//                //TODO 业务
//                return MessageBox.build ( "200","ok" );
//            }
//        };
//        return callable;
//    }


    @RequestMapping(value = "/testRedisList")
    public Callable<MessageBox> testRedisList(){
        Callable<MessageBox> callable = new Callable<MessageBox>(){
            @Override
            public MessageBox call() throws Exception {
                try {
                    //TODO 业务
                    //TODO list 坐进 右出
                    redisTemplate.boundListOps ( "TransferNotAllowed1111" ).leftPush (213783);
                    redisTemplate.boundListOps ( "TransferNotAllowed1111" ).leftPush (896568);
//                    Object o1 = redisTemplate.boundListOps ( "TransferNotAllowed1111" ).leftPop ();
//                    Object o2 = redisTemplate.boundListOps ( "TransferNotAllowed1111" ).leftPop ();
                    //map
                    //redisTemplate.boundHashOps ("game_log").put (playId+":"+userId,hashMap );

                }catch (Exception e){
                    e.printStackTrace ();
                }
                //TODO 业务
                return MessageBox.build ( "200","ok" );
            }
        };
        return callable;
    }


}
