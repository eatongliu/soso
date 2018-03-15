package com.apabi.finance.hello.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyutong on 2017/10/30.
 */
public class LoanUtil {

    public static Map<String, Object> repayWithDailyRate(int type, double loanAmount, int totalDays, int interval, double rate) {
//        int type = 0;
//        double loanAmount = 100000.00;
//        int totalDays = 365;
//        int day = 10;
//        double rate = 0.00045;
        Map<String, Object> result = new HashMap<>();

        double groupDay = (double) totalDays / (double) interval;
        int periods = (int) Math.ceil(groupDay);

        Map<Integer, Object> rateMap = new HashMap<>();
        Map<Integer, Object> accountMap = new HashMap<>();
        Map<Integer, Object> repayMap = new HashMap<>();

        double total = 0.00;
        double amount = loanAmount;
        for (int i = 0; i < periods; i++) {
            if (type == 1) {
                interval = getDaysOfMonth(i + 1);
                groupDay = 12;
            }
            accountMap.put(i, amount);
            if (amount <= 0) break;
            double rateVal = (amount * rate) * interval;
            total += rateVal;
            double repay = 100000.00 / groupDay;
            amount = amount - repay;
            rateMap.put(i, rateVal);
            repayMap.put(i, rateVal + repay);
        }
        result.put("rateMap", rateMap);
        result.put("accountMap", accountMap);
        result.put("repayMap", repayMap);
        result.put("total", total);

        return result;
    }

    public static int getDaysOfMonth(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, i - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
