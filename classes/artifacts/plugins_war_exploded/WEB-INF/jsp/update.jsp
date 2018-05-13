<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- admin/user/update.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/commonForDialog.jsp" />
<script language="javascript">
$(document).ready(function() {
	var value="${user.validFlag}";
	var account="${user.userCode}";
	if(value == 2){
		$("#user_name").attr("disabled","disabled");
		$("#phone").attr("disabled","disabled");
		$("#note").attr("disabled","disabled");
		$("#email").attr("disabled","disabled");
	}
	if(account=='admin' || account=='SP_SYS'){
		$("#valid_flag").attr("disabled","disabled");
	}
});

//修改
function save(){
	if(Validator.isValid("frm")) {
        if (checkUnique()){
        	var params = $('#frm').serializeObject();
        	jQuery.ajax({
                url:"<s:url value="/admin/user/update.action" encode="false"/>",
                type:"POST",
                cache:false,
                async:false,
                dataType: "json",
                data: params, 
                success:function(json, textStatus) {
                    if (json.code == "1") {
                    	 alert("操作成功！"); 
                  	   art.dialog.top.reload();
                  		art.dialog.close();
                    } else {
                    	alert("操作失败！原因："+json.message); 
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("保存时出现异常！");
                }
            });
        }
     }
}

// 检查用户名是否存在
function checkUnique() {
    var obj = $("#user_code");
    var obj_value= obj.val();
    obj_value = jQuery.trim(obj_value);
    var old_value = $("#old_name").val();
    if(obj_value == old_value) return true;
    var result = false;
    if (obj_value != "") {
        jQuery.ajax({
            url:"<s:url value="/admin/user/checkUnique.action" encode="false"/>",
            type:"POST",
            cache:false,
            async:false,
            dataType: "json",
            data: {"user.userCode":obj_value},
            success:function(json, textStatus) {
                if (json.code == "0") {
                    obj.addClass("error");
                    alert(json.message);
                } else {
                    obj.removeClass("error");
                    result = true;
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert("检查用户名是否存在时出现异常！");
            }
        });
    }
    
    return result;
}

// 重置form
function clear() {
	var value="${user.validFlag}";
	if(value == 2){
		return;
	}
	$("#frm").clearForm();
    //重置表单时账号被清空，重新注值
    $("#user_code").attr("value",$("#old_name").val());
}

// 关闭窗口
function closeDialog() {
	art.dialog.close();
}
</script>
</head>

<body>
<form id="frm" name="frm" method="post" class="extendarea">
	<input type="hidden" id="pkid" name="user.pkid"  value="${user.pkid}" />
	<input type="hidden" id="old_name" value="${user.userCode}" />
	<input type="hidden" id="validFlag" value="${user.validFlag}" />
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
            <th width="120px"><span class="required">*</span>账号：</th>
            <td>
                <input type="text" id="user_code" name="user.userCode" class="input"
                       title="登录系统的账号" disabled="disabled" onblur="checkUnique()"
                       tip="账号" rules="required,string" maxlength="20" value="${user.userCode }"/>
            </td>
        </tr>
        <tr>
            <th><span class="required">*</span>显示名：</th>
            <td><input type="text" id="user_name" name="user.userName" class="input" tip="显示名" rules="required,trimBlank"
                       maxlength="10" value="${user.userName }"/>
            </td>
        </tr>
        <tr>
            <th><span class="required">*</span>所属地区：</th>
            <td><select class="dropdownlist" id="area_id" name="user.areaId" tip="所属地区" disabled="disabled">
            		<s:iterator value="areas" status="stuts">
            			<option value="${pkid }" <s:if test="user.areaId==pkid">selected</s:if>>${areaName }</option>
					</s:iterator>
                </select>
            </td>
        </tr>
        <tr>
            <th>E-mail：</th>
            <td><input type="text" id="email" name="user.email" class="input" tip="E-mail" rules="email,trimBlank" maxlength="40" value="${user.email }"/></td>
        </tr>
        <tr>
            <th>手机：</th>
            <td><input type="text" id="phone" name="user.phone" class="input" tip="手机" rules="mobile,trimBlank" maxlength="40" value="${user.phone }"/></td>
        </tr>
        <tr>
            <th>状态：</th>
            <td>
                <span class="clr">
                    <select class="dropdownlist" id="valid_flag" name="user.validFlag" tip="状态">
                        <option value="1" <s:if test="user.validFlag==1">selected</s:if>>正常</option>
                        <option value="2" <s:if test="user.validFlag==2">selected</s:if>>锁定</option>
                        <option value="0" <s:if test="user.validFlag==0">selected</s:if>>无效</option>
                    </select>
                </span>
            </td>
        </tr>
        <tr>
            <th>说明：</th>
            <td>
                <textarea class="textarea" id="note" name="user.note" rows="5" tip="说明" rules="text,trimBlank" maxlength="100">${user.note }</textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
