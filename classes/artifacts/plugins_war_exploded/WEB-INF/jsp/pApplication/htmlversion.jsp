<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<!-- 新增修改html -->
<%@ include file="/WEB-INF/jsp/pApplication/html.jsp" %>
<title>账号信息</title>
<script type="text/javascript" src="<%=basePath %>js/easyui/datagrid-detailview.js" ></script>
<style type="text/css">
body{font-family:helvetica,"Microsoft Yahei",sans-serif;font-weight:normal;font-size:14px;line-height:20px;color:#333;height:100%;position:relative;}
.datagrid-cell {
	text-overflow:ellipsis;
}
.line{
	height:30px;
	line-height:30px;
	margin-left:20px;
	margin-top:15px;
	position: relative;
}
.tbtitle{
	width:120px;
	text-align: right;
}
.tbvalue{
	position:absolute;
	left:120px;
	top:0px;
}
.line2{
    height:30px;
    line-height:20px;
    margin-left:20px;
    margin-top:15px;
}
.tbtitle2{
    width:120px;
    text-align: right;
    float: left;
    clear: left;
}
.tbvalue2{
    left:120px;
    top:0px;
    float: left;
    width:600px;
}
.up_div{
	width:107px;
	height:175px;
	border:0px solid #000000;
	background:url(../image/upload.png);
	overflow: hidden; 
	position: relative; 
	direction: ltr;
	cursor: pointer;
	text-decoration: none;
}
.up_div:hover{
	background:url(../image/uploadhover.png);
	cursor: pointer;
}
.up_div_icon{
	width:72px;
	height:72px;
	border:0px solid #000000;
	background:url(../image/upload_icon.png);
	overflow: hidden; 
	position: relative; 
	direction: ltr;
	cursor: pointer;
	text-decoration: none;
}
.up_div_icon:hover{
	background:url(../image/upload_icon_hover.png);
	cursor: pointer;
}



.divover{
	border:2px solid red;
	position: relative;
}

.unlineM:hover{
text-decoration:underline;
}
.dialog-button{
		text-align:center;
		}
		
		
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
			color:#117bb8;
			padding-right:48px;
			vertical-align:top;
		}
		.dialog-button{
		text-align:center;
		border-top: 1px solid #eee;
		background:#eeeeee;
		padding:5px 5px;
		}
		.checkcss{
			margin:0px;
		}
		.mybtn{
			background:url(../image/table_td_button/Add_Btn_3.png) no-repeat;
			width:116px;
			height:29px;
			float:left;
			line-height: 29px;
			cursor: pointer;
			color:#000000;
			font-weight: bold;
			text-align: center;
		}
		.mybtn a{
			color:#000000;
			margin-top:5px;
			font-weight: bold;
		}
		.mybtn:HOVER {
			background:url(../image/table_td_button/Add_Btn_4.png) no-repeat;
			width:116px;
			height:29px;
		}
		.add-btn{
			margin-top:3px;
			width:118px;
			height:28px;
			line-height:28px;
			background:url(../image/table_td_button/Add_Btn_2.png) no-repeat;
		}
		.add-btn:HOVER {
			background:url(../image/table_td_button/Add_Btn_1.png) no-repeat;
		}
}

.transparent_class {
filter:alpha(opacity=0);
-moz-opacity:0;
-khtml-opacity: 0;
opacity: 0;
margin: 0px;
padding: 0px;
top: 0px;
right: 0px;
font-family: Arial;
font-size: 118px;
position: absolute;
cursor: pointer;
height:27px;
}
.pn-tishi{
	color:#C0BFBF;
}
</style>
</head>

<body>
<div class="easyui-layout" fit="true"  border="false">
	<div region="north" style="height:50px">
		<%--功能区--%>  
	   <div id="tb" style="padding: 10px; height: auto">  
	      <%-- 查找管理员信息，根据时间、管理员名 --%>  
	       <div>  
