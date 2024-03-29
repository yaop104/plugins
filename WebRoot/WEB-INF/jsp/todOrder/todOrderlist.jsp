<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%@ include file="/WEB-INF/jsp/sys/taglib2.jsp"%>
	<!-- 引用jQuery的css -->
	<link rel="stylesheet" href="${ctx }/js/webuploader/webuploader.css" type="text/css"/>
	<style type="text/css">
		img{
			width: 100px;
			height: 100px;
		}
		.sl_box
		{
			width:100px;
			height:100px;
			float:left;
			border:solid 1px #eee;
		}
		.sl_dele
		{
			width:40px;
			line-height:20px;
			float:left;
			text-align:center;
		}
		div.standard_padding
		{
			padding-top:20px;
		}
	</style>
	<!-- 引用jQuery的js  -->
	<script type="text/javascript" src="${ctx }/js/webuploader/webuploader.js" ></script>
	<title>信息</title>
</head>
<script language="javascript">
	var grid;
	var ctxall = 'http://114.55.150.199:8888/download/pic/';
	$(function() {
		var colArr = [];

		colArr = [
			{ field:'todOrderUnid', align:'center', width:'150' , title:'ID' },
			{ field:'todOrderOrdernum', align:'center', width:'150' , title:'订单编号' },
			{ field:'todOrderPositionprice', align:'center', width:'120', sortable:'true' , title:'单价（元/天）' },
			{ field:'todOrderPositionname', align:'center',  width:'80', sortable:'true' , title:'广告名称' },
			{ field:'todOrderTotaldays', align:'center',  width:'80', sortable:'true' , title:'推广天数' },
			{ field:'todOrderState', align:'center',  width:'80', sortable:'true' , title:'状态' , formatter : convertState },
			{ field:'asd', align:'center', width:'280' , title:'操作', formatter : ys1}
		];

		var _toolbars = [];


		//初始化显示列表
		grid = $('#t1').datagrid({
			iconCls : 'icon-save',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取。
			striped : true,//显示条纹
			collapsible : true,
			url : '${ctx }/TodOrder/page.do',
			pageList : [10, 15, 20],
			pageSize : 10,
			fitColumns : false,
			fit : true,
			loadMsg : '正在加载数据.......',//当从远程站点载入数据时，显示的一条快捷信息
			pagination : true,//设置true将在数据表格底部显示分页工具栏
			sortName : 'todOrderUnid',//当数据表格初始化时以哪一列来排序
			sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）
			remoteSort : false,//定义是否通过远程服务器对数据排序
			itColumns : false,
			singleSelect : true,//设置为true将只允许选择一行
			idField : 'todOrderUnid',//表明该列是一个唯一列。
			rownumbers : true,//设置为true将显示行数
			frozenColumns:[[
				{field:'ck',checkbox:true}
			]],
			columns:[colArr],
			toolbar : _toolbars
		});

		initUpload();
	});
	var $ = jQuery,
			$list = $('#thelist'),
			$btn = $('#ctlBtn'),
			state = 'pending',
			uploader;
	//获取参数
	function getQueryParams(queryParams) {
		/*  var StartTime = $("#s_startTime").datebox("getValue");
		 var EndTime = $("#s_endTime").datebox("getValue");              */
		var Name = document.getElementById("s_name").value;
		var State = $("#s_state").combobox("getValue");

		/* queryParams.StartTime = StartTime;
		 queryParams.EndTime = EndTime;   */
		queryParams.tptName = Name;
		queryParams.tptState = State;

		return queryParams;

	}

	function searchOption(){
		//查询参数直接添加在queryParams中
		var queryParams = grid.datagrid('options').queryParams;
		queryParams.rows = grid.datagrid('options').pageSize;
		queryParams.page = 0;
		queryParams = getQueryParams(queryParams);
		grid.datagrid('options').queryParams = queryParams;
		grid.datagrid('reload');
	}

	function addOption(){
		$('#d1').dialog('open');
		$('#f1').form('clear');
		$('#f1').form.url='${ctx }/TodOrder/save.do';
	}

	function editOption(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			if(1 == rows.length){
				$('#f1').form.url='${ctx }/TodOrder/save.do';
				$('#f1').form('load',{
					'tptUnid':row.tptUnid,
					'tptType':row.tptType,
					'tptName':row.tptName,
					'tptContactname':row.tptContactname,
					'tptContactphone':row.tptContactphone,
					'tptState':row.tptState,
					'tptDesc':row.tptDesc
				});
				$('#d1').dialog('open');
			}else{
				msgShow('错误','请选择一条要修改的记录！','error');
				grid.datagrid('clearSelections');
			}
		}else{
			msgShow('错误','请选择要修改的记录！','error');
		}
	}

	function deleteOption(){
		var row = grid.datagrid('getSelected');
		var rows = grid.datagrid('getSelections');
		if(row){
			var s='';
			$.each(rows,function(i,n){
				s+=n.tptUnid+',';
			});
			s=s.substr(0,s.length-1);
			$.messager.confirm('确认？', '确认删除所有选中记录吗', function(r){
				if (r){
					$.post('${ctx }/TodOrder/deleteTodOrders.do',{'ids': s},function(data){
						if(data.success){
							msgShow('成功',data.message,'info');
							grid.datagrid('reload');
							grid.datagrid('clearSelections');
						}else{
							msgShow('错误',data.message,'error');
						}
					}, 'json');
				}
			});
		}else{
			msgShow('错误','请选择要删除的记录！','error');
		}
	}

	function convertState(val, rec, index) {
		if(val == '1'){
			return '<span style="color: green">已支付</span>';
		}
		if(val == '2'){
			return '<span style="color: red">未支付</span>';
		}
	}

	function convertType(val, rec, index) {
			return '<span style="color: green">开发商</span>';
	}

	function ys1(val, rec, index) {
		var returnvalue = "";
		if("2" == rec.todOrderState){
		   returnvalue="<img  src='${ctx}/image/table_td_button/check.png' onclick='buyPosition("
				+ rec.todOrderUnid + ",\""
				+ rec.todOrderOrdernum + "\",\""
				+ rec.todOrderPositionprice + "\",\""
				+ rec.todOrderPositionname + "\",\""
				+ rec.todOrderTotaldays + "\")' " +
				"style='cursor:pointer;width:20px;height:20px;vertical-align:middle;'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='buyPosition("
				+ rec.todOrderUnid + ",\""
				+ rec.todOrderOrdernum + "\",\""
				+ rec.todOrderPositionprice + "\",\""
				+ rec.todOrderPositionname + "\",\""
				+ rec.todOrderTotaldays +  "\")'>支付</a>&nbsp;&nbsp;";
		}
		if("1" == rec.todOrderState){
			returnvalue +="<img  src='${ctx}/image/table_td_button/add.png' onclick='updatePosition("
					+ rec.todOrderUnid + ",\""
					+ rec.todOrderOrdernum + "\",\""
					+ rec.todOrderPositionurl + "\",\""
					+ rec.todOrderPositionname + "\",\""
					+ rec.todOrderTotaldays +   "\")' style='cursor:pointer;width:20px;height:20px;vertical-align:middle;'/>&nbsp;<a href='javascript:void(0)' style='height:20px;line-height:30px;vertical-align:middle;' onclick='updatePosition("
					+ rec.todOrderUnid + ",\""
					+ rec.todOrderOrdernum + "\",\""
					+ rec.todOrderPositionurl + "\",\""
					+ rec.todOrderPositionname + "\",\""
					+ rec.todOrderTotaldays +   "\")'>上传素材</a>&nbsp;&nbsp;";

		}
		return returnvalue;
	}
	var orderid;
	var ordernum;
	var orderprice;
	var ordername;
	var orderdays;
	function  saveFormHot(){
		$.post('${ctx }/TodOrder/pay.do',{
			'todOrderUnid': orderid
		},function(data){
			if(data.success){
				msgShow('成功',data.message,'info');
				clearFormHot();
				grid.datagrid('reload');
				grid.datagrid('clearSelections');
			}else{
				msgShow('错误',data.message,'error');
			}
		}, 'json');

	}
	function  saveForPic(){
		$.post('${ctx }/TodOrder/update.do',{
			'todOrderUnid': orderid,
			'todOrderPositionurl': $('#updatePic').val()
		},function(data){
			if(data.success){
				msgShow('成功',data.message,'info');
				clearForPic();
				grid.datagrid('reload');
				grid.datagrid('clearSelections');
			}else{
				msgShow('错误',data.message,'error');
			}
		}, 'json');

	}

	function buyPosition(id, num, price,name, orderdays){
		initUpload();
		orderid = id;
		ordernum = num;
		orderprice = price;
		ordername = name;
		orderdays = orderdays;
		$('#ordernum').html(num);
		$('#ordername').html(name);
		$('#orderprice').html(price);
		$('#orderdays').html(orderdays);
		$('#ordertotals').html(orderdays*price);
		$('#d222').dialog('open');
		$('#f222').form('clear');
	}

	function updatePosition(id, num, price,name, orderdays){
		initUpload();
		orderid = id;
		ordername = name;
		$('#ordername').html(name);
		if(!isNull(price)){
			$('#sl_item').html("");
			$('#sl_item').show();
			$('#updatePic').val(price);

			var str = '<div class="sl_box"><a href="'+ctxall + price + '" target="_blank"><img src="'+ctxall + price + '"/></a></div>';
			str += '<div class="sl_dele"><a href="javascript:deleSL(' +"'"+ price +"'"+ ');">删除</a></div>';

			$('#sl_item').append( str );
		}
		$('#d333').dialog('open');
		$('#f333').form('clear');
	}

	function clearFormHot(){
		orderid = '';
		ordername = '';
		uploader.destroy();
		deleSL('');
		$('#d222').dialog('close');
		$('#f222').form('clear');

	}
	function clearForPic(){
		$('#updatePic').val('')
		uploader.destroy();
		deleSL('');
		$('#d333').dialog('close');
		$('#f333').form('clear');
	}

	function  saveForm(){
		$('#f1').form('submit',{
			url:$('#f1').form.url,
			success:function(data){
				data=parseJSON(data);
				if(data.success){
					msgShow('成功',data.message,'info');
					$('#d1').dialog('close');
					grid.datagrid('load');
					grid.datagrid('clearSelections');
				}else{
					msgShow('错误',data.message,'error');
				}
			}
		});

	}

	function clearForm(){
		$('#d1').dialog('close');
		grid.datagrid('clearSelections');
	}

	function deleSL(path){
		$('#sl_item').html( '');
		$('#updatePic').val('');
	}

	function initUpload(){

		uploader=WebUploader.create({
			//不压缩image
			resize:false,
			//swf文件上传路径
			swf:'${ctx}/img/Webuploader/Uploader.swf',
			//文件接收服务端
			server:'${ctx}/imagefile/updatePic.do',
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick: '#picker',

			//单个文件大小是否超出限制, 超出则不允许加入队列(当前限制250kb)
			fileSingleSizeLimit:256000,

			// 只允许选择图片文件。
			accept: {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,bmp,png',
				mimeTypes: 'image/*'
			}
		});

		// 当有文件添加进来的时候
		uploader.on( 'fileQueued', function( file ) {

			$list.append( '<div id="' + file.id + '" class="item">' +
					'<h4 class="info">' + file.name + '</h4>' +
					'<p class="state">等待上传...</p>' +
					'</div>' );

// 		        $btn.click();
			uploader.upload();
		});
		//抓取上传前的错误信息
		uploader.on( 'error', function( handler ) {
			if(handler=="F_EXCEED_SIZE"){
				alert("图片大小不能超过250KB");
			}
		});
		// 文件上传过程中创建进度条实时显示。
		uploader.on( 'uploadProgress', function( file, percentage ) {
			var $li = $( '#'+file.id ),
					$percent = $li.find('.progress .progress-bar');

			// 避免重复创建
			if ( !$percent.length ) {
				$percent = $('<div class="progress progress-striped active">' +
						'<div class="progress-bar" role="progressbar" style="width: 0%">' +
						'</div>' +
						'</div>').appendTo( $li ).find('.progress-bar');
			}

			$li.find('p.state').text('上传中');

			$percent.css( 'width', percentage * 100 + '%' );
		});

		uploader.on( 'uploadSuccess', function( file , data) {
			$( '#'+file.id ).find('p.state').text('已上传');
			$list.html("");
			if(data.code==500){
				$('#sl_item').show();
				$('#sl_item').html("");
				$('#updatePic').val(data.info);

				var str = '<div class="sl_box"><a href="'+ctxall + data.info + '" target="_blank"><img src="'+ctxall + data.info + '"/></a></div>';
				str += '<div class="sl_dele"><a href="javascript:deleSL(' +"'"+ data.info +"'"+ ');">删除</a></div>';

				$('#sl_item').append( str );
			}
		});

		uploader.on( 'uploadError', function( file ) {
			$( '#'+file.id ).find('p.state').text('上传出错');
		});

		uploader.on( 'uploadComplete', function( file ) {
			$( '#'+file.id ).find('.progress').fadeOut();
		});

		uploader.on( 'all', function( type ) {
// 			        if ( type === 'startUpload' ) {
// 			            state = 'uploading';
// 			        } else if ( type === 'stopUpload' ) {
// 			            state = 'paused';
// 			        } else if ( type === 'uploadFinished' ) {
// 			            state = 'done';
// 			        }

// 			        if ( state === 'uploading' ) {
// 			            $btn.text('暂停上传');
// 			        } else {
// 			            $btn.text('开始上传');
// 			        }
		});

		$btn.on( 'click', function() {
			if ( state === 'uploading' ) {
				uploader.stop();
			} else {
				uploader.upload();
			}
		});

		$("#picker").on( 'click', function() {
			uploader.reset();
		});
	}
