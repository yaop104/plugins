package com.sme.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sme.core.model.BaseObject;

import java.util.Date;

/**
 * 信息发送表(TsmSendMessage)模型对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TsmSendMessage extends BaseObject {
	//======================字段列表========================
	/** unid */
	private Integer	tsmSendUnid;

	/** uuid */
	private String	tsmSendUuid;

	/** 插入时间 */
	private Date	tsmSendTime;

	/** 发送状态1已验证2未验证 */
	private String	tsmSendState;

	/** 验证码 */
	private String	tsmSendCode;

	/** 手机号 */
	private String	tsmSendMobile;
	
	/** 类型 1注册码2更换手机码 */
	private String	tsmSendType;
	
	//==============扩展字段============
	

	public Integer getTsmSendUnid() {
		return this.tsmSendUnid;
	}

	public void setTsmSendUnid(Integer tsmSendUnid) {
		this.tsmSendUnid = tsmSendUnid;
	}

	public String getTsmSendUuid() {
		return this.tsmSendUuid;
	}

	public void setTsmSendUuid(String tsmSendUuid) {
		this.tsmSendUuid = tsmSendUuid;
	}

	public Date getTsmSendTime() {
		return this.tsmSendTime;
	}

	public void setTsmSendTime(Date tsmSendTime) {
		this.tsmSendTime = tsmSendTime;
	}

	public String getTsmSendState() {
		return this.tsmSendState;
	}

	public void setTsmSendState(String tsmSendState) {
		this.tsmSendState = tsmSendState;
	}

	public String getTsmSendCode() {
		return this.tsmSendCode;
	}

	public void setTsmSendCode(String tsmSendCode) {
		this.tsmSendCode = tsmSendCode;
	}

	public String getTsmSendMobile() {
		return this.tsmSendMobile;
	}

	public void setTsmSendMobile(String tsmSendMobile) {
		this.tsmSendMobile = tsmSendMobile;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttsmSendUnid(unid):").append(tsmSendUnid);
		buffer.append("\ttsmSendUuid(uuid):").append(tsmSendUuid);
		buffer.append("\ttsmSendTime(插入时间):").append(tsmSendTime);
		buffer.append("\ttsmSendState(发送状态1已验证2未验证):").append(tsmSendState);
		buffer.append("\n");
		buffer.append("\ttsmSendCode(验证码):").append(tsmSendCode);
		buffer.append("\ttsmSendMobile(手机号):").append(tsmSendMobile);
		return buffer.toString();
	}
	//================== begin ======================

	public String getTsmSendType() {
		return tsmSendType;
	}

	public void setTsmSendType(String tsmSendType) {
		this.tsmSendType = tsmSendType;
	}

	//================== end ======================
}
