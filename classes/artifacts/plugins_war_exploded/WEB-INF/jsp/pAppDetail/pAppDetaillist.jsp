<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/js/easyui/jquery.lightbox.js"></script>
<script type="text/javascript" src="${ctx }/js/check.js" ></script>
<script type="text/javascript">
	var grid;
	var viewDig;
	var checkDig;
	var ctx = 'http://114.55.150.199:8888/download/pic/';
	$(function() {
		var colArr = [];
		colArr = [
				{
					field : 'pAppdetailName',
					title : '插件名称',
					width : 120,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppdetailPlugintype',
					title : '插件类型',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
							return "<span style=\"margin-left: 14px; margin-right: 10px;\">APK</span>";
					},
					resizable : false
				},
				{
					field : 'pAppdetailVersion',
					title : '版本',
					width : 80,
					align : 'center',
					resizable : false
				},
				{
					field : 'pAppdetailDesc',
					title : '描述',
					width : 200,
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
					resizable : false, formatter : CommonYao.DateFormatter
				},
				{
					field : 'pAppdetailAuditstate',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == 1) {
							value = "待审核";
						}
						if (value == 2) {
							value = "审核不通过";
						}
						if (value == 6) {
							value = "测试发布";
						}
						return value;
					},
					resizable : false
				},
				{
					field : 'pAppdetailId',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if (row.pAppdetailAuditstate == 2) {
							value = "<img src='<s:url value="/img/temp/look.png"/>' class='audit_img' onclick='showDetail("
									+ value
									+ ")'/>"
									+ "<a href='javascript:showDetail("
									+ value
									+ ");' title='查看' class='audit_link'>查看</a>";
							return value;
						}
						<%--if (row.pAppdetailAuditstate == 6) {--%>
							<%--value = "<img src='<s:url value="/img/temp/update.png"/>' class='audit_img' onclick='appAudit("--%>
									<%--+ value--%>
									<%--+ ")' />"--%>
									<%--+ "<a href='javascript:appAudit("--%>
									<%--+ value--%>
									<%--+ ", true);' title='审核' class='audit_link'>审核</a>";--%>
							<%--if (row.pAppdetailPlugintype == 0) {--%>
								<%--value += "<img src='<s:url value="/img/temp/download.png"/>' class='audit_img' style='width:18px;height:15px;' />"--%>
											<%--+ "<a href='' title='下载' class='audit_link'>下载</a>";--%>
							<%--}--%>
							<%--return value;--%>
						<%--}--%>
						value = "<img src='<s:url value="/img/temp/update.png"/>' class='audit_img' onclick='appAudit("
								+ value
								+ ")' />"
								+ "<a href='javascript:appAudit("
								+ value
								+ ", false);' title='审核' class='audit_link'>审核</a>";
						return value;
					},
					resizable : false
				} ];

		grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			url : '${ctx }/appDetail/getpAppDetaillists.do',
			pageList : [ 10, 15, 20 ],
			pageSize : 10,
			fitColumns : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			/* sortName : 'pAppdetailVersion',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序） */
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
			singleSelect : false,//设置为true将只允许选择一行
			idField : 'pAppdetailId',//表明该列是一个唯一列。
			rownumbers : true,//设置为true将显示行数
			columns : [ colArr ],
			onClickRow : function(index, row) {
				$(this).datagrid('unselectAll');
			}
		});

		viewDig = $("#d1").dialog({
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

		checkDig = $("#d2").dialog({
			title : '插件审核',
			width : 850,
			height : getHeight(),
			top : 10,
			closed : false,
			cache : false,
			href : '${ctx}/appDetail/checkDetail.do',
			modal : true,
			maximizable : true,
			draggable : true,
			buttons : [
//				{
//				id : 'btnTest',
//				text : '测试发布',
//				handler : function() {
//					$.messager.confirm("操作提示", "是否测试发布该插件？", function(data) {
//						if (data) {
//							submitState("test");
//						} else {
//							checkDig.dialog('close');
//							document.getElementById("corForm").reset();
//						}
//					}, "info");
//				}
//			},
				{
				id : 'btnPass',
				text : '审核通过',
				handler : function() {
					$.messager.confirm("操作提示", "是否上线该插件？", function(data) {
						if (data) {
							submitState("formal");
						} else {
							checkDig.dialog('close');
							document.getElementById("corForm").reset();
						}
					}, "info");
				}
			}, {
				id : 'btnNopass',
				text : '审核不通过',
				handler : function() {
					if (!checkAuditopinion()) {
						return;
					}

					submitState("nopass");
				}
			} ]
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

	function showDetail(id) {
		viewDig.dialog('open');
		$.post("${ctx}/appDetail/" + id + "/info.do", {}, function(data) {
			$('#v_id').val(data.pAppdetailId);
			$('#v_pluginname').html(data.pAppdetailName);
			$('#v_plugintype').html(data.pAppdetailPlugintype);
			$('#v_version').html(data.pAppdetailActionname);
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

	function appAudit(id, isTest) {
		checkDig.dialog('open');
//		if (isTest) {
//			$('#btnTest').linkbutton('disable');
//			$('#btnPass').linkbutton('enable');
//		} else {
//			$('#btnTest').linkbutton('enable');
//			$('#btnPass').linkbutton('disable');
//		}
		$.post("${ctx}/appDetail/" + id + "/info.do", {}, function(data) {
			$('#c_id').val(data.pAppdetailId);
			$('#c_pluginname').html(data.pAppdetailName);
			$('#c_plugintype').html(data.pAppdetailPlugintype);
			$('#c_version').html(data.pAppdetailVersionname);
			$('#c_app_logo').attr("src", ctx + data.pAppdetailLogo);
			$('#c_name').html(data.pAppdetailAdminame);
			$('#c_app_time').html(data.pAppdetailDate);
			$('#c_corporationname').html(data.pAppdetailAdmindesc);
			$('#c_app_url').html(
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

			$('#c_img1').attr("src", array[0]);
			$('#c_img1_big').attr("href", array[0]);
			$('#c_img2').attr("src", array[1]);
			$('#c_img2_big').attr("href", array[1]);
			$('#c_img3').attr("src", array[2]);
			$('#c_img3_big').attr("href", array[2]);

			if (array[0] == undefined) {
				$('#c_img1_big').unbind("click");
				$('#c_img1_big').hide();
			} else {
				$('#c_img1_big').lightbox({
					fitToScreen : true,
					imageClickClose : true,
					overlayOpacity : 0.2
				});
				$('#c_img1_big').show();
			}

			if (array[1] == undefined) {
				$('#c_img2_big').unbind("click");
				$('#c_img2_big').hide();
			} else {
				$('#c_img2_big').lightbox({
					fitToScreen : true,
					imageClickClose : true,
					overlayOpacity : 0.2
				});
				$('#c_img2_big').show();
			}

			if (array[2] == undefined) {
				$('#c_img3_big').unbind("click");
				$('#c_img3_big').hide();
			} else {
				$('#c_img3_big').lightbox({
					fitToScreen : true,
					imageClickClose : true,
					overlayOpacity : 0.2
				});
				$('#c_img3_big').show();
			}

			$('#c_auditopinion').val('');
			//$('#c_auditopinion').val(data.auditopinion);
			$('#c_auditopinion').attr("readonly", false);
			$('#c_app_desc').html(data.pAppdetailDesc);
			$('#c_app_changelog').html(data.pAppdetailChangelog);

			$("#c_apkinfo").hide();
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
				<%-- 查找管理员信息，根据时间、管理员名 --%>
				<div>
					插件名称: <input id="s_name" />  <a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="searchApp()">&nbsp;查&nbsp;&nbsp;询
						&nbsp; &nbsp;</a>
				</div>
			</div>


		</div>
		<div region="center" border="false">
			<table id="t1" fit="true"></table>

			<!-- 窗口-->
			<div id="d1"></div>
			<div id="d2"></div>
		</div>
	</div>
</body>
</html>