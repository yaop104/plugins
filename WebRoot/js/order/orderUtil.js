var ORDER_STATUS_0 = "0";
var ORDER_STATUS_1 = "1";
var ORDER_STATUS_2 = "2";
var ORDER_STATUS_3 = "3";
var ORDER_STATUS_4 = "4";
var ORDER_STATUS_5 = "5";
var ORDER_STATUS_6 = "6";

var ORDER_STATUS_0_DESC = "正常";
var ORDER_STATUS_1_DESC = "暂停";
var ORDER_STATUS_2_DESC = "退订";
var ORDER_STATUS_3_DESC = "临时订购";
var ORDER_STATUS_4_DESC = "未激活";
var ORDER_STATUS_5_DESC = "欠费暂停";
var ORDER_STATUS_6_DESC = "订购中";

//000-web门户	001-wap门户	002-客户端门		100-web应用 	101-wap应用	102-客户端应用
var ORDER_FROM_TYPE_000 = "000";
var ORDER_FROM_TYPE_001 = "001";
var ORDER_FROM_TYPE_002 = "002";
var ORDER_FROM_TYPE_100 = "100";
var ORDER_FROM_TYPE_101 = "101";
var ORDER_FROM_TYPE_102 = "102";

var ORDER_FROM_TYPE_000_DESC = "web门户";
var ORDER_FROM_TYPE_001_DESC = "wap门户";
var ORDER_FROM_TYPE_002_DESC = "客户端门户";
var ORDER_FROM_TYPE_100_DESC = "web应用";
var ORDER_FROM_TYPE_101_DESC = "wap应用";
var ORDER_FROM_TYPE_102_DESC = "客户端应用";
var actionPath = '<s:url value="/admin/order" encode="false"/>';
function getStatus(num){
    if(num == ORDER_STATUS_0){
    	return ORDER_STATUS_0_DESC;
    }
    if(num == ORDER_STATUS_1){
    	return ORDER_STATUS_1_DESC;
    }
    if(num == ORDER_STATUS_2){
    	return ORDER_STATUS_2_DESC;
    }
    if(num == ORDER_STATUS_3){
    	return ORDER_STATUS_3_DESC;
    }
    if(num == ORDER_STATUS_4){
    	return ORDER_STATUS_4_DESC;
    }
    if(num == ORDER_STATUS_5){
    	return ORDER_STATUS_5_DESC;
    }
    if(num == ORDER_STATUS_6){
    	return ORDER_STATUS_6_DESC;
    }
    return "";
} 

function getFromType(num){
	if(num == ORDER_FROM_TYPE_000){
    	return ORDER_FROM_TYPE_000_DESC;
    }
	if(num == ORDER_FROM_TYPE_001){
    	return ORDER_FROM_TYPE_001_DESC;
    }
	if(num == ORDER_FROM_TYPE_002){
    	return ORDER_FROM_TYPE_002_DESC;
    }
	if(num == ORDER_FROM_TYPE_100){
    	return ORDER_FROM_TYPE_100_DESC;
    }
	if(num == ORDER_FROM_TYPE_101){
    	return ORDER_FROM_TYPE_101_DESC;
    }
	if(num == ORDER_FROM_TYPE_102){
    	return ORDER_FROM_TYPE_102_DESC;
    }
	return "";
} 

function view(pk){
    //alert(pk);
	art.dialog.open(actionPath+'/view.action?orderInfo.pkid=' + pk, {
	    title : '查看订购关系详情',
	    width : 900,
	    height : 450,
	    lock : true
    });
}
function viewHistory(pk){
	art.dialog.open(actionPath+'/viewHistory.action?orderInfo.pkid=' + pk, {
	    title : '查看历史订购关系详情',
	    width : 900,
	    height : 450,
	    lock : true
    });
}
function changeWord(obj){
	if ($(obj).children("span")[0].innerHTML == "折叠") {
		$(obj).children("span")[0].innerHTML = "打开";
	} else {
		$(obj).children("span")[0].innerHTML = "折叠";
	}
}

// 控制折叠功能
$(function () {
	var array = ['reviewFlow','operationList'];
	for (var i = 0; i < array.length; i++) {
		$("#" + array[i]).bind("click",function () {
			if ($(this).next().css("display") == 'block') {
				$(this).next().hide();
			} else {
				$(this).next().show();
			}
			changeWord(this);
		});
	}
})

