package com.apabi.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyutong on 2018/6/13.
 */
@Controller
public class TemplateController {

    @RequestMapping("/template/{id}")
    public String getTemp(@PathVariable String id, HttpServletResponse response, Model model){
        model.addAttribute("singlename", "lyt");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小红");
        map.put("age", 12);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "小红");
        map2.put("age", 12);
        List<Map<String, Object>> people = Arrays.asList(map, map2);
        model.addAttribute("people", people);
        File file = new File("D:/" + id + ".html");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            int nRead;
            byte[] buf = new byte[4096];
            // read from stream and write to file
            while ((nRead = fileInputStream.read(buf)) > 0) {
                out.write(buf, 0, nRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "template";
    }
}
