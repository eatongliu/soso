package com.apabi.finance.hello.controller;

import com.apabi.finance.hello.utils.LoanUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyutong on 2017/10/30.
 */
@RestController
public class LoanController {

    @RequestMapping("/loan")
    public Object getOne(@RequestParam(required = false, defaultValue = "0") Integer type,
                         @RequestParam(required = false, defaultValue = "100000.00") Double loanAmount,
                         @RequestParam(required = false, defaultValue = "365") Integer totalDays,
                         @RequestParam(required = false, defaultValue = "1") Integer interval,
                         @RequestParam(required = false, defaultValue = "0.00045") Double rate){
        return LoanUtil.repayWithDailyRate(type, loanAmount, totalDays, interval, rate);
    }
}
