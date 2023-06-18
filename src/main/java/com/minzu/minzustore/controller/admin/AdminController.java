package com.minzu.minzustore.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.minzu.minzustore.constant.SystemConstant;
import com.minzu.minzustore.entity.Admin;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.service.AdminService;
import com.minzu.minzustore.util.DateUtil;
import com.minzu.minzustore.util.JwtUtils;
import com.minzu.minzustore.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员Controller
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    @PostMapping("/adminLogin")
    public Msg adminLogin(@RequestBody Admin admin) {
        if (admin == null) {
            return Msg.error();
        }
        if (StringUtil.isEmpty(admin.getUserName())) {
            return Msg.error("用户名不能为空！");
        }
        if (StringUtil.isEmpty(admin.getPassword())) {
            return Msg.error("密码不能为空！");
        }
        Admin resultAdmin = adminService.getOne(new QueryWrapper<Admin>().eq("userName", admin.getUserName()));
        if (resultAdmin == null) {
            return Msg.error("用户名不存在");
        }
        if (!resultAdmin.getPassword().trim().equals(admin.getPassword())) {
            return Msg.error("用户名或者密码错误！");
        }
        String token = JwtUtils.createJWT("-1", "admin", SystemConstant.JWT_TTL);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        return Msg.ok(resultMap);
    }

    /**
     * 修改密码
     *
     * @param admin
     * @return
     */
    @PostMapping("/admin/modifyPassword")
    public Msg modifyPassword(@RequestBody Admin admin) {
        if (StringUtil.isEmpty(admin.getUserName())) {
            return Msg.error("用户名不能为空");
        }
        if (StringUtil.isEmpty(admin.getPassword())) {
            return Msg.error("新密码不能为空");
        }
        adminService.update(new UpdateWrapper<Admin>().set("password", admin.getNewPassword()).eq("userName", admin.getUserName()));
        return Msg.ok();
    }

}
