package com.apabi.finance.hello.controller;

import com.apabi.finance.hello.dao.UserDao;
import com.apabi.finance.hello.entity.User;
import com.apabi.finance.hello.vo.BeanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by acer_liuyutong on 2017/5/10.
 */
@RestController
public class HelloController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/now")
    public String hehe() {
        return "现在时间：" + new Date();
    }

    @RequestMapping("/one/list")
    public BeanResult getOne(){
        List<User> all = userDao.findAll();
        return BeanResult.success(all);
    }
}
