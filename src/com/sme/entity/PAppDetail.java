package com.sme.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 应用详情(PAppDetail)模型对象
 */
public class PAppDetail extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1954320782048184791L;

	//======================字段列表========================
	/** id */
	private Integer	pAppdetailId;

	/** 应用ID */
	private Integer	pAppdetailApkactionid;

	/** 应用名称 */
	private String	pAppdetailName;

	/** 版本 */
	private Integer	pAppdetailVersion;

	/** 版本code */
	private Integer	pAppdetailVersioncode;

	/** 上传路径 */
	private String	pAppdetailActionname;

	/** 上传包路径 */
	private String	pAppdetailPackagename;

	/** 删除标记 */
	private String	pAppdetailDelflag;

	/** certdigest */
	private String	pAppdetailCertdigest;

	/** 描述 */
	private String	pAppdetailDesc;

	/** 变更日志 */
	private String	pAppdetailChangelog;

	/** apk */
	private String	pAppdetailApk;

	/** 图标 */
	private String	pAppdetailLogo;

	/** 日期 */
	private Date	pAppdetailDate;
	private String	pAppdetailDateStr;

	/** 平台 */
	private String	pAppdetailPlatform;

	/** 平台版本 */
	private String	pAppdetailPlatformversion;

	/** url地址 */
	private String	pAppdetailUrl;

	/** 应用大小 */
	private Integer	pAppdetailSize;

	/** 版本名称 */
	private String	pAppdetailVersionname;

	/** 补丁状态 */
	private String	pAppdetailPatchstate;

	/** MD5 */
	private String	pAppdetailMd5;

	/** 插件类型 */
	private String	pAppdetailPlugintype;

	/** 图片1 */
	private String	pAppdetailDescpic1;

	/** 图片2 */
	private String	pAppdetailDescpic2;

	/** 图片3 */
	private String	pAppdetailDescpic3;

	/** 审核意见 */
	private String	pAppdetailAuditoption;

	/** 审核状态 1待审核 2已审核  3停用 4启用 */
	private String	pAppdetailAuditstate;

	/** 审核riqi */
	private Date	pAppdetailAuditdate;

	/** 创建者 */
	private Integer	pAppdetailAdminid;

	/** cdnurl */
	private String	pAppdetailCdnurl;

	/** cdnlogo */
	private String	pAppdetailCdnlogo;

	/** cdnpic1 */
	private String	pAppdetailCdnpic1;

	/** cdnpic2 */
	private String	pAppdetailCdnpic2;

	/** cdnpic3 */
	private String	pAppdetailCdnpic3;
	
	private String pAppdetailAdminame;
	private String pAppdetailAdmindesc;

	public String getpAppApkactionType() {
		return pAppApkactionType;
	}

	public void setpAppApkactionType(String pAppApkactionType) {
		this.pAppApkactionType = pAppApkactionType;
	}

	private String pAppApkactionType;
	private Integer openNumber;

	public Integer getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(Integer praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

	public Integer getOpenNumber() {
		return openNumber;
	}

	public void setOpenNumber(Integer openNumber) {
		this.openNumber = openNumber;
	}

	private Integer praiseNumber;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	private String pid;

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpAppdetailId(id):").append(pAppdetailId);
		buffer.append("\tpAppdetailApkactionid(应用ID):").append(pAppdetailApkactionid);
		buffer.append("\tpAppdetailName(应用名称):").append(pAppdetailName);
		buffer.append("\tpAppdetailVersion(版本):").append(pAppdetailVersion);
		buffer.append("\n");
		buffer.append("\tpAppdetailVersioncode(版本code):").append(pAppdetailVersioncode);
		buffer.append("\tpAppdetailActionname(上传路径):").append(pAppdetailActionname);
		buffer.append("\tpAppdetailPackagename(上传包路径):").append(pAppdetailPackagename);
		buffer.append("\tpAppdetailDelflag(删除标记):").append(pAppdetailDelflag);
		buffer.append("\n");
		buffer.append("\tpAppdetailCertdigest(certdigest):").append(pAppdetailCertdigest);
		buffer.append("\tpAppdetailDesc(描述):").append(pAppdetailDesc);
		buffer.append("\tpAppdetailChangelog(变更日志):").append(pAppdetailChangelog);
		buffer.append("\tpAppdetailApk(apk):").append(pAppdetailApk);
		buffer.append("\n");
		buffer.append("\tpAppdetailLogo(图标):").append(pAppdetailLogo);
		buffer.append("\tpAppdetailDate(日期):").append(pAppdetailDate);
		buffer.append("\tpAppdetailPlatform(平台):").append(pAppdetailPlatform);
		buffer.append("\tpAppdetailPlatformversion(平台版本):").append(pAppdetailPlatformversion);
		buffer.append("\n");
		buffer.append("\tpAppdetailUrl(url地址):").append(pAppdetailUrl);
		buffer.append("\tpAppdetailSize(应用大小):").append(pAppdetailSize);
		buffer.append("\tpAppdetailVersionname(版本名称):").append(pAppdetailVersionname);
		buffer.append("\tpAppdetailPatchstate(补丁状态):").append(pAppdetailPatchstate);
		buffer.append("\n");
		buffer.append("\tpAppdetailMd5(MD5):").append(pAppdetailMd5);
		buffer.append("\tpAppdetailPlugintype(插件类型):").append(pAppdetailPlugintype);
		buffer.append("\tpAppdetailDescpic1(图片1):").append(pAppdetailDescpic1);
		buffer.append("\tpAppdetailDescpic2(图片2):").append(pAppdetailDescpic2);
		buffer.append("\n");
		buffer.append("\tpAppdetailDescpic3(图片3):").append(pAppdetailDescpic3);
		buffer.append("\tpAppdetailAuditoption(审核意见):").append(pAppdetailAuditoption);
		buffer.append("\tpAppdetailAuditstate(审核状态):").append(pAppdetailAuditstate);
		buffer.append("\tpAppdetailAuditdate(审核riqi):").append(pAppdetailAuditdate);
		buffer.append("\n");
		buffer.append("\tpAppdetailAdminid(创建者):").append(pAppdetailAdminid);
		buffer.append("\tpAppdetailCdnurl(cdnurl):").append(pAppdetailCdnurl);
		buffer.append("\tpAppdetailCdnlogo(cdnlogo):").append(pAppdetailCdnlogo);
		buffer.append("\tpAppdetailCdnpic1(cdnpic1):").append(pAppdetailCdnpic1);
		buffer.append("\n");
		buffer.append("\tpAppdetailCdnpic2(cdnpic2):").append(pAppdetailCdnpic2);
		buffer.append("\tpAppdetailCdnpic3(cdnpic3):").append(pAppdetailCdnpic3);
		return buffer.toString();
	}
	//================== begin ======================

	public Integer getpAppdetailId()
	{
		return pAppdetailId;
	}

	public void setpAppdetailId(Integer pAppdetailId)
	{
		this.pAppdetailId = pAppdetailId;
	}

	public Integer getpAppdetailApkactionid()
	{
		return pAppdetailApkactionid;
	}

	public void setpAppdetailApkactionid(Integer pAppdetailApkactionid)
	{
		this.pAppdetailApkactionid = pAppdetailApkactionid;
	}

	public String getpAppdetailName()
	{
		return pAppdetailName;
	}

	public void setpAppdetailName(String pAppdetailName)
	{
		this.pAppdetailName = pAppdetailName;
	}

	public Integer getpAppdetailVersion()
	{
		return pAppdetailVersion;
	}

	public void setpAppdetailVersion(Integer pAppdetailVersion)
	{
		this.pAppdetailVersion = pAppdetailVersion;
	}

	public Integer getpAppdetailVersioncode()
	{
		return pAppdetailVersioncode;
	}

	public void setpAppdetailVersioncode(Integer pAppdetailVersioncode)
	{
		this.pAppdetailVersioncode = pAppdetailVersioncode;
	}

	public String getpAppdetailActionname()
	{
		return pAppdetailActionname;
	}

	public void setpAppdetailActionname(String pAppdetailActionname)
	{
		this.pAppdetailActionname = pAppdetailActionname;
	}

	public String getpAppdetailPackagename()
	{
		return pAppdetailPackagename;
	}

	public void setpAppdetailPackagename(String pAppdetailPackagename)
	{
		this.pAppdetailPackagename = pAppdetailPackagename;
	}

	public String getpAppdetailDelflag()
	{
		return pAppdetailDelflag;
	}

	public void setpAppdetailDelflag(String pAppdetailDelflag)
	{
		this.pAppdetailDelflag = pAppdetailDelflag;
	}

	public String getpAppdetailCertdigest()
	{
		return pAppdetailCertdigest;
	}

	public void setpAppdetailCertdigest(String pAppdetailCertdigest)
	{
		this.pAppdetailCertdigest = pAppdetailCertdigest;
	}

	public String getpAppdetailDesc()
	{
		return pAppdetailDesc == null ? "" : pAppdetailDesc;
	}

	public void setpAppdetailDesc(String pAppdetailDesc)
	{
		this.pAppdetailDesc = pAppdetailDesc;
	}

	public String getpAppdetailChangelog()
	{
		return pAppdetailChangelog;
	}

	public void setpAppdetailChangelog(String pAppdetailChangelog)
	{
		this.pAppdetailChangelog = pAppdetailChangelog;
	}

	public String getpAppdetailApk()
	{
		return pAppdetailApk;
	}

	public void setpAppdetailApk(String pAppdetailApk)
	{
		this.pAppdetailApk = pAppdetailApk;
	}

	public String getpAppdetailLogo()
	{
		return pAppdetailLogo;
	}

	public void setpAppdetailLogo(String pAppdetailLogo)
	{
		this.pAppdetailLogo = pAppdetailLogo;
	}

	public Date getpAppdetailDate()
	{
		return pAppdetailDate;
	}

	public void setpAppdetailDate(Date pAppdetailDate)
	{
		this.pAppdetailDate = pAppdetailDate;
	}

	public String getpAppdetailPlatform()
	{
		return pAppdetailPlatform;
	}

	public void setpAppdetailPlatform(String pAppdetailPlatform)
	{
		this.pAppdetailPlatform = pAppdetailPlatform;
	}

	public String getpAppdetailPlatformversion()
	{
		return pAppdetailPlatformversion;
	}

	public void setpAppdetailPlatformversion(String pAppdetailPlatformversion)
	{
		this.pAppdetailPlatformversion = pAppdetailPlatformversion;
	}

	public String getpAppdetailUrl()
	{
		return pAppdetailUrl;
	}

	public void setpAppdetailUrl(String pAppdetailUrl)
	{
		this.pAppdetailUrl = pAppdetailUrl;
	}

	public Integer getpAppdetailSize()
	{
		return pAppdetailSize;
	}

	public void setpAppdetailSize(Integer pAppdetailSize)
	{
		this.pAppdetailSize = pAppdetailSize;
	}

	public String getpAppdetailVersionname()
	{
		return pAppdetailVersionname;
	}

	public void setpAppdetailVersionname(String pAppdetailVersionname)
	{
		this.pAppdetailVersionname = pAppdetailVersionname;
	}

	public String getpAppdetailPatchstate()
	{
		return pAppdetailPatchstate;
	}

	public void setpAppdetailPatchstate(String pAppdetailPatchstate)
	{
		this.pAppdetailPatchstate = pAppdetailPatchstate;
	}

	public String getpAppdetailMd5()
	{
		return pAppdetailMd5;
	}

	public void setpAppdetailMd5(String pAppdetailMd5)
	{
		this.pAppdetailMd5 = pAppdetailMd5;
	}

	public String getpAppdetailPlugintype()
	{
		return pAppdetailPlugintype;
	}

	public void setpAppdetailPlugintype(String pAppdetailPlugintype)
	{
		this.pAppdetailPlugintype = pAppdetailPlugintype;
	}

	public String getpAppdetailDescpic1()
	{
		return pAppdetailDescpic1;
	}

	public void setpAppdetailDescpic1(String pAppdetailDescpic1)
	{
		this.pAppdetailDescpic1 = pAppdetailDescpic1;
	}

	public String getpAppdetailDescpic2()
	{
		return pAppdetailDescpic2;
	}

	public void setpAppdetailDescpic2(String pAppdetailDescpic2)
	{
		this.pAppdetailDescpic2 = pAppdetailDescpic2;
	}

	public String getpAppdetailDescpic3()
	{
		return pAppdetailDescpic3;
	}

	public void setpAppdetailDescpic3(String pAppdetailDescpic3)
	{
		this.pAppdetailDescpic3 = pAppdetailDescpic3;
	}

	public String getpAppdetailAuditoption()
	{
		return pAppdetailAuditoption;
	}

	public void setpAppdetailAuditoption(String pAppdetailAuditoption)
	{
		this.pAppdetailAuditoption = pAppdetailAuditoption;
	}

	public String getpAppdetailAuditstate()
	{
		return pAppdetailAuditstate;
	}

	public void setpAppdetailAuditstate(String pAppdetailAuditstate)
	{
		this.pAppdetailAuditstate = pAppdetailAuditstate;
	}

	public Date getpAppdetailAuditdate()
	{
		return pAppdetailAuditdate;
	}

	public void setpAppdetailAuditdate(Date pAppdetailAuditdate)
	{
		this.pAppdetailAuditdate = pAppdetailAuditdate;
	}

	public Integer getpAppdetailAdminid()
	{
		return pAppdetailAdminid;
	}

	public void setpAppdetailAdminid(Integer pAppdetailAdminid)
	{
		this.pAppdetailAdminid = pAppdetailAdminid;
	}

	public String getpAppdetailCdnurl()
	{
		return pAppdetailCdnurl;
	}

	public void setpAppdetailCdnurl(String pAppdetailCdnurl)
	{
		this.pAppdetailCdnurl = pAppdetailCdnurl;
	}

	public String getpAppdetailCdnlogo()
	{
		return pAppdetailCdnlogo;
	}

	public void setpAppdetailCdnlogo(String pAppdetailCdnlogo)
	{
		this.pAppdetailCdnlogo = pAppdetailCdnlogo;
	}

	public String getpAppdetailCdnpic1()
	{
		return pAppdetailCdnpic1;
	}

	public void setpAppdetailCdnpic1(String pAppdetailCdnpic1)
	{
		this.pAppdetailCdnpic1 = pAppdetailCdnpic1;
	}

	public String getpAppdetailCdnpic2()
	{
		return pAppdetailCdnpic2;
	}

	public void setpAppdetailCdnpic2(String pAppdetailCdnpic2)
	{
		this.pAppdetailCdnpic2 = pAppdetailCdnpic2;
	}

	public String getpAppdetailCdnpic3()
	{
		return pAppdetailCdnpic3;
	}

	public void setpAppdetailCdnpic3(String pAppdetailCdnpic3)
	{
		this.pAppdetailCdnpic3 = pAppdetailCdnpic3;
	}

	public String getpAppdetailAdminame() {
		return pAppdetailAdminame == null ? "已删除" : pAppdetailAdminame;
	}

	public void setpAppdetailAdminame(String pAppdetailAdminame) {
		this.pAppdetailAdminame = pAppdetailAdminame;
	}

	public String getpAppdetailAdmindesc() {
		if (pAppdetailAdmindesc == null || pAppdetailAdminame == null)
			return "";
		return pAppdetailAdmindesc;
	}

	public void setpAppdetailAdmindesc(String pAppdetailAdmindesc) {
		this.pAppdetailAdmindesc = pAppdetailAdmindesc;
	}

	public String getpAppdetailDateStr() {
		if (pAppdetailDate != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(pAppdetailDate);
		}
		return pAppdetailDateStr;
	}

	public void setpAppdetailDateStr(String pAppdetailDateStr) {
		this.pAppdetailDateStr = pAppdetailDateStr;
	}
	//================== end ======================
}
