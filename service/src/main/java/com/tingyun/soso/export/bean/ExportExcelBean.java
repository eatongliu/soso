package com.tingyun.soso.export.bean;

import java.util.List;

/**
 * Created by liuyutong on 2018/12/14
 **/
public class ExportExcelBean {
    private Integer status;
    private String msg;
    /**
     * 导出类型：csv/xls
     */
    private String type;
    private String fileName;
    private List<List<String>> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExportExcelBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", fileName='" + fileName + '\'' +
                ", data=" + data +
                '}';
    }
}
