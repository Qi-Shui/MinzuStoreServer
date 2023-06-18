package com.minzu.minzustore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minzu.minzustore.constant.SystemConstant;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.UserInfo;
import com.minzu.minzustore.properties.WeixinProperties;
import com.minzu.minzustore.service.UserInfoService;
import com.minzu.minzustore.util.HttpClientUtil;
import com.minzu.minzustore.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 微信用户登录
     *
     * @return
     */
    @RequestMapping("/wxlogin")
    public Msg wxLogin(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        String jscode2sessionUrl = weixinProperties.getJscode2sessionUrl() + "?appid=" + weixinProperties.getAppid() + "&secret=" + weixinProperties.getSecret() + "&js_code=" + userInfo.getCode() + "&grant_type=authorization_code";
//        System.out.println(jscode2sessionUrl);
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.get("openid").toString();
//        System.out.println(openid);

        // 将用户插入数据库 若不存在：插入用户 若存在：更新用户
        UserInfo resultUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("openid", openid));
        if (resultUserInfo == null) {
            System.out.println("用户不存在 插入用户");
            userInfo.setOpenid(openid);
            userInfo.setRegisterDate(new Date());
            userInfo.setLastLoginDate(new Date());
            userInfoService.save(userInfo);
        } else {
            System.out.println("用户存在 更新用户");
            resultUserInfo.setNickName(userInfo.getNickName());
            resultUserInfo.setAvatarUrl(userInfo.getAvatarUrl());
            resultUserInfo.setLastLoginDate(new Date());
            userInfoService.updateById(resultUserInfo);
        }
        // 利用jwt生成token返回到前端
        String token = JwtUtils.createJWT(openid, userInfo.getNickName(), SystemConstant.JWT_TTL);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("openid", openid);
        return Msg.ok(resultMap);
    }
}
