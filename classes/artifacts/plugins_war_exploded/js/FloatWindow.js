/*-------------------------------------------------------------------------------------------
   文件名称: FloatWindow.js
   文件作用: 创建浮动窗口，提示信息用
   程序设计: 黄勇
   创建日期: 2006-08-11
   最后修改: 2009-05-08
-------------------------------------------------------------------------------------------*/
// JavaScript Document

var evcx0=0,evcy0=0,evcx1=0,evcy1=0;
var boxoffx=6,boxoffy=6;
var boxmoveable=false;
var boxhover='#006666',boxnormal='#669966 ';//color;
var boxindex=10000;//z-index;
//开始拖动;
function startDrag(obj){
 if(event.button==1){
  //锁定标题栏;
  obj.setCapture();
  //定义对象;
  var win = obj.parentNode;
  var sha = win.nextSibling;
  //记录鼠标和层位置;
  evcx0 = event.clientX;
  evcy0 = event.clientY;
  evcx1 = parseInt(win.style.left);
  evcy1 = parseInt(win.style.top);
  //记录颜色;
  boxnormal = obj.style.backgroundColor;
  //改变风格;
  obj.style.backgroundColor = boxhover;
  win.style.borderColor = boxhover;
  obj.nextSibling.style.color = boxhover;
  sha.style.left = evcx1 + boxoffx;
  sha.style.top  = evcy1 + boxoffy;
  boxmoveable = true;
 }
}
//拖动;
function drag(obj){
 if(boxmoveable){
  var win = obj.parentNode;
  var sha = win.nextSibling;
  win.style.left = evcx1 + event.clientX - evcx0;
  win.style.top  = evcy1 + event.clientY - evcy0;
  sha.style.left = parseInt(win.style.left) + boxoffx;
  sha.style.top  = parseInt(win.style.top) + boxoffy;
 }
}
//停止拖动;
function stopDrag(obj){
 if(boxmoveable){
  var win = obj.parentNode;
  var sha = win.nextSibling;
  var msg = obj.nextSibling;
  win.style.borderColor     = boxnormal;
  obj.style.backgroundColor = boxnormal;
  msg.style.color           = boxnormal;
  sha.style.left = obj.parentNode.style.left;
  sha.style.top  = obj.parentNode.style.top;
  obj.releaseCapture();
  boxmoveable = false;
 }
}
//获得焦点;
function getFocus(obj){
 if(obj.style.zIndex!=boxindex){
  boxindex = boxindex + 2;
  var idx = boxindex;
  obj.style.zIndex=idx;
  obj.nextSibling.style.zIndex=idx-1;
 }
}
//最小化;
function min(obj)
{
 var win = obj.parentNode.parentNode;
 var sha = win.nextSibling;
 var tit = obj.parentNode;
 var msg = tit.nextSibling;
 var flg = msg.style.display=="none";
 if(flg){
	var msgH = 0;
	if(msg.style.height!="") msgH = parseInt(msg.style.height);
	win.style.height  = msgH + parseInt(tit.style.height) + 2*2;
	sha.style.height  = win.style.height;
	msg.style.display = "block";
	obj.innerHTML = "0";
 }else{
  win.style.height  = parseInt(tit.style.height) + 2*2;
  sha.style.height  = win.style.height;
  obj.innerHTML = "2";
  msg.style.display = "none";
 }
}
//关闭;
function cls(obj){
	var win = obj.parentNode.parentNode;
	var sha = win.nextSibling;
	win.style.visibility = "hidden";
	sha.style.visibility = "hidden";
}

// 根据浮动窗口id关闭
function clsFloatWindow(id){
	var win = document.getElementById("xMsg"+id);
	var sha = win.nextSibling;
	win.style.visibility = "hidden";
	sha.style.visibility = "hidden";
}

// 根据浮动窗口id显示
function showFloatWindow(id){
	var win = document.getElementById("xMsg"+id);
	var sha = win.nextSibling;
	win.style.visibility = "visible";
	sha.style.visibility = "visible";
}

/*
 创建浮动窗口，提示信息用
 id 控件唯一id
 msg 标题显示信息
 text 提示主体信息
 w  width 窗口宽
 h  height 窗口高
 l   left窗口左上角x坐标
 t   top窗口左上角y坐标
*/
function createFloatWindow(id,title,msg,width,height,left,top){
	if(null != document.getElementById("xMsg"+id)){
		showFloatWindow(id);
		return;
	} 
	
	boxindex = boxindex+2;
	 var str = ""
  + "<div id=xMsg" + id + " "
  + "style='"
  + "z-index:" + boxindex + ";"
  + "width:" + width + ";"
  + "height:" + height + ";"
  + "left:" + left + ";"
  + "top:" + top + ";"
  + "background-color:" + boxnormal + ";"
  + "color:" + boxnormal + ";"
//  + "font-size:12px;"
  + "font-family:Verdana;"
  + "position:absolute;"
  + "cursor:default;"
  + "border:2px solid " + boxnormal + ";"
  + "' "
  + "onmousedown='getFocus(this)'>"
   + "<div "
   + "style='"
   + "background-color:" + boxnormal + ";"
   + "width:" + (width-2*2) + ";"
   + "height:20;"
   + "color:white;"
   + "font-size:12px;"
   + "' "
   + "onmousedown='startDrag(this)' "
   + "onmouseup='stopDrag(this)' "
   + "onmousemove='drag(this)' "
   + "ondblclick='min(this.childNodes[1])'"
   + ">"
    + "<span style='width:" + (width-2*12-4) + ";padding-left:3px;'>" + title + "</span>"
    + "<span style='width:12;border-width:0px;color:white;font-family:webdings;'onclick='min(this)'>0</span>"
    + "<span style='width:12;border-width:0px;color:white;font-family:webdings;'onclick='cls(this)'>r</span>"
   + "</div>"
    + "<div style='"
    + "width:100%;"
    + "height:" + (height-20-4) + ";"
    + "background-color:white;"
    + "line-height:14px;"
    + "word-break:break-all;"
    + "padding:3px;"
	+ "font-size:11pt;"
    + "'>" + msg + "</div>"
  + "</div>"
  + "<div style='"
  + "width:" + width + ";"
  + "height:" + height + ";"
  + "top:" + top + ";"
  + "left:" + left + ";"
  + "z-index:" + (boxindex-1) + ";"
  + "position:absolute;"
  + "background-color:black;"
  + "filter:alpha(opacity=40);"
  + "'></div>";
 document.body.insertAdjacentHTML("beforeEnd",str);
}