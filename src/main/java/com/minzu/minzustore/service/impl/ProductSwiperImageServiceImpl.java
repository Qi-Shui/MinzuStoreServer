package com.minzu.minzustore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzu.minzustore.entity.ProductSwiperImage;
import com.minzu.minzustore.mapper.ProductSwiperImageMapper;
import com.minzu.minzustore.service.ProductSwiperImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("productSwiperImageService")
public class ProductSwiperImageServiceImpl extends ServiceImpl<ProductSwiperImageMapper, ProductSwiperImage> implements ProductSwiperImageService {

    @Resource
    private ProductSwiperImageMapper productSwiperImageMapper;

    @Override
    public int maxId() {
        return productSwiperImageMapper.maxId();
    }
}
