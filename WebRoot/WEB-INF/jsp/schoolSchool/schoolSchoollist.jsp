<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
	<title>学校信息</title>
</head>
<script language="javascript">
	var grid;

	$(function() {
		var colArr = [];

		colArr = [
			{ field:'schoolUnid', align:'center', width:'50' , title:'ID' },
			{ field:'schoolName', align:'center', width:'250' , title:'学校名称' },
			{ field:'schoolRule', align:'center', width:'250' , title:'规则' },
			{ field:'schoolDesc', align:'center', width:'250',  title:'备注' }
		];

		var _toolbars = [{
			id : 'btnadd',
			text : '添加学校',
			iconCls : 'icon-add',
			handler : function() {
				addOption();
			}
		}, '-', {
			id : 'btncut',
			text : '删除学校',
			iconCls : 'icon-remove',
			handler : function() {
				deleteOption();
			}
		}, '-', {
			id : 'btnupdate',
			text : '更新学校',
			iconCls : 'icon-edit',
			handler : function() {
				editOption();
			}
		}];


		//初始化显示列表
		grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			url : '${ctx }/SchoolSchool/getschoolSchoollists.do',
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'schoolUnid',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
			singleSelect : false,//设置为true将只允许选择一行
			idField : 'schoolUnid',//表明该列是一个唯一列。
			rownumbers : true,//设置为true将显示行数
			frozenColumns:[[
				{field:'ck',checkbox:true}
			]],
			columns:[colArr],
			toolbar : _toolbars
		});
	});

	//获取参数
	function getQueryParams(queryParams) {
		/*  var StartTime = $("#s_startTime").datebox("getValue");
		 var EndTime = $("#s_endTime").datebox("getValue");              */
		var Name = document.getElementById("s_name").value;

		/* queryParams.StartTime = StartTime;
		 queryParams.EndTime = EndTime;   */
		queryParams.schoolName = Name;

		return queryParams;

	}

	function searchOption(){
		//查询参数直接添加在queryParams中
		var queryParams = grid.datagrid('options').queryParams;
		queryParams.rows = grid.datagrid('options').pageSize;
		queryParams.page = 0;
		queryParams = getQueryParams(queryParams);
		grid.datagrid('options').queryParams = queryParams;
		grid.datagrid('reload');
	}

	function addOption(){
		$('#d1').dialog('open');
		$('#f1').form('clear');
		$('#f1').form.url='${ctx }/SchoolSchool/save.do';
	}

	function editOption(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				$('#f1').form.url='${ctx }/SchoolSchool/save.do';
				$('#f1').form('load',{
					'schoolUnid':row.schoolUnid,
					'schoolName':row.schoolName,
					'schoolDesc':row.schoolDesc,
					'schoolRule':row.schoolRule
				});
				$('#d1').dialog('open');
			}else{
				msgShow('错误','请选择一条要修改的记录！','error');
				grid.datagrid('clearSelections');
			}
		}else{
			msgShow('错误','请选择要修改的记录！','error');
		}
	}

	function deleteOption(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.schoolUnid+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
				if (r){
					$.post('${ctx }/SchoolSchool/deleteSchools.do',{'ids': s},function(data){
						if(data.success){
							msgShow('成功',data.message,'info');
							grid.datagrid('reload');
							grid.datagrid('clearSelections');
						}else{
							msgShow('错误',data.message,'error');
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

	function convertType(val, rec, index) {
			return '<span style="color: green">开发商</span>';
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
				}else{
					msgShow('错误',data.message,'error');
				}
			}
		});

	}

	function clearForm(){
		$('#d1').dialog('close');
		grid.datagrid('clearSelections');
	}
</script>
<body>
<div class="easyui-layout" fit="true"  border="false">
	<div region="north" style="height:50px">
		<%--功能区--%>
		<div id="tb" style="padding: 10px; height: auto">
			<%-- 查找管理员信息，根据时间、管理员名 --%>
			<div>
				学校名称:
				<input id="s_name"/>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchOption()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>
			</div>
		</div>


	</div>
	<div region="center"   border="false">
		<table id="t1"></table>

		<!-- 窗口-->
		<div id="d1" class="easyui-dialog" buttons="#btn1" title="编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:500px;height:400px;overflow: hidden;">
			<div style="padding:10px 60px 20px 60px">
				<form id="f1"  class="easyui-form" method="post">
					<input type="hidden" id="schoolUnid" name="schoolUnid"/>
					<table>
						<tr>
							<td align="right">学校名称：</td>
							<td><input class="easyui-validatebox" name="schoolName" required="true" style="width: 152px" id="schoolName"/></td>
						</tr>
						<tr>
							<td>规则：</td>
							<td>
								<input class="easyui-textbox" id="schoolRule" name="schoolRule" data-options="multiline:true" style="height:60px"/>
								<span style="color: red">&nbsp;规则之间以英文分号分隔</span>
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><input class="easyui-textbox" id="schoolDesc" name="schoolDesc" data-options="multiline:true" style="height:60px"/></td>
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