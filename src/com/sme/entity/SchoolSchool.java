package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 学校管理(SchoolSchool)模型对象
 */
public class SchoolSchool extends BaseObject{
	//======================字段列表========================
	/** id */
	private Integer	schoolUnid;

	/** 排序 */
	private Integer	schoolOrder;

	/** 状态 */
	private String	schoolState;

	/** 记录日期 */
	private Date	schoolCdate;

	/** 更新日期 */
	private Date	schoolUdate;

	/** 记录人 */
	private Integer	schoolCuser;

	/** 创建人 */
	private Integer	schoolUuser;

	/** 类型1 */
	private String	schoolType;

	/** 名字 */
	private String	schoolName;

	/** 描述 */
	private String	schoolDesc;

	/** 规则 */
	private String	schoolRule;

	public Integer getSchoolUnid() {
		return this.schoolUnid;
	}

	public void setSchoolUnid(Integer schoolUnid) {
		this.schoolUnid = schoolUnid;
	}

	public Integer getSchoolOrder() {
		return this.schoolOrder;
	}

	public void setSchoolOrder(Integer schoolOrder) {
		this.schoolOrder = schoolOrder;
	}

	public String getSchoolState() {
		return this.schoolState;
	}

	public void setSchoolState(String schoolState) {
		this.schoolState = schoolState;
	}

	public Date getSchoolCdate() {
		return this.schoolCdate;
	}

	public void setSchoolCdate(Date schoolCdate) {
		this.schoolCdate = schoolCdate;
	}

	public Date getSchoolUdate() {
		return this.schoolUdate;
	}

	public void setSchoolUdate(Date schoolUdate) {
		this.schoolUdate = schoolUdate;
	}

	public Integer getSchoolCuser() {
		return this.schoolCuser;
	}

	public void setSchoolCuser(Integer schoolCuser) {
		this.schoolCuser = schoolCuser;
	}

	public Integer getSchoolUuser() {
		return this.schoolUuser;
	}

	public void setSchoolUuser(Integer schoolUuser) {
		this.schoolUuser = schoolUuser;
	}

	public String getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolDesc() {
		return this.schoolDesc;
	}

	public void setSchoolDesc(String schoolDesc) {
		this.schoolDesc = schoolDesc;
	}

	public String getSchoolRule() {
		return this.schoolRule;
	}

	public void setSchoolRule(String schoolRule) {
		this.schoolRule = schoolRule;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tschoolUnid(id):").append(schoolUnid);
		buffer.append("\tschoolOrder(排序):").append(schoolOrder);
		buffer.append("\tschoolState(状态):").append(schoolState);
		buffer.append("\tschoolCdate(记录日期):").append(schoolCdate);
		buffer.append("\n");
		buffer.append("\tschoolUdate(更新日期):").append(schoolUdate);
		buffer.append("\tschoolCuser(记录人):").append(schoolCuser);
		buffer.append("\tschoolUuser(创建人):").append(schoolUuser);
		buffer.append("\tschoolType(类型1):").append(schoolType);
		buffer.append("\n");
		buffer.append("\tschoolName(名字):").append(schoolName);
		buffer.append("\tschoolDesc(描述):").append(schoolDesc);
		buffer.append("\tschoolRule(规则):").append(schoolRule);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
