package com.minzu.minzustore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.Product;
import com.minzu.minzustore.entity.ProductSwiperImage;
import com.minzu.minzustore.service.ProductService;
import com.minzu.minzustore.service.ProductSwiperImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductSwiperImageService productSwiperImageService;

    /**
     * 查询轮播商品
     *
     * @return
     */
    @GetMapping("/findSwiper")
    public Msg findSwiper() {
        List<Product> swiperProductList = productService.list(new QueryWrapper<Product>().eq("isSwiper", true).orderByAsc("swiperSort"));
        Map<String, Object> map = new HashMap<>();
        map.put("message", swiperProductList);
        return Msg.ok(map);
    }

    /**
     * 查询热门推荐商品6个
     * @return
     */
    @GetMapping("/findHot")
    public Msg findHot(){
        List<Product> productList = productService.list(new QueryWrapper<Product>().eq("isHot", true).orderByAsc("hotDateTime"));
        Map<String,Object> map=new HashMap<>();
        map.put("message",productList);
        return Msg.ok(map);
    }

    /**
     * 根据id查询商品信息
     */
    @GetMapping("/detail")
    public Msg detail(Integer id) {
        Product product = productService.getById(id);
        List<ProductSwiperImage> productSwiperImageList = productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId", product.getId()).orderByAsc("sort"));
        product.setProductSwiperImageList(productSwiperImageList);
        Map<String, Object> map = new HashMap<>();
        map.put("message", product);
        return Msg.ok(map);
    }

    /**
     * 商品搜索
     */
    @GetMapping("/search")
    public Msg search(String q) {
        List<Product> productList = productService.list(new QueryWrapper<Product>().like("name", q));
        Map<String, Object> map = new HashMap<>();
        map.put("message", productList);
        return Msg.ok(map);
    }

}
