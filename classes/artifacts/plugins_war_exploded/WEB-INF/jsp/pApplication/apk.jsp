<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <style type="text/css">
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
.messager-icon
{
	height:55px;
}
</style>   
<div id="apk" >
<!-- 插件名称 -->
	<div class="line">
		<div class="tbtitle" >插件名称：</div>
		<div class="tbvalue">
			<span class="pn-fhelp" id="apk_appname">&nbsp;</span>
			<span class="pn-frequired" style="color:red;" id="name_check"></span>
		</div>
	</div>
	<div id="apkdiv" >
		<div class="line">
			<div class="tbtitle">上传apk：</div>
			<div class="tbvalue">
				<form id="upAppForm" name="upAppForm" style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/uploadApkFile.do" method="post" enctype="multipart/form-data" >
					<div id="addfile" style="overflow: hidden; position: relative; direction: ltr;" class="btn btn-primary">
						<input type="file" class="transparent_class" id="uploadify" name="upload_one_file" onchange="getFileSize(this)"><span id="upImg">&nbsp;上&nbsp;&nbsp;&nbsp;传&nbsp;</span>
					</div>
					<span id="appwenjian" ></span>
					<input type="hidden" id="updatefile_apkid" name="pAppdetailApkactionid">
					<input type="hidden" id="updatefile_corid" name="pAppdetailId">
				</form>
			</div>
		</div>
	</div>
	<br/>
	<div class="line" style="height:62px;line-height: 62px;" id="apk_logo_div">
		<div class="tbtitle">插件图标：</div>
		<div class="tbvalue">
			<div id="apk_icon">
				<form id="apk_icon_form" name="apk_icon_form" style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/updateLogo.do" method="post" enctype="multipart/form-data" >
					<div class="up_div_icon" >
						<input type="file" name="upload_one_file" id="update_icon" class="transparent_class" style="width:100%;height:100%" onchange="upload_Icon(this)" disabled="disabled">
					</div>
				</form>
			</div>
		</div>
		<div class="tbvalue" style='left:200px' id="deleicon"></div>
	</div>
	
	
	<div id="apk_viewinfomation" >
		<div class="line">
			<div class="tbtitle">插件版本：</div>
			<div class="tbvalue">
				<span class="pn-fhelp" id="apk_versionname">&nbsp;</span>
				<span class="pn-frequired" style="color:red;" id="version_check"></span>
			</div>
		</div>
		<div class="line">
			<div class="tbtitle">Action Name：</div>
			<div class="tbvalue">
				<span class="pn-fhelp" id="apk_action_name">&nbsp;</span>
				<span class="pn-frequired" style="color:red;" id="action_check"></span>
			</div>
		</div>
		<div class="line">
			<div class="tbtitle">插件包名：</div>
			<div class="tbvalue">
				<span class="pn-fhelp" id="apk_package_name">&nbsp;</span>
				<span class="pn-frequired" style="color:red;" id="package_check"></span>
			</div>
		</div>
		<div class="line">
			<div class="tbtitle">插件签名：</div>
			<div class="tbvalue">
				<span class="pn-fhelp" id="apk_cert_digest">&nbsp;</span>
				<span class="pn-frequired" style="color:red;" id="digest_check"></span>
			</div>
		</div>
	</div>
	
<!-- 	<div class="line terminal_checkbox" > -->
<!-- 		<div class="tbtitle">终端型号：</div> -->
<!-- 		<div class="tbvalue"> -->
<!-- 			<input type="checkbox" id="checkall" onclick="checkall(this)" style="margin:0px;"/>所有终端 -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="terminal_checkbox" id="checkboxheight" style="line-height:30px;margin-left:20px;margin-top:15px;position: relative;"> -->
<!-- 		<div class="tbtitle"></div> -->
<!-- 		<div style="margin-left:120px;position: relative; word-break:break-all ;margin-right: 20px;" style="line-height: 30px;" id="checkbox"> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="line terminal_radio" style="display:none"> -->
<!-- 		<div class="tbtitle">插件中心类型：</div> -->
<!-- 		<div class="tbvalue radio_parent"> -->
			
