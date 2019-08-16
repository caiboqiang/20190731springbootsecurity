package com.cai.mapper;


import com.cai.entity.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderProductMapper {

       int addOrderProduct(OrderProduct orderProduct);

       @Select("select * from order_product where orderno = #{orderno}")
       List<OrderProduct> getOrderProductAll(@Param("orderno") String orderno);
}