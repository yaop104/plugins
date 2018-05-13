<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
	<title>账号信息</title>
	<script type="text/javascript" src="<%=basePath %>js/easyui/datagrid-detailview.js" ></script>
</head>
<script language="javascript">
	var grid;

	$(function() {
		var colArr = [];

		colArr = [
			{ field:'sysAccId', align:'center', width:'150', sortable:'true'  , title:'ID' },
			{ field:'sysAccName', align:'center', width:'150' , title:'昵称' },
			{ field:'sysAccRealname', align:'center', width:'120', sortable:'true' , title:'姓名' },
			{ field:'sysAccState', align:'center',  width:'80', sortable:'true' , title:'状态' , formatter : convertState },
			{ field:'sysAccType', align:'center',  width:'80', sortable:'true' , title:'类型' , formatter : convertType },
			{ field:'orgname', align:'center',  width:'80', sortable:'true' , title:'角色' },
			{ field:'rolename', align:'center', width:'180' , title:'组织' }
		];

		var _toolbars = [{
			id : 'btnadd',
			text : '添加帐号',
			iconCls : 'icon-add',
			handler : function() {
				addAccount();
			}
		}, '-', {
			id : 'btncut',
			text : '删除帐号',
			iconCls : 'icon-remove',
			handler : function() {
				deleteAccount();
			}
		}, '-', {
			id : 'btnupdate',
			text : '更新帐号',
			iconCls : 'icon-edit',
			handler : function() {
				editAccount();
			}
		}];

		$('#sysAccRoleid').combobox({
// 			onShowPanel:function(){
// 				$('#sysAccRoleid').combobox({
// 					url:'${ctx }/sysRole/selectRoles.do'
// 				});
// 			},
			url:'${ctx }/sysRole/selectRoles.do',
			valueField:'roleid',
			textField:'rolename'
		});

		//初始化显示列表
		grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			//	url : '${ctx }/sysAccount/page.do',
			pageList : [10, 15, 20],
			pageSize : 15,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'sysAccId',//当数据表格初始化时以哪一列来排序
			sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
			singleSelect : false,//设置为true将只允许选择一行
			idField : 'sysAccId',//表明该列是一个唯一列。
			rownumbers : true,//设置为true将显示行数
			frozenColumns:[[
				{field:'ck',checkbox:true}
			]],
			columns:[colArr],
			toolbar : _toolbars
		});
		grid.datagrid('options').url = '${ctx }/sysAccount/page.do';

		$('#t1').datagrid({
			view: detailview,
			detailFormatter:function(index,row){
				return '<div id="ddv-' + index + '" style="padding:5px 0"></div>';
			},
			onExpandRow: function(index,row){
				$('#ddv-'+index).panel({
					border:false,
					cache:false,
					href:'${ctx }/sysAccount/sysAccountxxx.do',
					onLoad:function(){
						$('#t1').datagrid('fixDetailRowHeight',index);
					}
				});
				$('#t1').datagrid('fixDetailRowHeight',index);
			}
		});
	});

	//获取参数
	function getQueryParams(queryParams) {
		var StartTime = $("#s_startTime").datebox("getValue");
		var EndTime = $("#s_endTime").datebox("getValue");
		var Name = document.getElementById("s_name").value;
		var State = $("#s_state").combobox("getValue");

		queryParams.StartTime = StartTime;
		queryParams.EndTime = EndTime;
		queryParams.sysAccName = Name;
		queryParams.sysAccState = State;

		return queryParams;

	}

	function searchAccount(){
		//查询参数直接添加在queryParams中
		var queryParams = grid.datagrid('options').queryParams;
		queryParams.rows = grid.datagrid('options').pageSize;
		queryParams.page = 0;
		queryParams = getQueryParams(queryParams);
		grid.datagrid('options').queryParams = queryParams;
		grid.datagrid('options').url = '${ctx }/sysAccount/sysAcclist.do';
		grid.datagrid('reload');
	}

	function addAccount(){
		$('#d1').window('open');
		$('#f1').form('reset');
//		$('#f1').form.url='${ctx }/sysAccount/sysAccountSave.do';
		$('#f1').form.url='${ctx }/sysAccount/insert.do';
	}

	function editAccount(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				//	$('#f1').form.url='${ctx }/sysAccount/sysAccountUpdate.do';
				$('#f1').form.url='${ctx }/sysAccount/update.do';
				$('#f1').form('load',{
					'sysAccId':row.sysAccId,
					'sysAccName':row.sysAccName,
					'sysAccRealname':row.sysAccRealname,
					'sysAccState':row.sysAccState,
					'sysAccType':row.sysAccType,
					'sysAccDesc':row.sysAccDesc,
					'sysAccRoleid':row.sysAccRoleid,
					'sysAccOrgid':row.sysAccOrgid
				});
				$('#sysAccId').val(row.sysAccId);
				$('#sysAccName').val(row.sysAccName);
				$('#sysAccRealname').val(row.sysAccRealname);
				$('#sysAccDesc').val(row.sysAccDesc);
				$('#d1').dialog('open');
			}else{
				msgShow('错误','请选择一条要修改的记录！','error');
				grid.datagrid('clearSelections');
			}
		}else{
			msgShow('错误','请选择要修改的记录！','error');
		}
	}

	function deleteAccount(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.sysAccId+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
				if (r){
					$.post('${ctx }/sysAccount/sysAccountDelete.do',{'ids': s},function(data){
						if(data.success){
							msgShow('成功',data.message,'info');
							grid.datagrid('reload');
							grid.datagrid('clearSelections');
						}
					},'json');
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

	function convertType(val, rec, index) {
		if(val == '4'){
			return '<span style="color: green">系统管理员</span>';
		}else if(val == '3'){
			return '<span style="color: green">财务</span>';
		}else if(val == '2'){
			return '<span style="color: green">开发商</span>';
		}else{
			return '<span style="color: green">用户</span>';
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
	}
</script>
<body>
<div class="easyui-layout" fit="true"  border="false">
	<div region="north" style="height:50px">
		<%--功能区--%>
		<div id="tb" style="padding: 10px; height: auto">
			<%-- 查找管理员信息，根据时间、管理员名 --%>
			<div>
				时间从:
				<input id="s_startTime" class ="easyui-datebox" style="width: 100px" />
				到:
				<input id="s_endTime" class="easyui-datebox" style="width: 100px" />
				账号名:
				<input id="s_name"/>
				按状态：
				<select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">
					<option value="1">有效</option>
					<option value="2">无效</option>
				</select>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>
			</div>
		</div>


	</div>
	<div region="center"   border="false">
		<table id="t1"></table>

		<!-- 窗口-->
		<div id="d1" class="easyui-dialog" buttons="#btn1" title="账号编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">
			<div style="padding:10px 60px 20px 60px">
				<form id="f1"  class="easyui-form" method="post">
					<input type="hidden" id="sysAccId" name="sysAccId"/>
					<table>
						<tr>
							<td align="right">类型：</td>
							<td>
								<select  class="easyui-combobox" name="sysAccType" id="sysAccType" style="width:152px;" required="true" editable="false">
									<option value="1">用户</option>
									<option value="2">开发商</option>
									<option value="3">财务</option>
									<option value="4">系统管理员</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">昵称：</td>
							<td><input class="easyui-validatebox" name="sysAccName" required="true" style="width: 152px" id="sysAccName"></input></td>
						</tr>
						<tr>
							<td align="right">名称：</td>
							<td><input class="easyui-validatebox" name="sysAccRealname" required="true" style="width: 152px" id="sysAccRealname"></input></td>
						</tr>
						<tr>
							<td align="right">角色：</td>
							<td>
								<select  class="easyui-combobox" name="sysAccRoleid" id="sysAccRoleid" style="width:152px;" required="true" editable="false">

								</select>
							</td>
						</tr>
						<tr>
							<td align="right">组织：</td>
							<td><input name="sysAccOrgid" required="true" style="width: 152px" id="sysAccOrgid"></input></td>
						</tr>
						<tr>
							<td align="right">状态：</td>
							<td>
								<select  class="easyui-combobox" name="sysAccState" id="sysAccState" style="width:152px;" required="true" editable="false">
									<option value="1">有效</option>
									<option value="2">无效</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><input class="easyui-textbox" id="sysAccDesc" name="sysAccDesc" data-options="multiline:true" style="height:60px"></input></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="btn1">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px"> 保  存 </a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px"> 取  消 </a>
		</div>
	</div>
</div>
</body>
</html>