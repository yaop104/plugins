<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- admin/user/insert.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/commonForDialog.jsp" />
<jsp:include page="/inc/common.jsp" />
</head>

<body>
	<form id="frm" name="frm" method="post" class="extendarea">
		<!-- 工具栏 -->
		<div class="controlbar">
			<ul>
				<li class="bar_search"><a href="#" onclick="save();">
						<div class="itembody">
							<span class="ico ico_create"></span><span class="presenter">修改</span>
						</div>
				</a></li>
				<%--<li class="bar_refresh"><a href="#" onclick="closeDialog();">--%>
						<%--<div class="itembody">--%>
							<%--<span class="ico dialogclose"></span><span class="presenter">关闭</span>--%>
						<%--</div>--%>
				<%--</a></li>--%>
			</ul>
		</div>
		<table border="0" cellpadding="0" cellspacing="0" class="formlist"
			style="width: 100%; table-layout: fixed;">
			<tr>
				<th width="120px"><span class="required">*</span>原密码：</th>
				<td><input type="password" id="oldpwd" name="oldpwd"
					class="input" title="原密码" tip="原密码"
					rules="required,trimBlank" maxlength="20" /></td>
			</tr>
			<tr>
				<th width="120px"><span class="required">*</span>新密码：</th>
				<td><input type="password" id="newpwd" name="newpwd"
					class="input" title="新密码" tip="新密码"
					rules="required,trimBlank" maxlength="20" /></td>
			</tr>
			<tr>
				<th width="120px"><span class="required">*</span>重新输入新密码：</th>
				<td><input type="password" id="repwd" name="repwd"
					class="input" title="重新输入新密码" tip="重新输入新密码"
					rules="required,trimBlank" maxlength="20" /></td>
			</tr>
		</table>
	</form>
</body>
<script language="javascript">
var path = "/plugins/";
//保存
function save(){
	if(Validator.isValid("frm")) {
         var oldpwd = $('#oldpwd').val();
         var newpwd = $('#newpwd').val();
         var repwd = $('#repwd').val();
         if(checkPwd()){
        	 $.post(path+'repwd.do',{'oldpwd':oldpwd,'newpwd':newpwd}, function(data){
        		 console.info(data.code);
        		 if("0"==data.code){
        			 alert("成功，密码已修改");
					 location.href = path+'login.do';
        		 }else if("1"==data.code){
        			 alert("密码错误，重新修改");
        		 }else if("2"==data.code){
        			 alert("账号未登录，重新登入");
        		 }else if("3"==data.code){
        			 alert("系统异常，联系管理员");
        		 }
        	 },'json');
         }
        }
     }

function checkPwd(){
	var oldpwd = $('#oldpwd').val();
    var newpwd = $('#newpwd').val();
    var repwd = $('#repwd').val();
    if(oldpwd ==""){ alert("原密码未填写，请填写原密码。"); return false;}
    if(newpwd ==""){ alert("新密码未填写，请填写新密码。"); return false;}
    if(newpwd != repwd){ alert("新密码两次输入不同，请重新输入。"); return false;}
    return true;
}

function clear() {
    $("#frm").clearForm();
}
// 关闭窗口
function closeDialog() {
	window.parent.art.dialog.close();
}
</script>
</html>
