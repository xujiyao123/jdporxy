package com.xujiyao.jdporxy.util;

import java.io.Serializable;


public class JsonResult<T> implements Serializable{
	private static final long serialVersionUID = -3644950655568598241L;
	
	private int state;
	private String message;
	private T data;
	
	public static final int SUCCESS = 200;
	public static final int ERROR = -1;
	
	public JsonResult() {
		state = SUCCESS;
		message = "";
	}
	
	public JsonResult(T data){
		state = SUCCESS;
		this.data = data;
	}
	
	public JsonResult(Throwable e){
		state = ERROR;
		message = e.getMessage();
	}
	
	public JsonResult(int state, Throwable e){
		this.state = state;
		this.message = e.getMessage();
	}

	public JsonResult(int state, T data){
		this.state = state;
		this.data = data;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [stateCode=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
}





