	//字符串转json
	function parseJSON(data) {
		var dd={
			'success':false,
			'message':'对不起，系统遇到了未知的错误！'
		};
		try{
			dd=jQuery.parseJSON(data);
		}catch(e){
			alert(e.name + ": " + e.message);
		}
		return dd;
	}
	
	//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
	function msgShow(title, msgString, msgType) {
			$.messager.alert(title, msgString, msgType);
	}

	function  success(msg){
		msgShow('成功',msg,'info');
	}

	function  error(msg){
			msgShow('错误',msg,'error');
	}

	function warning(msg){
			msgShow('警告',msg,'warning');
	}