<!-- 		</div> -->
<!-- 	</div> -->
	
	<div class="line" >
		<div class="tbtitle">插件缩略图：</div>
		<div class="tbvalue">
			图片等于480*800  支持PNG/JPG
		</div>
	</div>
	<div style="margin-left:140px;" id="apk_up_img">
		<table>
			<tr>
				<td width="130px;" style="position: relative;" id="upload_One_form_td">
					<form id="apk_first_pic"  style="padding:0px;margin:0px;position: relative;" action="${pageContext.request.contextPath}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >
						<div class="up_div" >
							<input type="file" name="upload_one_file" class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">
						</div>
					</form>
				</td>
				<td width="130px;" style="position: relative;" id="upload_Two_form_td">
					<form id="apk_second_pic"  style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >
						<div class="up_div" >
							<input type="file" name="upload_one_file" class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">
						</div>
					</form>
				</td>
				<td width="130px;" style="position: relative;" id="upload_Three_form_td">
					<form id="apk_third_pic"  style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >
						<div class="up_div" >
							<input type="file"  name="upload_one_file" class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">
						</div>
					</form>
				</td>
			</tr>
		</table>
		
	</div>
	
	<div class="line">
		<div class="tbtitle">插件描述：</div>
		<div class="tbvalue">
			<textarea style="width:600px;height:80px;margin-bottom: 15px" id="apk_app_desc"></textarea>
		</div>
	</div>
	
	<div class="line" style="margin-top: 70px;">
		<div class="tbtitle">更新内容：</div>
		<div class="tbvalue">
			<textarea style="width:600px;height:80px;margin-bottom: 15px" id="apk_changelog"  ></textarea>
		</div>
	</div>
	
</div>
<script type="text/javascript">
	var ctx = 'http://114.55.150.199:8888/download/pic/';
var operating='';
//滚动条的高度
var height;
var pichtml='<input type="file"  name="upload_one_file"  class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">';

//滚动条跳到顶部
function myScollTop()
{
	height = $(document).scrollTop(); 
	//$("#apk").panel("move",{top:100});
	//window.scrollTo(0,0);
	$(".panel-body div").scrollTop(0);
}

//缩略图上传的html文本
var picHtml = $("#upload_One_form_td").html();

//上传logo图片
var logoHtml = $("#apk_icon").html();

var threePicHtml = $("#apk_up_img").html();

var addfile=$("#addfile").html();
		//加载新增对话框
$('#apk').show().dialog({
		modal : true,
		title : '新插件',
		closable: true,
		collapsible:false,
		maximizable:false,
		draggable: true,
		//fit:true,
		height:document.body.clientHeight-20,
		//width:document.body.clientWidth-30, 
		width: 850,
		onClose:function(){
			$("#apk").dialog("move",{top:20});
		},
		onOpen: function() {
			myScollTop();
			$("#apk").dialog("move",{top:height+20});

			$(".window-mask").height($("#box").height()+150);
			$(".window-mask").width($("#foot_btn").width());
		},
		buttons:[{
			id:'add_apk_save',
			text:'确认添加',
			//iconCls:'icon-add',
			handler:function(){
				saveApk();
			}
		},{
			id:'add_apk_ref',
			text:'取   消',
			//iconCls:'icon-add',
			handler:function(){
				 refApk();
			}
		}],
		onMove:function(left,top)
		{
			if(top < 0)
			{
				top =0;
				setTimeout("reMove('apk',"+top+","+left+")",100); 
			}
		}
	}).dialog('close');

