<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>管理平台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="<s:url value="/css/default/css/login.css" />"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/jquery.js" />"	type="text/javascript"></script>
<script type="text/javascript" >
	if(parent && parent.frames.length>0){
		top.location.href = "login.jsp";
	}
	document.onkeypress = keyListener;
	function keyListener(e) {
		var keyCode = e ? e.which : event.keyCode;
		if(keyCode == 13) {
			// 回车事件
			do_login();
		}
	}
	
	$(document).ready(function() {
		if ($.trim($("#user_code").val()).length == 0) {
			$("#user_code").focus();
		} else {
			$("#user_code").focus();
			$("#user_code").select();
		}


		// 自动除掉两边空格
		$("input").each(function (i,j) {
			var $obj = $(j).attr("rules");
			if ($obj != undefined && $obj.indexOf("trimBlank") != -1) {
				$(j).bind("blur",function () {
					$(j).val($.trim($(j).val()));
				});
			}
		});
		
	});

	function do_login() {
		var account = $('#user_code').val();
		var password = $('#user_pwd').val();
		if(account == ""){
			alert("请输入用户名！");
			$('#user_pwd').focus();
			return false;
		}
		if(password == ""){
			alert("请输入密码！");
			$('#user_pwd').focus();
			return false;
		}
		
		$.post('<%=basePath%>login.do', {'account':account, 'password':password}, function(data){
			if("0"==data.code){
				location.href = data.message;
			}else{
				alert(data.message);
				}
		}, "json");
			
    }
</script>
	</head>

<body>
<form name="frm" method="post">
<div class="loginpanel">
	<table width="100%" height="239" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="19%" height="77">&nbsp;</td>
      <td width="13%">&nbsp;</td>
      <td width="40%">&nbsp;</td>
      <td width="19%">&nbsp;</td>
      <td width="9%">&nbsp;</td>
    </tr>
    <tr>
      <td height="35">&nbsp;</td>
      <td height="35"><div align="right" class="STYLE1">用户名：</div></td>
      <td height="35">
      	<input type="text" id="user_code" name="user_code" class="tbox" style="width: 180px;" tabIndex="1"  rules="trimBlank"/>
      </td>
      <td height="35">&nbsp;</td>
      <td height="35">&nbsp;</td>
    </tr>
    <tr>
      <td height="35">&nbsp;</td>
      <td height="35"><div align="right" class="STYLE1">密 码：</div></td>
      <td height="35">
      	<input type="password" id="user_pwd"  name="user_pwd" class="tbox" style="width: 180px;" tabIndex="2" rules="trimBlank"/>
      </td>
      <td height="35">&nbsp;</td>
      <td height="35">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><label>
      	<input type="button" class="loginbtn" id="button" name="button" onclick="do_login();return false;" class="loginbtn" tabIndex="3"/>
      </label></td>
		<td height="35">
            <%--<a href="<%=basePath%>register.do">申请账号</a>--%>
        </td>
      <td colspan="2">
      <span id="msgDiv" style="color:red;text-align:center;font-size:12px">

	  </span></td>
    </tr>
</table>
</form>
</body>
</html>
