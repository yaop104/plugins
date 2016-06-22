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
    <link href="<s:url value="/js/dtree/dtree.css" encode="false"/>" type="text/css" rel="StyleSheet" />
    <script src="<s:url value="/js/dtree/dtree.js" encode="false"/>"	type="text/javascript"></script>
    <script src="<s:url value="/js/jquery/jquery-1.4.3.js" encode="false"/>"	type="text/javascript"></script>
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
	var imgPath = "<s:url value="/js/dtree/" encode="false"/>";
	d = new dTree('d');
	d.add('0','-1','管理系统','','管理系统');
	<s:iterator value="menus" status="stuts">
	d.add('${pkid}','${parentId}','${menuName}','${url}','${menuName}','main');
	</s:iterator>
	document.write(d);
	</script>
</div>
</body>
</html>
