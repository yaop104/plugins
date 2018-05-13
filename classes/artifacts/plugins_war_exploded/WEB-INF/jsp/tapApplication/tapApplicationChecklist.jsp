<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
	<script type="text/javascript" src="${ctx }/js/check.js" ></script>
	<title>信息</title>
</head>
<script language="javascript">
	var grid;
	var ctxall = 'http://114.55.150.199:8888/download/pic/';
	$(function() {
		var colArr = [];

		colArr = [
			{ field:'tapApplicationUnid', align:'center', width:'150' , title:'ID' },
			{ field:'companyname', align:'center', width:'180' , title:'运营商' },
			{ field:'tapApplicationMoneyid', align:'center', width:'150' , title:'名称' },
			{ field:'tapApplicationAppname', align:'center', width:'120', sortable:'true' , title:'金额（元）' },
			{ field:'tapApplicationUrl', align:'center',  width:'180', sortable:'true' , title:'充值凭证' , formatter : ys3},
			{ field:'tapApplicationCheckstate', align:'center',  width:'80', sortable:'true' , title:'审核状态' , formatter : convertState },
			{ field:'tapApplicationCheckname', align:'center',  width:'180', sortable:'true' , title:'审核人'  },
			{ field:'tapApplicationChecktime', align:'center',  width:'180', sortable:'true' , title:'审核时间' , formatter : CommonYao.DateFormatter  },
			{ field:'tapApplicationCheckdesc', align:'center',  width:'180', sortable:'true' , title:'审核备注'  },
			{ field:'asd', align:'center', width:'280' , title:'操作', formatter : ys1}
		];

		var _toolbars = [];


		//初始化显示列表
		grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			url : '${ctx }/TapApplication/page.do',
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'tapApplicationUnid',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
			singleSelect : false,//设置为true将只允许选择一行
			idField : 'tapApplicationUnid',//表明该列是一个唯一列。
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
		queryParams.tptName = Name;
		queryParams.tptState = State;

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
		$('#f1').form.url='${ctx }/TapApplication/save.do';
	}

	function editOption(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				$('#f1').form.url='${ctx }/TapApplication/save.do';
				$('#f1').form('load',{
					'tptUnid':row.tptUnid,
					'tptType':row.tptType,
					'tptName':row.tptName,
					'tptContactname':row.tptContactname,
					'tptContactphone':row.tptContactphone,
					'tptState':row.tptState,
					'tptDesc':row.tptDesc
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
				s+=n.tptUnid+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
				if (r){
					$.post('${ctx }/TapApplication/deleteTapApplications.do',{'ids': s},function(data){
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
			return '<span style="color: yellowgreen">未审核</span>';
		}
		if(val == '2'){
			return '<span style="color: green">通过</span>';
		}
		if(val == '3'){
			return '<span style="color: red">驳回</span>';
		}

	}
	function ys3(val, rec, index) {

		return '<a target="_blank"  href="' + ctxall +  rec.tapApplicationUrl +'">'+ rec.tapApplicationUrl +'</a>';

	}
	function convertType(val, rec, index) {
			return '<span style="color: green">开发商</span>';
	}

	function ys1(val, rec, index) {
		var returnvalue = "";
		if(rec.tapApplicationCheckstate!="1"){
			return returnvalue;
		}
		returnvalue="<img  src='${ctx}/image/table_td_button/check.png' onclick='buyPosition(" + rec.tapApplicationUnid + ",\"" + rec.tapApplicationMoneyid + "\")' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='buyPosition(" + rec.tapApplicationUnid + ",\"" + rec.tapApplicationMoneyid + "\")'>审核</a>&nbsp;&nbsp;";
		return returnvalue;
	}
	var hotAppId;
	var hotAppName;
	function buyPosition(id, name){
		hotAppId = id;
		hotAppName = name;
		$('#d222').dialog('open');
		$('#f222').form('reset');
	}

	function  saveFormHot(){
		var tapApplicationCheckstate =$('#tapApplicationCheckstate').combo('getValue');
		var tapApplicationCheckdesc =$('#tapApplicationCheckdesc').val();

		$.post('${ctx }/TapApplication/updateforCheck.do',{
			'tapApplicationUnid': hotAppId,
			'tapApplicationCheckstate': tapApplicationCheckstate,
			'tapApplicationCheckdesc': tapApplicationCheckdesc
		},function(data){
			if(data.success){
				msgShow('成功',data.message,'info');
				clearFormHot();
				grid.datagrid('reload');
				grid.datagrid('clearSelections');
			}else{
				msgShow('错误',data.message,'error');
			}
		}, 'json');

	}

	function clearFormHot(){
		$('#d222').dialog('close');
		$('#f222').form('reset');
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
					<option value="1">未审核</option>
					<option value="2">通过</option>
					<option value="3">驳回</option>
				</select>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchOption()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>
			</div>
		</div>


	</div>
	<div region="center"   border="false">
		<table id="t1"></table>

		<!-- 窗口-->
		<div id="d1" class="easyui-dialog" buttons="#btn1" title="充值"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:500px;height:600px;overflow: hidden;">
			<div style="padding:10px 60px 20px 60px">
				<form id="f1"  class="easyui-form" method="post">
					<input type="hidden" id="tapApplicationUnid" name="tapApplicationUnid"/>
					<table>
						<tr>
							<td align="right">申请充值名称：</td>
							<td><input class="easyui-validatebox" name="tapApplicationMoneyid" required="true" style="width: 152px" id="tapApplicationMoneyid"/></td>
						</tr>
						<tr>
							<td align="right">申请充值金额（元）：</td>
							<td><input class="easyui-validatebox" name="tapApplicationAppname" required="true" style="width: 152px" id="tapApplicationAppname"/></td>
						</tr>
						<tr>
							<td align="right">充值凭证：：</td>
							<td>
								<input class="easyui-validatebox" name="tapApplicationUrl" required="true" style="width: 152px" id="tapApplicationUrl"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="btn1">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px"> 申  请 </a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px"> 取  消 </a>
		</div>
	</div>

	<!-- 窗口-->
	<div id="d222" class="easyui-dialog" buttons="#btn2" title="编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">
		<div style="padding:10px 60px 20px 60px">
			<form id="f222"  class="easyui-form" method="post">
				<table>
					<tr>
						<td align="right">状态：</td>
						<td>
							<select  class="easyui-combobox" name="tapApplicationCheckstate" id="tapApplicationCheckstate" style="width:152px;" required="true" editable="false">
								<option value="2">通过</option>
								<option value="3">驳回</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input class="easyui-textbox" id="tapApplicationCheckdesc" name="tapApplicationCheckdesc" data-options="multiline:true" style="height:60px"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="btn2">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveFormHot()" style="width:90px"> 保  存 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearFormHot()" style="width:90px"> 取  消 </a>
	</div>
</div>
</body>
</html>