package com.minzu.minzustore.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.PageBean;
import com.minzu.minzustore.entity.Type;
import com.minzu.minzustore.service.HomeTypeService;
import com.minzu.minzustore.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员-商品小类Controller控制器
 */
@RestController
@RequestMapping("/admin/smallType")
public class AdminSmallTypeController {

    @Autowired
    private TypeService smallTypeService;

    @Autowired
    private HomeTypeService bigTypeService;


    /**
     * 根据条件分页查询
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public Msg list(@RequestBody PageBean pageBean){
        System.out.println(pageBean.getQuery());
        Map<String,Object> map=new HashMap<>();
        map.put("name",pageBean.getQuery().trim());
        map.put("start",pageBean.getStart());
        map.put("pageSize",pageBean.getPageSize());
        List<Type> smallTypeList=smallTypeService.list(map);
        Long total=smallTypeService.getTotal(map);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("smallTypeList",smallTypeList);
        resultMap.put("total",total);
        return Msg.ok(resultMap);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Msg delete(@PathVariable(value = "id")Integer id){
        smallTypeService.removeById(id);
        return Msg.ok();
    }

    /**
     * 添加或者修改
     * @param smallType
     * @return
     */
    @RequestMapping("/save")
    public Msg save(@RequestBody Type smallType){
        smallType.setHomeTypeId(smallType.getHomeType().getId());
        if(smallType.getId()==null || smallType.getId()==-1){
            int maxId = smallTypeService.maxId();
            smallType.setId(maxId + 1);
            smallTypeService.save(smallType);
        }else{
            smallTypeService.saveOrUpdate(smallType);
        }
        return Msg.ok();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Msg findById(@PathVariable(value = "id")Integer id){
        Type smallType = smallTypeService.getById(id);
        smallType.setHomeType(bigTypeService.getById(smallType.getHomeTypeId()));
        Map<String,Object> map=new HashMap<>();
        map.put("smallType",smallType);
        return Msg.ok(map);
    }

    /**
     * 根据商品大类id，查询所有小类
     * @return
     */
    @GetMapping("/listAll/{bigTypeId}")
    public Msg listAll(@PathVariable(value = "bigTypeId")Integer bigTypeId){
        Map<String,Object> map=new HashMap<>();
        map.put("smallTypeList",smallTypeService.list(new QueryWrapper<Type>().eq("homeTypeId",bigTypeId)));
        return Msg.ok(map);
    }

}
