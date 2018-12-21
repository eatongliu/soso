package com.tingyun.file.export.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuyutong on 2018/12/14
 **/
public class ExcelUtil {
    public static Workbook export(String sheetName, List<List<String>> tableList){
        Workbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet(sheetName);
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        if(tableList!= null){
            final AtomicInteger rownum = new AtomicInteger(0);
            tableList.forEach(rowList ->{
                Row hsrow = sheet.createRow(rownum.get());
                hsrow.setHeightInPoints(20);
                rownum.incrementAndGet();
                final AtomicInteger column = new AtomicInteger(0);
                rowList.forEach(cell ->{
                    Cell hsCell = hsrow.createCell(column.get());
                    hsCell.setCellStyle(cellStyle);
                    column.incrementAndGet();
                    if(NumberUtils.isNumber(cell)){
                        hsCell.setCellValue(new Double(cell));
                    }else{
//                        final CreationHelper createHelper = hsrow.getSheet().getWorkbook().getCreationHelper();
//                        hsCell.setCellValue(createHelper.createRichTextString(cell));
                        hsCell.setCellValue(cell);
                    }
                });
            });
        }
        return workBook;
    }
}
