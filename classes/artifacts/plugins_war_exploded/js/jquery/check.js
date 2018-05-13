	/**
		 * 表单检验
		 * @param name 表单id名称
		 * @param msg 消息名称
		 * @param inputType 表单类型（text,select,radio,checkbox）
		 * @param tab 选项卡
		 * @param max 最大长度
		 * @param min 最小长度（min: -1=可为空）
		 * @returns {Boolean}
		 */
	function isInput(name, msg, max, min){
			var inputType = document.getElementById(name).getAttribute("type");
			var val = document.getElementById(name).value;
			val = (val == null) ? "" : val;
			if(min <= 0){//可为空，不能超过max长度
				if(val.length > max){
					msg = msg + "不能超过"+max+"个字符";
					alert(msg);
					$("#" + name).focus();
					
					return false;
				}
			}else if(min > 0){//不为空，不能超过max长度，不能小于min长度
				if(val.length < min || val.length > max){
					if(inputType == "select-one"){
						msg = "请选择" + msg;
					}else{
						msg = msg + "不能超过"+max+"个字符，不能小于"+min+"个字符";
					}
					
					alert(msg);
					$("#" + name).focus();
				
					return false;
				}
			}
			return true;
		}