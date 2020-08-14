package com.cai.clientController;

import com.cai.entity.Orders;
import com.cai.service.OrdersService;
import com.cai.utilEntity.MessageBox;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
@RestController
public class feignClientController {

    @Autowired
    OrdersService ordersService;

    @GetMapping(value = "/getF")
    public String getF(){
        System.err.println (feignClientController.class.getClassLoader ().toString ());

        return  ordersService.getF();
       // return "ok";
    }

    @GetMapping(value = "/getFeignAll")
    @HystrixCommand
    public Callable<MessageBox> getFeignAll(){
        /*if(true){
            throw  new RuntimeException("-------");
        }*/
        Callable<MessageBox> callable = new Callable<MessageBox>() {
            @Override
            public MessageBox call() throws Exception {
                List<Orders> ordersList = ordersService.getAll ( );
                return MessageBox.build ( "200", "获取所有订单", ordersList );
            }
        };
        return callable;
    }

}
