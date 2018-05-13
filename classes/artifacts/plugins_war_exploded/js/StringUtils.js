/*-------------------------------------------------------------------------------------------
   文件名称: StringUtils.js
   文件作用: 字符串辅助工具
   程序设计: 黄勇
   创建日期: 2008-11-28
   修改日期: 
-------------------------------------------------------------------------------------------*/
var StrRegEx1 = "`~";		// 第一级分割
var StrRegEx2 = "`;";		// 第二级分割

// 将有2级分割的源字符串转化为Dictionary，形如"ab`;11`~aab`;33`~ac`;aa`~adf3`;3a"。第二级分隔前的字符串不能以数字开头.
function Str2Dictionary(value){
	var valueList = new ActiveXObject("Scripting.Dictionary");
	var values = value.split(StrRegEx1);
	for(var i=0; i<values.length; i++){
		var temp = values[i];
		var temps = temp.split(StrRegEx2);
		valueList.add(temps[0],temps[1]);
	}
	return valueList;
}

// 二维数组转化为2级分隔的字符串
function TwoArray2Str(Arr){
	var Str = "";
	for(var i=0; i<Arr.length-1; i++){
		Str += Array2Str(Arr[i],StrRegEx2) + StrRegEx1;
	}
	if(Arr.length>0)
		Str += Arr[Arr.length-1];
	return Str;
}

// 一维数组转化为regEx1分隔的字符串 
function Array2Str(Arr,regEx1){
	var Str = "";
	for(var i=0; i<Arr.length-1; i++){
		Str += Arr[i] + regEx1;
	}
	if(Arr.length>0)
		Str += Arr[Arr.length-1];
	return Str;
}

// 一维数组转化为regEx1分隔的字符串 
function Array2Str(Arr){
	return Array2Str(Arr,regEx1);
}

// 默认的第二级分隔符分隔的字符串转为一维数组
function Str2ArrayDefault(Str){
	return Str2Array(Str,StrRegEx2);
}

// 分隔的字符串转为一维数组
function Str2Array(Str,regEx2){
	return Str.split(regEx2);
}

// 得到当前时间，可作为全局唯一id
function getCurTime(){
	var date = new Date();
	return date.getTime();
}

// 字符串数组是否包含字符串,大小写不敏感
function StrArrayIncludeStr(arr,str){
	for(var j=0; j<arr.length; j++){
		if(arr[j]!=null && arr[j]!=""){
			if(str.toUpperCase()==arr[j].toUpperCase()){
				return true;
			}
		}
	}
	return false;
}

// 一级分隔的字符串pStr是否包含字符串cStr
function StrSplitIncludeStr(pStr,cStr){
	return StrArrayIncludeStr(Str2ArrayDefault(pStr),cStr);
}

// 将字符串str的内容写入fileName文件，文件存在则覆盖。
function WriteStrToFile(str,fileName){
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var a = fso.CreateTextFile(fileName,true);
	a.WriteLine(str);
	a.Close();
}

/** 源数据的值替换目标字段
  * source 二级分隔的字符串， 形如"ab1`;11`~aab1`;33`~ac1`;aa`~adf31`;3a"
  * dest  二级分隔的字符串，    形如"ab`;ab1`~aab`;aab1`~ac`;ac1`~adf3`;adf31"
  * 返回替换后的字符串，         形如"ab`;11`~aab`;33`~ac`;aa`~adf3`;3a"
  **/
function replaceDicStr(source,dest){
	var dictSource = Str2Dictionary(source);
	var dictDest = Str2Dictionary(dest);
	var a = (new VBArray(dictDest.Keys()));    // 获取主键。
	var s="";
	for(var i=0; i<dictDest.Count; i++){
		s += a.getItem(i)+StrRegEx2+dictSource.Item(dictDest.Item(a.getItem(i)));
		if(i<dictDest.Count)	s += StrRegEx1;
	}
	return s;
}

