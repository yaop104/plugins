<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机构列表</title>
<link href="<s:url value="/js/artDialog4.1.5/skins/blue.css" encode="false"/>"	rel="stylesheet" type="text/css"/>
<script src="<s:url value="/js/artDialog4.1.5/artDialog.js" encode="false"/>"	type="text/javascript"></script>
<script src="<s:url value="/js/artDialog4.1.5/plugins/iframeTools.source.js" encode="false"/>"	type="text/javascript"></script>

<link rel="stylesheet" href="<s:url value="/js/jquery-ztree/css/zTreeStyle/zTreeStyle.css" encode="false"/>" type="text/css">
<script type="text/javascript" src="<s:url value="/js/jquery-ztree/js/jquery-1.4.4.min.js" encode="false"/>" ></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ztree/js/jquery.ztree.core-3.2.js" encode="false"/>" ></script>
<style type="text/css">
.ztree li a.copy{padding-top:0; background-color:#316AC5; color:white; border:1px #316AC5 solid;}
.ztree li a.cut{padding-top:0; background-color:silver; color:#111; border:1px #316AC5 dotted;}
</style>
<script type="text/javascript">
var IDMark_A = "_a";
var setting = {
	async: {
		enable: true,
		url: getUrl
	},
	view: {
		fontCss: getFont,
		nameIsHTML: true,
		selectedMulti: false,
		showTitle:false
	},
	data: 
	{
		key: {
			name: "name"
		},
		simpleData: {
			enable: true,
			idKey: "orgaid",
			pIdKey: "parentid",
			rootPId: "root"
		}
	},
	callback: {
		onAsyncSuccess: onAsyncSuccess,
		onAsyncError: onAsyncError
	}
};
function getFont(treeId, node) {
	return node.font ? node.font : {};
}
function getUrl(treeId, treeNode) {
	var curCount = (treeNode.children) ? treeNode.children.length : 0;
	var param = "parentId="+ treeNode.orgaid;
	return "<s:url value="/manage/userManager/" encode="false"/>" + "getOrgList.action?" + param;
}


function ajaxGetNodes(treeNode, reloadType) {
	var zTree = $.fn.zTree.getZTreeObj("catalogTree");
	if (reloadType == "refresh") {
		treeNode.icon = "<s:url value="/js/jquery-ztree/css/zTreeStyle/img/loading.gif" encode="false"/>";
		zTree.updateNode(treeNode);
	}
	zTree.reAsyncChildNodes(treeNode, reloadType, true);
}

function onAsyncSuccess(event, treeId, treeNode, msg) {
	if (!msg || msg.length == 0) {
		return;
	}
	totalCount = treeNode.count;
	if (treeNode.children.length < totalCount) {
		setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
	} else {
		treeNode.icon = "";
		zTree.updateNode(treeNode);
	}
}

function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	var zTree = $.fn.zTree.getZTreeObj("catalogTree");
	var dialog = art.dialog("异步获取数据出现异常....");
	dialog.lock();
	treeNode.icon = "";
	zTree.updateNode(treeNode);
}

/**
 * 保存所选择机构
 */
function saveOrgTitle() {
	var categoryName="";
	var zTree = $.fn.zTree.getZTreeObj("catalogTree");
	var nodes=zTree.getSelectedNodes();
	if(nodes.length>0){
		parent.window.document.getElementById("orgaName").value=nodes[0].name;
		//parent.window.document.getElementById("orgId").value=nodes[0].orgaid;
	}else{
		parent.window.document.getElementById("orgaName").value="";
		//parent.window.document.getElementById("orgId").value=""
	}
	art.dialog.close();
}

/**
 * 清除机构
 */
function clearOrg() {
	parent.window.document.getElementById("orgaName").value="";
	//parent.window.document.getElementById("orgId").value=""
	art.dialog.close();
}

$(document).ready(function(){
	$.ajax({
	   type: "GET",
	   url: "<s:url value="/manage/userManager/" encode="false"/>" + "getOrgList.action?parentId=0&nocash=" + new Date().getTime(),
	   success: function(responseText){
	   	if(responseText != "") {
	   		var zNodes =eval(responseText);
	   		if(zNodes){
	   			zNodes[zNodes.length-1].nocheck=true;
		   	}
		    $.fn.zTree.init($("#catalogTree"), setting, zNodes);
		    zTree = $.fn.zTree.getZTreeObj("catalogTree");
		    zTree.cancelSelectedNode();
			rMenu = $("#rMenu");
	   	}
	   }
	}); 
});

(function (d) {
    d['okValue'] = '确定';
    d['cancelValue'] = '取消';
    d['title'] = '消息';
    // [more..]
});	

</script>
</head>
<body style="overflow:auto;background:#f7f7f7;">
<div class="cms_tree" style="float:left;">
	<div  style="margin: 2px;">
		<input type="hidden" value="" id="eduType"/>
		<input type="button" value="确定" class="btnstyle" onclick="saveOrgTitle();"/>
		<input type="button" value="清空" class="btnstyle" onclick="clearOrg();"/>
		<input type="button" value="取消" class="btnstyle" onclick="javascript:art.dialog.close();"/>
	</div>
	<ul id="catalogTree" class="ztree"></ul>
</div>
</body>
</html>