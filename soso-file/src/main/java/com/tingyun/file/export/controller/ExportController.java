package com.tingyun.file.export.controller;

import com.tingyun.file.export.sevice.ExcelService;
import com.tingyun.file.export.utils.Excel2007Util;
import com.tingyun.file.export.utils.ExcelDataBean;
import com.tingyun.file.export.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyutong on 2018/12/14
 **/
@Controller
@RequestMapping("export-data")
public class ExportController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/index")
    public String export() {
        return "export";
    }

    @RequestMapping("/common")
    public void exportData1(HttpServletResponse response) {
        List<List<String>> data = (List<List<String>>) excelService.exportExcelTableData();
        try(OutputStream ouputStream = response.getOutputStream()) {
            response.reset();
            response.setContentType("application/msexcel");
            String fileName = URLEncoder.encode("export.xlsx", "UTF8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);

            //导出Excel
            ExcelDataBean.Builder builder = new ExcelDataBean.Builder();
            // 创建一行
            data.forEach(builder::addRowList);
            //创建Excel
            ExcelDataBean excelDataBean = builder.build();
            Excel2007Util.exportExcel(excelDataBean, ouputStream, "test测试");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dataType  下载数据类型：tableData/chartData
     * @param dataFormat  请求数据结构：json
     * @param fileFormat  文件格式：xls/xlsx
     * @param option 0：生成文件，1：下载文件
     * @param token 令牌
     */
    @RequestMapping("{dataType}-{dataFormat}-{fileFormat}-{option}")
    public void exportData(HttpServletResponse response,
                           @PathVariable(name = "dataType") String dataType,
                           @PathVariable(name = "dataFormat") String dataFormat,
                           @PathVariable(name = "fileFormat") String fileFormat,
                            @PathVariable Integer option,
                           @RequestParam String token) {
        if (option == 0) {
            List<List<String>> data = (List<List<String>>) excelService.exportExcelTableData();
            try {
                //导出Excel
                ExcelDataBean.Builder builder = new ExcelDataBean.Builder();
                // 创建一行
                data.forEach(builder::addRowList);
                //创建Excel
                ExcelDataBean excelDataBean = builder.build();
                Excel2007Util.createExcelFile(excelDataBean, "test测试", token, fileFormat);
                Cookie cookie = new Cookie("export-data-" + token, "success");
                //单位s
                cookie.setMaxAge(10*60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else if (option == 1) {
            try (OutputStream ouputStream = response.getOutputStream()) {
                response.reset();
                response.setContentType("application/msexcel");
                String fileName = URLEncoder.encode("export.xlsx", "UTF8");
                response.setHeader("Content-disposition", "attachment; filename=" + fileName);
                Excel2007Util.downloadExcelFile(ouputStream, token, fileFormat);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
