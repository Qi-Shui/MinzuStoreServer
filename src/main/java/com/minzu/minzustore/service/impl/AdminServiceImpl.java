package com.minzu.minzustore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzu.minzustore.entity.Admin;
import com.minzu.minzustore.mapper.AdminMapper;
import com.minzu.minzustore.service.AdminService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;
}
