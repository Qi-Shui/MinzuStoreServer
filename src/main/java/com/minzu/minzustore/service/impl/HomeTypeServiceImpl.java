package com.minzu.minzustore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzu.minzustore.entity.HomeType;
import com.minzu.minzustore.mapper.HomeTypeMapper;
import com.minzu.minzustore.service.HomeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("homeTypeService")
public class HomeTypeServiceImpl extends ServiceImpl<HomeTypeMapper, HomeType> implements HomeTypeService {

    @Resource
    private HomeTypeMapper homeTypeMapper;

    @Override
    public List<HomeType> homeTypeList() {
        return homeTypeMapper.homeTypeList();
    }

    @Override
    public int maxId() {
        return homeTypeMapper.maxId();
    }
}
