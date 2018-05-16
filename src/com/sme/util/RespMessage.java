package com.sme.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespMessage {
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private Object  body;
	private Object  data;
	private String  message;
	private String  appflag;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public RespMessage() {
	}
	public String getAppflag()
	{
		return appflag;
	}
	public void setAppflag(String appflag)
	{
		this.appflag = appflag;
	}	
}
