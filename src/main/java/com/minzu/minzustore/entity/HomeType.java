package com.minzu.minzustore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@TableName("home_type")
@Data
public class HomeType {

    private Integer id;

    private String name;

    private String remark;

    private String image = "default.png";

//    @JsonIgnore
    @TableField(select = false)
    private List<Type> typeList; // 类别集合

}
