/**弹出层
 * @param wt
 * @param ww
 * @param wh
 * @param url
 */
function openWindow(wt,ww,wh,url)
{
show();
var newdiv = document.createElement("div");
newdiv.setAttribute("id","dialog_bg");
newdiv.setAttribute("class","tipwindowsoutside");
document.body.appendChild(newdiv);
$("body").append("<div id='dialog' style='opacity: 0.8; display: block; filter: progid:DXImageTransform.Microsoft.Alpha(opacity=40);  '></div>");
$("#dialog_bg").append("<div id='show' class='tipwindowsinside'><div id='close' style='display:block;'></div><div class='w001'><div class='titlehead1'>"+wt+"</div><div class='tablelist'><iframe width='100%' height='auto' id='dialogFrame' frameborder='0' src='"+url+"' name='dialogFrame' ></iframe></div></div></div>");
var webH = document.documentElement.clientHeight + document.documentElement.scrollTop;
var webSH = document.documentElement.scrollHeight+ document.documentElement.scrollTop;
var webW = document.documentElement.clientWidth;
var height=document.documentElement.clientHeight;
if(ww > webW)
{
ww = webW;
}
if(wh > webH)
{
wh = webH;
}
var wleft = (webW - ww)/2 + "px";
var wtop = (webH - wh)/2 + "px";


$("#dialog").css({height:webSH+"px"});
$("#dialog_bg").css({width:ww+"px",height:wh+"px",left:0,top:-height});
var frameH = (wh-35) + "px";
$("#dialogFrame").css({height:frameH,"margin-top":"2px"});
$("#close").click(function(){
  $("#dialog_bg").remove();
  $("#dialog").remove();
  $("#divbg").remove();
  });

}

function closePopWindow()
{
	window.parent.document.getElementById("show").removeChild(window.parent.document.getElementById("divbg"));
	window.parent.document.body.removeChild(window.parent.document.getElementById("dialog"));
	window.parent.document.body.removeChild(window.parent.document.getElementById("dialog_bg"));

}

function show(){
	  var div=top.document.createElement("div");

	  div.style.width=top.document.documentElement.scrollWidth+"px"; div.style.height=top.document.documentElement.scrollHeight+"px";

	  div.setAttribute("id","divbg");
	  
	  div.style.backgroundColor="gray";

	  div.style.position="absolute";

	  
	  div.style.left=0;

	  div.style.top=0;

	  div.style.zIndex=30;

	  if(top.document.all)

	  div.style.filter = "alpha(opacity=30)";

	  else div.style.opacity = .3;

	  document.getElementById("show").appendChild(div);

	  }