</script>
<body>
<div class="easyui-layout" fit="true"  border="false">
	<div region="north" style="height:50px">
		<%--功能区--%>
		<div id="tb" style="padding: 10px; height: auto">
			<%-- 查找管理员信息，根据时间、管理员名 --%>
			<div>
				订单编号:
				<input id="s_name"/>
				按状态：
				<select id="s_state" class="easyui-combobox" name="s_state" style="width: 150px;" panelheight="auto">
					<option value="">全部</option>
					<option value="1">已支付</option>
					<option value="2">未支付</option>
				</select>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchOption()">&nbsp; 查&nbsp;&nbsp;询 &nbsp; &nbsp;</a>
			</div>
		</div>


	</div>
	<div region="center"   border="false">
		<table id="t1"></table>

		<!-- 窗口-->
		<div id="d1" class="easyui-dialog" buttons="#btn1" title="编辑"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:500px;height:600px;overflow: hidden;">
			<div style="padding:10px 60px 20px 60px">
				<form id="f1"  class="easyui-form" method="post">
					<input type="hidden" id="tptUnid" name="tptUnid"/>
					<table>
						<tr>
							<td align="right">广告位命名：</td>
							<td><input class="easyui-validatebox" name="tptName" required="true" style="width: 152px" id="tptName"/></td>
						</tr>
						<tr>
							<td align="right">价格：</td>
							<td><input class="easyui-validatebox" name="tptPrice" required="true" style="width: 152px" id="tptPrice"/></td>
						</tr>
						<tr>
							<td align="right">示例：</td>
							<td>
								<input class="easyui-validatebox" name="tptDemourl" required="true" style="width: 152px" id="tptDemourl"/>
							</td>
						</tr>
						<tr>
							<td align="right">状态：</td>
							<td>
								<select  class="easyui-combobox" name="tptState" id="tptState" style="width:152px;" required="true" editable="false">
									<option value="1">有效</option>
									<option value="2">无效</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><input class="easyui-textbox" id="tptDesc" name="tptDesc" data-options="multiline:true" style="height:60px"/></td>
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

	<!-- 窗口-->
	<div id="d222" class="easyui-dialog" buttons="#btn2" title="支付"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">
		<div style="padding:10px 60px 20px 60px">
			<form id="f222"  class="easyui-form" method="post">
				<table>
					<tr>
						<td align="right">订单编号：</td>
						<td>
							<span id="ordernum"></span>
						</td>
					</tr>
					<tr>
						<td align="right">广告位名称：</td>
						<td>
							<span id="ordername"></span>
						</td>
					</tr>
					<tr>
						<td align="right">单价（元/天）：</td>
						<td>
							<span id="orderprice"></span>
						</td>
					</tr>
					<tr>
						<td align="right">推广天数：</td>
						<td>
							<span id="orderdays"></span>
						</td>
					</tr>
					<tr>
						<td align="right">总价：</td>
						<td>
							<span id="ordertotals"></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="btn2">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveFormHot()" style="width:90px"> 支  付 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearFormHot()" style="width:90px"> 取  消 </a>
	</div>
	<div id="d333" class="easyui-dialog" buttons="#btn3" title="上传"  data-options="novalidate:true,iconCls:'icon-save',closed:true,modal:true,minimizable:false" style="width:400px;height:300px;overflow: hidden;">
		<div style="padding:10px 60px 20px 60px">
			<form id="f333"  class="easyui-form" method="post">
				<input type="hidden" id="updatePic" name="updatePic"/>
				<table>
					<tr style="width: 150px; ">
						<td align="right">上传图片：<span style="color: red">图片仅支持：gif,jpg,jpeg,bmp,png</span></td>
					</tr>
					<tr>
						<td>
							<div id="uploader" class="wu-example">
								<!--用来存放文件信息-->
								<div id="thelist" class="uploader-list"></div>
								<div class="btns">
									<div id="picker">选择文件,点击上传！</div>
									<div style="display: none;">
										<button id="ctlBtn" class="btn btn-default">开始上传</button>
									</div>
								</div>
							</div>
							<span class="help-inline"></span>

							<div id="sl_item" class="standard_padding" style="width:600px;overflow: auto;height: 250px;display:none">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="btn3">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" data-options="iconCls:'icon-ok'" onclick="saveForPic()" style="width:90px"> 确  定 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForPic()" style="width:90px"> 取  消 </a>
	</div>
</div>
</body>
</html>