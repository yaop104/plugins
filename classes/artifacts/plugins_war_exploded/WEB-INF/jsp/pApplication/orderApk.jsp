<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="panel-fit">
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<div id="lay" class="easyui-layout" style="width: 100%; height: 100%;"
	fit="true" fitColumns="true">
	<div region="east" border="false"
		style="background: #efefef; width: 100px; text-align: center; vertical-align: center">
		<center>
			<br />
			<br /> <a id="btnTop" class="easyui-linkbutton">置顶</a><br />
			<br /> <a id="btnGoTop" class="easyui-linkbutton">上移</a><br />
			<br /> <a id="btnGoButton" class="easyui-linkbutton">下移</a><br />
			<br /> <a id="btnButon" class="easyui-linkbutton">置底</a><br />
			<br />
			<br /> <a id="saveorder" class="easyui-linkbutton">保存</a><br />
			<br />
		</center>
	</div>
	<div region="center" border="false">
		<table id="orderbyTb"></table>
	</div>
</div>
<script>
	//置顶
	$('#btnTop').bind("click", function() {
		var a = $('#orderbyTb').datagrid('getSelections');

		var indexRow = $('#orderbyTb').datagrid('getRowIndex', a[0]);
		if (indexRow == 0) {
			$.messager.alert("提示", "该信息已经置顶，不能再置顶！");
			return;
		}
		if (a.length == 1) {
			//新增行
			$('#orderbyTb').datagrid('insertRow', {
				index : 0, // index start with 0
				row : {
					id : a[0].id,
					pluginname : a[0].pluginname
				/* ,
										apptype:a[0].apptype */
				}
			});
			//删除原来的行
			var indexRow = $('#orderbyTb').datagrid('getRowIndex', a[0]);
			$('#orderbyTb').datagrid('deleteRow', indexRow);
			$('#orderbyTb').datagrid('selectRow', 0);
		} else {
			$.messager.alert("提示", "请选择一行进行操作！");
		}
	});
	//置低
	$('#btnButon').bind("click", function() {
		var a = $('#orderbyTb').datagrid('getSelections');
		var indexRow = $('#orderbyTb').datagrid('getRowIndex', a[0]);
		var len = $('#orderbyTb').datagrid('getRows');
		if (indexRow == len.length - 1) {
			$.messager.alert("提示", "该信息已经置底，不能再置底！");
			return;
		}
		if (a.length == 1) {
			//新增行
			var len = $('#orderbyTb').datagrid('getRows');
			$('#orderbyTb').datagrid('insertRow', {
				index : len.length, // index start with 0
				row : {
					id : a[0].id,
					pluginname : a[0].pluginname
				/* ,
										apptype:a[0].apptype */
				}
			});
			//删除原来的行
			var indexRow = $('#orderbyTb').datagrid('getRowIndex', a[0]);
			$('#orderbyTb').datagrid('deleteRow', indexRow);
			$('#orderbyTb').datagrid('selectRow', len.length - 1);
		} else {
			$.messager.alert("提示", "请选择一行进行操作！");
		}
	});
	//上移
	$('#btnGoTop').bind("click", function() {
		var a = $('#orderbyTb').datagrid('getSelections');
		if (a.length == 1) {
			//新增行
			var oldindex = $('#orderbyTb').datagrid('getRowIndex', a[0]);
			if (oldindex == 0) {
				$.messager.alert("提示", "第一行不允许上移！");
				return;
			}
			$('#orderbyTb').datagrid('insertRow', {
				index : oldindex - 1, // index start with 0
				row : {
					id : a[0].id,
					pluginname : a[0].pluginname
				/* ,
										apptype:a[0].apptype */
				}
			});
			//删除原来的行
			var indexRow = $('#orderbyTb').datagrid('getRowIndex', a[0]);
			$('#orderbyTb').datagrid('deleteRow', indexRow);
			$('#orderbyTb').datagrid('selectRow', oldindex - 1);
		} else {
			$.messager.alert("提示", "请选择一行进行操作！");
		}
	});
	//下移
	$('#btnGoButton').bind("click", function() {
		var a = $('#orderbyTb').datagrid('getSelections');
		if (a.length == 1) {
			//新增行
			var oldindex = $('#orderbyTb').datagrid('getRowIndex', a[0]);
			var len = $('#orderbyTb').datagrid('getRows');

			if (oldindex == len.length - 1) {
				$.messager.alert("提示", "末尾行不允许下移！");
				return;
			}
			$('#orderbyTb').datagrid('insertRow', {
				index : (oldindex - 0) + 2, // index start with 0
				row : {
					id : a[0].id,
					pluginname : a[0].pluginname
				/* ,
										apptype:a[0].apptype */
				}
			});
			//删除原来的行
			var indexRow = $('#orderbyTb').datagrid('getRowIndex', a[0]);
			$('#orderbyTb').datagrid('deleteRow', indexRow);
			$('#orderbyTb').datagrid('selectRow', oldindex + 1);
		} else {
			$.messager.alert("提示", "请选择一行进行操作！");
		}

	});

	$(function() {
		$('#orderbyTb')
				.datagrid(
						{
							nowrap : true,
							url : '${pageContext.request.contextPath}/application/getOrderApp.do?rows='
									+ size,
							remoteSort : false,
							idField : 'id',
							rownumbers : true,
							fitColumns : true,
							singleSelect : true,
							fit : true,
							columns : [ [
							{
								field : 'pAppId',
								title : 'id',
								width : 10,
								align : 'left',
								hidden : true,
								resizable : false
							}, {
								field : 'pAppPluginname',
								title : '插件名称',
								width : 150,
								align : 'left',
								resizable : false
							} ] ]
						});

		$('#saveorder')
				.bind(
						"click",
						function() {
							var selectall = $('#orderbyTb').datagrid("getRows");
							var appIdListStr = {};

							for (var i = 0; i < selectall.length; i++) {
								appIdListStr.push(selectall[i].id);
							}
							$
									.ajax({
										type : "POST",
										url : "${pageContext.request.contextPath}/application/saveOrder.do",
										data : "ids=" + appIdListStr.join('&'),
										dataType : "json",
										success : function(rowData) {
											if (rowData.code == 500) {
												orderByDig.dialog('close');
												$.messager.alert("提示", "操作成功！");
												$('#t1').datagrid("reload");
											} else {
												$.messager.alert("提示", "操作失败！");
											}
										},
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
											
										}
									});

						});
	});
</script>
</html>