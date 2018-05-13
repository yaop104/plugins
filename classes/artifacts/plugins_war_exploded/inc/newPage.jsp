<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.t_aera {
	color: #787878;
	background-color: #F6FEF6;
	clip: rect('20', '80', '20', '2');
	margin: -2px;
}
</style>
</head>
<body onload="init()">
	<input id="pageSize" name="pageSize" value="${pageList.pageInfo.pageSize}" type="hidden" />
	<input id="pageNo" name="pageNo"
		value="${pageList.pageInfo.pageNo }" type="hidden" />
	<input id="recordCount" name="recordCount" value="${pageList.pageInfo.recordCount}"
		type="hidden" />
	<input id="fieldOrder" name="fieldOrder" value="${pageList.pageInfo.fieldOrder}"
		type="hidden" />
	<input id="pageCount" name="pageCount" value="${pageList.pageInfo.pageCount}"
		type="hidden" />
	<input id="selectedPkidList" name="selectedPkidList"
		value="${selectedPkidList }" type="hidden" />
	<div id="othervalues">
		<s:iterator value="otherValues" var="d" status="su">
			<input type="hidden" id="othvalue${su.index }" value="${d }"
				name="otherValues[${su.index }]" />
		</s:iterator>
	</div>
	共
	<span>${pageList.pageInfo.recordCount }</span>条记录 &nbsp;
	<span>每页</span>10
	<!-- <select name="pageNum" id="pageNum" onchange="changePageSize()">
		<s:if test="#pageList.pageInfo.pageSize==5">
			<option value="5" selected>5</option>
		</s:if>
		<s:else>
			<option value="5">5</option>
		</s:else>
		<s:if test="#pageList.pageInfo.pageSize==10">
			<option value="10" selected>10</option>
		</s:if>
		<s:else>
			<option value="10">10</option>
		</s:else>
		<s:if test="#pageList.pageInfo.pageSize==20">
			<option value="20" selected>20</option>
		</s:if>
		<s:else>
			<option value="20">20</option>
		</s:else>
		<s:if test="#pageList.pageInfo.pageSize==30">
			<option value="30" selected>30</option>
		</s:if>
		<s:else>
			<option value="30">30</option>
		</s:else>
		<s:if test="pageList.pageInfo.pageSize==50">
			<option value="50" selected>50</option>
		</s:if>
		<s:else>
			<option value="50">50</option>
		</s:else> 

	</select>-->
	&nbsp;条&nbsp;

	<span> <a href="#" onclick="firstPage()" id="first">首页</a> <a
		href="#" onclick="prePage()" id="pre">上一页</a> <a href="#"
		onclick="nextPage()" id="next">下一页</a> <a href="#"
		onclick="lastPage()" id="last">尾页</a>
	</span> &nbsp;
	<div class="pnumberss">
		<input name="inputPage" id="inputPage" value="${pageList.pageInfo.pageNo }"
			type="text" onkeydown="if(event.keyCode==13)selectPage()" />
	</div>
	<span>/</span>${pageList.pageInfo.pageCount }
	<span id="pagesgo"><a href="#" onclick="selectPage()">跳转</a></span>


	<script type="text/javascript">
	    /**刷新页面*/
		function reload() {
			document.getElementById("grid").submit();
		}
	    /**搜索页面*/
		function serach() {
			document.getElementById("selectedPkidList").value = "";
			document.getElementById("pageNo").value = 1;
			document.getElementById("grid").submit();
		}
		//页面初始化
		function init() {
			//初始化分页样式
			var pageNo = document.getElementById("pageNo").value;
			var pageCount = document.getElementById("pageCount").value;
			if (pageNo == "1") {
				document.getElementById("pre").outerHTML = "<a href=\"#\" onclick=\"prePage()\" id=\"first\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">上一页</a>";
			}
			if (pageNo - pageCount >= 0) {
				document.getElementById("next").outerHTML = "<a href=\"#\" onclick=\"nextPage()\" id=\"first\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">下一页</a>";
				document.getElementById("last").outerHTML = "<a href=\"#\" onclick=\"lastPage()\" id=\"first\" style=\"cursor:default;background:#ffffff;color:#cdcdcd;\">尾页</a>";
			}

			//初始化复选框
			var pkids = document.getElementsByName("pkidList");
			var lastSelected = document.getElementById("selectedPkidList").value;
			var lastSelectedArray = new Array();
			lastSelectedArray = lastSelected.split(",");
			for ( var j = 0; j < pkids.length; j++) {
				for ( var i = 0; i < lastSelectedArray.length; i++) {
					if (pkids.item(j).value == lastSelectedArray[i]) {
						pkids.item(j).checked = true;
						lastSelectedArray.splice(i, 1);
					}
				}
			}
			lastSelected = "";
			for ( var k = 0; k < lastSelectedArray.length; k++) {
				if (k == 0) {
					lastSelected = lastSelectedArray[k];
				} else {
					lastSelected = lastSelectedArray[k] + "," + lastSelected;
				}
			}
			document.getElementById("selectedPkidList").value = lastSelected;
			initTable();
		}
		//选择每页显示条数
		function changePageSize() {
			var pageNum = document.getElementById("pageNum").value;
			var pageSize = document.getElementById("pageSize").value;
			if (pageNum == pageSize) {
				return;
			} else {
				changeSelectedList();
				document.getElementById("pageSize").value = pageNum;
				document.getElementById("grid").submit();
			}

		}
		//第一页
		function firstPage() {
			var pageNo = document.getElementById("pageNo").value;
			var pageCount = document.getElementById("pageCount").value;
			if (pageNo <= 1) {
				return;
			} else {
				changeSelectedList();
				document.getElementById("pageNo").value = 1;
				document.getElementById("grid").submit();
			}
		}
		//上一页
		function prePage() {
			var pageNo = document.getElementById("pageNo").value;
			var pageCount = document.getElementById("pageCount").value;
			if (pageNo <= 1) {
				alert("已经是第一页了！");
				return;
			} else {
				changeSelectedList();
				document.getElementById("pageNo").value = pageNo - 1;
				document.getElementById("grid").submit();
			}
		}
		//下一页
		function nextPage() {
			var pageNo = document.getElementById("pageNo").value;
			var pageCount = document.getElementById("pageCount").value;
			if (pageNo - pageCount >= 0) {
				return;
			} else {
				changeSelectedList();
				document.getElementById("pageNo").value = Number(pageNo) + 1;
				document.getElementById("grid").submit();
			}
		}
		//最后一页
		function lastPage() {
			var pageNo = document.getElementById("pageNo").value;
			var pageCount = document.getElementById("pageCount").value;
			if (pageNo - pageCount >= 0) {
				return;
			} else {
				changeSelectedList();
				document.getElementById("pageNo").value = pageCount;
				document.getElementById("grid").submit();
			}
		}
		//输入页面进行跳转
		function selectPage() {
			var num = document.getElementById("inputPage").value
			var isNum = forcheck(num);
			var pageNo = document.getElementById("pageNo").value;
			var pageCount = document.getElementById("pageCount").value;
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
			document.getElementById("pageNo").value = num;
			document.getElementById("grid").submit();

		}
		
		function forcheck(number) {
			var type = "^[0-9]*[1-9][0-9]*$";
			var re = new RegExp(type);
			if (re.test(number)) {
				return true;
			} else {
				return false;
			}
		}

		//复选框全选  
		function checkAll() {
			var pkids = document.getElementsByName("pkidList");
			for ( var j = 0; j < pkids.length; j++) {
				if (pkids.item(j).checked == false) {
					pkids.item(j).checked = true;
				}
			}
			changeTrColor();
		}

		//复选框全不选  
		function uncheckAll() {
			var pkids = document.getElementsByName("pkidList");
			for ( var j = 0; j < pkids.length; j++) {
				if (pkids.item(j).checked == true) {
					pkids.item(j).checked = false;
				}
			}
			changeTrColor();
		}

		function ckeckboxChange(id){
			var pkids = document.getElementsByName("pkidList");
			var sel = pkids.item(id).checked;//因为click已经进行了选中，所以这个值就是最后要的
			for ( var j = 0; j < pkids.length; j++) {
					pkids.item(j).checked = false;
			}
			pkids.item(id).checked = sel;
			changeTrColor();
		}
		
		//复选框选择转换  
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
		//改变所选。
		function changeSelectedList() {
			var lastSelected = document.getElementById("selectedPkidList").value;
			var currentSelected = getCurrentSelected();
			if (lastSelected != null && lastSelected != "" ) {
				if (currentSelected != "") {
					document.getElementById("selectedPkidList").value = lastSelected
							+ "," + currentSelected;
				}
			} else {
				document.getElementById("selectedPkidList").value = currentSelected;
			}
			changeOtherValue();
		}
		//获得当前页面所选的列表。
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
		//分页的时候，将 pkidList中的其他value保存起来
		function changeOtherValue() {
			var pkids = document.getElementsByName("pkidList");
			if(pkids.item(0) == undefined ){
				return;
			}
			//得到有几个其他值
			var otherValueList = new Array();
			var hasValue = true;
			var i = 0;
			while (hasValue) {
				var v = pkids.item(0).getAttribute("value" + (i + 1));
				if (v == undefined) {
					hasValue = false;
				} else {
					i++;
				}
			}
			for ( var k = 1; k <= i; k++) {
				otherValueList[k] = "";
				for ( var j = 0; j < pkids.length; j++) {
					if (pkids.item(j).checked == true) {
						if (otherValueList[k] == "" || otherValueList[k] == undefined || otherValueList[k] == null ) {
							otherValueList[k] = pkids.item(j).getAttribute(
									"value" + k);
						} else {
							otherValueList[k] = pkids.item(j).getAttribute("value" + k)+ "," +otherValueList[k] ;
						}
					}
				}

			}
			var oth = document.getElementById("othervalues");
			var reg = /[\r\n]/g; 
			var oth_inn = oth.innerHTML.replace(reg,"").replace(/(^\s*)|(\s*$)/g, "");//先截掉换行符，再截掉空格符号
			if (oth_inn == "") {
				for ( var h = 1; h <= i; h++) {
					oth.innerHTML=oth.innerHTML+"<input typr=\"hidden\" id=\"othvalue"+h+"\" value=\""+otherValueList[h]+"\" name=\"otherValues["+h+"]\" />";
				}
				
			} else {
				for ( var h = 1; h <= i; h++) {
					if (otherValueList[h] == "" || otherValueList[h] == undefined || otherValueList[h] == null ) {
						
					}else{
						if(document.getElementById("othvalue"+h).value=="" || document.getElementById("othvalue"+h).value== undefined){
							document.getElementById("othvalue"+h).value=otherValueList[h];
						}else{
							document.getElementById("othvalue"+h).value=document.getElementById("othvalue"+h).value+","+otherValueList[h];
						}
					}
				}
			}

		}
		/**
		*1.在名字为pkidList的复选框中自定义属性 value1 (value后面的数字从1开始，如： value1=...  value2=....)
		* 例如：
		*2.要得到当前页中选中的复选框 所有的 value1的值，调用方法 ： getCurrentSelectedByID(1);
		*3。要得到所有已选（包括非当前页选中）的value1的值，调用方法： getAllSelectedByID(1);
		*4。要得到当前页 复选框的值（value） 调用之前的方法： getCurrentSelected();
		*5.要得到所有页面复选框的值（value） 调用之前的方法：getAllSelected();
		*/
		function getCurrentSelectedByID(id) {
			var pkids = document.getElementsByName("pkidList");
			if(pkids.item(0) == undefined ){
				return;
			}
			//得到有几个其他值
			var otherValueList = new Array();
			var hasValue = true;
			var i = 0;
			while (hasValue) {
				var v = pkids.item(0).getAttribute("value" + (i + 1));
				if (v == undefined) {
					hasValue = false;
				} else {
					i++;
				}
			}
			for ( var k = 1; k <= i; k++) {
				otherValueList[k] = "";
				for ( var j = 0; j < pkids.length; j++) {
					if (pkids.item(j).checked == true) {
						if (otherValueList[k] == "" || otherValueList[k] == undefined || otherValueList[k] == null ) {
							otherValueList[k] = pkids.item(j).getAttribute(
									"value" + k);
						} else {
							otherValueList[k] =pkids.item(j).getAttribute("value" + k)+ "," +otherValueList[k] ;
						}
					}
				}

			}
			return otherValueList[id];
			
		}
		/**
		*1.在名字为pkidList的复选框中自定义属性 value1 (value后面的数字从1开始，如： value1=...  value2=....)
		* 例如：
		*2.要得到当前页中选中的复选框 所有的 value1的值，调用方法 ： getCurrentSelectedByID(1);
		*3。要得到所有已选（包括非当前页选中）的value1的值，调用方法： getAllSelectedByID(1);
		*4。要得到当前页 复选框的值（value） 调用之前的方法： getCurrentSelected();
		*5.要得到所有页面复选框的值（value） 调用之前的方法：getAllSelected();
		*/
		function getAllSelectedByID(id) {
			var pkids = document.getElementsByName("pkidList");
			if(pkids.item(0) == undefined ){
				return;
			}
			//得到有几个其他值
			var otherValueList = new Array();
			var hasValue = true;
			var i = 0;
			while (hasValue) {
				var v = pkids.item(0).getAttribute("value" + (i + 1));
				if (v == undefined) {
					hasValue = false;
				} else {
					i++;
				}
			}
			for ( var k = 1; k <= i; k++) {
				otherValueList[k] = "";
				for ( var j = 0; j < pkids.length; j++) {
					if (pkids.item(j).checked == true) {
						if (otherValueList[k] == "" || otherValueList[k] == undefined || otherValueList[k] == null ) {
							otherValueList[k] = pkids.item(j).getAttribute(
									"value" + k);
						} else {
							otherValueList[k] =pkids.item(j).getAttribute("value" + k)+ "," +otherValueList[k] ;
						}
					}
				}

			}
			var oth = document.getElementById("othervalues");
			var reg = /[\r\n]/g; 
			if (oth.innerHTML.replace(reg,"") == "") {
				return otherValueList[id];
			} else {
				if (otherValueList[id] == "" || otherValueList[id] == undefined || otherValueList[id] == null ) {
					return document.getElementById("othvalue"+id).value;
				}else{
					if(document.getElementById("othvalue"+id).value=="" || document.getElementById("othvalue"+id).value ==undefined){
						return otherValueList[id];
					}else{
						return document.getElementById("othvalue"+id).value+","+otherValueList[id];
					}
					
				}
			}
			
		}

		//获得全部已选
		function getAllSelected() {
			var lastSelected = document.getElementById("selectedPkidList").value;
			var currentSelected = getCurrentSelected();
			var selectedList = "";
			if (lastSelected != null && lastSelected != "") {
				if (currentSelected != "") {
					selectedList = lastSelected + "," + currentSelected;
				} else {
					selectedList = lastSelected;
				}
			} else {
				selectedList = currentSelected;
			}
			return selectedList;
		}
		//清楚所有已选
		function clearSelected() {
			document.getElementById("selectedPkidList").value = "";
			uncheckAll();
		}

		//将当前要删除的复选框去掉。
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
				success : function(data, textStatus) {
						alert(data.message);
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
		
	</script>

</body>