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
<link href="<s:url value="/css/default/css/register.css" />"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/jquery.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/check.js" />"	type="text/javascript"></script>
<script type="text/javascript" >

	document.onkeypress = keyListener;
	function keyListener(e) {
		var keyCode = e ? e.which : event.keyCode;
		if(keyCode == 13) {
			// 回车事件
			do_register();
		}
	}
	
	$(document).ready(function() {
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

	function ctxCancel(){
		history.go(-1);
	}

	function do_register() {
		if(!isInput('user_code', '公司名称', 128, 1)){
			return false;
		}
		if(!isInput('user_name', '用户名', 128, 1)){
			return false;
		}
		if(!isInput('user_connectname', '联系人', 128, 1)){
			return false;
		}

		if(!isMobilePhone($("#user_mobile").val(), false)){
			alert("输入手机号码为空或者号码不正确");
			$("#user_mobile").focus();
			return false;
		}

		if(!isInput('user_pwd', '密码', 64, 1)){
			return false;
		}
		var commpany = $('#user_code').val();
		var username = $('#user_name').val();
		var cname = $('#user_connectname').val();
		var moblie = $('#user_mobile').val();
		var password = $('#user_pwd').val();
		$.post('<%=basePath%>sysAcc/register.do',
				{   'sysAccName':username,
					'sysAccPassword':password,
			        'sysAccDesc':commpany,
					'sysAccRealname':cname,
					'sysAccMobile':moblie
				}, function(data){
			if(data.success){
				alert(data.message);
				location.href = '<%=basePath%>login.do';
			}else{
				alert(data.message);
				}
		}, "json");
			
    }
</script>
	</head>

<body>
<form name="frm" method="post">
<div class="loginpanel1">
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
      <td height="35"><div align="right" class="STYLE1">公司名：</div></td>
      <td height="35">
      	<input type="text" id="user_code" name="user_code" class="tbox" style="width: 180px;" tabIndex="1"  rules="trimBlank"/>
      </td>
      <td height="35">&nbsp;</td>
      <td height="35">&nbsp;</td>
    </tr>
    <tr>
      <td height="35">&nbsp;</td>
      <td height="35"><div align="right" class="STYLE1">用户名：</div></td>
      <td height="35">
      	<input type="text" id="user_name"  name="user_name" class="tbox" style="width: 180px;" tabIndex="2" rules="trimBlank"/>
      </td>
      <td height="35">&nbsp;</td>
      <td height="35">&nbsp;</td>
    </tr>
		<tr>
			<td height="35">&nbsp;</td>
			<td height="35"><div align="right" class="STYLE1">联系人：</div></td>
			<td height="35">
				<input type="text" id="user_connectname"  name="user_connectname" class="tbox" style="width: 180px;" tabIndex="2" rules="trimBlank"/>
			</td>
			<td height="35">&nbsp;</td>
			<td height="35">&nbsp;</td>
		</tr>
		<tr>
			<td height="35">&nbsp;</td>
			<td height="35"><div align="right" class="STYLE1">手机号：</div></td>
			<td height="35">
				<input type="text" id="user_mobile"  name="user_mobile" class="tbox" style="width: 180px;" tabIndex="2" rules="trimBlank"/>
			</td>
			<td height="35">&nbsp;</td>
			<td height="35">&nbsp;</td>
		</tr>
		<tr>
			<td height="35">&nbsp;</td>
			<td height="35"><div align="right" class="STYLE1">密码：</div></td>
			<td height="35">
				<input type="password" id="user_pwd"  name="user_pwd" class="tbox" style="width: 180px;" tabIndex="2" rules="trimBlank"/>
			</td>
			<td height="35">&nbsp;</td>
			<td height="35">&nbsp;</td>
		</tr>
    <tr>
      <td>&nbsp;</td>
      <td><label>
      	<input type="button" class="loginbtn1" id="button1" name="button" onclick="do_register();return false;"  tabIndex="2" value=""/>
      </label></td>
		<td height="35">&nbsp;&nbsp;&nbsp;&nbsp;<label>
			<input type="button" class="loginbtn2" id="button2" name="button" onclick="ctxCancel();return false;"  tabIndex="2" value=""/>
		</label></td>
      <td colspan="2">
      <span id="msgDiv" style="color:red;text-align:center;font-size:12px">

	  </span></td>
    </tr>
</table>
</form>
</body>
</html>