//dmGrid组件高度
function getGridHeight(){
  return getTotalHeight() - 280;
}
function updateOrder(pkid){
	art.dialog.open(actionPath+'/updateQuery.action?orderInfo.pkid='+pkid, {
	    title : '修改订购关系',
	    width : 900,
	    height : 400,
	    lock : true
    });

}
function pause(pkid){
	if (!confirm("确定暂停此订购关系吗？"))
	{
		return;
 	}
	art.dialog.open('/sme/admin/order/pauseOrderInfo.jsp?pkid='+pkid, {
	    title : '暂停订购关系',
	    width : 350,
	    height : 180,
	    lock : true
    });

}
function pauseList(pkid,status){
	if(status != ORDER_STATUS_0){
		alert("只能对状态为“正常”的订购记录进行暂停操作！");
		return ;
	}
	if (!confirm("确定暂停此订购关系吗？"))
	{
		return;
 	}
	art.dialog.open('/sme/admin/order/pauseOrderInfo.jsp?pkid='+pkid, {
	    title : '暂停订购关系',
	    width : 350,
	    height : 180,
	    lock : true
    });

}
function pauseOpration(pkid){
		if($("#operationDesc").val().Trim() == ""){
			alert("请填写暂停原因！");
			return;
		}
	  var params = $('#frm').serializeObject();
	  jQuery.ajax({
		  url:actionPath+"/pause.action?orderInfo.pkid="+pkid,
		  type:"POST",
		  cache:false,
		  async:false,
		  dataType: "json",
          data: params,
		  success:function(json, textStatus) {
				alert(json.message);
		      	var win = art.dialog.open.origin;//来源页面
			     // 如果父页面重载或者关闭其子对话框全部会关闭
		      	art.dialog.close();
			    win.location.reload();
		  },
		  error:function(XMLHttpRequest, textStatus, errorThrown) {
		      alert("暂停订购关系时出现异常！");
		  }
	});
}
function activateOpration(pkid){
	if($("#operationDesc").val().Trim() == ""){
		alert("请填写激活原因！");
		return;
	}
	var params = $('#frm').serializeObject();
   jQuery.ajax({
       url:actionPath+"/activate.action?orderInfo.pkid="+pkid,
       type:"POST",
       cache:false,
       async:false,
       dataType: "json",
       data: params,
       success:function(json, textStatus) {
    	    alert(json.message);
	      	var win = art.dialog.open.origin;//来源页面
		     // 如果父页面重载或者关闭其子对话框全部会关闭
	      	art.dialog.close();
		    win.location.reload();
       },
       error:function(XMLHttpRequest, textStatus, errorThrown) {
           alert("激活订购关系时出现异常！");
       }
   });
}
function activate(pkid,startDate){
	if(!comptime(Date.prototype.Format("yyyy-MM-dd HH:mm:ss"),startDate))
	{
		 alert("此订购关系的生效时间大于当前时间，现在还不能激活！");
		 return;
	}
	if (!confirm("确定激活此订购关系吗？"))
	{
		return;
 	}
	art.dialog.open('/sme/admin/order/activateOrderInfo.jsp?pkid='+pkid, {
	    title : '激活订购关系',
	    width : 350,
	    height : 180,
	    lock : true
    });
}
function activateList(pkid,startDate,status){
	if(!(status == ORDER_STATUS_1 || status == ORDER_STATUS_4 ||status == ORDER_STATUS_5 )){
		 alert("只能对状态为“暂停”、“未激活”、“欠费暂停”的订购记录进行激活操作！");
		 return;
	}
	if(!comptime(Date.prototype.Format("yyyy-MM-dd HH:mm:ss"),startDate))
	{
		 alert("此订购关系的生效时间大于当前时间，现在还不能激活！");
		 return;
	}
	if (!confirm("确定激活此订购关系吗？"))
	{
		return;
 	}
	art.dialog.open('/sme/admin/order/activateOrderInfo.jsp?pkid='+pkid, {
	    title : '激活订购关系',
	    width : 350,
	    height : 180,
	    lock : true
    });
}
function reload() {
	document.location.reload();
}
/*得到当前时间，格式yyyy-MM-dd HH:mm:ss*/
Date.prototype.Format = function(formatStr)   
{   
	var myDate = new Date();
    var str = formatStr;   
  
    str=str.replace(/yyyy|YYYY/,myDate.getFullYear());   
    str=str.replace(/yy|YY/,(myDate.getYear() % 100)>9?(myDate.getYear() % 100).toString():'0' + (myDate.getYear() % 100));   
  
    str=str.replace(/MM/,(myDate.getMonth()+1)>9?(myDate.getMonth()+1).toString():'0' + (myDate.getMonth()+1));   
  
  
    str=str.replace(/dd|DD/,myDate.getDate()>9?myDate.getDate().toString():'0' + myDate.getDate());   
  
    str=str.replace(/hh|HH/,myDate.getHours()>9?myDate.getHours().toString():'0' + myDate.getHours());   
    str=str.replace(/mm/,myDate.getMinutes()>9?myDate.getMinutes().toString():'0' + myDate.getMinutes());   
  
    str=str.replace(/ss|SS/,myDate.getSeconds()>9?myDate.getSeconds().toString():'0' + myDate.getSeconds());   
  
    return str;   
}   
//相关操作列
function renderOperation(rowId, rowData) {
	var str = "";
	var status = rowData[2];//状态
	if(status == ORDER_STATUS_0)//0：正常状态可以暂停
		str += '<span class="icon_jinyong" title="暂停订购关系"><a href="javascript:pause(\'' + rowData[5] + '\');"  >暂停订购关系</a></span>';
	
	if(status == ORDER_STATUS_1 || status == ORDER_STATUS_4 ||status == ORDER_STATUS_5 )
		str += '<span class="icon_start" title="激活订购关系"><a href="javascript:activate(\'' + rowData[5] + '\',\'' + rowData[3] + '\');" >激活订购关系</a></span>';
    return str;
}

