package com.apabi.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.business.provider.
 * 作者: barton.
 * 日期: 16-12-3.
 * 项目名称: business
 * 版本: 1.0
 * JDK: since 1.8
 */
@SpringBootApplication
@MapperScan("com.apabi.center.mapper")
public class CenterApplication {
  public static void main(String[] args) {
    SpringApplication.run(CenterApplication.class, args);
  }
}
