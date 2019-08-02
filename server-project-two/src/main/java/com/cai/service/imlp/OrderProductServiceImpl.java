package com.cai.service.imlp;

import com.cai.entity.OrderProduct;
import com.cai.mapper.OrderProductMapper;
import com.cai.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    OrderProductMapper orderProductMapper;

    @Override
    public int addOrderProduct(OrderProduct orderProduct) {
        return orderProductMapper.addOrderProduct(orderProduct);
    }
}
