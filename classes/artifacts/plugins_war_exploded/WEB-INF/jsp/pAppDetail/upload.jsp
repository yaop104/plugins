<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="<s:url value='/js/uploadify/uploadify.css' />">
<script type="text/javascript" src='<s:url value="/js/uploadify/jquery.uploadify.min.js" />'></script>
<style type="text/css">
	#file_queue {
		background-color: #FFF;
	    border-radius: 3px;
	    box-shadow: 0 1px 3px rgba(0,0,0,0.25);
	    height: 203px;
	    margin-bottom: 10px;
	    overflow: auto;
	    padding: 5px 10px;
	    width: 300px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#file_upload").uploadify({
			'swf':'${ctx}/js/uploadify/uploadify.swf',
			'method':'post',
			'uploader':'${ctx}/appDetail/uploadfile.do',
			'uploadLimit':99999, /* 上传文件的最大数量 */
			'progressData':'percentage',/* 上传进度显示百分比 设置为speed显示上传的速度  */
			'auto':false,/* 不自动上传 */
			'buttonText':'选择文件',
			'fileObjName':'file',/* The name of the file object to use in your server-side script. */
			'buttonClass':'btn',
			'height':14,
			'width':64,
			/* 'fileSizeLimit':'',允许上传的文件大小 */
			'fileTypeExts':'*.apk',/* 允许上传的文件类型 */
			'formData':{},/* 提交到服务器的参数 */
			'multi'    : false,/* 不允许多文件上传 */
			'preventCaching' : true,
			'onUploadStart' : function(file) {
	            $("#message_div").html(file.name + "开始上传..");
	        },
	        'onUploadSuccess' : function(file, data, response) {
	            $("#message_div").html('文件' + file.name + ' 上传成功，服务器返回 ' + response + ':' + data);
	        },
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	            $("#message_div").html('文件' + file.name + ' 上传失败: ' + errorString);
	        }
		});
		
		<%--$("#pic_upload").uploadify(--%>
			<%--{--%>
				<%--'swf':'${ctx}/js/uploadify/uploadify.swf',--%>
				<%--'method':'post',--%>
				<%--'uploader':'${ctx}/appDetail/uploadFile.do',--%>
				<%--'uploadLimit':99999, /* 上传文件的最大数量 */--%>
				<%--'progressData':'percentage',/* 上传进度显示百分比 */--%>
				<%--'auto':false,/* 不自动上传 */--%>
				<%--'buttonText':'选择文件',--%>
				<%--'fileObjName':'file',--%>
				<%--'queueID':'file_queue',--%>
				<%--'buttonClass':'btn',--%>
				<%--'height':14,--%>
				<%--'width':64,--%>
				<%--/* 'fileSizeLimit':'',允许上传的文件大小 */--%>
				<%--'fileTypeExts':'*.jpg;*.png;*.gif',/* 允许上传的文件类型 */--%>
				<%--'formData':{},/* 提交到服务器的参数 */--%>
				<%--'multi'    : true,/* 允许多文件上传 */--%>
				<%--'preventCaching' : true,--%>
				<%--'onUploadSuccess' : function (file, data, response) {--%>
					<%----%>
				<%--}--%>
			<%--}--%>
		<%--);--%>
	});
</script>
</head>
<body>
	<form action="${ctx }/appDetail/uploadfile.do" method="post" id="mainForm" name="mainForm" enctype="multipart/form-data">
		<input id="file_upload" type="file" name="file_upload">
		<a href="javascript:$('#file_upload').uploadify('upload','*')" style="padding-left: 12px;line-height: 30px;">开始上传</a>
		<div id="message_div"></div>
		
		<%--<hr>--%>
		<%--<div id="file_queue"></div>--%>
		<%--<input id="pic_upload" type="file" name="pic">--%>
		<%--<a href="javascript:$('#pic_upload').uploadify('upload','*')" style="padding-left: 12px;line-height: 30px;">开始上传</a>--%>
	</form>
</body>
</html>