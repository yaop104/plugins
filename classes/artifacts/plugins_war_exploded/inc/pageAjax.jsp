<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<style type="text/css">
</style>
</head>
<body>
	<input id="pageSize" name="pageSize"  type="hidden" />
	<input id="currentNumber" name="currentNumber"  type="hidden" />
	<input id="recordCount" name="recordCount" type="hidden" />
	<input id="fieldOrder" name="fieldOrder"  type="hidden" />
	<input id="pageCount" name="pageCount" 	type="hidden" />
	共
	<span id="rcc"></span>条记录 &nbsp;
	<span>每页</span>
	<select name="pageNum" id="pageNum" onchange="changePageSize()">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
	</select>&nbsp;条&nbsp;

	<span> <a href="#" onclick="firstPage()" id="first">首页</a> <a
		href="#" onclick="prePage()" id="pre">上一页</a> <a href="#"
		onclick="nextPage()" id="next">下一页</a> <a href="#"
		onclick="lastPage()" id="last">尾页</a>
	</span> &nbsp;
	<div class="pnumberss">
		<input name="inputPage" id="inputPage" type="text" onkeydown="if(event.keyCode==13)selectPage()" />
	</div>
	<span id="pco">/</span>
	<span id="pagesgo"><a href="#" onclick="selectPage()">跳转</a></span>
<script type="text/javascript">
	    /**刷新页面*/
		function reload() {
			ajaxReload();
		}
		$(document).ready(function(){
			ajaxReload();
		}); 
	    
		function ajaxReload(){
			var options = {
					type:"post",
		            dataType:"text/xml",  
		            
		            success : function(data,status) {
		                if(jQuery.parseJSON(data)){
		                	  var obj = jQuery.parseJSON(data); 
		                	  if(obj == undefined  || obj.currentNumber== undefined ){
		                		  alert("请求数据发生错误！");
		                	  }else{
		                		  $("#pageSize").val(obj.pageSize);
		                		  $("#currentNumber").val(obj.currentNumber);
		                		  $("#recordCount").val(obj.recordCount);
		                		  $("#fieldOrder").val(obj.fieldOrder);
		                		  $("#pageCount").val(obj.pageCount);
		                		  $("#pageSize").val(obj.pageSize);
		                		  $("#inputPage").val(obj.currentNumber);
		                		  $("#rcc").html(obj.recordCount);
		                		  $("#pco").html("/&nbsp;"+obj.pageCount);
		                		 /*  if(obj.otherValues!=undefined && obj.otherValues != null && obj.otherValues!= "" && obj.otherValues.length>0){
		                			  var othStr = "";
		                			  for(var i=0;i<obj.otherValues.length;i++){
		                				  othStr += "<input type=\"hidden\" id=\"othvalue"+i+"\"  value=\""+obj.otherValues[i]+"\" name=\"otherValues["+i+"]\" />";
		                			  }
		                			  $("#othervalues").html(othStr);
		                		  } */
		                		  //下拉框处理
		                		  var sel = document.getElementById("pageNum");
		                		  var sel_i = 1;
		                		  if(obj.pageSize == 5){
		                			  sel_i = 0;
		                		  }else if(obj.pageSize == 20){
		                			  sel_i = 2;
		                		  }else if(obj.pageSize == 30){
		                			  sel_i = 3;
		                		  }else{
		                			  sel_i = 1;
		                		  }
		              			  sel.options[sel_i].selected = true;
		              			  
		              			  
		                		  showResponse(obj.resultData);
		                		  initSelected();
		                		  initPage();
		                		  initTable();
		                	  }
		                }else{
		                	alert("请求数据发生错误！");
		                }
		            }  
		        };
				 $("#grid").ajaxSubmit(options);  
			     return false;  
	    }
		
		
</script>

</body>