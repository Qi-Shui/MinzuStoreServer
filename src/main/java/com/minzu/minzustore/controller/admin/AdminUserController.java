package com.minzu.minzustore.controller.admin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.PageBean;
import com.minzu.minzustore.entity.UserInfo;
import com.minzu.minzustore.service.UserInfoService;
import com.minzu.minzustore.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台管理-用户Controller控制器
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据条件分页查询用户信息
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public Msg list(@RequestBody PageBean pageBean){
        System.out.println(pageBean);
        String query=pageBean.getQuery().trim();
        Page<UserInfo> page=new Page<>(pageBean.getPageNum(),pageBean.getPageSize());
        Page<UserInfo> pageResult = userInfoService.page(page, new QueryWrapper<UserInfo>().like(StringUtil.isNotEmpty(query), "nickName", query));
        Map<String,Object> map=new HashMap<>();
        map.put("userList",pageResult.getRecords());
        map.put("total",pageResult.getTotal());
        return Msg.ok(map);
    }

}
