function doInsert(){
	art.dialog.open(actionPath+'/insertSync.jsp', {
	    title : '新建同步配置',
	    width : 520,
	    height : 250,
	    lock : true
    });
}
function view(msgname,sysname){
    //alert(pk);
	art.dialog.open(actionPath+'/view.action?syncSendConfig.msgName=' + msgname+"&syncSendConfig.sysName="+sysname, {
	    title : '查看同步配置详情',
	    width : 520,
	    height : 200,
	    lock : true
    });
}
function del(msgname,sysname){
	if (!confirm("确定删除此配置吗？"))
	{
		return;
 	}
	var urlStr = actionPath+"/delete.action?syncSendConfig.msgName=" + msgname+"&syncSendConfig.sysName="+sysname;
	jQuery.ajax({
        url:urlStr,
        type:"POST",
        cache:false,
        async:false,
        dataType: "json",
        success:function(json, textStatus) {
            	 alert(json.message); 
            	 if(json.message == "删除成功"){
            		 reload();
            	 }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert("保存时出现异常！");
        }
    });
}
function save(){
	if(Validator.isValid("frm")) {
       	var params = $('#frm').serializeObject();
       	jQuery.ajax({
               url:actionPath+"/insert.action",
               type:"POST",
               cache:false,
               async:false,
               dataType: "json",
               data: params,
               success:function(json, textStatus) {
                   	 alert(json.message); 
                   	 if(json.message == "新增成功"){
	                   	 art.dialog.top.reload();
	                   	 art.dialog.close();
                   	 }
               },
               error:function(XMLHttpRequest, textStatus, errorThrown) {
                   alert("保存时出现异常！");
               }
           });
     }
}

function update(msgname,sysname){
	art.dialog.open(actionPath+'/updateQuery.action?syncSendConfig.msgName=' + msgname+"&syncSendConfig.sysName="+sysname, {
	    title : '修改订购关系',
	    width : 520,
	    height : 250,
	    lock : true
    });

}

function updateSave(){
	if(Validator.isValid("frm")) {
       	var params = $('#frm').serializeObject();
       	jQuery.ajax({
               url:actionPath+"/update.action",
               type:"POST",
               cache:false,
               async:false,
               dataType: "json",
               data: params,
               success:function(json, textStatus) {
                   	 alert(json.message); 
                   	 if(json.message == "修改成功"){
	                   	 art.dialog.top.reload();
	                   	 art.dialog.close();
                   	 }
               },
               error:function(XMLHttpRequest, textStatus, errorThrown) {
                   alert("保存时出现异常！");
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
	art.dialog.close();
}
function reload() {
	document.location.reload();
}