<!-- 	           时间从:   -->
<!-- 	           <input id="s_startTime" class ="easyui-datebox" style="width: 100px" />   -->
<!-- 	           到:   -->
<!-- 	           <input id="s_endTime" class="easyui-datebox" style="width: 100px" />    -->
<!-- 	          账号名:    -->
<!-- 	           <input id="s_name"/>    -->
	           按类型：  
	             <select id="s_state" name="pAppPlugintype" style="width: 150px;">  
					 <option value="1">HTML</option>
	             </select>  
	           <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchAccount()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>  
	       </div>  
	   </div>  
		
	
	</div>
  	<div region="center"   border="false">
  		<table id="t1"></table>
  			<!-- 窗口-->
			<div id="d1" class="easyui-dialog" buttons="#btn123" title="账号编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">  
		         <div style="padding:10px 60px 20px 60px">
		            <form id="f1"  class="easyui-form" method="post">  
		            <input type="hidden" id="pAppId" name="pAppId"/>
		                <table>  
		                      <tr>  
		                        <td align="right">名称：</td>  
		                        <td><input class="easyui-validatebox" name="pAppPluginname" required="true" style="width: 152px" id="pAppPluginname"></input></td>  
		                    </tr> 
		                      <tr>  
		                        <td align="right">排序号：</td>  
		                        <td><input name="pAppDisplaysort" required="true" style="width: 152px" id="pAppDisplaysort"></input></td>  
		                    </tr> 
		                     <tr>  
		                        <td align="right">插件类型：</td>  
		                        <td>
		                        <select name="pAppPlugintype" id="pAppPlugintype" style="width:152px;">
		                       		 <option value="1" selected="selected">HTML</option>
		                        </select>
		                        </td>  
		                    </tr> 
		                    <tr>
								<td>备注：</td>
								<td><input class="easyui-textbox" id="pAppRemark" name="pAppRemark" data-options="multiline:true" style="height:60px"></input></td>
							</tr>
		                </table>  
		            </form>  
		            </div>  
		     </div>
             <div id="btn123">
		    	<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px"> 保  存 </a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px"> 取  消 </a>
		    </div>
  	</div>
 </div>
 
 <div style="display: none;">
	<form id="form" name="form" action="${ctx}/appDetail/addHtmlDetail.do" method="post">
				<input type="hidden" id="hdone" name="pAppdetailDescpic1"><br/>
				<input type="hidden" id="hdtwo" name="pAppdetailDescpic2"><br/>
				<input type="hidden" id="hdthree" name="pAppdetailDescpic3"><br/>
				<input type="hidden" id="app_name" name="pAppdetailName"  /><br/>
				<input type="hidden" id="plugintype" name="pAppdetailPlugintype" ><br/>
				<input type="hidden" id="app_desc" name="pAppdetailDesc" /><br/>
				<input type="hidden" id="changelog" name="pAppdetailChangelog" /><br/>
				<input type="hidden" id="app_platform"  name="pAppdetailPlatform" /><br/>
				<input type="hidden" id="app_apkactionid"  name="pAppdetailApkactionid" />
				<input type="hidden" id="app_versionname" name="pAppdetailVersionname"/><br/>
				<input type="hidden" id="app_size" name="pAppdetailSize" /><br/>
				<input type="hidden" id="app_package_name" name="pAppdetailPackagename" /><br/>
				<input type="hidden" id="app_action_name" name="pAppdetailActionname" /><br/>
				<input type="hidden" id="app_cert_digest" name="pAppdetailCertdigest"/><br/>
				<input type="hidden" id="app_logo" name="pAppdetailLogo" /><br/>
				<input type="hidden" id="app_version" name="pAppdetailVersion" /><br/>
				<input type="hidden" id="app_apk" name="pAppdetailApk"></input><br/>
				<input type="hidden" id="app_md5" name="pAppdetailMd5"/><br/>
				<input type="hidden" id="app_id" name="pAppdetailId"/><br/>
				<input type="hidden" id="app_platform_version" name="pAppdetailPlatformversion"/><br/>
				<input type="hidden" id="app_url" name="pAppdetailUrl"/><br/>
<!-- 				<input type="hidden" id="terminal_info" name="terminal_info"> <br/>-->
			</form>
		</div>
