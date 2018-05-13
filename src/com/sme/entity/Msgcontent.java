package com.sme.entity;

import com.sme.core.model.BaseObject;

public class Msgcontent extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7376314378917885462L;

	private Long id;
	private Integer systemmode;
	private Integer type;
	private Integer isAdd;
	private Long userid;
	private Long mainid;
	private String extra;
	private String touserids;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSystemmode() {
		return systemmode;
	}
	public void setSystemmode(Integer systemmode) {
		this.systemmode = systemmode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getMainid() {
		return mainid;
	}
	public void setMainid(Long mainid) {
		this.mainid = mainid;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getTouserids() {
		return touserids;
	}
	public void setTouserids(String touserids) {
		this.touserids = touserids;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
