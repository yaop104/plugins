<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="x-ua-compatible"content="IE=7"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!-- 引用jQuery的css -->
<link rel="stylesheet" href="<%=basePath %>css/selectTree.css" />
<link rel="stylesheet" href="<%=basePath%>js/ztree/js/zTree_v3/css/zTreeStyle.css" type="text/css"/>

<!-- 引用jQuery的js  -->
<script type="text/javascript" src="<%=basePath %>js/jquery/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>js/ztree/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ztree/js/zTree_v3//js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ztree/js/chooseTree.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/locale/easyui-lang-zh_CN.js" ></script>


<style type="text/css">
	.tip_div{
		  padding: 20px 30px;
		  font-size: 15px;
		  color: rgb(145, 135, 135);
	  }
	 input, button{font-family:"Arial", "Tahoma", "微软雅黑", "雅黑";border:0;vertical-align:middle;margin:8px;line-height:18px;font-size:18px}
	.btn{width:140px;height:36px;line-height:18px;font-size:18px;
		background:url("img/temp/bg26.jpg") no-repeat left top;color:#FFF;padding-bottom:4px;cursor: pointer;margin-left:45px;}
</style>


<script type="text/javascript">
	//屏幕自适应
function checkSize() {
    var h = document.documentElement.clientHeight;
    document.getElementById('aside').style.height = h - 10 + "px";
    document.getElementById('rside').style.height = h - 10 + "px";
}
	$(function(){
		$(".btn").mouseover(function() {
			$(this).css("background-position", "left -36px");
		})
		.mouseout(function(){
			$(this).css("background-position", "left top");
		});
	});
</script>

</head>

<body onload="checkSize()">
<div>
    <div id="aside">
	    <div>
			<input id="btn" class="btn" type="button" onclick="saveTreeNode()" value="保存权限" style="visibility: hidden;"/>
		</div>
	
		<div class="bottom_pic_border_left"><div class="bottom_pic_border_right"><div class="border_bg1"></div></div></div>
	
    	 <ul id="menuTree" class="ztree"><li><div class="tip_div">点击角色查看对应权限详情</div></li></ul>
    </div>
    <div id="rside">
   		 <iframe src="<%=basePath %>sysRole/roleView.do" name="contentFrame" id="contentFrame" frameborder="0" height="100%" width="100%"></iframe>
    </div>
</div>
</body>
</html>