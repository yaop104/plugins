<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<title>html</title>
</head>
<body>
<!-- 	<div region="north" style="height:50px"> -->
<!-- 	   <div id="tb" style="padding: 10px; height: auto">   -->
<!-- 	          账号名:    -->
<!-- 	           <input id="s_name"/>    -->
<!-- 	           按状态：   -->
<!-- 	             <select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">   -->
<!-- 	             	<option value="1">有效</option> -->
<!-- 					 <option value="2">无效</option> -->
<!-- 	             </select>   -->
<!-- 	           <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>   -->
<!-- 	       </div>   -->
<!-- 	   </div>   -->
		
		
<!-- 		</div> -->
<!-- 			<table class="easyui-datagrid"  style="width:100%;height:200px" -->
<%-- 					data-options="singleSelect:true,nowrap:true,loadMsg:'正在加载数据.......',collapsible:false,url:'${ctx }/appDetail/page.do',method:'post'"> --%>
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th data-options="field:'pAppdetailId',width:150,align:'center'">ID</th> -->
<!-- 						<th data-options="field:'pAppdetailName',width:150,align:'center'">名称</th> -->
<!-- 						<th data-options="field:'pAppdetailVersion',width:120,align:'center'">版本</th> -->
<!-- 						<th data-options="field:'pAppdetailPatchstate',width:80,align:'center'">状态</th> -->
<!-- 						<th data-options="field:'pAppdetailPlugintype',width:80,align:'center'">类型</th> -->
<!-- 						<th data-options="field:'pAppdetailDate',width:150,align:'center'">时间</th> -->
<!-- 						<th data-options="field:'pAppdetailId',width:50,align:'center',formatter:'ys2'">操作</th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 			</table> -->
		<table id="t2"></table>
		<input type="hidden" value="${pid}" id="pid_hid">
		<!-- 查看 -->
		<div id="viewDig"></div>
<script language="javascript">
//滚动条的高度
var height;
//滚动条跳到顶部
function myScollTop()
{
	height = $(document).scrollTop(); 
	//$("#apk").panel("move",{top:100});
	//window.scrollTo(0,0);
	$(".panel-body div").scrollTop(0);
}
var grid1;

$(function() {
	var colArr1 = [];
	
	colArr1 = [
		{ field:'pAppdetailId', align:'center', width:'150' , title:'ID' },
		{ field:'pAppdetailName', align:'center', width:'150' , title:'名称' },
		{ field:'pAppdetailVersion', align:'center', width:'120', sortable:'true' , title:'版本'},
		{ field:'pAppdetailPatchstate', align:'center',  width:'80', sortable:'true' , title:'状态'  },
		{ field:'pAppdetailPlugintype', align:'center',  width:'80', sortable:'true' , title:'类型'},
		{ field:'pAppdetailDateStr', align:'center',  width:'80', sortable:'true' , title:'时间' },
		{ field:'asd', align:'center', width:'180' , title:'操作', formatter : ys2}
	];
	
	//初始化显示列表		
	grid1 = $('#t2').datagrid({
			height:200,
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			pageList : [5, 10, 15],
			pageSize : 10,
			url:'${ctx }/appDetail/pageParams.do',
			fitColumns : false,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : false,//设置true将在数据表格底部显示分页工具栏
			sortName : 'pAppdetailId',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
 			singleSelect : true,//设置为true将只允许选择一行
			idField : 'pAppdetailId',//表明该列是一个唯一列。
			rownumbers : false,//设置为true将显示行数
			columns:[colArr1],
			queryParams: {
				ptype: '2',
				pid: $("#pid_hid").val()
			}

		});
});