// 默认值规则替换
function ReplaceDefaultValueReg(str){
	var reAccount = /``ACCOUNT``/i;
	var reId	  = /``ID``/i;
	var reName	  = /``NAME``/i;
	var reHostName= /``HOSTNAME``/i;
	var reHostIp  = /``HOSTIP``/i;
	var reDepartment = /``DEPARTMENT``/i;
	var reUnit	  = /``UNIT``/i;
	var reRoles   = /``ROLES``/i;
	var reUnitId  = /``UNITID``/i;
	var reDeptId  = /``DEPTID``/i;
	var reUserState  = /``USERSTATE``/i;
	var reIsAdmin  = /``ISADMIN``/i;
	var reYear = /``YEAR``/i;
	var reNextYear = /``NEXTYEAR``/i;
	var reLoginTime = /``LOGINTIME``/i;
	var reLoginDate = /``LOGINDATE``/i;
	var curUserInfo = parent.toolFrame.CurrentUser;
	return str.replace(reAccount,curUserInfo.account)
			   .replace(reId,curUserInfo.userId)
			   .replace(reName,curUserInfo.userName)
			   .replace(reHostName,curUserInfo.hostName)
			   .replace(reHostIp,curUserInfo.hostIp)
			   .replace(reDepartment,curUserInfo.department)
			   .replace(reUnit,curUserInfo.unit)
			   .replace(reUnitId,curUserInfo.unitId)
			   .replace(reDeptId,curUserInfo.deptId)
			   .replace(reUserState,curUserInfo.userState)
			   .replace(reIsAdmin,curUserInfo.isAdmin)
			   .replace(reYear,curUserInfo.year)
			   .replace(reNextYear,curUserInfo.nextYear)
			   .replace(reLoginTime,curUserInfo.loginTime)
			   .replace(reLoginDate,curUserInfo.loginDate);
}

// js 接收html问号后面的参数
// alert(QueryString("id"))
function QueryString(sArgName){ 
	var retval = "";
	var str = location.search;
	args = str.split("&");
	for(var i = 0; i < args.length; i ++)	{ 
		str = args[i];
		var arg = str.split("=");
		if(arg.length <= 1) continue;
		if(arg[0] == sArgName) retval = arg[1];
	}
	return retval;
} 

//去除左边的空格，包括全角空格
function LTrim(str) {
	return str.replace(/(^[\s|　]*)/g, "");
}

//去除右边的空格，包括全角空格
function RTrim(str){
	return str.replace(/([\s|　]*$)/g, "");
}

//去除前后空格，包括全角空格
function Trim(str){
	return str.replace(/(^[\s|　]*)|([\s|　]*$)/g, "");
}

// 是否含有非法字符
function StrIncludeInvalidateChar(str){
	if(str.indexOf("<")!=-1) return true;
	if(str.indexOf(">")!=-1) return true;
	if(str.indexOf("/")!=-1) return false;
}

// 对象转字符串形式
function json2str(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null) return json2str(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}

// 驼峰规则的属性转下划线分割的字段
function ProName2FieldName(str){
	var s = "";
	for(var i=0; i<str.length; i++){
		if((str.charCodeAt(i)>=65)&&(str.charCodeAt(i)<=90)){
			s += "_" + str.charAt(i).toLowerCase();
		}
		else{
			s += str.charAt(i);
		}
	}
	return s;
}


$.extend({
	
	showLoadingMsg: function() {
		var coverWidth = document.body.clientWidth - 5;
		var coverHeight = document.body.clientHeight - 5;
		var paddingLeft = coverWidth/2 - 100;
		var paddingTop = coverHeight/2 - 40;

		var coverDiv = document.getElementById("_sme_cover_div");
		var div = document.getElementById("_sme_loading_div");
		
		if (coverDiv == null) {
			coverDiv = document.createElement("DIV");
		    document.body.appendChild(coverDiv);
		    coverDiv.id = "_sme_cover_div";
		    coverDiv.className = "cover_css";
		    coverDiv.style.width = coverWidth + "px";
		    coverDiv.style.height = coverHeight + "px";
		}
		
		if (div == null) {
		    div = document.createElement("DIV");
		    document.body.appendChild(div);
		    div.id = "_sme_loading_div";
		    div.className = "loading_css";
		    div.innerHTML = "加载中...";
		}
		
		coverDiv.style.display = "block";
		coverDiv.style.top = "0px";
		coverDiv.style.left = "0px";
	    
	    div.style.top = paddingTop + "px";
	    div.style.left = paddingLeft + "px";
	    div.style.display = "block";
	},
	
	hideLoadingMsg: function() {
	    var div = document.getElementById("_sme_loading_div");
	    if (div != null) {
	       div.style.display = "none";
	    }
	    
	    var coverDiv = document.getElementById("_sme_cover_div");
	    if (coverDiv != null) {
	    	coverDiv.style.display = "none";
	    }
	}


});