//清楚输入框
function cleanApkform(){
	$("#form").form("clear");
	$("#checkall").attr("checked",false);
	$("#apk_appname").html("&nbsp;");
	$("#name_check").html("");
	$("#addfile").html("<input type='file' class='transparent_class' id='uploadify' name='upload_one_file' onchange='getFileSize(this)'><span id='upImg'>&nbsp;上&nbsp;&nbsp;&nbsp;传&nbsp;</span>");
    $("#appwenjian").empty();
	$("#updatefile_apkid").val('');
	$("#updatefile_corid").val('');
	$("#apk_icon").html(logoHtml);
	
	$("#apk_versionname").html("&nbsp;");
	$("#version_check").html("");
	$("#apk_action_name").html("&nbsp;");
	$("#action_check").html("");
	
	$("#apk_package_name").html("&nbsp;");
	$("#package_check").html("");
	$("#apk_cert_digest").html("&nbsp;");
	$("#digest_check").html("");
	
	$("#apk_app_desc").val('');
	$("#apk_changelog").val('');
	$("#apk_up_img").html(threePicHtml);
// 	$(".terminal_radio").hide();
// 	$(".terminal_checkbox").show();
// 	$("input[name='center_info']:checked").attr("checked",false);
}

//新增一个apk,打开窗口
function addopenapk(){

	
//	checkLogin();
	operating="add";
	cleanApkform();
	$('#apk').show().dialog("open");
	$("#apk").dialog("setTitle","新插件");
	$("#plugintype").val('2');
	$("#app_platform").val('Android');
	$("#add_apk_save").find("span").find("span").html("确认添加");
}

//查看图标
function upload_Icon(fileInput){
	var filePath = fileInput.value;  
    var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();  
    if (!checkFileImage(fileExt)) {
       $.messager.alert("操作提示","请上传 jpg、png格式文件！");
       $("#html_icon_form").html(html_icon_form);
      // $(fileInput).empty();
       return;
    }
   
    $('#html_icon_form').form("submit", {
		onSubmit:function(){
			return true;
		},
		success:function(data){ 
			var json=$.parseJSON(data); 
			if(json.code == 500){
				$("#app_logo").val(json.info);
				$("#html_icon_form").html("<img style='width:72px;height:72px;display:block;z-index:10' src='${ctx}/upload/"+json.info+"'/>" );
				$("#html_deleicon").html("<a href='javascript:void(0) style='color:red; onclick='deleteicon(this)'>删除</a>");
			}else{
				$("#html_icon_form").html(html_icon_form);
				$.messager.alert('提示',json.info,'info');
			}
		}
    });
}

//伤处logo
function deleteicon(obj){
	$("#html_logo_div").html(html_logo_div);
	$("#app_logo").val('');
	$(obj).remove();
}

//--------------
//上传组件事件
function getFileSize(fileInput) {
	var filePath = fileInput.value;  
    var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();  
    
    if (!checkFileExt(fileExt)) {
    	$.messager.alert("操作提示","请检查文件类型，上传apk类型文件！","info");
    	$("#addfile").html(addfile);
      	 cleanApkform();
       return;
    }
   
    $('#appwenjian').html("&nbsp;&nbsp;&nbsp;&nbsp;     <font color='blue'>正在上传请稍候...</font>");
    $('#upAppForm').form("submit", {
		onSubmit:function(){
			return true;
		},
		success:function(data){ 
			console.info(data);
			var infos=$.parseJSON(data); 
			var datas = infos.appNode;
			//501代表程序apk包校验有错误
        	if(infos.code == 501){
				$('#addfile').html("<input type='file' class='transparent_class' id='uploadify' name='upload_one_file' onchange='getFileSize(this)'><span id='upImg'>&nbsp;上&nbsp;&nbsp;&nbsp;传&nbsp;</span>");
        		$('#appwenjian').empty();
        		$('#uploadify').val("");
        		$.messager.alert("操作提示", infos.info,"info");
        	}else{        		
        		$('#addfile').html("<input type='file' class='transparent_class' id='uploadify' name='upload_one_file' onchange='getFileSize(this)'><span id='upImg'>&nbsp;修&nbsp;&nbsp;&nbsp;改&nbsp;</span>");
        		
        		$('#appwenjian').html("  &nbsp;&nbsp;&nbsp;&nbsp;  "+datas.app_apk+" - <font color='green'>上传成功！</font>");
        		
        		//设置隐藏域
        		$('#app_name').val(datas.app_name);
        		$('#app_versionname').val(datas.app_versionname);
        		$('#app_size').val(datas.app_size);
        		$('#app_package_name').val(datas.app_package_name);
        		$('#app_action_name').val(datas.app_action_name);
        		$('#app_cert_digest').val(datas.app_cert_digest);
        		$('#app_logo').val(datas.app_logo);
        		$('#app_version').val(datas.app_version);
        		$('#app_apk').val(datas.app_apk);
        		$('#app_md5').val(datas.app_md5);
        		$('#app_platform_version').val(datas.app_platform_version);
        		
        		//设置页面提示
        		$('#apk_appname').html(datas.app_name);
        		$('#apk_versionname').html(datas.app_versionname);
        		$('#apk_package_name').html(datas.app_package_name);
        		$("#apk_action_name").html(datas.app_action_name);
        		$('#apk_cert_digest').html(datas.app_cert_digest);
        	    $("#apk_icon").html("<img style='width:72px;height:72px;display:block;z-index:10' src='"+ ctx +datas.app_logo+"'/>" );
        		
        		$('#version_check').html(datas.version_check);
        		$('#digest_check').html(datas.digest_check);
        		$('#package_check').html(datas.package_check);
        		$('#action_check').html(datas.action_check);
        		$('#name_check').html(datas.name_check);
        		
        		
        	}
		}
    });
}

