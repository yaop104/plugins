//用来存选择的数据
var selectedArray = new Array();
var otherValueList = new Array();
function initSelected() {
	if (selectedArray != undefined && selectedArray != null
			&& selectedArray.length > 0) {
		var pkids = document.getElementsByName("pkidList");
		var valNum = getOtherValueNum();
		var tempArray = new Array();
		for(var i=0;i<valNum&&valNum>0;i++){
			tempArray[i] = otherValueList[i].split(",");
		}
		for ( var j = 0; j < pkids.length && pkids!=undefined; j++) {
			for ( var i = 0; i < selectedArray.length; i++) {
				if (pkids.item(j).value == selectedArray[i]) {
					pkids.item(j).checked = true;
					selectedArray.splice(i, 1);
						for(var k=0;k<valNum&&valNum>0;k++){
							tempArray[k].splice(i,1);
							}
						}
				}
			}
		}
		
		
		for(var k=0;k<valNum;k++){
			otherValueList[k]="";
			for(var i=0;i<tempArray[k].length;i++){
				if(i==0){
					otherValueList[k] = tempArray[k][i];
				}else{
					otherValueList[k] +=","+ tempArray[k][i];
				}
			}
		}
}



function serach() {
	$("#currentNumber").val(1);
	ajaxReload();
}
// 页面加载完成之后初始化分页样式
function initPage() {
	// 初始化分页样式
	var pageNo = $("#currentNumber").val();
	var pageCount = $("#pageCount").val();
	if (pageNo == "1" || pageNo == "0") {
		document.getElementById("first").outerHTML = "<a href=\"#\" onclick=\"firstPage()\" id=\"first\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">首页</a>";
		document.getElementById("pre").outerHTML = "<a href=\"#\" onclick=\"prePage()\" id=\"pre\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">上一页</a>";
	} else {
		document.getElementById("first").outerHTML = "<a href=\"#\" onclick=\"firstPage()\" id=\"first\">首页</a>";
		document.getElementById("pre").outerHTML = "<a href=\"#\" onclick=\"prePage()\" id=\"pre\">上一页</a>";
	}
	if (pageNo - pageCount >= 0) {
		document.getElementById("next").outerHTML = "<a href=\"#\" onclick=\"nextPage()\" id=\"next\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">下一页</a>";
		document.getElementById("last").outerHTML = "<a href=\"#\" onclick=\"lastPage()\" id=\"last\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">尾页</a>";
	} else {
		document.getElementById("next").outerHTML = "<a href=\"#\" onclick=\"nextPage()\" id=\"next\">下一页</a>";
		document.getElementById("last").outerHTML = "<a href=\"#\" onclick=\"lastPage()\" id=\"last\">尾页</a>";
	}

}
// 选择每页显示条数
function changePageSize() {
	var pageNum = $("#pageNum").val();
	var pageSize = $("#pageSize").val();
	if (pageNum == pageSize) {
		return;
	} else {
		changeSelectedList();
		$("#pageSize").val(pageNum);
		ajaxReload();
	}

}
// 第一页
function firstPage() {
	var pageNo = $("#currentNumber").val();
	var pageCount = $("#pageCount").val();
	if (pageNo <= 1) {
		return;
	} else {
		changeSelectedList();
		$("#currentNumber").val(1);
		ajaxReload();
	}
}
// 上一页
function prePage() {
	var pageNo = $("#currentNumber").val();
	var pageCount = $("#pageCount").val();
	if (pageNo <= 1) {
		return;
	} else {
		changeSelectedList();
		$("#currentNumber").val(pageNo - 1);
		ajaxReload();
	}
}
// 下一页
function nextPage() {
	var pageNo = $("#currentNumber").val();
	var pageCount = $("#pageCount").val();
	if (pageNo - pageCount >= 0) {
		return;
	} else {
		changeSelectedList();
		$("#currentNumber").val(Number(pageNo) + 1);
		ajaxReload();
	}
}
// 最后一页
function lastPage() {
	var pageNo = $("#currentNumber").val();
	var pageCount = $("#pageCount").val();
	if (pageNo - pageCount >= 0) {
		return;
	} else {
		changeSelectedList();
		$("#currentNumber").val(pageCount);
		ajaxReload();
	}
}
// 输入页面进行跳转
function selectPage() {
	var num = $("#inputPage").val();
	var isNum = forcheck(num);
	var pageNo = $("#currentNumber").val();
	var pageCount = $("#pageCount").val();
	if (!isNum) {
		alert("页码请输入正整数！");
		return;
	}
	if (num == pageNo) {
		return;
	}
	if (num - pageCount > 0) {
		alert("输入的数字不能超过最大页数！");
		return;
	}
	changeSelectedList();
	$("#currentNumber").val(num);
	ajaxReload();

}
// 对一个数字进行判断是否为正整数
function forcheck(number) {
	var type = "^[0-9]*[1-9][0-9]*$";
	var re = new RegExp(type);
	if (re.test(number)) {
		return true;
	} else {
		return false;
	}
}

// 复选框全选
function checkAll() {
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).checked == false) {
			pkids.item(j).checked = true;
		}
	}
	changeTrColor();
}

// 复选框全不选
function uncheckAll() {
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).checked == true) {
			pkids.item(j).checked = false;
		}
	}
	changeTrColor();
}
// 单选的复选框
function ckeckboxChange(id) {
	
	var pkids = document.getElementsByName("pkidList");
	var sel=pkids.item(id).checked;
	for ( var j = 0; j < pkids.length; j++) {
		pkids.item(j).checked = false;
	}
	pkids.item(id).checked = sel;
}

// 复选框 反选
function switchAll() {
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		pkids.item(j).checked = !pkids.item(j).checked;
	}
	changeTrColor();
}

