<%@ page language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="<s:url value="/css/default/css/css.css" />"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/jquery/jquery-1.4.3.js" />"	type="text/javascript"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.epiclock.js" />">

<script>
$(function(){
    $('#epiClock').epiclock({ format : ' Y年F月j日　G:i:s　D' });  //绑定
    $.epiclock();               //开始运行
});

		function btnChangeClass(btn,clsName){
			if(btn){
				btn.className = clsName;
			}
		}
		
		function btnChangepwd_Click()
		{
			var url="<s:url value="/modifyPwd.jsp"/>";
			top.frames.main.openDialog('修改密码',url,400,200,null);
		}
		
		/* function btnExit_Click()
		{
			top.location = "logout.action";
		} */
		
		function btnLogin_Click()
		{
			top.location = "login.jsp";
		}
		
		function switchSysBar(){
			 if (parent.document.getElementById('frame2').cols=="200,*")
			 {
				document.getElementById('menuHid').innerHTML="显示菜单";
				parent.document.getElementById('frame2').cols="0,*";
			 }
			 else{
				 document.getElementById('menuHid').innerHTML="隐藏菜单";
				 parent.document.getElementById('frame2').cols="200,*";
			 }
		}
</script>
<%
	String userName = "游客";
	if (session.getAttribute("login.userName") != null
			&& !session.getAttribute("login.userName").toString()
					.equalsIgnoreCase("NULL")
			&& session.getAttribute("login.userName").toString().trim()
					.length() > 0) {
		userName = session.getAttribute("login.userName").toString();
	}
	else if (session.getAttribute("login.userCode") != null
			&& !session.getAttribute("login.userCode").toString()
					.equalsIgnoreCase("NULL")
			&& session.getAttribute("login.userCode").toString().trim()
					.length() > 0) {
		userName = session.getAttribute("login.userCode").toString();
	}
%>
</head>
<body>
<div id="head">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
 <td align="left"> <div id="logo"></div>
 </td>
 <td align="right"><div id="logol"></div></td>
  </tr>
</table>
 </div>
<div id="menuu">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="6%">
      <div id="menul"></div>
    </td>
    <td width="48%" nowrap>
    <div id="epiClock"></div>
   	</td>
    <%if("游客".equals(userName)){ %>
    	 <td width="28%" nowrap><span id="spInfo">你好 <%=userName%> 欢迎使用本系统&nbsp;&nbsp;</span>
    	 |<a href="#" onclick="switchSysBar()">&nbsp;<span id="menuHid">隐藏菜单</span>&nbsp;</a>
    	 | <a href="#" onclick="btnLogin_Click()">&nbsp;登陆&nbsp;</a>
    <%}else{ %>
	    <td width="28%" nowrap><span id="spInfo">你好 <%=userName%> 欢迎使用本系统 &nbsp;</span>
	    |<a href="#" onclick="switchSysBar()">&nbsp;<span id="menuHid">隐藏菜单</span>&nbsp;</a>
	    |<a href="#" onclick="btnChangepwd_Click()">&nbsp;修改密码&nbsp;</a>
	    |<a href="#" onclick="btnExit_Click()">&nbsp;注销&nbsp;</a>
    <%} %>
    </td>
    <td width="6%"><div id="menur"></div></td>
  </tr>
</table>
</div>
</body>
</html>
