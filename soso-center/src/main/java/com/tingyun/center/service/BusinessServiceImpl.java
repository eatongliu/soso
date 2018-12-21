package com.tingyun.center.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tingyun.soso.service.domain.BusinessDomain;
import com.tingyun.soso.service.service.BusinessService;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.business.provider.
 * 作者: barton.
 * 日期: 16-12-3.
 * 项目名称: business
 * 版本: 1.0
 * JDK: since 1.8
 */
@Service(version = "1.0.0")
public class BusinessServiceImpl implements BusinessService {
  @Override
  public BusinessDomain findBusiness(int id, String name) {
    return new BusinessDomain(id,name);
  }
}
