<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
<!-- admin/user/select.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/commonAjax.jsp" />
<script language="javascript">
		var actionPath = "<s:url value="/admin/user/" encode="false"/>";

     
        function showView(pk){
        	art.dialog.open(actionPath+'view.action?user.pkid=' + pk, {
			    title : '查看详情',
			    width : 600,
			    height : 400,
			    lock : true
		    });
        }
        function preUpdate(pk){
        	art.dialog.open(actionPath+'preUpdate.action?user.pkid=' + pk, {
			    title : '修改',
			    width : 600,
			    height : 400,
			    lock : true
		    });
        }
        function relateRole(pk,isSp){
            if(isSp == "1"){
            	art.dialog.open(actionPath+'preRelateRole.action?user.pkid=' + pk, {
    			    title : '分配角色',
    			    width : 650,
    			    height : 400,
    			    lock : true
    		    });
        	}else{
                alert("SP用户不能手动赋权限");
            }
        }
        function preInsert(){
            art.dialog.open(actionPath+'preInsert.action', {
    			    title : '新建用户',
    			    width : 650,
    			    height : 400,
    			    lock : true
    		    });
        }

    
        
     	// 删除单行
        function del(pk,state) {
            if(state == "0"){
                alert("用户"+pk+"已经被删除!");
                return;
             }
            if (window.confirm("删除不可恢复，确定要删除用户[ "+pk+" ]吗？")) {
                    clearSelectedByPK(pk);
			        delAjax(actionPath+'delete.action?user.pkid=' + pk);
                }
        }
     	// 删除单行
        function delAdmin(pk) {
        	alert("超级用户不可删除");
        }
        
        // 批量删除
        function batDelete() {
        	var pks = getCurrentSelected();
            if (pks != "") {
                if (window.confirm("确定要删除用户[ "+pks+" ]吗？")) {
                	delAjax(actionPath+'delete.action?user.pkid=' + pks);
                }
            } else {
                alert("请选中要删除的用户！");
            }
        }
        
     	

     	// 初始化密码
        function initPassword(pks){
            if (window.confirm("确定要初始化用户[ "+pks+" ]的密码吗？")) {
                jQuery.ajax({
                    url:actionPath+'initPassword.action?user.pkid=' + pks,
                    type:"POST",
                    cache:false,
                    dataType: "json",
                    success:function(data) {
                        alert(data.message);
                        var params = $('#frm').serializeObject();
                        $("#grid").trigger("query", [
                            {
                                searchParams:params
                            }
                        ]);
                    }
                });
            }
        }

        // 批量初始化密码
        function batInitPassowrd(){
            var pks = getCurrentSelected();
            if (pks != "") {
                initPassword(pks);
            } else {
                alert("请选中要初始化密码的用户！");
            }
        }
    	// 相关操作列
        function renderOperation(userCode, pkid,validFlag,state) {
        	var str = '<div class="yanshi_newultda">';
        	str += '<span  class="icon_chakan"><a href="javascript:showView(\'' + pkid + '\');"  title="查看详情">查看详情</a></span>';
        	str += '<span  class="icon_Modify"><a href="javascript:preUpdate(\'' + pkid + '\');"  title="修改">修改</a></span>';
        	str += '<span  class="icon_mimaa"><a href="javascript:initPassword(\'' + pkid + '\');"  title="初始化密码">初始化密码</a></span>';
        	str += '<span  class="icon_user" ><a href="javascript:relateRole(\'' + pkid + '\',\'' + validFlag+ '\');"  title="分配角色"/></span>';
        	if("${protectedUser}".indexOf(","+userCode+",")<0){
        		str += '<span  class="icon_del" ><a href="javascript:del(\'' + pkid+'\', \'' + state+ '\');" title="删除">删除</a></span>';
			}else{
				str += '<span  class="icon_del" ><a href="javascript:delAdmin(\'' +pkid + '\');" title="删除">删除</a></span>';
			}
        	str +='</div>';
            return str;
        }
    	
    	
     	// 转换状态列数据
        function renderValidFlag(flag) {
            var ret = "";
            if (flag == "1") {
                ret = "正常";
            } else if (flag == "0") {
                ret = "无效";
            } else if (flag == "2"){
				ret = "锁定";
            } else {
                ret = "";
            }
            return ret;
        }
     	
        function showResponse(resultData) {
			var trStr = "";
			if (resultData == undefined || resultData == "" || resultData == null || resultData.length <= 0) {
				var td_i = $("#sortable thead tr th").size();
				trStr = "<tr><td colspan='"+td_i+"'>没有找到任何相关记录</td></tr>";
			} else {
				//得到要填充的字符串
				for ( var i = 0; i < resultData.length; i++) {
					d = resultData[i];
					trStr += '<tr>';
					trStr += '<td><div align="center"><input type="checkbox" class="checkboxClas" name="pkidList" value="'+d.pkid+'" /></div></td>';
					trStr += '<td>'+ d.userCode + '</td>';
					trStr += '<td>'+ d.userName + '</td>';
					trStr += '<td  class="subStr">'+ d.email + '</td>';
					trStr += '<td  class="subStr">'+ d.phone + '</td>';
					trStr += '<td  class="subStr">'+ d.lastLoginIp + '</td>';
					trStr += '<td  class="subStr">' +getDateString(d.lastLoginTime); + '</td>';
					trStr += '<td>'+ renderValidFlag(d.state) + '</td>';
					trStr += '<td >'+renderOperation(d.userCode,d.pkid,d.validFlag);+'</td>';
					trStr += '</tr>';
				}
			}
			//进行表格填充
			$("#sortable tbody").html(trStr);

		}
        
			
        </script>
