package com.sme.entity;

import com.sme.core.model.BaseObject;

public class OperateLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1709653447622935981L;
	
	private Integer id;							//primary-key
	private Integer accId;						//关联sys_acc主键
	private String operateType;					//操作类型
	private String operateDes;					//操作描述
	
	
	private transient String operateTime;		//操作时间
	private transient String accName;			//登陆账号
	private transient String realName;			//真实姓名
	private transient String accType;			//管理员类型
	public OperateLog() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getOperateDes() {
		return operateDes;
	}
	public void setOperateDes(String operateDes) {
		this.operateDes = operateDes;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}

}
