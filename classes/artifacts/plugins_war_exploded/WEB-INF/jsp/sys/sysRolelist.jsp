<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<title>角色管理</title>
<script type="text/javascript" src="${ctx }/js/sys/role/role.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true"  border="false">
	<div region="north" style="height:50px">
		<%--功能区--%>  
	   <div id="tb" style="padding: 10px; height: auto">  
	      <%-- 查找管理员信息，根据时间、管理员名 --%>  
	       <div>    
	         	角色名称:   
	           <input id="s_name"/>   
	           	状态：  
	             <select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">  
	             	<option value="">全部</option>
	             	<option value="1">有效</option>
					 <option value="2">无效</option>
	             </select>  
	           <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>  
	       </div>  
	   </div>  
		
	
	</div>
  	<div region="center"   border="false">
  		<table id="t1"></table>
  		
  			<!-- 窗口-->
			<div id="d1" class="easyui-dialog" buttons="#btn1" title="账号编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;top: 28px;">  
		         <div style="padding:10px 60px 20px 60px">
		            <form id="f1"  class="easyui-form" method="post">  
		            <input type="hidden" id="roleid" name="roleid"/>
		                <table>  
		                    <tr>  
		                        <td align="right">角色名称：</td>  
		                        <td><input class="easyui-validatebox" name="rolename" required="true" style="width: 152px" id="rolename"></input></td>  
		                    </tr>  
		                      <tr>  
		                        <td align="right">状态：</td>  
		                        <td>
		                        <select  class="easyui-combobox" name="rolestate" id="rolestate" style="width:152px;" required="true" editable="false">
		                       		 <option value="1">有效</option>
									 <option value="2">无效</option>
		                        </select>
		                        </td>  
		                    </tr> 
		                      <tr>  
		                        <td align="right">角色类型：</td>  
		                        <td>
		                        <select  class="easyui-combobox" name="roletype" id="roletype" style="width:152px;" required="true" editable="false">
						          	<option value="1">超级管理员</option>
						           	<option value="2">管理员</option>
						           	<option value="3">用户</option>
		                        </select>
		                        </td>  
		                    </tr> 
		                      <tr>  
		                        <td align="right">排序：</td>  
		                        <td><input name="roleorder" class="easyui-validatebox" required="true" style="width: 152px" id="roleorder"></input></td>  
		                    </tr> 
		                    <tr>
								<td>备注：</td>
								<td><input class="easyui-textbox" id="roledesc" name="roledesc" data-options="multiline:true" style="height:60px"></input></td>
							</tr>
		                </table>  
		            </form>  
		            </div>  
		     </div>
             <div id="btn1">
		    	<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px"> 保  存 </a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px"> 取  消 </a>
		    </div>
  	</div>
 </div>
</body>
</html>