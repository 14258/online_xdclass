package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.User;

import java.util.Map;

public interface UserService {

    int save(Map<String, String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
