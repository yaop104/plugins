<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/js/easyui/jquery.lightbox.js"></script>
<style type="text/css">
	.icon-sortMy{
		background:url('${ctx}/img/temp/sort.png') no-repeat;
		margin-left: 16px;
	}
</style>
<script type="text/javascript">
	var grid, orderByDig, viewDig;
	var ctx = 'http://114.55.150.199:8888/download/pic/';
	$(function() {
		var colArr = [];
		colArr = [
				{
					field : 'pAppPluginname',
					title : '插件名称',
					width : 120,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppdetailVersion',
					title : '插件版本',
					width : 80,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppOpen',
					title : '使用数',
					width : 80,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppPraise',
					title : '点赞数',
					width : 80,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppPlugintype',
					title : '插件类型',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
							return "<span style=\"margin-left: 14px; margin-right: 10px;\">APK</span>";
					},
					resizable : false
				},
				{
					field : 'pAppdetailDesc',
					title : '描述',
					width : '140',
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppdetailAdminame',
					title : '提交人',
					width : 80,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppdetailAdmindesc',
					title : '提交单位',
					width : 100,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppdetailDate',
					title : '提交时间',
					width : 100,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppId',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.pAppdetailAuditstate == 5) {
							value = "<span style='width: 20px;height: 20px;vertical-align: middle;'>已下线</span>"
									+ "<img src='<s:url value="/img/temp/look.png"/>' class='audit_img' onclick='showDetail("
									+ row.pAppdetailId
									+ ")' />"
									+ "<a href='javascript:showDetail("
									+ row.pAppdetailId
									+ ");' title='查看' class='audit_link'>查看</a>";
							return value;
						}
						value = "<img src='<s:url value="/img/temp/off.png"/>' class='audit_img' onclick='offLine("
								+ value
								+ ")' />"
								+ "<a href='javascript:offLine("
								+ value
								+ ");' title='下线' class='audit_link'>下线</a>"
								+ "<img src='<s:url value="/img/temp/look.png"/>' class='audit_img' onclick='showDetail("
								+ row.pAppdetailId
								+ ")' />"
								+ "<a href='javascript:showDetail("
								+ row.pAppdetailId
								+ ");' title='查看' class='audit_link'>查看</a>";
						return value;
					},
					resizable : false
				} ];

		grid = $('#t1')
				.datagrid(
						{
							iconCls : 'icon-save',
							nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
							striped : true,//显示条纹
							collapsible : true,
							url : '${ctx }/application/getpApplicationlists.do',
							pageList : [ 10, 15, 20 ],
							pageSize : 10,
							fitColumns : true,
							loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
							pagination : true,//设置true将在数据表格底部显示分页工具栏
							/* sortName : 'pAppOpen',//当数据表格初始化时以哪一列来排序
							sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序） */
							remoteSort : false,//定义是否通过远程服务器对数据排序
							itColumns : false,
							singleSelect : false,//设置为true将只允许选择一行
							idField : 'pAppId',//表明该列是一个唯一列。
							rownumbers : true,//设置为true将显示行数
							columns : [ colArr ],
							onClickRow : function(index, row) {
								$(this).datagrid('unselectAll');
							},
							toolbar : [
								<%--{--%>
								<%--id : 'btncut',--%>
								<%--text : '自定义排序',--%>
								<%--iconCls : 'icon-sortMy',--%>
								<%--handler : function() {--%>
									<%--var rc = $('#t1').datagrid('getData');--%>
									<%--size = rc.total;--%>
									<%--if (size > 1) {--%>
										<%--orderByDig = $('#orderByDig')--%>
												<%--.show()--%>
												<%--.dialog(--%>
														<%--{--%>
															<%--cache : false,--%>
															<%--modal : true,--%>
															<%--title : '插件列表',--%>
															<%--closable : true,--%>
															<%--collapsible : false,--%>
															<%--href : "${ctx }/application/orderApp.do",--%>
															<%--maximizable : false,--%>
															<%--minimizable : false,--%>
															<%--draggable : false,--%>
															<%--width : 400,--%>
															<%--height : 300--%>
														<%--}).dialog('close');--%>
										<%--orderByDig.dialog('open');--%>
									<%--} else if (size == 1) {--%>
										<%--$.messager.alert("提示", "插件过少，无需排序！");--%>
									<%--} else {--%>
										<%--$.messager.alert("提示", "没有可排序的数据！");--%>
									<%--}--%>
								<%--}--%>
							<%--}--%>
							]
						});
		
		$(".l-btn-text").css("padding-left","16px");
		$(".l-btn-icon").css("width","18px");
		
		viewDig = $("#viewDetail").dialog({
			title : '插件详情',
			width : 850,
			height : getHeight(),
			top : 10,
			closed : false,
			cache : false,
			href : '${ctx}/appDetail/viewDetail.do',
			modal : true,
			maximizable : true,
			draggable : true
		}).dialog('close');
	});
	

	function searchApp() {
		var name = $("#s_name").val();
		var queryParams = grid.datagrid('options').queryParams;
		queryParams.rows = grid.datagrid('options').pageSize;
		queryParams.page = 0;
		queryParams.pAppdetailName = name;
		grid.datagrid('options').queryParams = queryParams;
		grid.datagrid('reload');
	}

	function offLine(id) {
		$.messager.confirm("Confirm?", "下线操作将会下线所有的插件版本<br />确认下线？",
				function(r) {
					if (r) {
						$.post("${ctx}/application/offLine.do", {
							"appId" : id
						}, function(result) {
							if (result.code == "500") {
										msgShow('成功', result.message, 'info');
											grid.datagrid('reload');
							} else {
								msgShow('错误', "下线失败", 'error');
							}
						});
					}
				});
	}

	function showDetail(id) {
		viewDig.dialog('open');
		$.post("${ctx}/appDetail/" + id + "/info.do", {}, function(data) {
			$('#v_id').val(data.pAppdetailId);
			$('#v_pluginname').html(data.pAppdetailName);
			if(data.pAppdetailPlugintype == "2"){
				$('#v_plugintype').html("apk");
			}else{
				$('#v_plugintype').html("");
			}


			$('#v_version').html(data.pAppdetailVersionname);
			$('#v_app_logo').attr("src", ctx + data.pAppdetailLogo);
			$('#v_name').html(data.pAppdetailAdminame);
			$('#v_app_time').html(data.pAppdetailDate);
			$('#v_corporationname').html(data.pAppdetailAdmindesc);
			$('#v_app_url').html(
					"<a href='"+data.pAppdetailUrl+"'>" + data.pAppdetailUrl
							+ "</a>");

			var array = new Array();
			if (data.descpic1 != '') {
				array.push(ctx + data.pAppdetailDescpic1);
			}
			if (data.descpic2 != '') {
				array.push(ctx + data.pAppdetailDescpic2);
			}
			if (data.descpic3 != '') {
				array.push(ctx + data.pAppdetailDescpic3);
			}

			$('#v_img1').attr("src", array[0]);
			$('#v_img1_big').attr("href", array[0]);
			$('#v_img2').attr("src", array[1]);
			$('#v_img2_big').attr("href", array[1]);
			$('#v_img3').attr("src", array[2]);
			$('#v_img3_big').attr("href", array[2]);

			if (array[0] == undefined) {
				$('#v_img1_big').unbind("click");
				$('#v_img1_big').hide();
			} else {
				$('#v_img1_big').lightbox({
					fitToScreen : true,
					imageClickClose : true,
					overlayOpacity : 0.2
				});
				$('#v_img1_big').show();
			}

			if (array[1] == undefined) {
				$('#v_img2_big').unbind("click");
				$('#v_img2_big').hide();
			} else {
				$('#v_img2_big').lightbox({
					fitToScreen : true,
					imageClickClose : true,
					overlayOpacity : 0.2
				});
				$('#v_img2_big').show();
			}

			if (array[2] == undefined) {
				$('#v_img3_big').unbind("click");
				$('#v_img3_big').hide();
			} else {
				$('#v_img3_big').lightbox({
					fitToScreen : true,
					imageClickClose : true,
					overlayOpacity : 0.2
				});
				$('#v_img3_big').show();
			}

			$('#v_auditopinion').val(data.pAppdetailAuditoption);
			$('#v_auditopinion').attr("readonly", true);
			$('#v_app_desc').html(data.pAppdetailDesc);
			$('#v_app_changelog').html(data.pAppdetailChangelog);

			$("#apkinfo").hide();
		});
	}
	
	function getHeight() {
		if ($(window).height() > 700) {
			return 700;
		} else {
			return $(window).height();
		}
	}
</script>
<title>终端列表</title>
<style type="text/css">
.audit_img {
	cursor: pointer;
	width: 20px;
	height: 20px;
	vertical-align: middle;
}

.audit_link {
	height: 20px;
	line-height: 30px;
	vertical-align: middle;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false">
		<div region="north" style="height: 50px">
			<%--功能区--%>
			<div id="tb" style="padding: 10px; height: auto">
				<div>
					插件名称: <input id="s_name" />
					<a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="searchApp()">&nbsp;查&nbsp;&nbsp;询
						&nbsp; &nbsp;</a>
				</div>
			</div>


		</div>
		<div region="center" border="false">
			<table id="t1" fit="true"></table>

			<!-- 窗口-->
			<div id="orderByDig"></div>
			<div id="viewDetail"></div>
		</div>
	</div>
</body>
</html>