package com.minzu.minzustore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minzu.minzustore.entity.HomeType;

import java.util.List;

public interface HomeTypeMapper extends BaseMapper<HomeType> {

    public HomeType findById();

    List<HomeType> homeTypeList();

    int maxId();
}
