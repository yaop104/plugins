<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="panel-fit">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/lightbox.css"></link>
	<script type="text/javascript" src="<%=basePath%>js/easyui/jquery.lightbox.js"></script>
	<style>
		
		.td_left{
			background-color: #ffffff;
			text-align:right;
			width:400px;
			vertical-align:top;
		}
		
		.trtext{
			height:35px;
		}
		.td{
			background-color: #ffffff;
			padding-left:5px;
			color:black;
			padding-right:48px;
			vertical-align:top;
		}
		.td span a{
		text-decoration:underline;
		}
		
		.dialog-button{
		text-align:center;
		border-top: 1px solid #eee;
		background: #eeeeee;
		padding: 5px 5px;
		height:45px
		}
				
		
	</style>
	</head>
		<table id="addtable"  cellpadding="1" cellspacing="0" border="0" style="font-size:12px;" width="100%" height="100%" bordercolor="#B4CFCF"><!--  -->
			<tr class="trtext">
				<td class="td_left" style="padding-top: 10px;">插件名称 ：</td>
				<td class="td" style="padding-top: 10px; padding-bottom: 10px;word-break:break-all;">
					<span id="v_pluginname" ></span>
				</td>
			</tr>
			<tr class="trtext">
				<td class="td_left" >插件类型：</td>
				<td class="td">
					<span id="v_plugintype" ></span>
				</td>
			</tr>
			<tr class="trtext">
				<td class="td_left" >插件版本：</td>
				<td class="td">
					<span id="v_version" ></span>
				</td>
			</tr>
			
			<tr class="trtext">
				<td class="td_left" >图标：</td>
				<td class="td">
					<img id="v_app_logo" style="width:72px;height:72px;padding-bottom: 10px;" title="已安装图标" />
				</td>
			</tr>
			<tr class="trtext" id="apkinfo">
				<td class="td_left" >终端型号：</td>
				<td class="td">
					<span id="v_terminal" ></span>
				</td>
			</tr>
			<tr class="trtext">
				<td class="td_left" >提交者：</td>
				<td class="td">
					<span id="v_name" ></span>
				</td>
			</tr>
			
			<tr class="trtext">
				<td class="td_left" >提交时间：</td>
				<td class="td">
					<span id="v_app_time" ></span>
				</td>
			</tr>
			
			<tr class="trtext">
				<td class="td_left" >提交单位：</td>
				<td class="td">
					<span id="v_corporationname" ></span>
				</td>
			</tr>
			
			<tr class="trtext">
				<td class="td_left" >插件地址：</td>
				<td class="td" style="word-break:break-all;">
					<span id="v_app_url" ></span>
				</td>
			</tr>
			
			<tr>
				<td class="td_left" >审核缩略图：</td>
				<td class="td" style="height: 175px">
						
						<a class="lightbox" href="" id="v_img1_big" onclick="return false;">
						<img style="width:107px;height:175px;margin-right: 25px;" id="v_img1" /></a>
						<a class="lightbox" href="" id="v_img2_big" onclick="return false;">
					    <img style="width:107px;height:175px;margin-right: 25px;" id="v_img2" /></a>
					    <a class="lightbox" href="" id="v_img3_big" onclick="return false;">
					    <img style="width:107px;height:175px;margin-right: 25px;" id="v_img3" /></a>
					    
				</td>
			</tr>
			
			<tr style="height: 35px">
				<td class="td_left"  style='padding-top: 10px;'>插件描述：</td>
				<td class="td" style="padding-top: 7px; padding-bottom: 5px;word-break:break-all;">
					<span id="v_app_desc" style="line-height:24px;"></span>
				</td>
			</tr>
			
			<tr style="height: 35px">
				<td class="td_left"  style='padding-top: 7px;'>更新内容：</td>
				<td class="td" style="padding-top: 5px; padding-bottom: 15px;word-break:break-all;">
					<span id="v_app_changelog" style="line-height:24px;"></span>
				</td>
			</tr>
			
			<tr>
				<td class="td_left" >审核意见：<br/></td>
				
				<td class="td" style="height: 63px">
					<textarea style="width:630px;height:65px;resize: none;color:#117bb8;margin-bottom:18px" name="cor.auditopinion" id="v_auditopinion" onfocus="blur()"></textarea>
					</td>
			</tr>
		</table>
		<input type="hidden" id="id" name="cor.id"/>
		<script>
		
		</script>
</html>