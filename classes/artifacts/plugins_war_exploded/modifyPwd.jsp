<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- modifyPwd.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/common.jsp" />
<script language="javascript">
//修改
function save(){
	if(Validator.isValid("frm")) {
		if($("#password").val()!=$("#confirmPassword").val()){
			alert("新密码和确认密码不一致");
			$("#password").select(); 
			$("#password").focus();
			return false;
		}
		if($("#password").val().length<6){
			alert("新密码不到6位");
			$("#password").select(); 
			$("#password").focus();
			return false;
		}
     	jQuery.ajax({
             url:"<s:url value="/modifyPwd.action" encode="false"/>",
             type:"POST",
             cache:false,
             async:false,
             dataType: "json",
             data: {"oldPwd":$("#oldPwd").val(),"user.userPassword":$("#password").val()},
             success:function(json, textStatus) {
                 if (json.code == "1") {
                     closeDialog();
                     alert("密码修改成功！"); 
                 } else {
                 	alert(json.message); 
                 }
             },
             error:function(XMLHttpRequest, textStatus, errorThrown) {
                 alert("修改密码时出现异常！");
             }
         });
     }
}

// 重置form
function clear() {
    $("#frm").clearForm();
}

// 关闭窗口
function closeDialog() {
	window.parent.innerWindowClose();
}
</script>
</head>

<body>
<form id="frm" name="frm" method="post" class="extendarea">
    <!-- 工具栏 -->
	<div class="controlbar">
	    <ul>
	        <li class="bar_search">
	            <a href="#" onclick="save();">
	                <div class="itembody"><span class="ico ico_create"></span><span class="presenter">保存</span></div>
	            </a>
	        </li>
	        <li>
	            <a href="javascript:clear()">
	                <div class="itembody"><span class="ico ico_refresh"></span><span class="presenter">重填</span></div>
	            </a>
	        </li>
	        <li class="bar_refresh">
	            <a href="#" onclick="closeDialog();">
	                <div class="itembody"><span class="ico dialogclose"></span><span class="presenter">关闭</span></div>
	            </a>
	        </li>
	    </ul>
	</div>
    <table border="0" cellpadding="0" cellspacing="0" class="formlist" style="width:100%;table-layout:fixed;">
        <tr>
            <th width="120px"><span class="required">*</span>旧密码：</th>
            <td>
                <input type="password" id="oldPwd" name="oldPwd" class="input"
                       tip="旧密码" rules="required" maxlength="20"/>
            </td>
            <td width="120px"></td>
        </tr>
        <tr>
            <th width="120px"><span class="required">*</span>新密码：</th>
            <td>
                <input type="password" id="password" name="password" class="input"
                       tip="新密码" rules="required" maxlength="20"/>
            </td>
            <td width="120px">最短6位，最长20位</td>
        </tr>
        <tr>
            <th width="120px"><span class="required">*</span>确认密码：</th>
            <td>
                <input type="password" id="confirmPassword" name="confirmPassword" class="input"
                       tip="确认密码" rules="required" maxlength="20"/>
            </td>
            <td width="120px">最短6位，最长20位</td>
        </tr>
    </table>
</form>
</body>
</html>
