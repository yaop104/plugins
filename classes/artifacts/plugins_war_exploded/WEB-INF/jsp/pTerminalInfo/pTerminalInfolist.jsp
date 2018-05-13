<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/inc/commonAjax.jsp" />
		<script type="text/javascript">
		    var path = "/plugins/PTerminalInfo/";
		
			function search(){
				getResponseJson();
				$("#sysMenu_Name").val("");
			}
			
			function getResponseJson(){
				var param = "sysMenuName="+$('#sysMenu_Name').val();
				var url = "getsysmenulists.do?"+param;
				$.getJSON(url, function(json){
					createTbody(json);
				});
			}
			
			function createTbody(json){
				var tableBody = $(".yanshi_newul > tbody");
				tableBody.html("");
				var htm = "<tbody>";
				//处理JSON对象i=索引,n=内容
				$.each(json,function(i, n){
					if(i%2==0){
						tableBody.append('<tr class="gradeX"></tr>'); 
					}else{
						tableBody.append('<tr class="gradeC"></tr>'); 
					}
			     	var tr = $('tr:last', tableBody); 
			     	
			    	tr.append('<td class="sortCla">' + json[i].sysMenuId + '</td>'); 
		    		tr.append('<td class="sortCla">' + json[i].sysMenuName + '</td>'); 
					tr.append('<td class="sortCla">' + json[i].sysMenuPid + '</td>'); 
					tr.append('<td class="sortCla">' + json[i].sysMenuState + '</td>'); 
					tr.append('<td class="sortCla">' + json[i].sysMenuUrl + '</td>'); 
					tr.append('<td class="sortCla"><span  class="icon_chakan"><a href="javascript:showView('+ json[i].sysMenuId +')" title="查看详情"></a></span>|'+
									'<span class="icon_Modify" ><a href="javascript:preUpdate('+ json[i].sysMenuId +')" title="更新"></a></span>|'+
									'<span  class="icon_del" ><a href="javascript:del('+ json[i].sysMenuId +')" title="删除"></a></span></td>');
				});
			    }
				//查看
				 function showView(pk){
				 	window.location=path + pk +'/info.do';
		        }
			    //修改
		        function preUpdate(pk){
		        	window.location=path + pk +'/update.do';
		        }
		        //添加
		        function preInsert(){
		        	window.location=path+'add.do';
		        }
		        
		     	// 删除单行
		        function del(pk) {
		            if (window.confirm("删除不可恢复，确定要删除吗？")) {
		                   window.location=path + pk + "/delete.do";
		                }
		        }
			
		</script>
		<title>终端列表</title>
			<style type="text/css">
			.sortCla a{
			padding-top:0px;
			height:23px !important;
			}
			.sortCla a:hover{
			padding-top:0px;
			height:23px !important;
			}
		</style>
	</head>
	<body>   <!-- onload="fillTable();" -->
	<form id="grid" name="grid" method="post"  />
		<div class="rightarea"><!---------------------------搜索条件  开始---------------------------------- -->
    <ul class="yanshi_search01">
	<table width="600" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">名称</td>
			<td>
			<div class="geinputleft">
			<div class="geinputright">
			<div class="geinputmid"><input  type="text" id="sysMenu_Name" name="sysMenuName" value="${ sysMen.sysMenuName}" class="input"  rules="trimBlank"/></div>
			</div>
			</div>
			</td>
			<td>
			<div><input id="yanshi_search3" name="" type="button" value=""
				onclick="search()" /></div>
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

<!-- -------------------------  按钮  开始---------------------------------- -->
<div class="yanshi_newinput1">
<input name="" type="button" onclick="preInsert()" value="新增终端"/>
</div>
		<div>
			<c:choose>
				<c:when test="${empty pageList.list}">
					尚无用户，请添加！
				</c:when>
				<c:otherwise>
					<table id="myTable" class="yanshi_newul"  cellpadding="0" cellspacing="0" cla="even_odd">
						<thead>
							<tr align="center">
								<th nowrap width="5%" class="noSort">ID</th>
								<th nowrap width="8%" class="noSort" sortNum=2>终端品牌</th>
								<th nowrap width="12%" class="noSort" sortNum=3>提交者</th>
								<th nowrap width="12%" class="noSort" sortNum=4>提交时间</th>
								<th nowrap width="12%" class="noSort" sortNum=5>备注</th>
								<th nowrap width="20%" class="noSort">相关操作</th>
							</tr>
						</thead>
						<tbody align="center">
							<c:forEach var="data" items="${pageList.list}" varStatus="count">
								 <tr align="center">
									<td class="sortCla">${data.pTerminfoId}</td>
									<td class="sortCla">${data.pTerminfoName}</td>
									<td class="sortCla">${data.pTerminfoCuser}</td>
									<td class="sortCla">${data.pTerminfoCdate}</td>
									<td class="sortCla">${data.pTerminfoDemo}</td>
									<td class="sortCla">
									<span  class="icon_chakan"><a href="javascript:showView(${data.pTerminfoId});"  title="查看详情"></a></span>
									<span  class="icon_del" ><a href="javascript:del(${data.pTerminfoId});" title="删除"></a></span>
								    </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="pagenumber"><jsp:include page="/inc/newPage.jsp" /></div>
</div>
<div class="bottom_pic_border_left">
<div class="bottom_pic_border_right">
<div class="border_bg1"></div>
</div>
</div>
</div>
</form>
	</body>
</html>