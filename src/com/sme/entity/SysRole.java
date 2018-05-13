package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;

public class SysRole extends BaseObject 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3862937042667215903L;
	private Integer roleid;
	private String  rolename;
	private String  roledesc;
	private String  roletype;
	private String  rolestate;
	private Integer roleorder;
	private Date rolecdate;
	private Date roleudate;
	private String rolecuser;
	private String roleuuser;
	public Integer getRoleid()
	{
		return roleid;
	}
	public void setRoleid(Integer roleid)
	{
		this.roleid = roleid;
	}
	public String getRolename()
	{
		return rolename;
	}
	public void setRolename(String rolename)
	{
		this.rolename = rolename;
	}
	public String getRoledesc()
	{
		return roledesc;
	}
	public void setRoledesc(String roledesc)
	{
		this.roledesc = roledesc;
	}
	public String getRoletype()
	{
		return roletype;
	}
	public void setRoletype(String roletype)
	{
		this.roletype = roletype;
	}
	public String getRolestate()
	{
		return rolestate;
	}
	public void setRolestate(String rolestate)
	{
		this.rolestate = rolestate;
	}
	public Integer getRoleorder()
	{
		return roleorder;
	}
	public void setRoleorder(Integer roleorder)
	{
		this.roleorder = roleorder;
	}
	public Date getRolecdate()
	{
		return rolecdate;
	}
	public void setRolecdate(Date rolecdate)
	{
		this.rolecdate = rolecdate;
	}
	public Date getRoleudate()
	{
		return roleudate;
	}
	public void setRoleudate(Date roleudate)
	{
		this.roleudate = roleudate;
	}
	public String getRolecuser()
	{
		return rolecuser;
	}
	public void setRolecuser(String rolecuser)
	{
		this.rolecuser = rolecuser;
	}
	public String getRoleuuser()
	{
		return roleuuser;
	}
	public void setRoleuuser(String roleuuser)
	{
		this.roleuuser = roleuuser;
	}
}
