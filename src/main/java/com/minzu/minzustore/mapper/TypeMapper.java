package com.minzu.minzustore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minzu.minzustore.entity.Product;
import com.minzu.minzustore.entity.Type;

import java.util.List;
import java.util.Map;

public interface TypeMapper extends BaseMapper<Type> {

    List<Type> list(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    Type findById(Integer id);

    int maxId();
}