</body>
<script language="javascript">
var imgform = '<form id="html_second_pic" name="upload_Two_form" style="padding:0px;margin:0px;" action="${ctx}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >'+
'<div class="up_div" >'+
	'<input type="file" name="upload_one_file"  class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">'+
'</div>'+
'</form>';
var grid;

$(function() {
	var colArr = [];
	
	colArr = [
		{ field:'pAppId', align:'center', width:'150' , title:'ID' },
		{ field:'pAppPluginname', align:'center', width:'150' , title:'名称' },
		{ field:'pAppPlugintype', align:'center', width:'120', sortable:'true' , title:'类型', formatter : convertState},
		{ field:'pAppOpen', align:'center',  width:'80', sortable:'true' , title:'下载叔'  },
		{ field:'pAppPraise', align:'center',  width:'80', sortable:'true' , title:'点赞数' },
		{ field:'asd', align:'center', width:'280' , title:'操作', formatter : ys1}
	];
	
	var _toolbars = [{
	        id : 'btnadd',
	        text : '添加插件大类',
	        iconCls : 'icon-add',
	        handler : function() {
	        	addHtml();
	        }
	}, '-', {
	        id : 'btncut',
	        text : '删除插件大类',
	        iconCls : 'icon-remove',
	        handler : function() {
	                deleteHtml();
	        }
	}, '-', {
	        id : 'btnupdate',
	        text : '更新插件大类',
	        iconCls : 'icon-edit',
	        handler : function() {
	                editHtml();
	        }
	}];
	
	$('#sysAccRoleid').combobox({
// 			onShowPanel:function(){
// 				$('#sysAccRoleid').combobox({
// 					url:'${ctx }/sysRole/selectRoles.do'
// 				});
// 			}, 
			url:'${ctx }/sysRole/selectRoles.do',
			valueField:'roleid', 
			textField:'rolename' 
		});
	
	//初始化显示列表		
	grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
		//	url : '${ctx }/application/getApplicationlists.do',
			pageList : [10, 15, 20],
			pageSize : 15,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'pAppId',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
 			singleSelect : false,//设置为true将只允许选择一行
			idField : 'pAppId',//表明该列是一个唯一列。
			rownumbers : true,//设置为true将显示行数
			frozenColumns:[[
	                {field:'ck',checkbox:true}
	     	]],
			columns:[colArr],
			toolbar : _toolbars
		});
