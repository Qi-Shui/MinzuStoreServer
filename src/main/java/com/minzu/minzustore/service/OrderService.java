package com.minzu.minzustore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minzu.minzustore.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<Order> {

    // 分页查询
    List<Order> list(Map<String, Object> map);

    // 订单总记录数
    Long getTotal(Map<String, Object> map);
}
