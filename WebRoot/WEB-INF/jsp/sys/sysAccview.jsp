<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- admin/user/view.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/common.jsp" />
</head>

<body>
<form id="frm" name="frm" method="post" class="extendarea">
    <!-- 工具栏 -->
	<div class="controlbar">
	    <ul>
	        <li>
	            <a href="${ctx }/cmcc_sme/sysAcc/sysAcclist.do">
	                <div class="itembody"><span class="ico dialogclose"></span><span class="presenter">返回</span></div>
	            </a>
	        </li>
	    </ul>
	</div>
	<div class="listinfo2">
    <table border="0" cellpadding="0" cellspacing="0" style="width:100%;table-layout:fixed;">
    	<tr>
            <th width="120px">昵称：</th>
            <td>${sysAcc.sysAccName }</td>
        </tr>
        <tr>
            <th>姓名：</th>
            <td>${sysAcc.sysAccRealname }</td>
        </tr>
        <tr>
            <th>状态：</th>
            <td>${sysAcc.sysAccState }</td>
        </tr>
        <tr>
            <th>角色：</th>
            <td>${sysAcc.sysAccRoleid }</td>
        </tr>
        <tr>
            <th>组织：</th>
            <td>${sysAcc.sysAccOrgid }</td>
        </tr>
        <tr>
            <th>说明：</th>
            <td>
                <textarea class="textarea"  rows="5" readonly>${sysAcc.sysAccDesc }</textarea>
            </td>
        </tr>
    </table>
    </div>
</form>
</body>
</html>
