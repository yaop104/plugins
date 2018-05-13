<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- 引用jQuery的css -->
<link rel="stylesheet" href="<%=basePath%>js/easyui/themes/default/easyui.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>js/easyui/themes/icon.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>js/easyui/demo/demo.css" type="text/css"/>
<!-- 引用jQuery的js  -->
<script type="text/javascript" src="<%=basePath %>js/jquery/jquery-1.11.1.js" ></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/common.js" ></script>


<script src="<%=basePath %>js/check.js"	type="text/javascript"></script>