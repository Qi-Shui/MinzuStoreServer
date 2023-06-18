package com.minzu.minzustore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzu.minzustore.entity.OrderDetail;
import com.minzu.minzustore.mapper.OrderDetailMapper;
import com.minzu.minzustore.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Resource
    private OrderDetailMapper orderDetailMapper;
}
