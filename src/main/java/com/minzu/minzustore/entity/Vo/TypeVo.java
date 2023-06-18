package com.minzu.minzustore.entity.Vo;
import com.minzu.minzustore.entity.HomeType;
import lombok.Data;

import java.io.Serializable;

@Data
public class TypeVo implements Serializable {

    private Integer id;

    private String name;

    private String remark;

    private Integer homeTypeId; //类别编号

    private HomeType homeType;

}
