<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- admin/user/view.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/commonForDialog.jsp" />
<script language="javascript">
// 取消
function closeDialog() {
	art.dialog.close();
}
</script>
</head>

<body>
<form id="frm" name="frm" method="post" class="extendarea">
    <!-- 工具栏 -->
	<div class="controlbar">
	    <ul>
	        <li>
	            <a href="#" onclick="closeDialog();">
	                <div class="itembody"><span class="ico dialogclose"></span><span class="presenter">关闭</span></div>
	            </a>
	        </li>
	    </ul>
	</div>
	<div class="listinfo2">
    <table border="0" cellpadding="0" cellspacing="0" style="width:100%;table-layout:fixed;">
    	<tr>
            <th width="120px">账号：</th>
            <td>${user.userCode }</td>
        </tr>
        <tr>
            <th>显示名：</th>
            <td>${user.userName }</td>
        </tr>
        <tr>
            <th>所属地区：</th>
            <td>
           		<s:iterator value="areas" status="stuts">
           			<s:if test="user.areaId==pkid">${areaName }</s:if>
				</s:iterator>
            </td>
        </tr>
        <tr>
            <th>E-mail：</th>
            <td>${user.email }</td>
        </tr>
        <tr>
            <th>联系电话：</th>
            <td>${user.phone }</td>
        </tr>
        <tr>
            <th>状态：</th>
            <td>
                <s:if test="user.validFlag==1">正常</s:if>
                <s:elseif test="user.validFlag==0">无效</s:elseif>
                <s:else>锁定</s:else>
            </td>
        </tr>
        <tr>
            <th>说明：</th>
            <td>
                <textarea class="textarea" id="note" name="user.note" rows="5" readonly>${user.note }</textarea>
            </td>
        </tr>
    </table>
    </div>
</form>
</body>
</html>
