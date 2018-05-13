<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<title>账号信息</title>
</head>
<body>
<!-- 	<div region="north" style="height:50px"> -->
<!-- 	   <div id="tb" style="padding: 10px; height: auto">   -->
<!-- 	          账号名:    -->
<!-- 	           <input id="s_name"/>    -->
<!-- 	           按状态：   -->
<!-- 	             <select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">   -->
<!-- 	             	<option value="1">有效</option> -->
<!-- 					 <option value="2">无效</option> -->
<!-- 	             </select>   -->
<!-- 	           <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>   -->
<!-- 	       </div>   -->
<!-- 	   </div>   -->
		
		
<!-- 		</div> -->
	  	<div region="center"   border="false">
			<table class="easyui-datagrid"  style="width:760px;height:330px"
					data-options="singleSelect:true,nowrap:true,loadMsg:'正在加载数据.......',collapsible:false,url:'${ctx }/sysAccount/page.do',method:'post'">
				<thead>
					<tr>
						<th data-options="field:'sysAccId',width:150,align:'center'">ID</th>
						<th data-options="field:'sysAccName',width:150,align:'center'">昵称</th>
						<th data-options="field:'sysAccRealname',width:120,align:'center'">姓名</th>
						<th data-options="field:'sysAccState',width:80,align:'center'">状态</th>
						<th data-options="field:'sysAccRoleid',width:80,align:'center'">角色</th>
						<th data-options="field:'sysAccOrgid',width:150,align:'center'">组织</th>
					</tr>
				</thead>
			</table>
		</div>
</body>
</html>