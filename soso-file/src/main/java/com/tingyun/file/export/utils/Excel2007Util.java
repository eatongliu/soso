package com.tingyun.file.export.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * 使用 POI 创建一个简单的 Excel 文件
 */
public class Excel2007Util {
    private static String filePath = "E:/test/";
    
    public static void exportExcel(final ExcelDataBean excelDataBean,
                                   final OutputStream outputStream, String sheetName) throws IOException {


        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet(sheetName);
        final List<List<?>> headerList = excelDataBean.getHeaderList();

        IntStream.range(0, headerList.size())
                .forEach(i ->
                        createRow(i, 0, headerList, sheet ));

        final int rowOffset = headerList.size();
        final List<List<?>> rowList = excelDataBean.getRowList();

        IntStream.range(0, rowList.size())
                .forEach(i ->
                        createRow(i, rowOffset, rowList, sheet ));


        workbook.write(outputStream);

    }

    private static void createRow(final int index, final int rowOffset,
                                  final List<List<?>> rowList,
                                  final Sheet sheet) {
        List<?> data = rowList.get(index);
        if (Objects.nonNull(data) && !data.isEmpty()) {
            final Row row = sheet.createRow(index + rowOffset);
            IntStream.range(0, data.size())
                    .forEach(j ->
                        createCell(j, data, row));
        }
    }

    private static void createCell(final int index, final List<?> data, final Row row) {

        final Object obj = data.get(index);
        final Cell cell = row.createCell(index);
        if (obj == null) {
            cell.setCellValue("");
        } else if (Double.class.isInstance(obj)) {
            cell.setCellValue((double) obj);
        } else if (Date.class.isInstance(obj)) {
            Date date = (Date) obj;
            cell.setCellValue(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toString());
        } else if (Calendar.class.isInstance(obj)) {
            cell.setCellValue((Calendar) obj);
        } else if (Boolean.class.isInstance(obj)) {
            cell.setCellValue((boolean) obj);
        } else {
            final CreationHelper createHelper = row.getSheet().getWorkbook().getCreationHelper();
            cell.setCellValue(createHelper.createRichTextString(obj.toString()));
        }
    }

    public static void createExcelFile(final ExcelDataBean excelDataBean, String sheetName, String token, String suffix) throws IOException {
        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet(sheetName);

        final List<List<?>> headerList = excelDataBean.getHeaderList();

        IntStream.range(0, headerList.size())
                .forEach(i ->
                        createRow(i, 0, headerList, sheet ));

        final int rowOffset = headerList.size();
        final List<List<?>> rowList = excelDataBean.getRowList();

        IntStream.range(0, rowList.size())
                .forEach(i ->
                        createRow(i, rowOffset, rowList, sheet ));

        File file = new File(filePath +token + "." + suffix);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
    }

    public static void downloadExcelFile(OutputStream outputStream, String token, String suffix) throws IOException {
        File file = new File(filePath +token + "." + suffix);
        FileInputStream inputStream = new FileInputStream(file);
        FileCopyUtils.copy(inputStream, outputStream);
    }
}
