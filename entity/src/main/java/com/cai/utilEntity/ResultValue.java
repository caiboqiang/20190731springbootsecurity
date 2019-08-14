package com.cai.utilEntity;

import java.io.Serializable;

public class ResultValue implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String app;
	
	/**
	 * @return the app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * @return the body
	 */
	public Object getBody() {
		return body;
	}


	private int code;
	
	private String msg;
	
	private Object body;

	public ResultValue() {
    }

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


	public void setBody(Object body) {
		this.body = body;
	}

	public ResultValue(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public ResultValue(int code, String msg, Object body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }
	
	

	public ResultValue(String app, int code, String msg) {
		super();
		this.app = app;
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 默认的成功
	 * 
	 * @return
	 */
	public ResultValue success() {
		return new ResultValue(Status.SUCCESS.Code, Status.SUCCESS.msg);
	}

	/**
	 * 默认的失败
	 * 
	 * @return
	 */
	public ResultValue error() {
		return new ResultValue(Status.FAIL.Code, Status.FAIL.msg);
	}

	public ResultValue sussess(Object body) {
		return new ResultValue(Status.SUCCESS.Code, Status.SUCCESS.msg, body);
	}

	public ResultValue(String app, int code, String msg, Object body) {
		super();
		this.app = app;
		this.code = code;
		this.msg = msg;
		this.body = body;
	}

	public enum Status {
		SUCCESS(0, "成功"), FAIL(1, "失败");

		private int Code;
		private String msg;

		Status(int code, String msg) {
			Code = code;
			this.msg = msg;
		}
	}
}