// 读取复选框的所有选中的checkbox
function getPkids() {
	var pks = "";
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).checked == true) {
			if (pks == "") {
				pks = pkids.item(j).value;
			} else {
				pks = pks + "," + pkids.item(j).value;
			}
		}
	}
	return pks;
}
// 改变所选。
function changeSelectedList() {
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).checked == true) {
			selectedArray[selectedArray.length] = pkids.item(j).value;
		}
	}
	changeOtherValue();
}
// 获得当前页面所选的列表。
function getCurrentSelected() {
	var pks = "";
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).checked == true) {
			if (pks == "") {
				pks = pkids.item(j).value;
			} else {
				pks = pks + "," + pkids.item(j).value;
			}
		}
	}
	return pks;
}

//获得全部已选
function getAllSelected() {
	var str = "";
	var pkids = document.getElementsByName("pkidList");
	
	if (selectedArray.length > 0) {
		for ( var i = 0; i < selectedArray.length; i++) {
			if (i == 0) {
				str = selectedArray[i];
			} else {
				str += "," + selectedArray[i];
			}
		}
	}
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).checked == true) {
			if(str==""){
				str+=pkids.item(j).value;
			}else{
				str += "," + pkids.item(j).value;
			}
			
		}
	}
	return str;
}


function getOtherValueNum() {
	var pkids = document.getElementsByName("pkidList");
	if (pkids.item(0) == undefined) {
		return 0;
	}
	// 得到有几个其他值
	var hasValue = true;
	var i = 0;
	while (hasValue) {
		var v = pkids.item(0).getAttribute("value" + i );
		if (v == undefined) {
			hasValue = false;
		} else {
			i++;
		}
	}
	return i;
}


function changeOtherValue() {
	var i = getOtherValueNum();
	if (i == 0) {
		return;
	}

	var pkids = document.getElementsByName("pkidList");
	for ( var k = 0; k < i; k++) {
		for ( var j = 0; j < pkids.length; j++) {
			if (pkids.item(j).checked == true) {
				if (otherValueList[k] == "" || otherValueList[k] == undefined
						|| otherValueList[k] == null) {
					otherValueList[k] = pkids.item(j).getAttribute("value" + k);
				} else {
					otherValueList[k] = otherValueList[k]
							+ "," + pkids.item(j).getAttribute("value" + k);
				}
			}
		}

	}

}
/**
 * 1.在名字为pkidList的复选框中自定义属性 value1 (value后面的数字从1开始，如： value1=... value2=....)
 * 例如： 2.要得到当前页中选中的复选框 所有的 value1的值，调用方法 ： getCurrentSelectedByID(1);
 * 3。要得到所有已选（包括非当前页选中）的value1的值，调用方法： getAllSelectedByID(1); 4。要得到当前页
 * 复选框的值（value） 调用之前的方法： getCurrentSelected(); 5.要得到所有页面复选框的值（value）
 * 调用之前的方法：getAllSelected();
 */
function getCurrentSelectedByID(id) {
	var i = getOtherValueNum();
	if (i == 0) {
		return null;
	}
	var otherValue = new Array();
	var pkids = document.getElementsByName("pkidList");
	for ( var k = 0; k < i; k++) {
		otherValue[k] = "";
		for ( var j = 0; j < pkids.length; j++) {
			if (pkids.item(j).checked == true) {
				if (otherValue[k] == "" || otherValue[k] == undefined
						|| otherValue[k] == null) {
					otherValue[k] = pkids.item(j).getAttribute("value" + k);
				} else {
					otherValue[k] = otherValue[k]
							+ "," + pkids.item(j).getAttribute("value" + k);
				}
			}
		}

	}
	return otherValue[id];

}
/**
 * 1.在名字为pkidList的复选框中自定义属性 value1 (value后面的数字从1开始，如： value1=... value2=....)
 * 例如： 2.要得到当前页中选中的复选框 所有的 value1的值，调用方法 ： getCurrentSelectedByID(1);
 * 3。要得到所有已选（包括非当前页选中）的value1的值，调用方法： getAllSelectedByID(1); 4。要得到当前页
 * 复选框的值（value） 调用之前的方法： getCurrentSelected(); 5.要得到所有页面复选框的值（value）
 * 调用之前的方法：getAllSelected();
 */
function getAllSelectedByID(id) {
	var i = getOtherValueNum();
	if (i == 0) {
		return;
	}
	var curr = getCurrentSelectedByID(id);
	var othSel = otherValueList[id];
	if (othSel != undefined && othSel != "" && curr != null && curr != "") {
		return othSel + "," + curr;
	} else if (othSel != undefined && othSel != "") {
		return othSel;
	} else {
		return curr;
	}
}

// 清楚所有已选
function clearSelected() {
	uncheckAll();
}

// 将当前要删除的复选框去掉。
function clearSelectedByPK(pk) {
	var pkids = document.getElementsByName("pkidList");
	for ( var j = 0; j < pkids.length; j++) {
		if (pkids.item(j).value == pk) {
			pkids.item(j).checked = false;
		}
	}
	changeSelectedList();
}

function delAjax(urlAddress) {
	jQuery.ajax({
		url : urlAddress,
		type : "GET",
		cache : false,
		async : false,
		dataType : "json",
		success : function(json, textStatus) {
			if (json.code == "0") {
				alert(json.message);
			} else {
				alert(json.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("删除数据时出现异常！");
		}
	});
	reload();

}
function delAjaxWithoutReload(urlAddress) {
	jQuery.ajax({
		url : urlAddress,
		type : "GET",
		cache : false,
		async : false,
		dataType : "json",
		success : function(json, textStatus) {
			if (json.code == "0") {
				alert(json.message);
			} else {
				alert(json.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("删除数据时出现异常！");
		}
	});
}