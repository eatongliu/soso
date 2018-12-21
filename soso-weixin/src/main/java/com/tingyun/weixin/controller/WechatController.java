package com.tingyun.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.tingyun.soso.service.vo.RespData;
import com.tingyun.weixin.utils.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @RequestMapping("/index")
    public String weixin3(Model model) {
        String  redirectUrl = "";
        try {
            redirectUrl = URLEncoder.encode("http://xuyy.viphk.ngrok.org/soso-weixin/weixin2?a=1", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String wxOauthUrl= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx86280a282df8bbf0&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=xxxx_state#wechat_redirect";

        return "redirect:" + wxOauthUrl;
    }

    @RequestMapping("weixin2")
    public String getCode(@RequestParam String code, @RequestParam String state, HttpServletRequest request) {
        String appId = "wx86280a282df8bbf0";
        String appSecret = "cbebf44c214efb4027d01d7e446af06e";
        JSONObject tokenJson = WechatUtil.getOauth2AccessToken(appId, appSecret, code);

        String accessToken = tokenJson.getString("access_token");
        String openId = tokenJson.getString("openid");
        JSONObject infoJson = WechatUtil.getUserBaseinfo(accessToken, openId);
        request.setAttribute("authUser", infoJson);
        return "weixin2";
    }
}
