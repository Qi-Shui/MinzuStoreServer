package com.minzu.minzustore.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@TableName("product")
@Data
public class Product implements Serializable {

    private Integer id;

    private String name;

    private BigDecimal price;

    private String productIntroImgs; // 商品介绍图片

    private String productParaImgs; // 商品详情图片

    private Integer stock; // 库存

    private String proPic = "default.png";

    private boolean isHot = false;

    private boolean isSwiper = false;

    private Integer swiperSort = 0;

    private String swiperPic = "default.png";

    private String description;

    private Integer typeId;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT=8")
    private Date hotDateTime;

    @TableField(select = false)
    private List<ProductSwiperImage> productSwiperImageList;

    @TableField(select = false,exist = false)
    private Type type;

}
