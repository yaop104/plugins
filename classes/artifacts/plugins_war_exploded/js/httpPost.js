/**
 * 发送http-post 请求，用于下载，防止中文乱码问题
 */
function postDownload(url,params){
	var temp = document.createElement("form");
	temp.action = url;
	temp.method ="post";
	temp.style.diaplay="none";
	for (var x in params){
		 var opt = document.createElement("input");
		 opt.name = x;
		 opt.type ="hidden";
		 opt.value = params[x];
		 temp.appendChild(opt);
	}
	document.body.appendChild(temp);
	temp.submit();
	return temp;
}