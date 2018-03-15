package com.apabi.finance.hello.vo;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengchao on 2016/10/26.
 */
public class BeanResult implements Serializable {

    public static final String ERROR = "ERROR";
    public static final String SUCCESS = "SUCCESS";


    private String status;
    private String cause;
    private Object data;
    private String code;


    public static final BeanResult error(String cause) {
        BeanResult bean = new BeanResult();
        bean.status = ERROR;
        bean.cause = cause;

        return bean;
    }

    public static final BeanResult error(String cause, String code) {
        BeanResult bean = new BeanResult();
        bean.status = ERROR;
        bean.cause = cause;
        bean.code = code;
        return bean;
    }


    public static final BeanResult success(Object data) {
        BeanResult bean = new BeanResult();
        bean.status = SUCCESS;
        bean.data = data;

        return bean;
    }

    public static final BeanResult success(Object data, String code) {
        BeanResult bean = new BeanResult();
        bean.status = SUCCESS;
        bean.data = data;
        bean.code = code;
        return bean;
    }


    public static final BeanResult success(Integer total, List<?> rows) {
        BeanResult bean = new BeanResult();
        bean.status = SUCCESS;
        bean.data = new ListResult(total, rows);

        return bean;
    }

    public static final BeanResult success(Integer currPage, Integer total, List<?> rows) {
        BeanResult bean = new BeanResult();
        bean.status = SUCCESS;
        bean.data = new ListResult(currPage, total, rows);

        return bean;
    }


    public static final BeanResult success(Integer total, Model model) {
        BeanResult bean = new BeanResult();
        bean.status = SUCCESS;
        bean.data = new ListResult(total, model);

        return bean;
    }


    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return
     */
    public String getCause() {
        return cause;
    }

    /**
     * @return
     */
    public Object getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this)
                .toString();
    }

    public static class ListResult implements Serializable {

        private Integer currPage;
        private Integer total;
        private List<?> rows;
        private Model model;

        ListResult(Integer total, List<?> rows) {
            this.total = total;
            this.rows = rows;
        }

        ListResult(Integer currPage, Integer total, List<?> rows) {
            this.currPage = currPage;
            this.total = total;
            this.rows = rows;
        }

        ListResult(Integer total, Model model) {
            this.total = total;
            this.model = model;
        }

        public Integer getCurrPage() {
            return currPage;
        }

        public Integer getTotal() {
            return total;
        }


        public List<?> getRows() {
            return rows;
        }

        public Model getModel() {
            return model;
        }


    }
}
