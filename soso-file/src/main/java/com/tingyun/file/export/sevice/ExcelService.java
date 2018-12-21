package com.tingyun.file.export.sevice;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuyutong on 2018/12/14
 **/
@Service
public class ExcelService {

    public Object exportExcelTableData() {
        List<List<String>> result = new ArrayList<>();
        List<String> heads = Arrays.asList("AAA", "BBB", "嘀DI嘀");
        result.add(heads);
        for (int i = 0; i < 10; i++) {
            List<String> row = Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
            result.add(row);
        }
        return result;
    }
}
