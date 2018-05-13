var grid;

$(function() {
	var colArr = [];

	colArr = [ {
		field : 'roleid',
		align : 'center',
		width : '0',
		hidden : true
	}, {
		field : 'rolename',
		align : 'center',
		width : '120',
		sortable : 'false',
		title : '角色名称'
	}, {
		field : 'roletype',
		align : 'center',
		width : '100',
		sortable : 'false',
		title : '角色类型',
		formatter : function(val, rec, index) {
			if (val == 1) {
				return "超级管理员";
			}
			if (val == 2) {
				return "管理员";
			}
			return "用户";
		}
	}, {
		field : 'rolestate',
		align : 'center',
		width : '80',
		sortable : 'false',
		title : '状态',
		formatter : convertState
	} ];

	var _toolbars = [ {
		id : 'btnadd',
		text : '添加角色',
		iconCls : 'icon-add',
		handler : function() {
			addRole();
		}
	}, '-', {
		id : 'btncut',
		text : '注销角色',
		iconCls : 'icon-remove',
		handler : function() {
			deleteRole();
		}
	}, '-', {
		id : 'btnupdate',
		text : '更新角色',
		iconCls : 'icon-edit',
		handler : function() {
			editRole();
		}
	} ];

	// 初始化显示列表
	grid = $('#t1').datagrid({
		iconCls : 'icon-save',
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取。
		striped : true,// 显示条纹
		collapsible : true,
		url : 'sysRolelist.do',
		pageList : [ 10, 15, 20 ],
		pageSize : 10,
		fitColumns : false,
		fit : true,
		loadMsg : '正在加载数据.......',// 当从远程站点载入数据时，显示的一条快捷信息
		pagination : true,// 设置true将在数据表格底部显示分页工具栏
		sortName : 'roleid',// 当数据表格初始化时以哪一列来排序
		sortOrder : 'asc',// 定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
		remoteSort : false,// 定义是否通过远程服务器对数据排序
		itColumns : false,
		singleSelect : true,// 设置为true将只允许选择一行
		idField : 'roleid',// 表明该列是一个唯一列。
		rownumbers : true,// 设置为true将显示行数
//		frozenColumns : [ [ {
//			field : 'ck',
//			checkbox : true
//		} ] ],
		columns : [ colArr ],
		toolbar : _toolbars,
		onClickRow : function(index,row) {
			if (row.rolestate == 1) {	//无效用户无法查看权限详情
				parent.document.getElementById("btn").style.visibility = "visible";
				parent.getMenus(row.roleid);
			} else {
				parent.document.getElementById("btn").style.visibility = "hidden";
				parent.document.getElementById("menuTree").innerHTML = "<li><div class=\"tip_div\">无效用户没有权限</div></li>";
			}
		}
	});
	
	$('#d1').dialog({
	    closed: false,
	    cache: false,
	    modal: true
	}).dialog('close');
});

// 获取参数
function getQueryParams(queryParams) {
	/*
	 * var StartTime = $("#s_startTime").datebox("getValue"); var EndTime =
	 * $("#s_endTime").datebox("getValue");
	 */
	var Name = document.getElementById("s_name").value;
	var State = $("#s_state").combobox("getValue");

	/*
	 * queryParams.StartTime = StartTime; queryParams.EndTime = EndTime;
	 */
	queryParams.rolename = Name;
	queryParams.rolestate = State;

	return queryParams;

}

function searchAccount() {
	// 查询参数直接添加在queryParams中
	var queryParams = grid.datagrid('options').queryParams;
	queryParams.rows = grid.datagrid('options').pageSize;
	queryParams.page = 0;
	queryParams = getQueryParams(queryParams);
	grid.datagrid('options').queryParams = queryParams;
	grid.datagrid('reload');
}

function addRole() {
	$('#d1').dialog('open');
	$('#f1').form('reset');
	$('#f1').form.url = 'save.do';
}

function editRole() {
	var row = grid.datagrid('getSelected');
	var rows = grid.datagrid('getSelections');
	if (row) {
		if (1 == rows.length) {
			// $('#f1').form.url='${ctx }/sysAccount/sysAccountUpdate.do';
			$('#f1').form.url = 'save.do';
			$('#f1').form('load', {
				'roleid' : row.roleid,
				'rolename' : row.rolename,
				'rolestate' : row.rolestate,
				'roletype' : row.roletype,
				'roleorder' : row.roleorder,
				'roledesc' : row.roledesc
			});
			$('#roleid').val(row.roleid);
			$('#rolename').val(row.rolename);
			$('#roleorder').val(row.roleorder);
			$('#roledesc').val(row.roledesc);
			$('#d1').dialog('open');
		} else {
			msgShow('错误', '请选择一条要修改的记录！', 'error');
			grid.datagrid('clearSelections');
		}
	} else {
		msgShow('错误', '请选择要修改的记录！', 'error');
	}
}

function deleteRole() {
	var row = grid.datagrid('getSelected');
	var rows = grid.datagrid('getSelections');
	if (row) {
		var s = '';
		$.each(rows, function(i, n) {
			if (n.rolestate == 1) {
				s += n.roleid + ',';
			}
		});
		s = s.substr(0, s.length - 1);
		$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r) {
			if (r) {
				$.post('deleteById.do', {
					'ids' : s
				}, function(data) {
					console.log(data);
					if (data.success) {
						msgShow('成功', data.message, 'info');
						grid.datagrid('reload');
						grid.datagrid('clearSelections');
					}
				}, 'json');
			}
		});
	} else {
		msgShow('错误', '请选择要删除的记录！', 'error');
	}
}

function convertState(val, rec, index) {
	if (val == '1') {
		return '<span style="color: green">有效</span>';
	} else {
		return '<span style="color: red">无效</span>';
	}
}

function saveForm() {
	$('#f1').form('submit', {
		url : $('#f1').form.url,
		success : function(data) {
			data = parseJSON(data);
			if (data.success) {
				msgShow('成功', data.message, 'info');
				$('#d1').dialog('close');
				grid.datagrid('load');
				grid.datagrid('clearSelections');
			}
		}
	});

}

function clearForm() {
	$('#d1').dialog('close');
	grid.datagrid('clearSelections');
}