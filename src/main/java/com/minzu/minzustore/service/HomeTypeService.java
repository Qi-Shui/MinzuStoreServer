package com.minzu.minzustore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minzu.minzustore.entity.HomeType;

import java.util.List;

public interface HomeTypeService extends IService<HomeType> {
    List<HomeType> homeTypeList();

    int maxId();
}
