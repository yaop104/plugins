<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- admin/user/relate_role.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/commonForDialog.jsp" />
<script language="javascript">
//修改
function save(){
	var str = "";
	var oAssign = document.getElementById("assign");
	for(var i = 0; i < oAssign.options.length; i++ ) {
        str+= oAssign.options[i].value;
        if(i<oAssign.options.length-1){
            str +=",";
        }
    }
    $("#roleId").val(str);
   	var params = $('#frm').serializeObject();
   	jQuery.ajax({
           url:"<s:url value="/admin/user/relateRole.action" encode="false"/>",
           type:"POST",
           cache:false,
           async:false,
           dataType: "json",
           data: params,
           success:function(json, textStatus) {
               if (json.code == "1") {
            	   alert("操作成功！");
               	   art.dialog.top.reload();
              	 	art.dialog.close();s
               } else {
               	alert("操作失败！原因："+json.message); 
               }
           },
           error:function(XMLHttpRequest, textStatus, errorThrown) {
               alert("保存时出现异常！");
           }
       });
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
	<input type="hidden" id="roleId" name="user.userName"  />
	<!-- 工具栏 -->
	<div class="controlbar">
	    <ul>
	        <li class="bar_search">
	            <a href="#" onclick="save();">
	                <div class="itembody"><span class="ico ico_create"></span><span class="presenter">保存</span></div>
	            </a>
	        </li>
	        <li class="bar_refresh">
	            <a href="#" onclick="closeDialog();">
	                <div class="itembody"><span class="ico dialogclose"></span><span class="presenter">关闭</span></div>
	            </a>
	        </li>
	    </ul>
	</div>
    <div class="title">
        <div class="itembody">用户基本信息</div>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
        <tr>
            <td align="left" valign="top">

                <table border="0" cellpadding="0" cellspacing="0" class="formlist" style="width:100%;table-layout:fixed;">
                    <tr>
                        <th>用户账号：</th>
                        <td>${user.userCode }</td>
                        <th>显示名：</th>
                        <td>${user.userName }</td>
                    </tr>
                    <tr>
                        <th>状态：</th>
                        <td><s:if test="user.validFlag==1">正常</s:if>
                        	<s:elseif test="user.validFlag==0">无效</s:elseif>
                			<s:else>锁定</s:else>
                        </td>
                        <th>所属地区</th>
                        <td>
                        	<s:iterator value="areas" status="stuts">
			           			<s:if test="user.areaId==pkid">${areaName }</s:if>
							</s:iterator>
						</td>
                    </tr>
                </table>

            </td>
        </tr>
    </table>
    <div class="title">
        <div class="itembody">用户分配角色</div>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
        <tr>
            <td align="center" valign="top">
                未分配角色<br/>
                <select id="unassign" multiple="multiple" size="10" style="width:260px;height:200px" onDblClick="moveSelected('unassign','assign')">
                <s:iterator value="unassignRoles" status="stuts">
           			<option value="${pkid }">${roleName }--${note }</option>
				</s:iterator>
                </select>
            </td>
            <td align="center" valign="center" style="width:10px;">
                <a href="javascript:moveSelected('unassign','assign')" class="sbtn"><span class="itembody">添加&gt;&gt;</span></a>
                <br/><br/>
                <a href="javascript:moveSelected('assign','unassign')" class="sbtn"><span class="itembody">&lt;&lt;删除</span></a>
            </td>
            <td align="center" valign="top">
                已分配角色<br/>
                <select id="assign" multiple="multiple" size="10" style="width:260px;height:200px" onDblClick="moveSelected('assign','unassign')">
                <s:iterator value="assignRoles" status="stuts">
           			<option value="${pkid }">${roleName }--${note }</option>
				</s:iterator>
                </select>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
