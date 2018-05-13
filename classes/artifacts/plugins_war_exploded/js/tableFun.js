
//给同步请求列表调用
function initFun(){
	//initTable();重复调用了，所以去掉。写这个方法是为了防止确保页面中调用了找不到方法
}
function strlength(str,english_num){
    var sub_len=0;
    var i=0;//已经截取的字符个数
    for (;i<str.length&& sub_len<english_num;i++)
    {
        if (str.charCodeAt(i)<0||str.charCodeAt(i)>255) {
        	sub_len +=2;
        }else{
        	sub_len++;
        }
    }
    if(i>=str.length){
    	return 0;
    }else{
    	var j = str.length-i;  //没有截取个字符个数
    	if(j<=4){
    		return 0;
    	}else{
    		return i;
    	}
    }
}


function initTable() {
		$(".subStr").each(function(i) {
			var textLength= strlength($(this).text(),Math.ceil($(this).attr("clientWidth") / 11));
			if (textLength > 0) {
				$(this).attr("title",  $(this).text());
				var text = $(this).text().substring(0, textLength) + "...";
				$(this).text(text);
			}
		});
		initTableHead();
		initTrClick();
		initTrColor();
}

function initTableHead() {
	$(".yanshi_newul thead tr th").unbind('click');
	$(".yanshi_newul thead tr th").bind("click", function() {
		var this_class = $(this).attr("class");
		if (this_class == "noSort") {
			return false;
		}
		// 将当前为asc排序的设为原来的。
		$(".ascCla").each(function(i) {
			$(this).removeClass();
			$(this).addClass("sortCla");
		});
		// 将当前为desc 的设置为默认的
		$(".descCla").each(function(i) {
			$(this).removeClass();
			$(this).addClass("sortCla");
		});
		if (this_class == "sortCla") {
			$(this).removeClass();
			$(this).addClass("descCla");
		} else if (this_class == "ascCla") {
			$(this).removeClass();
			$(this).addClass("descCla");
		} else if (this_class == "descCla") {
			$(this).removeClass();
			$(this).addClass("ascCla");
		}
		var i = $(this).attr("sortNum");
		var re = /^[0-9]*[1-9][0-9]*$/;
		if (i != null && (re.test(i) || i == 0)) {
			var temp = getCurrentSelected().split(",");
			SortTable(i);
			if(temp != undefined && temp.length>0){
				var pkids = document.getElementsByName("pkidList");
				for ( var j = 0; j < pkids.length && pkids!=undefined; j++) {
					for ( var i = 0; i < temp.length; i++) {
						if (pkids.item(j).value == temp[i]) {
							pkids.item(j).checked = true;
							temp.splice(i, 1);
						}
					}
				}
			}
			initTrClick();
			initTrColor();
		}
	});
}

function initTrClick(){
	$(".yanshi_newul tbody tr td").unbind('click');
	$(".yanshi_newul tbody tr td").bind('click', function() {
		if($(this).find(":input")[0] || $(this).find("a")[0]){
			
		}else{
			if($(this).parents("tr").find('input:checkbox')[0]){
				//$(this).parents("tr").find('input:checkbox')[0].checked = ! $(this).parents("tr").find('input:checkbox')[0].checked;
				$(this).parents("tr").find('input:checkbox')[0].click();
			}
		}
	});
}


//页面初始化的时候对其复选的进行初始化
function initTrColor() {
	$(".yanshi_newul tbody tr").removeClass();
	var cla = $(".yanshi_newul").attr("cla");
	if (cla == "even_odd") {
		$(".yanshi_newul tbody tr:odd").addClass("t_odd");
		$(".yanshi_newul tbody tr:even").addClass("t_even");
		//复选的加上复选颜色
		$(".yanshi_newul tbody tr:odd").each(function(i) {
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).removeClass("t_odd");
				$(this).addClass("t_selected");
			}
		});
		$(".yanshi_newul tbody tr:even").each(function(i) {
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).removeClass("t_even");
				$(this).addClass("t_selected");
			}
		});
		
		$(".yanshi_newul tbody tr:odd").mouseover(function() {
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).removeClass("t_selected");
			}else{
				$(this).removeClass("t_odd");
			}
			$(this).addClass("t_over");
		});

		$(".yanshi_newul tbody tr:odd").mouseout(function() {
			$(this).removeClass("t_over");
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).addClass("t_selected");
			}else{
				$(this).addClass("t_odd");
			}
		});

		$(".yanshi_newul tbody tr:even").mouseover(function() {
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).removeClass("t_selected");
			}else{
				$(this).removeClass("t_even");
			}
			$(this).addClass("t_over");
		});

		$(".yanshi_newul tbody tr:even").mouseout(function() {
			$(this).removeClass("t_over");
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).addClass("t_selected");
			}else{
				$(this).addClass("t_even");
			}
		});
	} else {

		$(".yanshi_newul tbody tr").addClass("t_even");

		$(".yanshi_newul tbody tr").each(function(i) {
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).removeClass("t_even");
				$(this).addClass("t_selected");
			}
		});

		$(".yanshi_newul tbody tr").mouseover(function() {
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).removeClass("t_selected");
			}else{
				$(this).removeClass("t_even");
			}
			$(this).addClass("t_over");
		});

		$(".yanshi_newul tbody tr").mouseout(function() {
			$(this).removeClass("t_over");
			if ($(this).find('input:checkbox')[0] && $(this).find('input:checkbox')[0].checked){
				$(this).addClass("t_selected");
			}else{
				$(this).addClass("t_even");
			}
		});

	}

}

//反选改变颜色
function changeTrColor(){
	var cla = $(".yanshi_newul").attr("cla");
	if (cla == "even_odd") {
		$(".yanshi_newul tbody tr:odd").each(function(i) {
			if ($(this).find('input:checkbox')[0].checked){
				$(this).removeClass();
				$(this).addClass("t_selected");
			}else{
				$(this).removeClass();
				$(this).addClass("t_odd");
			}
		});
		$(".yanshi_newul tbody tr:even").each(function(i) {
			if ($(this).find('input:checkbox')[0].checked){
				$(this).removeClass();
				$(this).addClass("t_selected");
			}else{
				$(this).removeClass();
				$(this).addClass("t_even");
			}
		});
	}else{
		$(".yanshi_newul tbody tr").each(function(i) {
			if ($(this).find('input:checkbox')[0].checked){
				$(this).removeClass();
				$(this).addClass("t_selected");
			}else{
				$(this).removeClass();
				$(this).addClass("t_even");
			}
		});
	}
}


Date.prototype.format = function(format)
{
    var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(),    //day
    "h+" : this.getHours(),   //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
    "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    {
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o)
    {
        if(new RegExp("("+ k +")").test(format))
        {
            format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}

function getDateString(date){
	//月份不需要+1，tanghua by 2012-09-08
	if( date ==null || date==undefined || date.year == undefined || date.year==null ){
		return "";
	}
	var d=new Date(date.year+1900,date.month,date.date,date.hours,date.minutes,date.seconds);
	var str=d.format("yyyy-MM-dd hh:mm:ss");
	return str;
}
//将null 输出 ""
function getString(date){
	if( date== null ){
		return "";
	}else{
		return date;
	}
}
