package com.apabi.finance.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liuyutong on 2017/12/1.
 */
@Controller
public class UserController {

    @RequestMapping("/user")
    public String getUser(Map<String, Object> map, HttpServletRequest request) {
        String value = "123456";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String name = cookie.getName();
                if (Objects.equals("cookieTest", name)) {
                    value = cookie.getValue();
                    break;
                }
            }
        }
        System.out.println(value);
        map.put("description", value);
        return "user";
    }

    @RequestMapping("/wx/subscribe")
    public String subscribe(HttpServletRequest request, HttpServletResponse response) {
        try {
            String gzh = "方正阿帕比";//公众号名字
            String user_agent = request.getHeader("user-agent");
            System.out.println(user_agent);
            String url="http://weixin.qq.com/mp/getmasssendmsg?__biz=wx49ef724fe2aee8cf#wechat_webview_type=1&wechat_redirect#rd";
            String url2="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzA3NzQzNTgyNw==&scene=110#wechat_redirect";

            if (user_agent.indexOf("MicroMessenger")>=0 &&
                    user_agent.indexOf("iPhone")>=0 ) {

                response.sendRedirect(url);
            }


            if (user_agent.indexOf("MicroMessenger")>=0 &&
                    user_agent.indexOf("Android")>=0 ) {
//                response.sendRedirect("weixin://profile/"+gzh);
                response.sendRedirect(url2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "subscribe";
    }

    @RequestMapping("/wx/login")
    public String login (HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionTest", "!@#$%^&*()_+");

        Cookie cookie = new Cookie("cookieTest", "abcdefghijklmn");
        cookie.setMaxAge(10);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/user";
    }

    @RequestMapping("/wx/logout")
    public String logout (HttpServletResponse response) {
        //清除Cookies
        Cookie cookie = new Cookie("cookieTest", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/user";
    }


}
