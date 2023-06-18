package com.minzu.minzustore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.HomeType;
import com.minzu.minzustore.entity.Product;
import com.minzu.minzustore.entity.Type;
import com.minzu.minzustore.service.HomeTypeService;
import com.minzu.minzustore.service.ProductService;
import com.minzu.minzustore.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/homeType")
public class HomeTypeController {

    @Resource
    private HomeTypeService homeTypeService;

    @Resource
    private TypeService typeService;

    @Resource
    private ProductService productService;

    /**
     * 查商品类别
     *
     * @return
     */
    @GetMapping("/findType")
    public Msg findType() {
        List<HomeType> homeTypeList = homeTypeService.homeTypeList();
        Map<String, Object> map = new HashMap<>();
        map.put("message", homeTypeList);
        return Msg.ok(map);
    }

    /**
     * 获取商品类别页面信息
     * @return
     */
    @GetMapping("/findTypePage")
    public Msg findCategories(){
        List<HomeType> homeTypeList = homeTypeService.list();
        System.out.println("----------------"+homeTypeList);
        for (HomeType homeType : homeTypeList) {
            List<Type> smallTypeList = typeService.list(new QueryWrapper<Type>().eq("homeTypeId", homeType.getId()));
            homeType.setTypeList(smallTypeList);
            for (Type smallType : smallTypeList) {
                List<Product> productList = productService.list(new QueryWrapper<Product>().eq("typeId", smallType.getId()));
                smallType.setProductList(productList);
            }
        }
        System.out.println("----------------"+homeTypeList);
        Map<String, Object> map = new HashMap<>();
        map.put("message", homeTypeList);
        return Msg.ok(map);
    }

}
