package com.tingyun.soso.service.vo;

import java.io.Serializable;
import java.util.List;

public class RespData implements Serializable{
    public static String SUCCESS = "1";
    public static String ERROR = "0";
	private String code;
	private String msg;
	private Object data;

	public RespData(){}
	
	public RespData(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public RespData(String code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static RespData apply(String code, String msg){
		return new RespData(code, msg);
	}

	public static RespData apply(String code, String msg, Object data){
		return new RespData(code, msg, data);
	}

	public static RespData apply(String code, String msg, Integer total, List<?> rows){
		return new RespData(code, msg, new ListResult(total, rows));
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "RespData{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}

	private static class ListResult implements Serializable {

		private Integer total;
		private List<?> rows;

		ListResult(Integer total, List<?> rows) {
			this.total = total;
			this.rows = rows;
		}

		public Integer getTotal() {
			return total;
		}

		public List<?> getRows() {
			return rows;
		}
	}
}
