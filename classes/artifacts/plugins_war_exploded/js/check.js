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
			var val = document.getElementById(name).value.trim();
			val = (val == null) ? "" : val;
			if(min <= 0){//可为空，不能超过max长度
				if(val.length > max){
					msg = msg + "长度不能超过"+max+"字符";
					alert(msg);
					$("#" + name).focus();
					
					return false;
				}
			}else if(min > 0){//不为空，不能超过max长度，不能小于min长度
				if(val.length < min || val.length > max){
					if(inputType == "select-one"){
						msg = "请选择" + msg;
					}else{
						msg = msg + "长度必须在" + min +"-"+ max +"字符之间";
					}
					
					alert(msg);
					$("#" + name).focus();
				
					return false;
				}
			}
			return true;
		}
	
	function isMobilePhone(mobilePhone,allownull){
		var len=mobilePhone.length;
		if(len==0){
			if(allownull)return true;
			else return false;
		}else{
			var p2 = /^(13[0-9]|14[0-9]|15[0-9]|17[0|1|3|6|7|8]|18[0|1|2|3|4|5|6|7|8|9])\d{8}$/;
			return p2.test(mobilePhone);
		}
	}
	
	function isEmpty(name, msg){
		var val = $('#' + name).val();
		if (val == null || val.length == 0) {
			alert(msg);
			$("#" + name).focus();
			return false;
		}
		return true;
	}
	
	/** 
     * js截取字符串，中英文都能用 
     * @param str：需要截取的字符串 
     * @param len: 需要截取的长度 
     */
    function cutstr(str, len) {
    	if (str.length >= len) { 
    		var str_cut = "";
    		str_cut = str.substring(0, len);   // 取子字符串。
            str_cut = str_cut.concat("...");
            return str_cut;
    	} else{
    		return str;
    	}
    }
    
    function isNum(num){
	     var r=num.match(/^(0|[1-9][0-9]*)$/);//
	     if (r==null) return false;
	     else return true;
	}

	/**
	 * 判断是否null
	 * @param data
	 */
	function isNull(data){
		return (data == "" || data == undefined || data == null || data == "null");
	}

	var CommonYao = {

		//EasyUI用DataGrid用日期格式化
		DateFormatter: function (value, rec, index) {
			if (value == undefined) {
				return "";
			}
			var date = new Date(value);
			var datetime = date.getFullYear()
				+ "-"// "年"
				+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
				+ (date.getMonth() + 1))
				+ "-"// "月"
				+ (date.getDate() < 10 ? "0" + date.getDate() : date
					.getDate());
			return datetime;
		}
	};