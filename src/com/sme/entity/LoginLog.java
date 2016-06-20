package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;
/**
 * 登陆日志实体类
 * @author haoy
 *
 */
public class LoginLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2303925612365526594L;
	
	private Integer id;			//id
	private Integer accId;		//关联账户
	private String loginIp;		//登陆ip
	private Date loginTime;		//登陆时间
	
	private transient String accName;	//登陆账号
	private transient String realName;	//真是姓名
	private transient String accType;	//管理员类型
	private transient String time;
	
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
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public LoginLog() {
		super();
	}
	
	
}
