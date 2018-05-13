<%@ page language="java" isELIgnored="false" pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>管理平台</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
	</head>
	<body>
		<script language="JavaScript" type="text/JavaScript">
			<%
				String lastPage = request.getParameter("lastPage");
				if(lastPage != null && lastPage.trim().length() > 0 && !"null".equalsIgnoreCase(lastPage)){
					lastPage = request.getContextPath() + lastPage;
			%>
				if(top.main && top.leftmenu){
					top.leftmenu.location.href = top.leftmenu.location.href;
			    	top.main.location = "<%=lastPage%>";
				}else{
					window.location = "<%=lastPage%>";
				}
			<%} else {%>
				window.location = '<%=request.getContextPath()%>';
			<%}%>
		</script>
	</body>
</html>