</head>

<body>
<!-- form名字为grid 不要修改，js调用 -->
<form id="grid" name="grid" method="post"  action="<s:url value="user/selectGrid.do" encode="false"/>" >

<div class="rightarea"><!-- -------------------------搜索条件  开始---------------------------------- -->
<ul class="yanshi_search01">
	<table width="600" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">账号</td>
			<td>
			<div class="geinputleft">
			<div class="geinputright">
			<div class="geinputmid"><input  type="text" id="user_code" name="user.userCode" value="${user.userCode}" class="input"  rules="trimBlank"/></div>
			</div>
			</div>
			</td>
			<td align="center">显示名</td>
			<td>
			<div class="geinputleft">
			<div class="geinputright">
			<div class="geinputmid"><input type="text" id="user_name" name="user.userName" value="${user.userName }"  class="input"  rules="trimBlank"/></div>
			</div>
			</div>
			</td>
			<td>
			<div><input id="yanshi_search3" name="" type="button" value=""
				onclick="serach()" /></div>
			</td>
		</tr>
	</table>
</ul>
<!-- -------------------------搜索条件  结束---------------------------------- -->

<div class="pic_border">

<div class="top_pic_border_left">
<div class="top_pic_border_right">
<Div class="border_bg"></Div>
</div>
</div>

<div class="mid_boder"><!-- -------------------------  按钮  开始---------------------------------- -->
<div class="yanshi_newinput1">
<input name="" type="button" onclick="preInsert()" value="新建用户"/>
<input type="button" value="初始化密码" onclick="batInitPassowrd()" name=""/>
</div>
<!-- -------------------------  按钮 结束---------------------------------- -->
<table id="sortable" class="yanshi_newul"  cellpadding="0" cellspacing="0" cla="even_odd">
	<thead>
		<!-- -------------------------  表头部分---------------------------------- -->
		<tr>
			<th nowrap width="5%" class="noSort"><input type="checkbox"
									onclick="switchAll()" class="checkboxClas"/></th> 
			<th nowrap width="8%" class="sortCla" sortNum=1>账号</th>
			<th nowrap width="8%" class="sortCla" sortNum=2>显示名</th>
			<th nowrap width="12%" class="sortCla" sortNum=3>E-mail</th>
			<th nowrap width="12%" class="sortCla" sortNum=4>联系电话</th>
			<th nowrap width="12%" class="sortCla" sortNum=5>上次登录IP</th>
			<th nowrap width="15%" class="sortCla" sortNum=6>上次登录时间</th>
			<th nowrap width="8%" class="sortCla" sortNum=7>状态</th>
			<th nowrap width="20%" class="noSort">相关操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="pagenumber"><jsp:include page="/inc/pageAjax.jsp" /></div>
</div>
<div class="bottom_pic_border_left">
<div class="bottom_pic_border_right">
<div class="border_bg1"></div>
</div>
</div>
</div>

</div>
</form>
</body>
</html>
