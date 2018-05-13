<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<title>菜单管理</title>
</head>
<script language="javascript">
var grid;

$(function() {
	var colArr = [];
	
	colArr = [
		{ field:'sysMenuId', align:'center', width:'150' , title:'ID' },
		{ field:'sysMenuName', align:'center', width:'150' , title:'菜单名称' },
		{ field:'sysMenuType', align:'center', width:'150' , title:'菜单等级', formatter : convertType },
		{ field:'sysMenuPname', align:'center', width:'120', sortable:'false' , title:'上级菜单' },
		{ field:'sysMenuState', align:'center',  width:'80', sortable:'false' , title:'状态' , formatter : convertState },
		{ field:'sysMenuUrl', align:'center',  width:'320', sortable:'false' , title:'地址' }
	];
	
	var _toolbars = [{
	        id : 'btnadd',
	        text : '添加菜单',
	        iconCls : 'icon-add',
	        handler : function() {
	                addMenu();
	        }
	}, '-', {
	        id : 'btncut',
	        text : '删除菜单',
	        iconCls : 'icon-remove',
	        handler : function() {
	                deleteMenu();
	        }
	}, '-', {
	        id : 'btnupdate',
	        text : '编辑菜单',
	        iconCls : 'icon-edit',
	        handler : function() {
	                editMenu();
	        }
	}];
	
	$('#sysMenuPid').combobox({
// 			onShowPanel:function(){
// 				$('#sysAccRoleid').combobox({
// 					url:'${ctx }/sysRole/selectRoles.do'
// 				});
// 			}, 
			url:'${ctx }/sysMenu/selectMenus.do',
			valueField:'sysMenuId', 
			textField:'sysMenuName' 
		});
	
	//初始化显示列表		
	grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			url : '${ctx }/sysMenu/sysmenulist.do',
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			/* sortName : 'sysMenuId',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序） */
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
 			singleSelect : false,//设置为true将只允许选择一行
			idField : 'sysMenuId',//表明该列是一个唯一列。
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
	    queryParams.sysMenuName = Name;  
	    queryParams.sysMenuState = State;  
	 
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

	function addMenu(){
		$('#d1').dialog('open');
		$('#f1').form('clear');
		$('#f1').form.url='${ctx }/sysMenu/save.do';
	}

	function editMenu(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
			//	$('#f1').form.url='${ctx }/sysAccount/sysAccountUpdate.do';
				$('#f1').form.url='${ctx }/sysMenu/save.do';
				$('#f1').form('load',{	
					'sysMenuId':row.sysMenuId,
					'sysMenuName':row.sysMenuName,
					'sysMenuPid':row.sysMenuPid,
					'sysMenuState':row.sysMenuState,
					'sysMenuOrder':row.sysMenuOrder,
					'sysMenuUrl':row.sysMenuUrl,
					'sysMenuType':row.sysMenuType,
					'sysMenuDesc':row.sysMenuDesc
				});
				$('#sysMenuId').val(row.sysMenuId);
				$('#sysMenuName').val(row.sysMenuName);
				$('#sysMenuDesc').val(row.sysMenuDesc);
				$('#d1').dialog('open');
			}else{
				msgShow('错误','请选择一条要修改的记录！','error');
				grid.datagrid('clearSelections');
			}
		}else{
			msgShow('错误','请选择要修改的记录！','error');
		}
	}
	
	function deleteMenu(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.sysMenuId+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
					if (r){
							$.post('${ctx }/sysMenu/deleteById.do',{'ids': s},function(data){
									if(data.success){
										msgShow('成功',data.message,'info');
										grid.datagrid('reload');
										grid.datagrid('clearSelections');
										//操作成功后，菜单栏刷新一次
		   								window.parent.frames["leftmenu"].location.reload(); 
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
			return '<span style="color: green">一级菜单</span>';
		}else if(val == '2'){
			return '<span style="color: green">二级菜单</span>';
		}else if(val == '3'){
		   return '<span style="color: green">三级菜单</span>';
	   }else{
		   return '<span style="color: green"></span>';
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
		   						//操作成功后，菜单栏刷新一次
		   						window.parent.frames["leftmenu"].location.reload(); 
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
	          菜单名称:   
	           <input id="s_name"/>   
	           按状态：  
	             <select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">  
	             	<option value="">全部</option>
	             	<option value="1">有效</option>
					 <option value="2">无效</option>
	             </select>  
	           <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>  
	       </div>  
	   </div>  
		
	
	</div>
  	<div region="center"   border="false">
  		<table id="t1"></table>
  		
  			<!-- 窗口-->
			<div id="d1" class="easyui-dialog" buttons="#btn1" title="账号编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">  
		         <div style="padding:10px 60px 20px 60px">
		            <form id="f1"  class="easyui-form" method="post">  
		            <input type="hidden" id="sysMenuId" name="sysMenuId"/>
		                <table>  
		                    <tr>  
		                        <td align="right">菜单名称：</td>  
		                        <td><input class="easyui-validatebox" name="sysMenuName" required="true" style="width: 152px" id="sysMenuName"></input></td>  
		                    </tr>  
		                      <tr>  
		                        <td align="right">上级菜单：</td>  
		                        <td>
		                        <select  class="easyui-combobox" name="sysMenuPid" id="sysMenuPid" style="width:152px;" required="true" editable="false">
							          
		                        </select>
		                        </td>  
		                    </tr> 
		                    <tr>  
		                        <td align="right">状态：</td>  
		                        <td>
		                        <select class="easyui-combobox" name="sysMenuState" id="sysMenuState" style="width:152px;" required="true" editable="false">
		                       		 <option value="1">有效</option>
									 <option value="2">无效</option>
		                        </select>
		                        </td>  
		                    </tr> 
		                      <tr>  
		                        <td align="right">排序：</td>  
		                        <td>
		                        	<input class="easyui-validatebox" name="sysMenuOrder" style="width: 152px" id="sysMenuOrder" required="true" /> 
		                        </td>  
		                    </tr> 
		                      <tr>  
		                        <td align="right">url地址：</td>  
		                        <td><input class="easyui-validatebox" name="sysMenuUrl"  style="width: 152px" id="sysMenuUrl"></input></td>  
		                    </tr> 
		                     
		                    <tr>
								<td>菜单等级：</td>
								<td>
								<select class="easyui-combobox" name="sysMenuType" id="sysMenuType" style="width:152px;" required="true" editable="false">
		                       		 <option value="3">三级菜单</option>
							         <option value="2">二级菜单</option>
							         <option value="1">一级菜单</option>
		                        </select>
								</td>
							</tr>
							<tr>
								<th>备注：</th>
								<td>
								<input class="easyui-textbox" id="sysMenuDesc" name="sysMenuDesc" data-options="multiline:true" style="height:60px"></input>
								</td>
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