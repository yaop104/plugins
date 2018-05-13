//	Author:yp

var setting = {
	callback : {
		onRightClick : function(event, treeId, treeNode) {
			$('#mm').menu({
				onClick : function(item) {
                    treeNode.id=treeNode.code;
                    treeNode.text=tree_data(treeNode);
					if (item.name == 'add') {
						addTreeNode(treeNode);
					} 
					if (item.name == 'edit') {
						editTreeNode(treeNode);
					} 
					if (item.name == 'del') {
						delTreeNode(treeNode);
					} 
				}
			});
			$('#mm').menu('show', {
						left : event.pageX,
						top : event.pageY
					});
		},
		onClick : function(event, treeId, treeNode) {
		 treeNode.id=treeNode.code;
         treeNode.text=tree_data(treeNode);
		 selectTreeNode(treeNode);
		},
		// beforeAsync: zTreeBeforeAsync, 	// 异步加载事件之前得到相应信息
		asyncSuccess : zTreeOnAsyncSuccess, // 异步加载成功的fun
		asyncError : zTreeOnAsyncError, // 加载错误的fun
		beforeClick : beforeClick
		// 捕获单击节点之前的事件回调函数
	}
};
function beforeClick(treeId, treeNode) {
	
}
function zTreeOnAsyncError(event, treeId, treeNode) {
	alert("异步加载失败!");
}
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
}
function tree_data(treeNode){
var tree_text;
var tree1_text;
var tree2_text;
var tree3_text;
var tree1=treeNode;
var tree2;
var tree3;
tree1_text=tree1.name;
tree2=tree1.getParentNode();
	if(tree2){
	 tree2_text=tree2.name;
	 tree3=tree2.getParentNode();
			if(tree3){
			  tree3_text=tree3.name;
			  tree_text=tree3_text+tree2_text+tree1_text;
			}else{
			  tree_text=tree2_text+tree1_text;
			}
	}else{
	 tree_text=tree1_text;
	}
treeNode.text=tree_text;
return tree_text;
}
/** ************************************************* */
var zNodes = [];
$(document).ready(function() {
	if(document.getElementById('ictionTree'))
		$.ajax({
			url : '/cmcc_sme/iction/ictionTrees.do',
			type : 'post',
			data : {},
			dataType : 'json',
			async:false,
			success : function(data) {  
				zNodes = data;
				//树状菜单容器的展开与
				$.fn.zTree.init($("#ictionTree"), setting, zNodes);
				zTree = $.fn.zTree.getZTreeObj("ictionTree");
				zTree.expandAll(true);//全部展开 
				ztree=$(document).height()-65;
				$('.ztree').css({height:ztree})
				$(window).resize(function(){
					var ztree=$(document).height()-65;
				$('.ztree').css({height:ztree});
				});
			}
		});
});