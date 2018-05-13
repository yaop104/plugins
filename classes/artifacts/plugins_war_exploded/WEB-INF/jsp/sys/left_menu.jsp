<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理平台</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="管理平台" />
	<meta http-equiv="description" content="菜单" />
	<link href="js/dtree/dtree.css"	rel="stylesheet" type="text/css"/>
	<script src="js/dtree/dtree.js" type="text/javascript"></script>
	<script src="js/jquery/jquery-1.4.3.js" type="text/javascript"></script>
    <script>
    $(document).ready(function(){
		$('.leftMenu').height($(window).height()-19);
	})
    </script>
</head>
<style type="text/css">
<!--
.leftMenu {
	border: 1px solid #a5d3e7;
	padding-top: 5px;
	padding-right: 5px;
	padding-bottom: 10px;
	padding-left: 10px;
	visibility: visible;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	background-image: url(css/default/images/fsdgfj.gif);
	background-repeat: no-repeat;
}
-->
</style>
<body style="margin:0px;">
<div class="leftMenu">
<script type="text/javascript">
var imgPath = "js/dtree/";
d = new dTree('d');
<s:forEach items="${menus}" var="menu">  
	d.add('${menu.sysMenuId}','${menu.sysMenuPid}','${menu.sysMenuName}','${menu.sysMenuUrl}','${menu.sysMenuName}','main');
</s:forEach> 

document.write(d);
	</script>
</div>
</body>
</html>
