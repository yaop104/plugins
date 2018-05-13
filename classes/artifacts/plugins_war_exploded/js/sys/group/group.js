var grid;

$(function() {
	var colArr = [];
	
	colArr = [
		{ field:'pgpName', align:'center', width:'150' , title:'名称' },
		{ field:'pgpOrder', align:'center', width:'120', sortable:'true' , title:'顺序' },
		{ field:'pgpState', align:'center',  width:'80', sortable:'true' , title:'状态' , formatter : convertState }
	];
	
	var _toolbars = [{
	        id : 'btnadd',
	        text : '添加',
	        iconCls : 'icon-add',
	        handler : function() {
	                addGroup();
	        }
	}, '-', {
	        id : 'btncut',
	        text : '删除',
	        iconCls : 'icon-remove',
	        handler : function() {
	                deleteGroup();
	        }
	}, '-', {
	        id : 'btnupdate',
	        text : '更新',
	        iconCls : 'icon-edit',
	        handler : function() {
	                editGroup();
	        }
	}];
	
	//初始化显示列表		
	grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			url : 'page.do',
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'pgpOrder',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
 			singleSelect : false,//设置为true将只允许选择一行
			idField : 'pgpUnid',//表明该列是一个唯一列。
			rownumbers : true,//设置为true将显示行数
			frozenColumns:[[
	                {field:'ck',checkbox:true}
	     	]],
			columns:[colArr],
			toolbar : _toolbars
		});
	});

	function addGroup(){
		$('#d1').dialog('open');
		$('#f1').form('reset');
		$('#f1').form.url='save.do';
	}

	function editGroup(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				$('#f1').form.url='save.do';
				$('#f1').form('load',{	
					'pgpUnid':row.pgpUnid,
					'pgpUuid':row.pgpUuid,
					'pgpState':row.pgpState,
					'pgpOrder':row.pgpOrder,
					'pgpCdate':row.pgpCdate,
					'pgpCuser':row.pgpCuser,
					'pgpName':row.pgpName
				});
					$('#pgpUnid').val(row.pgpUnid);
					$('#pgpName').val(row.pgpName);
					$('#pgpState').val(row.pgpState);
					$('#pgpOrder').val(row.pgpOrder);
					$('#d1').dialog('open');
			}else{
				msgShow('错误','请选择一条要修改的记录！','error');
				grid.datagrid('clearSelections');
			}
		}else{
			msgShow('错误','请选择要修改的记录！','error');
		}
	}
	
	function deleteGroup(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.pgpUnid+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
					if (r){
							$.post('deleteById.do',{'ids': s},function(data){
									if(data.success){
										msgShow('成功',data.message,'info');
										grid.datagrid('reload');
										grid.datagrid('clearSelections');
									}
							}, 'json');
					}
			});
		}else{
				msgShow('错误','请选择要删除的记录！','error');
		}
	}
	
	function convertState(val, rec, index) {
       if(val == '1'){
			return '<span style="color: green">有效</span>';
		}else{ 
			return '<span style="color: red">无效</span>';
		}
    }

	function  saveForm(){   					
			$('#f1').form('submit',{
	   					url:$('#f1').form.url,
	   					success:function(data){
	   						data=parseJSON(data);
		   					if(data.success){
		   						msgShow('成功',data.message,'info');
		   						$('#d1').dialog('close');
		   						grid.datagrid('load');
		   						grid.datagrid('clearSelections');
		   					}
 					}
   				});
		
	}
	
	function clearForm(){
			$('#d1').dialog('close');
			grid.datagrid('clearSelections');
	}