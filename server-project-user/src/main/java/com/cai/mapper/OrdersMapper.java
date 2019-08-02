package com.cai.mapper;

import com.cai.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrdersMapper {

    int deleteById(Integer id);

    int insert(Orders record);

    @Select("select * from orders")
    List<Orders> selectByAll();

    int updateById(Orders record);
}