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

d.add(0,-1,'My example tree');
d.add(1,0,'Node 1','js/dtree/example01.html');
d.add(2,0,'Node 2','js/dtree/example01.html');
d.add(3,1,'Node 1.1','js/dtree/example01.html');
d.add(4,0,'Node 3','js/dtree/example01.html');
d.add(5,3,'Node 1.1.1','js/dtree/example01.html');
d.add(6,5,'Node 1.1.1.1','js/dtree/example01.html');
d.add(7,0,'Node 4','js/dtree/example01.html');
d.add(8,1,'Node 1.2','js/dtree/example01.html');
d.add(9,0,'My Pictures','js/dtree/example01.html','Pictures I\'ve taken over the years','','','js/dtree/img/imgfolder.gif');
d.add(10,9,'The trip to Iceland','js/dtree/example01.html','Pictures of Gullfoss and Geysir');
d.add(11,9,'Mom\'s birthday','js/dtree/example01.html');
d.add(12,0,'Recycle Bin','js/dtree/example01.html','','main','js/dtree/img/trash.gif');

document.write(d);
	</script>
</div>
</body>
</html>
