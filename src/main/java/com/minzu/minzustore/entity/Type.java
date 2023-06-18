package com.minzu.minzustore.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName("type")
@Data
public class Type implements Serializable {

    private Integer id;

    private String name;

    private String remark;

    private Integer homeTypeId; //类别编号

    @TableField(select = false,insertStrategy = FieldStrategy.NEVER,updateStrategy = FieldStrategy.NEVER)
    private HomeType homeType;

    @TableField(select = false)
//    @JsonIgnore
    private List<Product> productList;

}