function comptime(currTime,startTime) {
    var currTimes = currTime.substring(0, 10).split('-');
    var startTimes = startTime.substring(0, 10).split('-');
    
    currTime = currTimes[1] + '-' + currTimes[2] + '-' + currTimes[0] + ' ' + currTime.substring(10, 19);
    startTime = startTimes[1] + '-' + startTimes[2] + '-' + startTimes[0] + ' ' + startTime.substring(10, 19);
    
    currTime=currTime.replace("-", "/").replace("-", "/"); 
    startTime=startTime.replace("-", "/").replace("-", "/"); 
    
    var a = (Date.parse(startTime) - Date.parse(currTime)) / 3600 / 1000;
    if (a < 0) {
        return true;//startTime比当前时间小，表示可以激活
    } else if (a > 0) {
        return false;//alert("startTime大!");
    } else if (a == 0) {
    	return false;//alert("时间相等!");
    } else {
    	return false;//return 'exception'
    }
}
/**
 * 当现在的时间<生效时间时，可以修改
 * 判断现在时间是否小于生效时间，如果是返回true
 * @param currTime
 * @param startTime
 * @returns {Boolean}
 */
function currTimeComptimeStratTime(currTime,startTime) {
    var currTimes = currTime.substring(0, 10).split('-');
    var startTimes = startTime.substring(0, 10).split('-');
    
    currTime = currTimes[1] + '-' + currTimes[2] + '-' + currTimes[0] + ' ' + currTime.substring(10, 19);
    startTime = startTimes[1] + '-' + startTimes[2] + '-' + startTimes[0] + ' ' + startTime.substring(10, 19);
    
    currTime=currTime.replace("-", "/").replace("-", "/"); 
    startTime=startTime.replace("-", "/").replace("-", "/"); 
    
    var a = (Date.parse(startTime) - Date.parse(currTime)) / 3600 / 1000;
    if (a < 0) {
        return false;//startTime比当前时间小
    } else if (a > 0) {
        return true;//alert("startTime大!");
    } else if (a == 0) {
    	return false;//alert("时间相等!");
    } else {
    	return false;//return 'exception'
    }
}
String.prototype.Trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}
//修改
function updatesave(){
	if(!$("#beginTime").is(":hidden")){
		if($("#beginTime").val() == ""){
			alert("生效时间不能为空！");
			return;
		}
		//生效时间必须大于当前时间
		if(!comptime($('#beginTime').val(),Date.prototype.Format("yyyy-MM-dd HH:mm:ss"))){
			alert("生效时间必须大于当前时间！");
			return;
		}
	}
	if(!$("#endTime").is(":hidden")){
		if($("#endTime").val() == ""){
			alert("失效时间不能为空！");
			return;
		}
		//失效时间必须大于生效时间
		if(!comptime($('#endTime').val(),$('#beginTime').val())){
			alert("失效时间必须大于生效时间！");
			return;
		}
	}
   	var params = $('#frm').serializeObject();
   	jQuery.ajax({
           url:actionPath+"/updateOrder.action",
           type:"POST",
           cache:false,
           async:false,
           dataType: "json",
           data: params,
           success:function(json) {
        	    alert(json.message);
       	      	var win = art.dialog.open.origin;//来源页面
       		     // 如果父页面重载或者关闭其子对话框全部会关闭
       	      	art.dialog.close();
       		    win.location.reload();
           },
           error:function(XMLHttpRequest, textStatus, errorThrown) {
               alert("修改保存时出现异常！");
           }
       });
}
function dateForStr(date){
	var d=new Date(date.year+1900,date.month,date.day,date.hours,date.minutes,date.seconds);
	var str=d.format("yyyy-MM-dd hh:mm:ss");
	return str;
}