function refApk(){
	$('#apk').show().dialog("close");
}

//保存apk型插件
function saveApk(){

			var app_name=$('#app_name').val();
			var app_versionname=$('#app_versionname').val();
			var app_size=$('#app_size').val();
			var app_package_name=$('#app_package_name').val();
			var app_cert_digest=$('#app_cert_digest').val();
			if(!app_name||!app_versionname||!app_size||!app_package_name||!app_cert_digest){
				$.messager.alert("操作提示","完整上传APP类型插件文件后，方可保存！","info");
				return;
			} 
			var version_check=$.trim($("#version_check").html());
			var digest_check=$.trim($("#digest_check").html());
			var package_check=$.trim($("#package_check").html());
			var action_check=$.trim($("#action_check").html());
			var name_check=$.trim($("#name_check").html());
			
			if(name_check!=""){
				if(operating=="add")
				{
					$.messager.alert("操作提示","该类型插件已经存在，请在  "+app_name+" 中上传！","info");
				}
				else
				{
					$.messager.alert("操作提示",name_check+"，不能发布！","info");
				}
				return;
			}
			
			//版本校验
			if(version_check!=""){
				$.messager.alert("操作提示",version_check+"，不能发布！","info");
				return;
			}
			
			//数字签名不一致
			if(digest_check!=""){
				$.messager.alert("操作提示",digest_check+"，不能发布！","info");
				return;
			}
			
			//包名校验
			if(package_check!=""){
				$.messager.alert("操作提示",package_check+"，不能发布！","info");
				return;
			} 

		if(checkIMGUpdate())
		{
			return;
		}
// 	$("#app_platform").val($("#demo_app_platform").val());
	var dappdesc=$.trim($("#apk_app_desc").val());
	
	if(dappdesc.length == 0){
		$.messager.alert("操作提示","插件描述不可以为空！","info");
		return;
	}
	else if(dappdesc.length >1000)
	{
		$.messager.alert("操作提示","插件描述不能超过1000个字符！","info");
		return;
	}
	
	$("#app_desc").val(parseText(dappdesc));
	

	var changelog=$.trim($("#apk_changelog").val());
	
	if(changelog.length == 0){
		$.messager.alert("操作提示","更新内容不可以为空！","info");
		return;
	}
	else if(changelog.length >1000)
	{
		$.messager.alert("操作提示","更新内容不能超过1000个字符！","info");
		return;
	}
	
	$("#changelog").val(parseText(changelog));
	
	
	$('#form').form("submit", {
		onSubmit:function(){
			$('#add_apk_save').linkbutton('disable');
			return true;
		},
		success:function(json){
			var data = $.parseJSON(json); 
			$('#add_apk_save').linkbutton('enable');
			if(data.code== 501){
				$.messager.alert("提示",data.info,"info");
			}
			else if(data.code == 503)
			{
				$.messager.alert("提示",data.info);
			}
			else if(data.code== 500){
// 				getData();
				$.messager.alert("提示", data.info);
				$('#apk').show().dialog("close");
				grid.datagrid('load');
				grid.datagrid('clearSelections');
			}
		}
	});
}

