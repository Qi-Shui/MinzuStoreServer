package com.minzu.minzustore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minzu.minzustore.entity.ProductSwiperImage;

public interface ProductSwiperImageService extends IService<ProductSwiperImage> {
    int maxId();
}
