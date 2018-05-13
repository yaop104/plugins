<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
<style>
		.toolbar{
			height:30px;
			padding:200px;
		}
		div table tr td{
			padding: 10px 0;
		}
	</style>
<title>推送</title>
</head>
<body>
<div class="easyui-layout" fit="true"  border="false">
  	<div region="center"   border="false">
			<div class="toolbar">
				选择推送类型：
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="hotSend()">按热度推送</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selfSend()">自定义推送</a>
			</div>  		
  			<!-- 窗口-->
			<div id="d1" class="easyui-dialog" buttons="#btn1" title="推送"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width: 750px; height: 400px;">  
		            <form id="f1"  class="easyui-form" method="post">
		            	<input type="hidden" id="cjid" name="cjid"/>  
		            	<input type="hidden" id="cjurl" name="cjurl"/>  
			            <div class="easyui-panel" title="插件信息" style="width: 750px" id="highdiv1">
			                <table width="100%" border="0" cellpadding="0" cellspacing="0">  
			                    <tr>
									<td align="right">
										选择插件类型:
									</td>
									<td colspan="5">
										 <select id="cjtype" class="easyui-combobox" name="cjtype" style="width:165px;">
										    <option value="0">正式插件</option>
										    <option value="1">测试插件</option>
										 </select>  
									</td>
								</tr>
								<tr>
									<td align="right">
										插件列表:
									</td>
									<td colspan="5">
										<input name="cjlx" id="cjlx" />
									</td>
								</tr>
								<tr>
									<td align="right">
										插件名称:
									</td>
									<td colspan="5">
										<input name="cjmc" id="cjmc" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										插件版本:
									</td>
									<td colspan="5">
										<input name="cjbb" id="cjbb" readonly="readonly"/>
									</td>
								</tr>
			                </table>  
		                </div>
						<div class="easyui-panel" title="热度信息" style="width: 750px;" id="highdiv2">
							 <table width="100%" border="0" cellpadding="0" cellspacing="0">  
			                    <tr>
									<td align="right">
										选择推送人数:
									</td>
									<td colspan="5">
										 <select id="tsrs" class="easyui-combobox" name="tsrs" style="width:165px;">
										    <option value="10" selected="selected">10</option>
										    <option value="50">50</option>
										    <option value="100">100</option>
										    <option value="500">500</option>
										    <option value="1000">1000</option>
										 </select>  
									</td>
								</tr>
			                </table>  
		                </div>
		                <div class="easyui-panel" title="自定义信息" style="width: 750px;" id="highdiv3">
			                <table width="100%" border="0" cellpadding="0" cellspacing="0">  
			                    <tr>
			                    	<td colspan="6" align="left" style="padding-left: 10px;">
			                    		输入推送账号:
			                    	</td>
			                    </tr>
			                    <tr>
									<td colspan="6"  style="padding-left: 50px;">
										<textarea rows="10" cols="100" id="tszdy"></textarea>
									</td>
								</tr>
			                </table>  
		                </div>
		            </form>  
		     </div>
             <div id="btn1">
		    	<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px"> 保  存 </a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px"> 取  消 </a>
		    </div>
  	</div>
 </div>
 <script src="<%=basePath%>js/jquery/check.js" type="text/javascript"></script>
 <script src="<%=basePath%>js/jquery/check.js" type="text/javascript"></script>
 <script type="text/javascript">
 var sendtype='0';
	$(function() {
		
		$('#cjlx').combogrid({
			delay : 500,
			mode : 'remote',
			panelWidth : 450,
			pagination : true,
			pageList : [10, 15, 20],
		    idField:'pAppdetailId',
		    textField:'pAppdetailName',
		    url:encodeURI('${ctx}/appDetail/page.do?cjtype='+ $('#cjtype').combobox('getValue')),
		    columns:[[
		        {field:'pAppdetailName',title:'插件名称',width:300},
		        {field:'pAppdetailVersion',title:'插件版本',width:100}
		    ]],
		    onClickRow : function(rowIndex, rowData) {
 					$('#cjmc').val(rowData.pAppdetailName);
 					$('#cjbb').val(rowData.pAppdetailVersion);
 					$('#cjid').val(rowData.pAppdetailId);
 					$('#cjurl').val(rowData.pAppdetailUrl);
 				}
		});   		
	});
	 
	function hotSend(){
		sendtype = '1';
		$('#d1').dialog('open');
		$('#highdiv3').panel('close');
		$('#highdiv2').panel('open');
	}
	
	function selfSend(){
		sendtype = '2';
		$('#d1').dialog('open');
		$('#highdiv2').panel('close');
		$('#highdiv3').panel('open');
	}
 
	function  saveForm(){
		
		
		if(checkform()){
			
			var cjmc = $('#cjmc').val();
			var cjurl = $('#cjurl').val();
			var cjid = $('#cjid').val();
			var cjbb = $('#cjbb').val();
			var tszdy = $('#tszdy').val();
			var cjtype = $('#cjtype').combobox('getValue');
			var tsrs = $('#tsrs').combobox('getValue');
			
			if('1'==sendtype){
				$.post('${ctx}/PlgSend/save.do',{'psdPlgname' : cjmc ,'psdPlgversion' : cjbb ,'psdPlgurl' : cjurl ,'psdAppid' : cjid ,'psdState' : cjtype, 'psdAccount' : tsrs ,'psdIssend' : sendtype},function(data){
					if(data.success){
						success(data.message);
					}else{
						error(data.message);
					}
				},'json');
			}else if('2'==sendtype){
				$.post('${ctx}/PlgSend/save.do',{'psdPlgname' : cjmc ,'psdPlgversion' : cjbb ,'psdPlgurl' : cjurl ,'psdAppid' : cjid ,'psdState' : cjtype, 'psdAccount' : tszdy ,'psdIssend' : sendtype},function(data){
					if(data.success){
						success(data.message);
					}else{
						error(data.message);
					}
				},'json');
			}
			
			sendtype = '0';
			$('#d1').dialog('close');
		}
		
	}
	
	function clearForm(){
		sendtype = '0';
		$('#d1').dialog('close');
		
	}
	
	function checkform(){
		
		if(!isInput('cjmc', '插件名称', 10000, 1)) return false;
		if(!isInput('cjurl', '插件url地址', 10000, 1)) return false;
		if(!isInput('cjbb', '插件版本', 10000, 1)) return false;
		if('2'==sendtype){
			if(!isInput('tszdy', '自定义推送账户', 10000, 1)) return false;	
		}
		
		
		return true;
	}
 </script>
</body>
</html>
