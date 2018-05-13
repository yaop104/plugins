<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<title>登陆日志</title>
</head>
<script language="javascript">
var grid;

$(function() {
	var colArr = [];
	
	colArr = [
		{ field:'id', hidden:true },
		{ field:'accName', align:'center', width:'150' , title:'登陆账号' },
		{ field:'realName', align:'center', width:'150' , title:'真实姓名' },
		{ field:'accType', align:'center', width:'150', sortable:'false' , title:'管理员类型' },
		{ field:'loginIp', align:'center',  width:'200', sortable:'false' , title:'登陆地址'},
		{ field:'time', align:'center',  width:'220', sortable:'true' , title:'登陆时间' }
	];
	
	var _toolbars = [{
	        id : 'btncut',
	        text : '删除',
	        iconCls : 'icon-remove',
	        handler : function() {
	        	deleteLog();
	        }
	}];
	
	//初始化显示列表		
	grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			url : '${ctx }/login/getLoginLog.do',
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'time',//当数据表格初始化时以哪一列来排序
			sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
 			singleSelect : false,//设置为true将只允许选择一行
			idField : 'id',//表明该列是一个唯一列。
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
	    queryParams.accName = Name;  
	 
	    return queryParams;  
	  
	}  

	function searchAccount(){
		 //查询参数直接添加在queryParams中      
        var queryParams = grid.datagrid('options').queryParams;  
        queryParams.rows = grid.datagrid('options').pageSize;
        queryParams.page = 0;
        queryParams = getQueryParams(queryParams);  
        grid.datagrid('options').queryParams = queryParams;  
        grid.datagrid('reload'); 
	}

	
	function deleteLog(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.id+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '删除后不可恢复，确认删除吗？', function(r){
					if (r){
							$.post('${ctx }/login/batchDelete.do',{'ids': s},function(data){
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
</script>
<body>
<div class="easyui-layout" fit="true"  border="false">
	<div region="north" style="height:50px">
		<%--功能区--%>  
	   <div id="tb" style="padding: 10px; height: auto">  
	      <%-- 查找管理员信息，根据时间、管理员名 --%>  
	       <div>    
	          登陆账号:   
	           <input id="s_name"/>   
	           <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>  
	       </div>  
	   </div>  
		
	
	</div>
  	<div region="center"   border="false">
  		<table id="t1"></table>
  	</div>
 </div>
</body>
</html>