// 		grid.datagrid('options').url = '${ctx }/application/page.do';
	
		$('#t1').datagrid({
			view: detailview,
			detailFormatter:function(index,row){
				return '<div id="ddv-' + index + '" style="padding:5px 0"></div>';
			},
			onExpandRow: function(index,row){
				$('#ddv-'+index).panel({
					border:false,
					cache:false,
					href:'${ctx }/appDetail/smallHtml.do',
					onLoad:function(){
						$('#t1').datagrid('fixDetailRowHeight',index);
					}
				});
				$('#t1').datagrid('fixDetailRowHeight',index);
			}
		});
		
		searchAccount();
	});

	//获取参数       
	function getQueryParams(queryParams) {  
	//	    var StartTime = $("#s_startTime").datebox("getValue");  
	//	    var EndTime = $("#s_endTime").datebox("getValue");               
	//	    var Name = document.getElementById("s_name").value;  
	    var State = $("#s_state").val();  
	 
	//	    queryParams.StartTime = StartTime;  
	//	    queryParams.EndTime = EndTime;  
	//	    queryParams.sysAccName = Name;  
	    queryParams.pAppPlugintype = State;  
	 
	    return queryParams;  
	  
	}  

	function searchAccount(){
		 //查询参数直接添加在queryParams中      
        var queryParams = grid.datagrid('options').queryParams;  
        queryParams.rows = grid.datagrid('options').pageSize;
        queryParams.page = 0;
        queryParams = getQueryParams(queryParams);  
        grid.datagrid('options').queryParams = queryParams;  
        grid.datagrid('options').url = '${ctx }/application/getApplicationlists.do';
        grid.datagrid('reload'); 
	}

	function parseText(oldOne)
	{
		return oldOne.replace(new RegExp(/(')/g),"&#39;").replace(new RegExp(/(<)/g),"&#60;").replace(new RegExp(/(>)/g),"&#62;").replace(new RegExp(/(-)/g),"&#45;").replace(new RegExp(/(\^)/g),"&#94;");
	}
	
	function reparse(oldOne)
	{
		return oldOne.replace(new RegExp(/(&#39;)/g),"'").replace(new RegExp(/(&#60;)/g),"<").replace(new RegExp(/(&#62;)/g),">").replace(new RegExp(/(&#45;)/g),"-").replace(new RegExp(/(&#94;)/g),"^");
	}
	
	function checkIMGUpdate()
	{
		
		var logo = $('#app_logo').val();
		
		var app_name = $('#app_name').val();

		if(app_name !='com.cmri.rcs.plugincenter' && logo== '')
		{
			$.messager.alert("操作提示", "插件图标不可以为空！","info");
			return true;
		}

		if($("#hdone").val() != '' || $("#hdtwo").val() != '' || $("#hdthree").val() != '')
		{
			return false;
		}
		else
		{
			$.messager.alert("操作提示", "插件缩略图不可全为空！","info");
			return true;
		}
	}
	
	//验证是否为图片文件
	function checkFileImage(ext){  
	    return ext.match(/.jpg|.png/i);  
	} 
	
	//显示删除框
	function onremove(obj){
		$(obj).parent().find("div").show();
		$(obj).parent().find("div").css("z-index","1");
	}

	//隐藏栓出框
	function onremoveout(obj){
		$(obj).parent().find("div").css("z-index","9999");
		$(obj).parent().find("div").hide();
	}
	
	//删除上传缩略图
	function removeupload(obj){
		
		//图标的id
		var oldFormId = $(obj).attr("id");
		
		//表格对象
		var tdObj=$(obj).parent().parent();
		//将图片上传框重新填回
		
		tdObj.html(imgform);
		tdObj.find("form").attr("id",oldFormId);
		if(oldFormId == "apk_first_pic" || oldFormId == "html_first_pic")
		{
			$("#hdone").val("");
		}
		else if(oldFormId == "apk_second_pic" || oldFormId == "html_second_pic")
		{
			$("#hdtwo").val("");
		}
		else if(oldFormId == "apk_third_pic" || oldFormId == "html_third_pic")
		{
			$("#hdthree").val("");
		}
		
	}

	
	//upload pictue for apk and html
	function upload_pic(fileInput){
		var filePath = fileInput.value;  
	    var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();  
	    
	    //表单对象
	    var formObj = $(fileInput).parent().parent();
	    
	    //表单外面td对象
	    var tdObj = formObj.parent();
	    var formID = formObj.attr("id");
	    
	    if (!checkFileImage(fileExt)) {
	       $.messager.alert("提示", "请上传 jpg、png 格式文件！");
	       $(fileInput).parent().html(pichtml);
	       //cleanApkform();
	       return;
	    }
	   
	    formObj.form("submit", {
			onSubmit:function(){
				return true;
			},
			success:function(data){ 
				var json=$.parseJSON(data); 
				if(json.code == 500){
					if(formID == "apk_first_pic" ||formID == "html_first_pic" )
					{
						$("#hdone").val(json.info);
					}
					else if(formID == "apk_second_pic" || formID == "html_second_pic")
					{
						$("#hdtwo").val(json.info);
					}
					else if(formID == "apk_third_pic" || formID == "html_third_pic")
					{
						$("#hdthree").val(json.info);
					}
					tdObj.html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' src='${ctx}/upload/"+json.info+"'  onMouseOver='onremove(this)'   />"+
					"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
					"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='"+formID+"'></div></div>");
				}else{
					$.messager.alert('提示',json.info,'info');
					$(fileInput).parent().html(pichtml);
				}
			}
	    });
	}
	
	
	function addHtml(){
		$('#d1').window('open');
		$('#f1').form('reset');
		$('#f1').form.url='${ctx }/application/insert.do';
	}

	function editHtml(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				$('#f1').form.url='${ctx }/application/update.do';
				$('#f1').form('load',{	
					'pAppId':row.pAppId,
					'pAppPluginname':row.pAppPluginname,
					'pAppPlugintype':row.pAppPlugintype,
					'pAppRemark':row.pAppRemark,
					'pAppDisplaysort':row.pAppDisplaysort
				});
					$('#pAppPluginname').val(row.pAppPluginname);
					$('#pAppPlugintype').val(row.pAppPlugintype);
					$('#pAppDisplaysort').val(row.pAppDisplaysort);
					$('#pAppRemark').val(row.pAppRemark);
					$('#d1').dialog('open');
			}else{
				msgShow('错误','请选择一条要修改的记录！','error');
				grid.datagrid('clearSelections');
			}
		}else{
			msgShow('错误','请选择要修改的记录！','error');
		}
	}
	
	function deleteHtml(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.pAppId+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
					if (r){
							$.post('${ctx }/application/applicationDelete.do',{'ids': s},function(data){
									if(data.success){
										msgShow('成功',data.message,'info');
										grid.datagrid('reload');
										grid.datagrid('clearSelections');
									}
							},'json');
					}
			});
		}else{
				msgShow('错误','请选择要删除的记录！','error');
		}
	}
	
	function convertState(val, rec, index) {
       if(val == '1'){
			return '<span style="color: green">HTML</span>';
		}else{ 
			return '<span></span>';
		}
    }
	
	function openhtml(id, name){
		addopenhtml();
		$("#html_appname").html(name);
		$("#app_name").val(name);
		$("#app_apkactionid").val(id);
		$("#html").dialog("setTitle","上传插件新版本");
	}

	function ys1(val, rec, index) {
		var returnvalue="<img  src='${ctx}/image/table_td_button/check.png' onclick='openhtml(" + rec.pAppId + ",\"" + rec.pAppPluginname + "\")' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='openhtml(" + rec.pAppId + ",\"" + rec.pAppPluginname + "\")'>添加</a>&nbsp;&nbsp;";
// 		if((rec.pAppdetailAuditstate-0)<=2){
// 			returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/update.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='updatechild(\""+rec.pAppId+"\",\""+rec.pAppdetailActionname+"\",\""+rec.pAppdetailPlugintype+"\")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='updatechild(\""+rec.pAppId+"\",\""+rec.pAppdetailActionname+"\",\""+rec.pAppdetailPlugintype+"\")'>修改</a>&nbsp;&nbsp;";								
// 		}else if((rec.pAppdetailAuditstate-0)==3){
// 			returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/shutdown.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='stopSafa(4,"+rec.pAppId+")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='stopSafa(4,"+rec.pAppId+")'>停用</a>&nbsp;&nbsp;";								
// 		}else if((rec.pAppdetailAuditstate-0)==4){
// 			returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/startup.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='stopSafa(3,"+rec.pAppId+")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='stopSafa(3,"+rec.pAppId+")'>启用</a>&nbsp;&nbsp;";
// 		}
// 		if((rec.pAppdetailAuditstate-0)<3){
// 			returnvalue=returnvalue+"<img src='${ctx}/image/table_td_button/delete.png' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;' onclick='deleteb("+rec.pAppId+","+rec.pAppdetailApkactionid+")'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='deleteb("+rec.pAppId+","+rec.pAppdetailApkactionid+")'>删除</a>&nbsp;&nbsp;";
// 		}
		return returnvalue;
   }
	
	function  saveForm(){   					
			$('#f1').form('submit',{
	   					url:$('#f1').form.url,
	   					success:function(data){
	   						data=parseJSON(data);
		   					if(data.success){
		   						msgShow('成功',data.message,'info');
		   						$('#d1').dialog('close');
		   						grid.datagrid('load');
		   						grid.datagrid('clearSelections');
		   					}
 					}
   				});
		
	}
	
	function clearForm(){
			$('#d1').dialog('close');
	}
</script>
</html>