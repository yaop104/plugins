<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:set var="ctx" value="${pageContext.request.contextPath}"/>


<link href="<s:url value="/css/default/default.css" />"	rel="stylesheet" type="text/css"/>
<link href="<s:url value="/css/all_icon.css" />"	rel="stylesheet" type="text/css"/>
<link href="<s:url value="/css/index.css" />"	rel="stylesheet" type="text/css"/>
<link href="<s:url value="/css/newcss/css/demo.css" />"	rel="stylesheet" type="text/css"/>
<link href="<s:url value="/js/jquery//validationEngine.jquery.css"/>"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/jquery/jquery-1.11.1.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/jquery/jquery.validationEngine.min.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/jquery/jquery.validationEngine-zh_CN.js" />"	type="text/javascript"></script>
<!-- V200R400 -->
<link href="<s:url value="/css/v200r400/welcome.css" />" rel="stylesheet" type="text/css"/>

<link href="<s:url value="/js/artDialog4.1.5/skins/blue.css" />"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/artDialog4.1.5/artDialog.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/artDialog4.1.5/plugins/iframeTools.source.js" />"	type="text/javascript"></script>
