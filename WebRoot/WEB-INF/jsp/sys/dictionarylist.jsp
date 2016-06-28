<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
	<title>字典信息</title>
</head>
<script language="javascript">
	var grid;

	$(function() {
		var colArr = [];

		colArr = [
			{ field:'tdcDictionaryUnid', align:'center', width:'150' , title:'ID' },
			{ field:'tdcDictionaryName', align:'center', width:'150' , title:'名称' },
			{ field:'tdcDictionaryCuser', align:'center', width:'120', sortable:'true' , title:'记录人' },
			{ field:'tdcDictionaryCdate', align:'center', width:'120', sortable:'true' , title:'记录时间' },
			{ field:'tdcDictionaryParentid', align:'center',  width:'80', sortable:'true' , title:'上级' },
			{ field:'tdcDictionaryType', align:'center',  width:'80', sortable:'true' , title:'类型' , formatter : convertType },
			{ field:'tdcDictionaryState', align:'center',  width:'80', sortable:'true' , title:'状态' , formatter : convertState }
		];

		var _toolbars = [{
			id : 'btnadd',
			text : '添加运营商',
			iconCls : 'icon-add',
			handler : function() {
				addOption();
			}
		}, '-', {
			id : 'btncut',
			text : '删除运营商',
			iconCls : 'icon-remove',
			handler : function() {
				deleteOption();
			}
		}, '-', {
			id : 'btnupdate',
			text : '更新运营商',
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
			url : '${ctx }/TdcDictionary/page.do',
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'tdcDictionaryUnid',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
			singleSelect : false,//设置为true将只允许选择一行
			idField : 'tdcDictionaryUnid',//表明该列是一个唯一列。
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
		var State = $("#s_state").combobox("getValue");

		/* queryParams.StartTime = StartTime;
		 queryParams.EndTime = EndTime;   */
		queryParams.tioName = Name;
		queryParams.tioState = State;

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
		$('#f1').form('reset');
		$('#f1').form.url='${ctx }/TbcInfo/save.do';
	}

	function editOption(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				$('#f1').form.url='${ctx }/TbcInfo/save.do';
				$('#f1').form('load',{
					'tioUnid':row.tioUnid,
					'tioType':row.tioType,
					'tioName':row.tioName,
					'tioContactname':row.tioContactname,
					'tioContactphone':row.tioContactphone,
					'tioState':row.tioState,
					'tioDesc':row.tioDesc
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
				s+=n.tioUnid+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
				if (r){
					$.post('${ctx }/TbcInfo/deleteTbcInfos.do',{'ids': s},function(data){
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

	function convertType(val, rec, index) {
		if(val == '1'){
			return '<span style="color: green">大类</span>';
		}else{
			return '<span style="color: green">小类</span>';
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
				公司名:
				<input id="s_name"/>
				按状态：
				<select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">
					<option value="">全部</option>
					<option value="1">有效</option>
					<option value="2">无效</option>
				</select>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchOption()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>
			</div>
		</div>


	</div>
	<div region="center"   border="false">
		<table id="t1"></table>

		<!-- 窗口-->
		<div id="d1" class="easyui-dialog" buttons="#btn1" title="编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">
			<div style="padding:10px 60px 20px 60px">
				<form id="f1"  class="easyui-form" method="post">
					<input type="hidden" id="tioUnid" name="tioUnid"/>
					<table>
						<tr>
							<td align="right">类型：</td>
							<td>
								<select  class="easyui-combobox" name="tioType" id="tioType" style="width:152px;" required="true" editable="false">
									<option value="1">开发商</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">公司名称：</td>
							<td><input class="easyui-validatebox" name="tioName" required="true" style="width: 152px" id="tioName"/></td>
						</tr>
						<tr>
							<td align="right">联系人：</td>
							<td><input class="easyui-validatebox" name="tioContactname" required="true" style="width: 152px" id="tioContactname"/></td>
						</tr>
						<tr>
							<td align="right">联系电话：</td>
							<td>
								<input class="easyui-validatebox" name="tioContactphone" required="true" style="width: 152px" id="tioContactphone"/>
							</td>
						</tr>
						<tr>
							<td align="right">状态：</td>
							<td>
								<select  class="easyui-combobox" name="tioState" id="tioState" style="width:152px;" required="true" editable="false">
									<option value="1">有效</option>
									<option value="2">无效</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><input class="easyui-textbox" id="tioDesc" name="tioDesc" data-options="multiline:true" style="height:60px"/></td>
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