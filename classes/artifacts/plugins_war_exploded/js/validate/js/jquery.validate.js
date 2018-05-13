

/**
 * 自动除掉输入框中字串两边的空格
 * 用法
 * 1、在input元素加上rules属性，
 * 2、给rules属性赋上trimBlank值，给rules赋多个值不会影响使用
 * 3、由于override原先onblur方法，则必定是先除空格后再做业务自定义的onblur事件
 * added by fanzhen 2011-8-16
 */
$(document).ready(function (){
	$("input,textarea").each(function (i,j) {
		var $obj = $(j).attr("rules");
		if ($obj != undefined && $obj.indexOf("trimBlank") != -1) {
			
			try {
				var oldBlur = $(j)[0].onblur;
				$(j)[0].onblur = function () {
					$(j).val($.trim($(j).val()));
					
					if (oldBlur != undefined) {
						oldBlur();
					}
				}
			} catch (e) {
				//alert(e);
			}
		}
	});
});


var Validator = {

    /**
     * 依赖jquery1.3.js
     * 1：查找具有rules属性的element，得到一个elementArray
     * 2：如果elementArray不为空，显示下标为0的错误提示信息，设置全局属性allPass=false;
     * 3：如果elementArray为空 return
     */
    allPass:true,
    borderColor:"#EDD109",
    borderStyle:"solid",
    getValidatorArray:function(formName) {
        if(formName==null){
           return $("*[rules]");
        }else{
           return $("#"+formName + " [rules]");
        }

    },

    /**
     * 用正则表达式将前后空格
     * 用空字符串替代。
     */
    trim:function(obj) {
        return obj.replace(/(^[\s　]*)|([\s　]*$)/g, "");
    },

    isValid:function(formName) {
        Validator.allPass = true;
        var objs = Validator.getValidatorArray(formName);
        for (var j = 0; j < objs.length; j++) {
            var objold = objs[j];
            // objold.className = "text";
            // objold.style.border = "1px solid #A5D2EB";

           // objold.style.borderStyle = Validator.borderStyle;
           // objold.style.borderColor = Validator.borderColor;

            objold.title = "";
        }

        for (var i = 0; i < objs.length; i++) {
            var rules = objs[i].attributes["rules"].value;
            if (rules != "") {
                var obj = objs[i];
                var failMsg = Validator.getFailMsg(rules, obj) ;
                if (failMsg != "") {
                    failMsg = obj.attributes["tip"].value + failMsg;
                    /**
                     * 在页面上高亮显示错误消息
                     * 改变该对象的css样式
                     **/
                    // obj.className="error";
                   // obj.style.border = "1px solid #cd0a0a";
                    obj.title = failMsg;
                    try{
                        obj.focus(); // IE下隐含对象等不支持该方法，可以直接忽略
                    } catch(e){
                        //alert(e);
                    }
                    alert(failMsg);
                    Validator.allPass = false;
                    break;
                }
            }
        }
       return Validator.allPass;
    },

    getFailMsg:function(rules, obj) {
        var ruleArray = rules.split(",");
        if (rules.indexOf("required") != -1) {
            if (Validator.trim(obj.value) == "") {
                return "不能为空！";
            }
        }
        if(obj.value){
	        for (var i = 0; i < ruleArray.length; i++) {
	        	ruleArray[i] = Validator.trim(ruleArray[i]);
	        	if(!ruleArray[i] || ! typeof ruleArray[i] || ruleArray[i]=="required"){
	        		continue;
	        	}
	            if (ruleArray[i] == "email") {
	                if (!Validator.checkRegexp(obj.value, Validator.email)) {
	                    return "格式不正确,请输入正确的email地址";
	                }
	            }
	            else if (ruleArray[i] == "zipcode") {
	                if (!Validator.checkRegexp(obj.value, Validator.zipcode)) {
	                    return "格式不正确，应为六位数字";
	                }
	            }
	            else if (ruleArray[i] == "tel") {
	                if (!Validator.checkRegexp(obj.value, Validator.tel)) {
	                    return "格式不正确，应为与1399999999/027-8888888类似的格式";
	                }
	            }
	            else if (ruleArray[i] == "string") {
	                if (!Validator.checkRegexp(obj.value, Validator.string)) {
	                    return "格式不正确，只能是以字母开头的字母、数字和下划线的组合。";
	                }
	            }
	            else if (ruleArray[i] == "posint") {
	                if (!Validator.checkRegexp(obj.value, Validator.posint)) {
	                    return "应为正整数";
	                }
	            }
	            else if (ruleArray[i] == "negint") {
	                if (!Validator.checkRegexp(obj.value, Validator.negint)) {
	                    return "应为负整数";
	                }
	            }
	            else if (ruleArray[i] == "posint0") {
	                if (!Validator.checkRegexp(obj.value, Validator.posint0)) {
	                    return "应为非负整数";
	                }
	            }
	            else if (ruleArray[i] == "negint0") {
	                if (!Validator.checkRegexp(obj.value, Validator.negint0)) {
	                    return "应为非正整数";
	                }
	            }
	            else if (ruleArray[i] == "posfloat") {
	                if (!Validator.checkRegexp(obj.value, Validator.posfloat)) {
	                    return "应为正整数或小数";
	                }
	            }
	            else if (ruleArray[i] == "negfloat") {
	                if (!Validator.checkRegexp(obj.value, Validator.negfloat)) {
	                    return "应为负浮点数";
	                }
	            }
	            else if (ruleArray[i] == "negfloat0") {
	                if (!Validator.checkRegexp(obj.value, Validator.negfloat0)) {
	                    return "应为非正浮点数";
	                }
	            }
	            else if (ruleArray[i] == "posfloat0") {
	                if (!Validator.checkRegexp(obj.value, Validator.posfloat0)) {
	                    return "应为非负浮点数";
	                }
	            }
	            else if (ruleArray[i] == "num") {
	                if (!Validator.checkRegexp(obj.value, Validator.num)) {
	                    return "应为数值";
	                }
	            }
	            else if (ruleArray[i] == "url") {
	                if (!Validator.checkRegexp(obj.value, Validator.url)) {
	                    return "格式不正确,正确格式为http://***";
	                }
	            }
	            
	
	            //自增规则
	            else if (ruleArray[i] == "mobile") {
	                if (!Validator.checkRegexp(obj.value, Validator.mobile)) {
	                    return "格式不正确，应为与(86)13866789876类似的格式；" +
	                    		"\n" +
	                    		"国际区号为1～4位，可不填；" +
	                    		"\n" +
	                    		"手机号11位，13、15、18开头";
	                }
	            }
	            else if (ruleArray[i] == "telephone") {
	                if (!Validator.checkRegexp(obj.value, Validator.telephone)) {
	                    return "格式不正确，应为与(86)021-88888888*222类似的格式；" +
	                    		"\n" +
	                    		"国际区号为1～4位，可不填，分机号1～6位，可不填；" +
	                    		"\n" +
	                    		"电话号6～8位，分机连接符为 \"-\"或\"*\"";
	                }
	            }
	            else if (ruleArray[i] == "intnum") {
	                if (!Validator.checkRegexp(obj.value, Validator.intnum)) {
	                    return "格式不正确，应为整数，" +
	                    		"\n" +
	                    		"包括正整数、负整数和0";
	                }
	            }
	            else if (ruleArray[i] == "realnum") {
	                if (!Validator.checkRegexp(obj.value, Validator.realnum)) {
	                    return "格式不正确，应为实数，" +
	                    		"\n" +
	                    		"包括正数、负数和0";
	                }
	            }
	            else if (ruleArray[i] == "text") {
	                if(obj.attributes["maxlength"]){
	                    var len = obj.attributes["maxlength"].value;
	                    if(len != ""){
	                        var text_length = obj.value.length;
	                        if(text_length > parseInt(len)){
	                            return "超过了" + len + "个字数限制！";
	                        }
	                    }
	                }
	            }
	            else if (ruleArray[i] == "ip") {
	                if (!Validator.checkRegexp(obj.value, Validator.ip)) {
	                    return "格式不正确，应为与192.168.1.8类似的IP地址格式";
	                }
	            }
	            else if (ruleArray[i] == "currency") {
	                if (!Validator.checkRegexp(obj.value, Validator.currency)) {
	                    return "格式不正确，应为6.00或6";
	                }
	            }
	            else if (ruleArray[i] == "discount") {
	                if (!Validator.checkRegexp(obj.value, Validator.discount)) {
	                    return "格式不正确，范围应该是100到0";
	                }
	            }
	            else if (ruleArray[i] == "idcard") {
	                if (!Validator.isIdCard(obj.value)) {
	                    return "不正确";
	                }
	            }

	        }
        }

        return "";
    },
    
    // 身份证验证
    isIdCard : function(number){
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if(re == null) return false;
		if(re[1] >= area.length || area[re[1]] == "") return false;
		if(re[2].length == 12){
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		}
		else{
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if(!this.isDate(date, "ymd")) return false;
		var sum = 0;
		for(var i = 0;i<=16;i++){
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai +=  verify.charAt(sum%11);
		return (number.length ==15 || number.length == 18 && number == Ai);
	},
	// 是否日期
	isDate : function(op, formatString){
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch(formatString){
			case "ymd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-/])(\\d{1,2})\\4(\\d{1,2})$"));
				if(m == null ) return false;
				day = m[6];
				month = m[5]*1;
				year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy" :
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if(m == null ) return false;
				day = m[1];
				month = m[3]*1;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default :
				break;
		}
		if(!parseInt(month)) return false;
		month = month==0 ?12:month;
		var date = new Date(year, month-1, day);
        return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
		function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
	},
    
    checkRegexp:function(obj, regexp) {
		// added by fanzhen 2012-02-20 为支持sybase查询出来有空串，故加如下判断
		if ($.trim(obj).length == 0) {
			return true;
		}
        if (!( regexp.test(obj) )) {
            return false;
        } else {
            return true;
        }
    },
    email:/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
    zipcode:/^(\d){6}$/,           //邮政编码
    tel:/^(0?1[3,5](\d){9}(-(\d){1,6})?|0(\d){3}-(\d){7}(-(\d){1,6})?|0(\d){2}-(\d){8}(-(\d){1,6})?|00(\d){11,20})$/,   //电话号码
    string:/^[a-zA-Z][a-zA-Z0-9_]*$/,      //匹配帐号是否合法(字母开头，允许字母数字下划线)
    posint:/^[1-9]\d*$/,      // 正整数
    negint :/^-[1-9]\d*$/,    // 负整数
    posint0:/^([1-9]\d*)|0$/,   // 正整数，包含0
    negint0 :/^(-[1-9]\d*)|0$/,    // 负整数，包含0
    posfloat:/^(([1-9]\d*)|0)(\.\d+)?$/,      // 正浮点数
    negfloat :/^-(([1-9]\d*)|0)(\.\d+)?$/,    // 负浮点数
    num :/^[-+]?\d+(\.\d+)?$/    // 数值
    //自增规则
    ,
    ip:         /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/,
    mobile:		/^((\((\d){1,4}\))?1[3,5,8](\d){9})$/,			//手机号码(86)13866789876-，(86)可选
    telephone:	/^(\((\d){1,4}\))?0(\d){2,3}-(\d){6,8}([-,\*](\d){1,6})?$/,	//电话号码(86)021-88888888*222，国际区号1～4位，可选，国内区号3～4位，电话号码6～8位，分机号1～6位，可选
    intnum:		/^(((-)?[1-9]+(\d)*)|0)$/,		//整数，包括正整数、负整数和0
    realnum:	/^(((-)?((([1-9]\d*)|0)\.\d+))|0)$/,		//实数，包括正浮点数、负浮点数和0
    currency: 	/^\d+(\.\d{1,2})?$/,	//货币类型
    discount:	/^(100)|(\d{1,2})$/,
    // url 验证 
    url:        /^(https|http|ftp|rtsp|mms)?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/

};
