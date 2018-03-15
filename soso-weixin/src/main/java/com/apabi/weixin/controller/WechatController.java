package com.apabi.weixin.controller;

import com.apabi.soso.service.vo.RespData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyutong on 2018/2/26.
 */
@Controller
public class WechatController {

    @RequestMapping("/weixin1")
    public String toLogin(Model model) {
        model.addAttribute("msg", "hello");
        return "weixin1";
    }

    @RequestMapping("/getJsAPITicket")
    @ResponseBody
    public RespData getJsAPITicket(String url) {
        RespData respData = new RespData();
        Map<String, String> ret = new HashMap<>();
        ret.put("appId", "wx1330eb70cf614e77");
        ret.put("jsapi_ticket", "HoagFKDcsGMVCIY2vOjf9j9D1YB-ed04R-qfA5Lwz65TdhrRnfpJJiDIn-3Iojjv_0lgbD6k2jz9W80Ua1pQwA");
        ret.put("nonceStr", "a1cdaf65151e44c4");
        ret.put("timestamp", "1519701548");
        ret.put("signature", "8f8777b8181cbf49852d43097f71339a118341d7");
//        respData.setData(WechatUtil.sign(url));
        respData.setData(ret);
        return respData;
    }
}
