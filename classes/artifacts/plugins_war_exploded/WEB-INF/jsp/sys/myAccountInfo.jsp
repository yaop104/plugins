<%@ page language="java" isELIgnored="false"
		 contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- admin/user/insert.jsp by hy -->
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<jsp:include page="/inc/commonForDialog.jsp" />
	<jsp:include page="/inc/common.jsp" />
</head>

<body>
<form id="frm" name="frm" method="post" class="extendarea">
	<!-- 工具栏 -->
	<div class="controlbar">
		<ul>
			<li class="bar_search">
				<div class="itembody">
					<span class="presenter"><h2>帐号信息</h2></span>
				</div>
			</li>
			<%--<li class="bar_refresh"><a href="#" onclick="closeDialog();">--%>
			<%--<div class="itembody">--%>
			<%--<span class="ico dialogclose"></span><span class="presenter">关闭</span>--%>
			<%--</div>--%>
			<%--</a></li>--%>
		</ul>
	</div>
	<table border="0" cellpadding="0" cellspacing="0" class="formlist"
		   style="width: 100%; table-layout: fixed;">
		<tr>
			<th width="120px">用户名：</th>
			<td>${sysAcc.sysAccName}</td>
		</tr>
		<tr>
			<th width="120px">创建时间：</th>
			<td>
				<fmt:formatDate pattern="yyyy-MM-dd" value="${sysAcc.sysAccCdate}" />
			</td>
		</tr>
		<tr>
			<th width="120px">联系人：</th>
			<td>${sysAcc.sysAccRealname}</td>
		</tr>
		<tr>
			<th width="120px">联系人手机：</th>
			<td>${sysAcc.sysAccMobile}</td>
		</tr>
		<tr>
			<th width="120px">账户余额：</th>
			<td>${sysAcc.sysAccMoney}</td>
		</tr>
	</table>
</form>
</body>
</html>
