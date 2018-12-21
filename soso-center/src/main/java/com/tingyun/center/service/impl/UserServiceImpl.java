package com.tingyun.center.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tingyun.center.mapper.UserMapper;
import com.tingyun.soso.service.domain.User;
import com.tingyun.soso.service.service.UserService;
import com.tingyun.soso.service.vo.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by liuyutong on 2018/2/11.
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final List<String> LOGIN_TYPES = Arrays.asList("username", "email", "phone");
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByOneField(String field, String value, String sourceCode){
        User user = new User();
        user.setSource(sourceCode);
        Class<User> userClass = User.class;
        String methodName = "set" + field.substring(0,1).toUpperCase() + field.substring(1);

        try {
            Method method = userClass.getMethod(methodName,String.class);
            method.invoke(user, value);
        } catch (Exception e) {
            logger.error("反射异常：{}", e);
            return null;
        }
        List<User> users = userMapper.selectByConditions(user);
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public boolean checkDuplicateUsername(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        int count = userMapper.count(user);
        return count > 0;
    }

    @Override
    public boolean checkDuplicateEmail(Long id, String email) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        int count = userMapper.count(user);
        return count > 0;
    }

    @Override
    public boolean checkDuplicatePhone(Long id, String phone) {
        User user = new User();
        user.setId(id);
        user.setPhone(phone);
        int count = userMapper.count(user);
        return count > 0;
    }

    @Override
    public RespData login(String username, String password, String loginType, String sourceCode){
        if (!LOGIN_TYPES.contains(loginType))
            return RespData.apply("0", "账号" + username + "无效！");

        User user = selectByOneField(loginType, username, sourceCode);
        if (user == null)
            return RespData.apply("0", "账号不存在！");

        String userPwd = user.getPassword();
        if (!Objects.equals(password, userPwd))
            return RespData.apply("0", "密码错误！");

        return RespData.apply("1", "登陆成功！", user);
    }
}
