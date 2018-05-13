<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib.jsp"%>
<title>角色信息</title>
</head>
<body>
	<h2>角色信息</h2>
	<div class="bottom_pic_border_left"><div class="bottom_pic_border_right"><div class="border_bg1"></div></div></div>
	<form:form id="inputForm" modelAttribute="sysRole"
		action="${ctx }/sysRole/save.do" method="post" class="extendarea">
		<form:input path="roleid" type="hidden" />
		 <!-- 工具栏 -->
	    <div class="controlbar">
		    <ul>
		        <li class="bar_search">
		            <a href="#" onclick="save();">
		                <div class="itembody"><span class="ico ico_create"></span><span class="presenter">保存</span></div>
		            </a>
		        </li>
		        <li class="bar_refresh">
		            <a href="javascript:back();">
		                <div class="itembody"><span class="ico ico_search"></span><span class="presenter">返回</span></div>
		            </a>
		        </li>
		    </ul>
		</div>
		<div class="listinfo2">
		<table border="0" cellpadding="0" cellspacing="0" class="formlist" style="width:90%;table-layout:fixed;">
			<tr>
				<th width="120px">角色名称：</th>
				<td><form:input path="rolename" class="input validate[required,maxSize[10]]" style="width=75%;"/></td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
				 <form:select path="rolestate" class="input" style="width=75%;">  
			           <form:option value="1">有效</form:option>
			           <form:option value="2">无效</form:option>
			     </form:select>
				</td>
			</tr>
			<tr>
				<th>角色类型：</th>
				<td>
				<form:select path="roletype" class="input" style="width=75%;">  
		           <form:option value="1">超级管理员</form:option>
		           <form:option value="2">管理员</form:option>
		           <form:option value="3">用户</form:option>
		        </form:select>
				</td>
			</tr>
			<tr>
				<th>排序：</th>
				<td><form:input path="roleorder" class="input validate[required,integer,max[99]]" style="width=75%;"/></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><form:textarea path="roledesc" rows="5" class="textarea validate[max[128]]" style="width=75%;"/></td>
			</tr>
		</table>
		<div class="bottom_pic_border_left"><div class="bottom_pic_border_right"><div class="border_bg1"></div></div></div>
	</form:form>
	</div>
	<script language="javascript">

// 返回方法
function back() {
	history.go(-1);
}

//保存方法
function save(){
	var a = $("#inputForm").validationEngine('validate');
	if(a){
		$.ajax({
			url:'${ctx }/sysRole/save.do',
			type:'post',
			data:$("#inputForm").serialize(),
			dataType : 'json',
			success:function(data){
				if(data.code=="0"){
					alert("成功！");
					location.href = "${ctx }"+ data.message;
				}else{
					alert("失败！");
				}
			}
		});
	}
}
</script>
</body>
</html>