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
.pn-tishi{
	color:#C0BFBF;
}
</style>
<div id="html" >
	<div class="line2" style="height: 20px">
		<div class="tbtitle2">插件名称：</div>
		<div class="tbvalue2" id="html_appname">
			<input type="text"  id="html_appname_input"  name="cor.app_name" class="span5"  maxlength="60" />
		</div>
	</div>
	
	<div id="html_version" class="hid_html">
		<div class="line">
			<div class="tbtitle2">插件版本：</div>
			<div class="tbvalue2">
				<input type="text"  id="html_app_version"  class="span5" maxlength="8"/>
			</div>
		</div>
	</div>
	
	<div class="line" style="height:62px;line-height: 62px;" id="html_logo_div">
		<div class="tbtitle">插件图标：</div>
		<div class="tbvalue">
			<div id="html_icon">
				<form id="html_icon_form" name="html_icon_form" style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/updateLogo.do" method="post" enctype="multipart/form-data" >
					<div class="up_div_icon" >
						<input type="file" name="upload_one_file" id="upload_one_file" class="transparent_class" style="width:100%;height:100%" onchange="upload_Icon(this)">
					</div>
				</form>
			</div>
		</div>
		<div class="tbvalue" style='left:200px' id="html_deleicon"></div>
	</div>
	
	<div id="htmldiv" class="hid_html">
		<div class="line">
			<div class="tbtitle">插件地址：</div>
			<div class="tbvalue">
				<input type="text"  id="html_app_url"  class="span5" maxlength="250" placeholder="http://"/>
			</div>
		</div>
	</div>
	
	<div class="line">
		<div class="tbtitle">插件略缩图：</div>
		<div class="tbvalue">
			图片等于480*800  支持PNG/JPG
		</div>
	</div>
	<div style="margin-left:140px;" id="html_up_img">
		<table>
			<tr>
				<td width="130px;" style="position: relative;" id="upload_One_form_td">
					<form id="html_first_pic" name="upload_One_form" style="padding:0px;margin:0px;position: relative;" action="${pageContext.request.contextPath}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >
						<div class="up_div" >
							<input type="file" name="upload_one_file"  class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">
						</div>
					</form>
				</td>
				<td width="130px;" style="position: relative;" id="upload_Two_form_td">
					<form id="html_second_pic" name="upload_Two_form" style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >
						<div class="up_div" >
							<input type="file" name="upload_one_file"  class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">
						</div>
					</form>
				</td>
				<td width="130px;" style="position: relative;" id="upload_Three_form_td">
					<form id="html_third_pic" name="upload_Three_form" style="padding:0px;margin:0px;" action="${pageContext.request.contextPath}/imagefile/updateThumbnail.do" method="post" enctype="multipart/form-data" >
						<div class="up_div" >
							<input type="file"  name="upload_one_file"  class="transparent_class" style="width:100%;height:100%" onchange="upload_pic(this)">
						</div>
					</form>
				</td>
			</tr>
		</table>
		
	</div>
	
	<div class="line">
		<div class="tbtitle">插件描述：</div>
		<div class="tbvalue">
			<textarea style="width:600px;height:80px;margin-bottom: 15px" id="html_app_desc"  ></textarea>
		</div>
	</div>
	
	<div class="line" style="margin-top: 70px;">
		<div class="tbtitle">更新内容：</div>
		<div class="tbvalue">
			<textarea style="width:600px;height:80px;margin-bottom: 15px" id="html_changelog"  ></textarea>
		</div>
	</div>
</div>
<script type="text/javascript">
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
//logo选择框
var html_logo_div = $("#html_logo_div").html();

var html_icon_form=$("#html_icon_form").html();

//缩略图选择框
var html_up_img = $("#html_up_img").html();
$('#html').show().dialog({
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
			$("#html").dialog("move",{top:20});

		},
		onOpen: function() {
			myScollTop();
			$("#html").dialog("move",{top:height+20});

			$(".window-mask").height($("#box").height()+150);
			$(".window-mask").width($("#foot_btn").width());
		},
		buttons:[{
			id:'add_html_save',
			text:'确认添加',
			//iconCls:'icon-add',
			handler:function(){
				saveHtml();
			}
		},{
			id:'add_html_ref',
			text:'取    消',
			//iconCls:'icon-add',
			handler:function(){
				refHtml();
			}
		}],
		onMove:function(left,top)
		{
			if(top < 0)
			{
				top =0;
				setTimeout("reMove('html',"+top+","+left+")",100); 
			}
		}
	}).dialog('close');

	//打开页面
function addopenhtml(){

	//checkLogin();
	operating="add";
	
	cleanHtmlForm();
	//1--->html  2---->apk
	$("#plugintype").val("1");

	$("#html").show().dialog("open");
	$("#html").dialog("setTitle","新插件");
	$("#add_html_save").find("span").find("span").html("确认添加");
}

