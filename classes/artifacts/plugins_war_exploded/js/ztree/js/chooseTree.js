//	Author:yp		
	/* 初始化，加载菜单树 */
	var zTree;
	var zNodes;
	var roleid;

	/* zTree参数设定  */
	var setting = {
			view: {
				dblClickExpand: false
			},
			check: {
				enable: true
			}
		};
	
	/* 获取菜单资源 */
	function getMenus(id){
		roleid = id;
		$.ajax({
			url : '/plugins/sysMenu/menutree.do',
			type : 'post',
			data : {'roleid' : roleid},
			dataType : 'json',
			async:false,
			success : function(data) {  
				zNodes = data;
			}
		});
		
		
		$.fn.zTree.init($("#menuTree"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("menuTree");
		zTree.expandAll(true);//全部展开 
		ztree=$(document).height()-65;
		$('.ztree').css({height:ztree})
		$(window).resize(function(){
			var ztree=$(document).height()-65;
		$('.ztree').css({height:ztree});
		});
	}
	
	
	function saveTreeNode(){
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		var nodes = new Array();
		var delData = "",addData = "",treeNode;
		nodes = zTree.getChangeCheckedNodes();
		for (var i = 0;i< nodes.length;i++){
			treeNode = nodes[i];
				if (treeNode.checked) {
					addData += treeNode.menuId+"*";
				} else {
					delData += treeNode.menuId+"*";
				}
			
		}
		$.ajax({
			url : '/plugins/sysMenu/menutreeupdate.do',
			type : 'post',
			data : {
				'delData' : delData,
				'addData' : addData,
				'rolId' : roleid
			},
			dataType : 'json',
			async:false,
			success : function(data) {  
				if ("0" == data.code) {
					alert(data.message);
//					$.messager.alert('成功',data.message,'info', function() {
//						
//					});
//					window.parent.location = "/cmcc_sme/selectTree.do";
					window.parent.parent.location.reload();
				} else {
					alert(data.message);
				}
				
			}
		});
	}