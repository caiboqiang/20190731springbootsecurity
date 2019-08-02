package com.cai.service.imlp;

import com.cai.entity.Orders;
import com.cai.mapper.OrdersMapper;
import com.cai.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Orders> getAll() {

        return ordersMapper.selectByAll();
    }
}