//清楚表格
function cleanHtmlForm()
{
	$("#form").form("clear");
	$("#html_appname").html("<input type=\"text\"  id=\"html_appname_input\"  name=\"cor.app_name\" class=\"span5\"   maxlength='60'/>");
	$("#html_app_version").val('');
	
	
	$('#html_app_url').val('http://');
	$("#html_logo_div").html(html_logo_div);
	$("#html_up_img").html(html_up_img);	
	$("#html_app_desc").val('');
	$("#html_changelog").val('');
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
function refHtml(){
	$('#html').show().dialog("close");
}

function saveHtml()
{
	var apkapp_name=$('#html_appname_input').val();
		if(apkapp_name!=undefined){
			if($.trim(apkapp_name).length==0){
				$.messager.alert("操作提示", "插件名称不可以为空！","info");
				return;
			}
			else if( countStringByte($.trim(apkapp_name)) > 18 )
			{
			     $.messager.alert("操作提示", "插件名称不能超过18个字符！","info");
                return;
			}
			if(operating=="add"){
					var flg=false;
					$.ajax({
						type:"POST",
						url:"${pageContext.request.contextPath}/checkHtmlName",
						data:"cor.pluginname="+$.trim(apkapp_name),
						dataType:"json",
						async:false,
						success:function(data){
							if(data.code == 501){
								flg=true;
							}
						}
					});
					if(flg){
						$.messager.alert("操作提示","该类型插件已经存在，请在  "+apkapp_name+" 中添加！","info");
						return;
					}
			}
			$('#app_name').val(apkapp_name);
		}
		
	 //验证插件版本
		var demo_app_version=$("#html_app_version").val();
		if($.trim(demo_app_version).length==0){
			$.messager.alert("操作提示", "版本号不可以为空！","info");
			return;
		}
		
	    var strP=/^\d+$/;
	  
	    var isnum=demo_app_version.match(new RegExp(strP)); 
	    if(isnum==null || demo_app_version <=0){
		   $.messager.alert("操作提示", "版本号必须为正整数！","info");
		   return;
	    }
		
		
		//验证插件地址
		var app_url=$.trim($("#html_app_url").val());
		
		if(app_url==""){
			$.messager.alert("操作提示", "插件地址不可以为空！");
			return;
		}
		else if(app_url.toLowerCase().indexOf('http://') != 0 )
		{
			$.messager.alert("操作提示", "插件地址必须以http://开头！");
			return;
		}
		else if(app_url.length > 250)
		{
			$.messager.alert("操作提示", "插件地址不能超过250个字符！");
			return;
		}
		
		if(checkIMGUpdate())
		{
			return;
		}
		
		var dappdesc=$.trim($("#html_app_desc").val());
	
		if(dappdesc.length == 0){
			$.messager.alert("操作提示","插件描述不可以为空！");
			return;
		}
		else if(dappdesc.length >1000)
		{
			$.messager.alert("操作提示","插件描述不能超过1000个字符！");
			return;
		}
		$("#app_desc").val(parseText(dappdesc));
		
		
		var changelog = $.trim($("#html_changelog").val());

		if (changelog.length == 0) {
			$.messager.alert("操作提示", "更新内容不可以为空！", "info");
			return;
		} else if (changelog.length > 1000) {
			$.messager.alert("操作提示", "更新内容不能超过1000个字符！", "info");
			return;
		}

		$("#changelog").val(parseText(changelog));

		
		$("#app_versionname").val(demo_app_version);
		$("#app_version").val(demo_app_version);
		$("#app_url").val(app_url);

		$('#form').form("submit", {
			onSubmit : function() {
				$('#add_html_save').linkbutton('disable');
				return true;
			},
			success : function(json) {
				var data = $.parseJSON(json);
				$('#add_html_save').linkbutton('enable');
				if (data.code == 501) {
					$.messager.alert("操作提示", data.info);
				} else if (data.code == 503) {
					$.messager.alert("提示", data.info);
				} else if (data.code == 500) {
					//getData();
					$.messager.alert("提示", data.info);
					$('#html').show().dialog("close");
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
				
        			addopenhtml();
        			
        			$("#html_appname").html(datas.pAppdetailName);
        			$("#html_app_url").val(datas.pAppdetailUrl);
        			$("#html_app_desc").val(reparse(datas.pAppdetailDesc));
        			$("#html_changelog").val(reparse(datas.pAppdetailChangelog));
        			$("#html_app_version").val(datas.pAppdetailVersion);
        			
        			if(datas.pAppdetailLogo != "")
        			{
        			 	$("#html_icon").html("<img style='width:72px;height:72px;display:block;z-index:10' src='${pageContext.request.contextPath}/upload/"+datas.pAppdetailLogo+"'/>" );
        			 	$("#html_deleicon").html("<a href='javascript:void(0) style='color:red; onclick='deleteicon(this)'>删除</a>");
        			}
        			
        			if(datas.pAppdetailDescpic1!=""){
        				$("#hdone").val(datas.pAppdetailDescpic1);
				$("#html_first_pic").parent().html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' src='${pageContext.request.contextPath}/upload/"+datas.pAppdetailDescpic1+"'  onMouseOver='onremove(this)'   />"+
				"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
				"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='html_first_pic'></div></div>");
				}
			
				if(datas.pAppdetailDescpic2!=""){
					$("#hdtwo").val(datas.pAppdetailDescpic2);
					$("#html_second_pic").parent().html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' src='${pageContext.request.contextPath}/upload/"+datas.pAppdetailDescpic2+"'  onMouseOver='onremove(this)'   />"+
				"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
				"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='html_second_pic'></div></div>");
					}
			
				if(datas.pAppdetailDescpic3!=""){
					$("#hdthree").val(datas.pAppdetailDescpic3);
					$("#html_third_pic").parent().html("<img style='width:107px;height:175px;display:block;z-index:10;float:left;' src='${pageContext.request.contextPath}/upload/"+datas.pAppdetailDescpic3+"'  onMouseOver='onremove(this)'   />"+
				"<div style='width:104px;height:170px;border:2px solid red;position: relative;display:none;' onMouseOut='onremoveout(this)'>"+
				"<div style='position:relative;left:86px;top:150px;width:20px;height:20px;background:url(../image/red.png)' onclick='removeupload(this)' id='html_third_pic'></div></div>");
					}
					
					$("#html").dialog("setTitle","修改插件");
					$("#add_html_save").find("span").find("span").html("保 存");
				
				
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