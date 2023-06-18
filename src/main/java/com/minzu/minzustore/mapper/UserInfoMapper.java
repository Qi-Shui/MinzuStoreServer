package com.minzu.minzustore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minzu.minzustore.entity.HomeType;
import com.minzu.minzustore.entity.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    public UserInfo findByOpenId(String openId);

}
