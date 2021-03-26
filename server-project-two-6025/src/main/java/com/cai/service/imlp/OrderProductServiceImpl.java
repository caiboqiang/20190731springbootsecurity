package com.cai.service.imlp;

import com.cai.entity.OrderProduct;
import com.cai.mapper.OrderProductMapper;
import com.cai.service.OrderProductService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    //@LcnTransaction //分布式事务注解
    public int addOrderProduct(OrderProduct orderProduct) {
        return orderProductMapper.addOrderProduct(orderProduct);
    }
}
