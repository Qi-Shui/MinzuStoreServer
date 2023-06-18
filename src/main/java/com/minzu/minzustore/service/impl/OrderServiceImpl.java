package com.minzu.minzustore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzu.minzustore.entity.Order;
import com.minzu.minzustore.mapper.OrderMapper;
import com.minzu.minzustore.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> list(Map<String, Object> map) {
        return orderMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return orderMapper.getTotal(map);
    }
}
