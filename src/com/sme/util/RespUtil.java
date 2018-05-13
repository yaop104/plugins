package com.sme.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具
 * @author yao
 *
 */
public  class RespUtil {
	
	/**
	 * 分页操作
	 * 
	 * @param total
	 * @param rows
	 * @return
	 */
	public static Map<String, Object> pageResult(int total, List rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", rows);
		return result;
	}
	
	/**
	 * 返回到页面的对象
	 * PageList 
	 * list:
	                     列表中的具体对象属性列表
	 * recordCount:
	                    分页参数（总条数）
	 * pageSize:
	                   分页参数(每页的大小)
	 * pageNo:
	                   分页参数(当前页数)
	 * 
	 */
	public static void setResp(List list,int count,String pageSize,String pageNo,HttpServletRequest req){
		int pageSize1;
		int pageNum;
		if("".equals(pageNo)||"".equals(pageSize)||pageNo==null||pageSize==null){
		     pageSize1=10;
		     pageNum=1;
		}else{
			 pageSize1=Integer.parseInt(pageSize);
			 pageNum=Integer.parseInt(pageNo);
		}
		int pageCount;
		pageCount=count/pageSize1;
		if(count%pageSize1>=1){
			pageCount++;
		}
		PageList pageList=new PageList();
		PageInfo pageInfo=new PageInfo();
		pageInfo.setRecordCount(count);
		pageInfo.setPageSize(pageSize1);
		pageInfo.setPageNo(pageNum);
		pageInfo.setPageCount(pageCount);
		if(list!=null&&list.size()>0){
		   pageList.setList(list);
		}
		pageList.setPageInfo(pageInfo);
		req.setAttribute("pageList", pageList);
	}
	
	/**
	 * 将页面分页参数，转为mybatis（limit）所需参数
	 * PageList 
	 * list:
	                     列表中的具体对象属性列表
	 * recordCount:
	                    分页参数（总条数）
	 * pageSize:
	                   分页参数(每页的大小)
	 * pageNo:
	                   分页参数(当前页数)
	 * 
	 */
	public static Map<String, Object> changePage(String pageSize,String pageNo,Map<String, Object>  parm){
		int pageSize1;
		int pageNum;
		if("".equals(pageNo)||"".equals(pageSize)||pageNo==null||pageSize==null){
		     pageSize1=10;
		     pageNum=1;
		}else{
			 pageSize1=Integer.parseInt(pageSize);
			 pageNum=Integer.parseInt(pageNo);
		}
		int page=(pageNum-1)*pageSize1;
		int pageCount=10;
		parm.put("page", page);
		parm.put("pageCount", pageCount);
		return parm;
	}
	
}
