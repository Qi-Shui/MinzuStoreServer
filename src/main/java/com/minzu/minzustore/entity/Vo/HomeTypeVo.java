package com.minzu.minzustore.entity.Vo;
import lombok.Data;

@Data
public class HomeTypeVo {

    private Integer id;

    private String name;

    private String remark;

    private String image = "default.png";

}
