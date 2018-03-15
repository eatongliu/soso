package com.apabi.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.apabi.soso.service.domain.User;
import com.apabi.soso.service.service.UserService;
import com.apabi.soso.service.vo.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.apabi.soso.service.vo.RespData.SUCCESS;

/**
 * Created by liuyutong on 2018/2/11.
 */
@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Reference(version = "1.0.0")
    private UserService userService;

    @Value("${spring.dubbo.application.name}")
    private static String SOURCE_CODE;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespData login(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam(defaultValue = "username") String loginType,
                       HttpServletRequest request) {

        RespData respData = userService.login(username, password, loginType, SOURCE_CODE);

        User user = (User) respData.getData();
        String code = respData.getCode();
        if (Objects.equals(code, SUCCESS)) {
            request.getSession().setAttribute("currUser", user.getUsername());
        }
        return respData;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:login";
    }

}
