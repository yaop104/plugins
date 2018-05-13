
function appendLoading(url){
	  var div=document.createElement("div");

	  var webW = document.documentElement.clientWidth;
	  var height=document.documentElement.clientHeight;

	  div.style.width=webW+"px"; 
	  div.style.height=height+"px";

	  div.setAttribute("id","divloading");
	  
	  div.style.backgroundColor="#ffffff";

	  div.style.position="absolute";
	  
	  div.style.left=0;

	  div.style.top=0;

	  div.style.zIndex=30;

	  if(top.document.all)

	  div.style.filter = "alpha(opacity=40)";

	  else div.style.opacity = .4;
	  
	  var imgurl=url +"img/loading.gif";
	  var img = new Image();
	  img.src =imgurl;
	  img.style.position="absolute";
	  img.style.left =(webW/2-30)+"";
	  img.style.top=(height/2-40)+"";
	  div.appendChild(img);
	  
	  document.body.appendChild(div);
	 
}

function removeLoading(){
	document.body.removeChild(document.getElementById("divloading"));
}

function showLoading(){
	var url = top.location.href;
	var a =url.split("/");
    var b=a[a.length-1];
	var uri =url.substr(0,url.length-b.length)
	var img=uri+"img/loading.gif";
	$('#icon_loading').html('<img src="'+img+'" width="35" height="35" />');
}
function showLoadingWithId(id){
	var url = top.location.href;
	var a =url.split("/");
    var b=a[a.length-1];
	var uri =url.substr(0,url.length-b.length)
	var img=uri+"img/loading.gif";
	id="#"+id;
	$(id).html('<img src="'+img+'" width="35" height="35" />');
}
function showLoadingWithSize(id,size){
	var url = top.location.href;
	var a =url.split("/");
    var b=a[a.length-1];
	var uri =url.substr(0,url.length-b.length)
	var img=uri+"img/loading.gif";
	id="#"+id;
	$(id).html('<img src="'+img+'" width='+size+' height='+size+' />');
}

function hiddenLoading(data){
	if(data==null){
		data="";
	}
	$('#icon_loading').html(data);
}
function hiddenLoadingWithId(id,data){
	if(data==null){
		data="";
	}
	id="#"+id;
	$(id).html(data);
}

