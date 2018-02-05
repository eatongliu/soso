package com.apabi.soso.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.apabi.soso.service.domain.BusinessDomain;
import com.apabi.soso.service.service.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.business.consumer.controller.
 * 作者: barton.
 * 日期: 16-12-3.
 * 项目名称: business
 * 版本: 1.0
 * JDK: since 1.8
 */
@Controller
public class BusinessConsumerController {

  @Reference(version = "1.0.0")
  public BusinessService businessService;


  @RequestMapping("/business")
  @ResponseBody
  public BusinessDomain getBusiness() {
    return businessService.findBusiness(1, "businessaaa");
  }
}
