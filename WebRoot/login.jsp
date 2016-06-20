<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>黄页管理平台</title>
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
            
            frm.action = "<s:url value="/login.do" />";
            frm.submit();
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
      	<input type="text" id="user_code" name="user.userCode" class="tbox" style="width: 180px;" tabIndex="1" value="${user.userCode }" rules="trimBlank"/>
      </td>
      <td height="35">&nbsp;</td>
      <td height="35">&nbsp;</td>
    </tr>
    <tr>
      <td height="35">&nbsp;</td>
      <td height="35"><div align="right" class="STYLE1">密 码：</div></td>
      <td height="35">
      	<input type="password" id="user_pwd" value="888888" name="user.userPassword" class="tbox" style="width: 180px;" tabIndex="2" rules="trimBlank"/>
      </td>
      <td height="35">&nbsp;</td>
      <td height="35">&nbsp;</td>
    </tr>
    <tr>
      <td height="40">&nbsp;</td>
      <td height="40"><div align="right" class="STYLE1">验证码：</div></td>
      <td height="40" colspan="3"><div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="23%">
            	<input type="text" id="securityCode" name="securityCode" class="tbox" style="width: 60px;" tabIndex="3" rules="trimBlank"/>
            </td>
            <td width="43%">
            	<div align="left">
            	<img onclick="this.src='RandImage?'+ Math.random()" style="height:25px;margin-top:0px;margin-left: 1px;cursor: pointer;" alt="点击更换验证码" src="RandImage" id="imgRand" align="absmiddle"/>
            	</div>
            </td>
            <td width="34%">
            	
            </td>
            	
          </tr>
        </table>
      </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><label>
      	<input type="button" class="loginbtn" id="button" name="button" onclick="do_login();return false;" class="loginbtn" tabIndex="4"/>
      </label></td>
      <td colspan="2">
      <span id="msgDiv" style="color:red;text-align:center;font-size:12px">
</span></td>
    </tr>
</table>
</form>

<script type="text/javascript">
		<%
		request.getSession().setAttribute("login.userCode","");
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