function ys2(val, rec, index) {
	var returnvalue="<img  src='${ctx}/image/table_td_button/look.png' onclick='lookapk("+rec.pAppdetailId+")' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='lookapk("+rec.pAppdetailId+")'>查看</a>&nbsp;&nbsp;";
	if((rec.pAppdetailAuditstate-0)<=2){
		returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/update.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='updatechild(\""+rec.pAppdetailId+"\",\""+rec.pAppdetailActionname+"\",\""+rec.pAppdetailPlugintype+"\")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='updatechild(\""+rec.pAppdetailId+"\",\""+rec.pAppdetailActionname+"\",\""+rec.pAppdetailPlugintype+"\")'>修改</a>&nbsp;&nbsp;";								
	}else if((rec.pAppdetailAuditstate-0)==3){
		returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/shutdown.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='stopSafa(4,"+rec.pAppdetailId+")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='stopSafa(4,"+rec.pAppdetailId+")'>停用</a>&nbsp;&nbsp;";								
	}else if((rec.pAppdetailAuditstate-0)==4){
		returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/startup.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='stopSafa(3,"+rec.pAppdetailId+")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='stopSafa(3,"+rec.pAppdetailId+")'>启用</a>&nbsp;&nbsp;";
	}
	if((rec.pAppdetailAuditstate-0)<3){
		returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/delete.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='deletes("+rec.pAppdetailId+","+rec.pAppdetailApkactionid+")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='deletes("+rec.pAppdetailId+","+rec.pAppdetailApkactionid+")'>删除</a>&nbsp;&nbsp;";
	}
	return returnvalue;
}

//删除
function deletes(id,apkactionid){

//		checkLogin();
		$.messager.confirm("操作提示", "<div class='messager-icon'></div>您确定要执行操作吗？", function (data) {
            if (data) {
				$.ajax({
					type:"POST",
					url:"${ctx}/appDetail/deleteHtml.do",
					data:{"pAppdetailId" : id, "pAppdetailApkactionid" : apkactionid},
					dataType:"json",
					success:function(data){
						$.messager.alert("提示", data.info);
						if(data.code == 500 ||data.code == 503){
							//getData();
							grid.datagrid('load');
	   						grid.datagrid('clearSelections');
						}
					}
				});
            }
        });
}	

//cha kan 插件
function lookapk(id){

	openViewDialog(id);
}

//查看框
var viewDig = $('#viewDig').show().dialog({
	modal : true,
	title : '查看插件',
	closable: true,
	collapsible:false,
	href: "${ctx}/appDetail/viewHtml.do",
	maximizable:false,
	draggable: true,
	width: 850,
	height:document.body.clientHeight-20,
	//width:document.body.clientWidth-30,
	onClose:function(){
			$("#viewDig").dialog("move",{top:20});
		},
		onOpen: function() {
			myScollTop();
			$("#viewDig").dialog("move",{top:height+20});
			$(".window-mask").height($("#box").height()+150);
			$(".window-mask").width($("#foot_btn").width());
		}, 
	top:10,
	resizable:false,
	cache:false,
	onMove:function(left,top)
	{
			if(top < 0)
			{
				top = 0;
				setTimeout("reMove('viewDig',"+top+","+left+")",100); 
			}
	}
}).dialog('close');	

function reMove(obj,nt,nl)
{
	$('#'+obj).dialog("move",{
		left:nl,
		top:nt
	});
}

//查看方法
function openViewDialog(ids){

//	checkLogin();
	$('#viewDig').dialog('open');
	$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/appDetail/getById.do",
			data:{'pAppdetailId':ids},
			dataType:"json",
			success:function(data){
				$('#v_id').val(data.pAppdetailId);
				$('#v_pluginname').html(data.pAppdetailName);
				if("1" == data.pAppdetailPlugintype){
					$('#v_plugintype').html('HTML');
				}else{
					$('#v_plugintype').html('APK');
				}
				$('#v_version').html(data.pAppdetailVersionname);
				$('#v_app_logo').attr("src","${ctx}/upload/"+data.pAppdetailLogo);
				$('#v_name').html(data.pAppdetailName);
				$('#v_app_time').html(data.pAppdetailDate);
				$('#v_corporationname').html(data.pAppdetailPlatform);
				$('#v_app_url').html("<a href='"+data.pAppdetailUrl+"'target=_blank>"+data.pAppdetailUrl+"</a>");
				
				var array = new Array();
				if(data.pAppdetailDescpic1 != '')
				{
					array.push("${ctx}/upload/"+data.pAppdetailDescpic1);
				}
				if(data.pAppdetailDescpic2 != '')
				{
					array.push("${ctx}/upload/"+data.pAppdetailDescpic2);
				}
				if(data.pAppdetailDescpic3 != '')
				{
					array.push("${ctx}/upload/"+data.pAppdetailDescpic3);
				}

				$('#v_img1').attr("src",array[0]);
				$('#v_img1_big').attr("href",array[0]);
				$('#v_img2').attr("src",array[1]);
				$('#v_img2_big').attr("href",array[1]);
				$('#v_img3').attr("src",array[2]);
				$('#v_img3_big').attr("href",array[2]);
				
				if(array[0] == undefined)
				{
					$('#v_img1_big').unbind("click");
					$('#v_img1_big').hide();
				}
				else
				{
					$('#v_img1_big').lightbox({
					    fitToScreen: true,
					    imageClickClose: true,
					    overlayOpacity: 0.2
				    });
				    $('#v_img1_big').show();
				}
				
				if(array[1] == undefined)
				{
					$('#v_img2_big').unbind("click");
					$('#v_img2_big').hide();
				}
				else
				{
					$('#v_img2_big').lightbox({
					    fitToScreen: true,
					    imageClickClose: true,
					    overlayOpacity: 0.2
				    });
				    $('#v_img2_big').show();
				}
				
				if(array[2] == undefined)
				{
					$('#v_img3_big').unbind("click");
					$('#v_img3_big').hide();
				}
				else
				{
					$('#v_img3_big').lightbox({
					    fitToScreen: true,
					    imageClickClose: true,
					    overlayOpacity: 0.2
				    });
				    $('#v_img3_big').show();
				}
				
				$('#v_auditopinion').html(data.pAppdetailAuditoption);
				$('#v_auditopinion').attr("readonly",true);
				$('#v_app_desc').html(data.pAppdetailDesc);
				$('#v_app_changelog').html(data.pAppdetailChangelog);
					$("#apkinfo").hide();
			}
		});
}
</script>
</body>
</html>