<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="panel-fit">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<style>
.td_left {
	background-color: #ffffff;
	text-align: right;
	width: 400px;
	vertical-align: top;
}

.trtext {
	height: 35px;
}

.td {
	background-color: #ffffff;
	padding-left: 5px;
	color: #000000;
	padding-right: 48px;
	vertical-align: top;
}

.td span {
	color: black;
}

.td span a {
	text-decoration: underline;
}

.dialog-button {
	text-align: center;
	border-top: 1px solid #eee;
	background: #eeeeee;
	padding: 5px 5px;
	height: 45px
}
</style>
</head>

<table id="addtable" cellpadding="1" cellspacing="0" border="0"
	style="font-size: 12px;" width="100%" height="100%"
	bordercolor="#B4CFCF">
	<!--  -->
	<tr class="trtext">
		<td class="td_left" style="padding-top: 10px;">插件名称 ：</td>
		<td class="td"
			style="padding-top: 10px; padding-bottom: 10px; word-break: break-all;">
			<span id="c_pluginname"></span>
		</td>
	</tr>
	<tr class="trtext">
		<td class="td_left">插件类型：</td>
		<td class="td"><span id="c_plugintype"></span></td>
	</tr>
	<tr class="trtext">
		<td class="td_left">插件版本：</td>
		<td class="td"><span id="c_version"></span></td>
	</tr>
	<tr class="trtext">
		<td class="td_left">图标：</td>
		<td class="td"><img id="c_app_logo"
			style="width: 72px; height: 72px; padding-bottom: 10px;"
			title="已安装图标" /></td>
	</tr>
	<tr class="trtext" id="c_apkinfo">
		<td class="td_left">终端型号：</td>
		<td class="td"><span id="c_terminal"></span></td>
	</tr>
	<tr class="trtext">
		<td class="td_left">提交者：</td>
		<td class="td"><span id="c_name"></span></td>
	</tr>

	<tr class="trtext">
		<td class="td_left">提交时间：</td>
		<td class="td"><span id="c_app_time"></span></td>
	</tr>

	<tr class="trtext">
		<td class="td_left">提交单位：</td>
		<td class="td"><span id="c_corporationname"></span></td>
	</tr>

	<tr class="trtext">
		<td class="td_left">插件地址：</td>
		<td class="td" style="word-break: break-all;">
			<!-- <a href="ddrb/090630.asp" target="_blank" style="text-decoration:underline;"><span id="c_app_url" ></span></a>
					 --> <span id="c_app_url"></span>
		</td>
	</tr>

	<tr>
		<td class="td_left">审核缩略图：</td>
		<td class="td" style="height: 175px"><a class="lightbox" href=""
			id="c_img1_big" onclick="return false;"> <img
				style="width: 107px; height: 175px; margin-right: 25px;" id="c_img1" /></a>
			<a class="lightbox" href="" id="c_img2_big" onclick="return false;">
				<img style="width: 107px; height: 175px; margin-right: 25px;"
				id="c_img2" />
		</a> <a class="lightbox" href="" id="c_img3_big" onclick="return false;">
				<img style="width: 107px; height: 175px; margin-right: 25px;"
				id="c_img3" />
		</a></td>
	</tr>

	<tr style="height: 35px">
		<td class="td_left" style='padding-top: 10px;'>插件描述：</td>
		<td class="td"
			style="padding-top: 7px; padding-bottom: 5px; word-break: break-all;">
			<span id="c_app_desc" style="line-height: 24px;"></span>
		</td>
	</tr>

	<tr style="height: 35px">
		<td class="td_left" style='padding-top: 7px;'>更新内容：</td>
		<td class="td"
			style="padding-top: 5px; padding-bottom: 15px; word-break: break-all;">
			<span id="c_app_changelog" style="line-height: 24px;"></span>
		</td>
	</tr>

	<tr>
		<td class="td_left">审核意见：<br /></td>

		<td class="td" style="height: 63px"><textarea
				style="width: 630px; height: 65px; resize: none; color: #117bb8; margin-bottom: 18px"
				id="c_auditopinion"></textarea></td>
	</tr>
</table>
<form id="corForm">
	<input type="hidden" id="c_id" name="pAppdetailId" /> <input
		type="hidden" id="auditopinion" name="pAppdetailAuditoption" />
</form>
<input type="hidden" id="c_apkactionid" />
<input type="hidden" id="c_version_code" />
<script>
	// 检查组件action名字
	function checkAuditopinion() {
		var auditOption = document.getElementById('c_auditopinion').value;

		if (auditOption.trim().length == 0) {
			$.messager.alert('操作提示', '请填写不通过理由！', "info");
			$('.messager-button .l-btn:first-child span:last').html('确定');
			document.getElementById('c_pluginname').focus();
			return false;
		} else {
			return true;
		}
	}

	//处理按钮 test表示测试发布，formal表示正式发布，nopass表示审核不通过
	function submitState(method) {
		var state;
		if (method == 'formal') {
			state = 3;
			$('#btnPass').linkbutton('disable');
		} else if (method == 'nopass') {
			state = 2;
		} else if (method == 'test') {
			$('#btnTest').linkbutton('disable');
			state = 6;
		}

		$('#auditopinion').val($('#c_auditopinion').val());
		var parm = $('#corForm').serialize();

		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/appDetail/appAudit.do",
			data : parm + "&pAppdetailAuditstate=" + state,
			dataType : "json",
			success : function(data) {
				data = parseJSON(data);
				$.messager.alert('提示', data.message);

				if (data.code == 500) {
					checkDig.dialog('close');
					document.getElementById("corForm").reset();
				} else {
					if (state == 3) {
						$('#btnPass').linkbutton('enable');
					} else if (state == 6) {
						$('#btnTest').linkbutton('enable');
					}
				}
				grid.datagrid('reload');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}

	//清除文本框
	function res() {
		document.getElementById("corForm").reset();
		$('#id').val(null);
		$('#pluginname').html(null);
		$('#plugintype').html(null);
		$('#version').html(null);
		$('#app_logo').attr("src", null);
		$('#name').html(null);
		$('#app_url').html(null);
		$('#img1').attr("src", null);
		$('#img2').attr("src", null);
		$('#img3').attr("src", null);
		$('#auditopinion').html(null);
		$('#app_desc').html(null);
		$('#auditopinion').attr("readonly", false);
	}
</script>
</html>