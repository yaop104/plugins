<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<title>自定义分组</title>
</head>
<body>
<div class="easyui-layout" fit="true"  border="false">
  	<div region="center"   border="false">
  		<table id="t1"></table>
  		
  			<!-- 窗口-->
			<div id="d1" class="easyui-dialog" buttons="#btn1" title="编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">  
		         <div style="padding:10px 60px 20px 60px">
		            <form id="f1"  class="easyui-form" method="post">  
		                <table>  
		                    <tr>  
		                        <td align="right">名称：</td>  
		                        <td><input class="easyui-validatebox" name="pgpName" required="true" style="width: 152px" id="pgpName"></input></td>  
		                    </tr>  
		                      <tr>  
		                        <td align="right">顺序：</td>  
		                        <td><input class="easyui-validatebox" name="pgpOrder" required="true" style="width: 152px" id="pgpOrder"></input></td>  
		                    </tr> 
		                     <tr>  
		                        <td align="right">状态：</td>  
		                        <td>
		                        <select  class="easyui-combobox" name="pgpState" id="pgpState" style="width:152px;" required="true" editable="false">
		                       		 <option value="1">有效</option>
									 <option value="2">无效</option>
		                        </select>
		                        </td>  
		                    </tr> 
		                </table>  
		            </form>  
		            </div>  
		     </div>
             <div id="btn1">
		    	<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px"> 保  存 </a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px"> 取  消 </a>
		    </div>
  	</div>
 </div>
 <script type="text/javascript" src="${ctx }/js/sys/group/group.js"></script>
</body>
</html>
