package com.minzu.minzustore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minzu.minzustore.entity.Type;

import java.util.List;
import java.util.Map;

public interface TypeService extends IService<Type> {
    List<Type> list(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    int maxId();
}
