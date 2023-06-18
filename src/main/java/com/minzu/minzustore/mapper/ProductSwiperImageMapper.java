package com.minzu.minzustore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minzu.minzustore.entity.ProductSwiperImage;
import com.minzu.minzustore.entity.Type;

public interface ProductSwiperImageMapper extends BaseMapper<ProductSwiperImage> {
    int maxId();
}
