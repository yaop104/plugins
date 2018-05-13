<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>管理平台</title>
<link href="<s:url value="/css/default/css/login.css" encode="false"/>"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/jquery.js" encode="false"/>"	type="text/javascript"></script>
<script type="text/javascript">
	document.onkeydown = keyListener;
	function keyListener(e) {
		var keyCode = e ? e.which : event.keyCode;
		if(keyCode == 13) {
			// 回车事件
			do_login();
		}
	}
	
	$(document).ready(function() {
		window.setTimeout('$("#securityCode").focus()', 1000);
		$("#imgRand").click();
	});
	
	function do_login() {
            var frm = document.frm;
			
            var user_code = frm.user_code.value;
            var user_pwd = frm.user_pwd.value;
            if (user_code == "") {
                alert("请输入用户名！");
                frm.user_code.focus();
                return;
            }
            if (user_pwd == "") {
                alert("请输入密码！");
                frm.user_pwd.focus();
                return;
            }
            
            frm.action = "<s:url value="/minilogin.action" encode="false"/>";
            frm.submit();
        }
</script>
	</head>

<body>
<form name="frm" method="post">
	<input type="hidden" name="lastPage" value="<%=request.getParameter("lastPage")%>"/>
	<div class="loginpanel">
    	<table width="39%" border="0" cellpadding="0" cellspacing="0" id="logintb">
            <tr>
                <td width="24%" align="right">用户名：</td>
                <td width="76%"><input type="text" id="user_code" name="user.userCode" class="tbox"  tabIndex="1" value="admin"/></td>
            </tr>
            <tr>
                <td align="right">密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="user_pwd" name="user.userPassword" class="tbox"  tabIndex="2" value="888888"/></td>
            </tr>
            <tr>
                <td align="right">验证码：</td>
                <td>
                    <input type="text" id="securityCode" name="securityCode" class="tbox" style="width:50px" tabIndex="3"/>
                    <img onclick="this.src='RandImage?'+ Math.random()" style="height:25px;margin-top:0px;margin-left: 1px;cursor: pointer;" alt="点击更换验证码" src="RandImage" id="imgRand" align="absmiddle"/>
                </td>
            </tr>
            <tr>
                <td align="right">&nbsp;</td>
                <td>
					<input type="button" id="button" name="button" onclick="do_login();" class="loginbtn" tabIndex="4"/>
				</td>
            </tr>
      </table>
    </div>
    <div id="msgDiv" style="margin-bottom:30px;color:red;text-align:center;">
    </div>
</form>
<script type="text/javascript">
		<%
		String strError = "";
		if (request.getAttribute("fieldErrors['securityCode']") != null
				&& !request.getAttribute("fieldErrors['securityCode']").toString()
						.equalsIgnoreCase("NULL")
				&& request.getAttribute("fieldErrors['securityCode']").toString().trim()
						.length() > 0) {
			strError = request.getAttribute("fieldErrors['securityCode']").toString();
		}
		else if (request.getAttribute("fieldErrors['pwd']") != null
				&& !request.getAttribute("fieldErrors['pwd']").toString()
						.equalsIgnoreCase("NULL")
				&& request.getAttribute("fieldErrors['pwd']").toString().trim()
						.length() > 0) {
			strError = request.getAttribute("fieldErrors['pwd']")
					.toString();
		}
	%>
	var err = <%="\""+strError+"\""%>;
	if(err!=""){
		$("#msgDiv").html(err);
	}
</script>
</body>
</html>
