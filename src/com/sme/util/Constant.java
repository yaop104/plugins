package com.sme.util;

import java.util.HashMap;

public abstract class Constant {
	   /**
	    * 返回成功标示
	    */
	   public static String RESPMESSAGE_SUSSECE = "000000";
	   /**
	    * 返回失败标示
	    */
	   public static String RESPMESSAGE_FAIL = "100000";
	   /**
	    * 参数为空
	    */
	   public static String RESPMESSAGE_ISEMPTY = "100088";
	   
	   /**
	    * 查询成功标示
	    */
	   public static String SELECT_SUSSECE = "查询成功！";
	   /**
	    * 查询失败标示
	    */
	   public static String SELECT_FAIL = "查询失败！";
	   
	   /**
	    * 删除成功标示
	    */
	   public static String DELETE_SUSSECE = "删除成功！";
	   /**
	    * 删除失败标示
	    */
	   public static String DELETE_FAIL = "删除失败！";
	   
	   /**
	    * 修改成功标示
	    */
	   public static String UPDATE_SUSSECE = "修改成功！";
	   /**
	    * 修改失败标示
	    */
	   public static String UPDATE_FAIL = "修改失败！";
	   
	   /**
	    * 根据父类查询子类应用列表接口
	    */
	   public static Integer INTERFACE_PIDLIST = 1;
	   
	   /**
	    * 查询app列表接口
	    */
	   public static Integer INTERFACE_APPLIST = 1;
	   
	   /**
	    * 根据应用的ID查询应用详情接口
	    */
	   public static Integer INTERFACE_AIDLIST = 1;
	   
	   /**
	    * 根据实体，AOP修改接口标识
	    */
	   public static HashMap<String,String> INTERFACE_MAP = new HashMap<String,String>();
	   
	   static{
		   	INTERFACE_MAP.put("AppDetail", "AppDetail");
			INTERFACE_MAP.put("AppInfo", "AppInfo");
			INTERFACE_MAP.put("AppProperty", "AppProperty");
			INTERFACE_MAP.put("Iction", "Iction");
//			INTERFACE_MAP.put("SysRole", "SysRole");
	   }
}
