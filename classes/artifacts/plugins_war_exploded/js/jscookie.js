//操作cookie相关


function getCookie(name){
	var mycookie=document.cookie;
	var idx=mycookie.indexOf(name+'=');
	var value=null;
	if(idx==-1){
		return null;
	}else{
		var start=mycookie.indexOf('=',idx)+1;
		var end=mycookie.indexOf(';',start);
		if(end==-1){
			end=mycookie.length;
		} 
		value=unescape(mycookie.substring(start,end)); 
	} 
	return value;
}
function setCookie(name,value,expire){
	if(typeof expire == "undefined"){
		expire = 1000;
	}
    var cookieString = name+'='+escape(value)+';';
	var expireDate=new Date();
	if(expire){
		expireDate.setTime(expireDate.getTime()+expire*86400000);//8640000一天的毫秒数
        cookieString += 'expires='+expireDate.toGMTString()+';';
	}
	//cookieString += 'domain=map.routon.com';
    document.cookie = cookieString;
}
function delCookie(name){ 
	var value=getCookie(name);
	if(value){
		var expireDate=new Date();
		expireDate.setTime(expireDate.getTime()-1);
		document.cookie=name+'='+value+';expires='+expireDate.toGMTString();
	}
}