package com.tingyun.center.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tingyun.soso.service.service.UserService;
import org.springframework.stereotype.Controller;

/**
 * Created by liuyutong on 2018/2/11.
 */
@Controller
public class LoginController {
    @Reference(version = "1.0.0")
    private UserService userService;

}