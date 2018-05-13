<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib.jsp"%>
<title>账号信息</title>
</head>
<body>
	<h2>账号信息</h2>
	<div class="bottom_pic_border_left"><div class="bottom_pic_border_right"><div class="border_bg1"></div></div></div>
	<form:form id="inputForm" modelAttribute="sysAcc"
		action="${ctx }/sysAcc/save.do" method="post" class="extendarea">
		<form:input path="sysAccId" type="hidden" />
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
		<table border="0" cellpadding="0" cellspacing="0" class="formlist" style="width:95%;table-layout:fixed;">
			<tr>
				<th width="120px">昵称：</th>
				<td><form:input path="sysAccName" class="input validate[required,maxSize[10]]" style="width=75%;"/></td>
			</tr>
			<tr>
				<th>真实姓名：</th>
				<td><form:input path="sysAccRealname" class="input" style="width=75%;"/></td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
				 <form:select path="sysAccState" class="input" style="width=75%;">  
			           <form:option value="1">有效</form:option>
			           <form:option value="2">无效</form:option>
			     </form:select>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>密码：</td> -->
<%-- 				<td><form:input path="sysAccPassword"/></td> --%>
<!-- 			</tr> -->
			<tr>
				<th>角色：</th>
				<td>
				<form:select path="sysAccRoleid" class="input" style="width=75%;">  
			           <s:forEach items="${uproles }" var="option">
			           		<form:option value="${option.roleid }" >
			           			${option.rolename }
			           		</form:option>
			           </s:forEach>
			        </form:select>
				</td>
			</tr>
			<tr>
				<th>组织：</th>
				<td><form:input path="sysAccOrgid" class="input" style="width=75%;"/></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><form:textarea path="sysAccDesc" rows="5" class="textarea" style="width=75%;"/></td>
			</tr>
			<br/>
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
			url:'${ctx }/sysAcc/save.do',
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