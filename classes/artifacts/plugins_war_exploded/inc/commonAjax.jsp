<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
 <s:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="<s:url value="/css/newcss/css/demo.css" />"	rel="stylesheet" type="text/css"/>
<link href="<s:url value="/css/newcss/css/all_icon.css" />"	rel="stylesheet" type="text/css"/>

<script src="<s:url value="/js/jquery/jquery-1.4.3.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/sortTable.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/tableFun.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/pageFun.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/validate/js/jquery.validate.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/jquery.form.js" />"	type="text/javascript"></script>

<link href="<s:url value="/js/artDialog4.1.5/skins/blue.css" />"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/artDialog4.1.5/artDialog.js" />"	type="text/javascript"></script>
<script src="<s:url value="/js/artDialog4.1.5/plugins/iframeTools.source.js" />"	type="text/javascript"></script>

<script src="<s:url value="/js/loading.js" />"	type="text/javascript"></script>
<link href="<s:url value="/css/loading.css" />" rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/keypress/keypress.js" />"	type="text/javascript"></script>

<!-- js发送post请求，用于处理文件下载请求参数中带中文问题  -->
<script src="<s:url value="/js/httpPost.js" />"	type="text/javascript"></script>