function updatechild(id,actionname,plugintype){


	
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/appDetail/getById.do",
		data:{"pAppdetailId":id},
		dataType:"json",
		success:function(datas){
        		addopenapk();
        		//设置页面提示
        		$('#apk_appname').html(datas.pAppdetailName);
        		$('#apk_versionname').html(datas.pAppdetailVersionname);
        		$('#apk_package_name').html(datas.pAppdetailPackagename);
        		$("#apk_action_name").html(datas.pAppdetailActionname);
        		$('#apk_cert_digest').html(datas.pAppdetailCertdigest);
        	    $("#apk_icon").html("<img style='width:72px;height:72px;display:block;z-index:10' src='"+ ctx +datas.pAppdetailLogo+"'/>" );
        	    
				$("#apk_app_desc").val(reparse(datas.pAppdetailDesc));
				$("#apk_changelog").val(reparse(datas.pAppdetailChangelog));
				
				$("#updatefile_corid").val(datas.pAppdetailId);
				$("#updatefile_apkid").val(datas.pAppdetailApkactionid);
				
				if(datas.pAppdetailDescpic1!=""){
    				$("#hdone").val(datas.pAppdetailDescpic1);
			$("#apk_first_pic").parent().html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' src='"+ ctx +datas.pAppdetailDescpic1+"'  onMouseOver='onremove(this)'   />"+
			"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
			"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='apk_first_pic'></div></div>");
			}
		
			if(datas.pAppdetailDescpic2!=""){
				$("#hdtwo").val(datas.pAppdetailDescpic2);
				$("#apk_second_pic").parent().html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' ssrc='"+ ctx +datas.pAppdetailDescpic2+"'  onMouseOver='onremove(this)'   />"+
			"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
			"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='apk_second_pic'></div></div>");
				}
		
			if(datas.pAppdetailDescpic3!=""){
				$("#hdthree").val(datas.pAppdetailDescpic3);
				$("#apk_third_pic").parent().html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' src='"+ ctx +datas.pAppdetailDescpic3+"'  onMouseOver='onremove(this)'   />"+
			"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
			"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='apk_third_pic'></div></div>");
				}
				
				$("#add_apk_save").find("span").find("span").html("保 存");
				
				$("#apk").dialog("setTitle","修改插件");
				
				operating="update";
				//设置隐藏域
        		$('#app_name').val(datas.pAppdetailName);
        		$('#app_versionname').val(datas.pAppdetailVersionname);
        		$('#app_size').val(datas.pAppdetailSize);
        		$('#app_package_name').val(datas.pAppdetailPackagename);
        		$('#app_action_name').val(datas.pAppdetailActionname);
        		$('#app_cert_digest').val(datas.pAppdetailCertdigest);
        		$('#app_logo').val(datas.pAppdetailLogo);
        		$('#app_version').val(datas.pAppdetailVersion);
        		$('#app_apk').val(datas.pAppdetailApk);
        		$('#app_md5').val(datas.pAppdetailMd5);
        		$('#app_platform_version').val(datas.pAppdetailPlatformversion);
        		$("#hdone").val(datas.pAppdetailDescpic1);
        		$("#hdtwo").val(datas.pAppdetailDescpic2);
        		$("#hdthree").val(datas.pAppdetailDescpic3);
        		$("#app_desc").val(datas.pAppdetailDesc);
        		$("#changelog").val(datas.pAppdetailChangelog);
				$("#app_apkactionid").val(datas.pAppdetailApkactionid);
				$("#app_id").val(datas.pAppdetailId);
				$("#app_url").val(datas.pAppdetailUrl);
				
		}
	});
}

</script>