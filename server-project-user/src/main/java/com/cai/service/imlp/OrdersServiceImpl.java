package com.cai.service.imlp;

import com.cai.entity.Orders;
import com.cai.feignClient.OrderProductFeignClient;
import com.cai.mapper.OrdersMapper;
import com.cai.service.OrdersService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    OrderProductFeignClient orderProductFeignClient;
    @Override
    public List<Orders> getAll() {

        return ordersMapper.selectByAll();
    }

    /**
     * TODO 分布式事物测试
     * @return
     */
    @Override
    @LcnTransaction
    public String getF() {
        return orderProductFeignClient.getFeign();
        //throw new RuntimeException("异常");
    }
}
