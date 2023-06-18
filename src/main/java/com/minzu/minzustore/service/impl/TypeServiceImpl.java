package com.minzu.minzustore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzu.minzustore.entity.Type;
import com.minzu.minzustore.mapper.TypeMapper;
import com.minzu.minzustore.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public List<Type> list(Map<String, Object> map) {
        return typeMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return typeMapper.getTotal(map);
    }

    @Override
    public int maxId() {
        return typeMapper.maxId();
    }
}
