//获取网页可见区域高度
function getTotalHeight() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.offsetHeight : document.body.offsetHeight;
	} else {
		return self.innerHeight;
	}
}

//获取网页可见区域宽度
function getTotalWidth() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.scrollWidth : document.body.scrollWidth;
	} else {
		return self.innerWidth;
	}
}

//获取网页可见区域宽度（带滚动条）
function getMaskWidth() {
	var sheight = window.document.compatMode == "CSS1Compat" ? window.document.documentElement.scrollHeight : window.document.body.scrollHeight;
	if ($.browser.msie) {
		return getTotalWidth();
	} else {
		if (sheight > window.self.innerHeight) {
			return getTotalWidth() - 17;
		} else {
			return getTotalWidth();
		}
	}
}

//获取网页被卷去的高
function scrollTop() {
	var scrollPos;
	if (typeof window.pageYOffset != 'undefined') {
		scrollPos = window.pageYOffset;
	}
	else if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
		scrollPos = document.documentElement.scrollTop;
	}
	else if (typeof document.body != 'undefined') {
		scrollPos = document.body.scrollTop;
	}
	return scrollPos;
}

//js设置cookie
function setCookie(name,value) {
	var Days = 30;
	var exp  = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//js获取cookie
function getCookie(name) {
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null) return unescape(arr[2]); return null;
}

//js删除cookie
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

// Ajax加载远程 HTML 文件代码并插入至 DOM 中
function ajaxLoad(obj_id,url,data,callback) {
	var random_url = url;
	if(url.indexOf("?") == -1){
		random_url = url + "?random=" + (new Date()).getTime();
	} else {
		random_url = url + "&random=" + (new Date()).getTime();
	}
    $("#" + obj_id).load(random_url,data,callback);
}

// 清除表单的内容
$.fn.clearForm = function() {
	$(':input', this)
			.not(":button, :submit, :reset, :hidden, :[readonly='readonly']")
			.val('')
			.removeAttr('checked')
			.removeAttr('selected');
};

//dropWindow 组件
$.fn.dropWindow = function() {
	var isIE6=( $.browser.msie && /6.0/.test(navigator.userAgent) )? true:false;
	this.each(function() {
		var displayId = $(this).attr("rel");
		var displayparent = $("#" + displayId + "");
		var display = displayparent.contents();
		var appear = $(this);
		var oh = (displayparent.css("height")) ? parseInt(displayparent.css("height").split("px")[0]) : 100;
		var ow = (displayparent.css("width")) ? parseInt(displayparent.css("width").split("px")[0]) : 200;
		var offx;
		var offy;
		var extendarea = $("<div class='extendarea' style='width:" + displayparent.css("width") + ";height:" + displayparent.css("height") + ";display:none'></div>");
		if(isIE6){
			var overlay=$("<iframe frameborder='0' style='position:absolute;z-index:2000;width:" + (parseInt(ow)+2) + "px;height:" + (parseInt(oh)+1) + "px'><html></html></iframe>");
		}

		var closefunc = function(e) {
			if (e.data.clickme) {
				extendarea.hide();
				displayparent.append(display);
				if(isIE6) overlay.remove();
				extendarea.remove();
				$(document.body).unbind("click", closefunc);//alert("bodyclick");
			}
		};
		$(this).click(function(e) {
			var limh = appear.offset().top + appear.outerHeight() + oh;
			var limw = appear.offset().left + ow + 2;

			if (limh < getTotalHeight()) {
				offy = limh - oh;
				extendarea.css("border-top", "none");
			} else {
				offy = limh - appear.outerHeight() - 2 * oh - 1;
				extendarea.css("border-bottom", "none");
			}
			if (limw > getTotalWidth()) {
				offx = getTotalWidth() - ow - 2;
			} else {
				offx = appear.offset().left;
			}
			extendarea.css({"top":offy + "px","left":offx + "px"});
			if(isIE6) overlay.css({"top":offy + "px","left":offx + "px"});

			if (!extendarea.is(":visible")) {
				$(document.body).trigger("click", {clickme:false});
				$(document.body).bind("click", {clickme:true}, closefunc);
				if(isIE6) overlay.appendTo($(document.body));
				extendarea.appendTo($(document.body));
				extendarea.append(display).show().click(function() {
					return false;
				});

			} else {
				extendarea.hide();
				displayparent.append(display);
				extendarea.remove();
				if(isIE6) overlay.remove();
			}
			return false;

		});
	});
	return this;
};

//dropURL 组件
$.fn.dropURL = function() {
	var isIE6=( $.browser.msie && /6.0/.test(navigator.userAgent) )? true:false;
	this.each(function() {
		var appear = $(this);
		var width = parseInt(appear.attr("dpwidth"));
		var height = parseInt(appear.attr("dpheight"));
		var URL = appear.attr("url");
		var offx;
		var offy;
		var extendarea = $("<div class='extendarea' style='width:" + width + "px;height:" + height + "px;display:none'><iframe width='100%' height='100%' frameborder='0' allowtransparency='true'></iframe></div>");
		if(isIE6){
			var overlay=$("<iframe frameborder='0' style='position:absolute;z-index:2000;width:" + (parseInt(width)+2) + "px;height:" + (parseInt(height)+1) + "px'><html></html></iframe>");
		}

		var closeurlfunc = function(e) {
			if (e.data.clickme) {
				if(isIE6) overlay.remove();
				extendarea.hide().remove();
				$(document.body).unbind("click", closeurlfunc);
			}
		};
		$(this).click(function() {
			var limh = appear.offset().top + appear.outerHeight() + height;
			var limw = appear.offset().left + width + 2;
			if (limh < getTotalHeight()) {
				offy = limh - height;
				extendarea.css("border-top", "none");
			} else {
				offy = limh - appear.outerHeight() - 2 * height - 1;
				extendarea.css("border-bottom", "none");
			}
			if (limw > getTotalWidth()) {
				offx = getTotalWidth() - width - 2;
			} else {
				offx = appear.offset().left;
			}
			extendarea.css({"top":offy + "px","left":offx + "px"});
			if(isIE6) overlay.css({"top":offy + "px","left":offx + "px"});

			if (!extendarea.is(":visible")) {
				$(document.body).trigger("click", {clickme:false});
				$(document.body).bind("click", {clickme:true}, closeurlfunc);
				if(isIE6) overlay.appendTo($(document.body));
				extendarea.appendTo($(document.body)).end().children("iframe").attr("src", URL);
				extendarea.show().attr("open", true).click(function() {
					return false;
				});
			} else {
				extendarea.hide().remove();
				if(isIE6) overlay.remove();
			}
			return false;
		});
	});
};

// 切换元素的可见状态
function toggle(element_id) {
    $("#" + element_id).toggle();
}

//------------------------------------------textarea实现maxlength控制--------------------------------------//
//------浏览器不支持textarea 的maxlength属性，框架提供支持
//------为需要限制长度的textarea设置 class="textarea"  maxlength="50"两个属性即可
//------maxlength全部小写，值为数据库中字段长度的1半
function taKeypress(){
	var obj = event.srcElement;
	var maxLength = obj.maxlength;
	if(!isNaN(maxLength)){
        maxLength = parseInt(maxLength);
        var oTR = document.selection.createRange();
        if(oTR.text.length >= 1)
            event.returnValue = true;
        else if(obj.value.length > maxLength-1)
            event.returnValue = false;
    }
}

function taKeydown(){
	// 屏蔽esc键
	if(event.keyCode==27){
		event.keyCode=0;
		event.returnValue=false;
		return;
	}
	// 对系统键不做处理
	if(event.ctrlKey || event.altKey || event.shiftKey){
		return;
	}
	// tab键直接返回
	if(event.keyCode==9)
		return;
	var obj = event.srcElement;
	setTimeout(function()
    {
		var maxLength = obj.maxlength;
        maxLength = parseInt(maxLength);
        if(!isNaN(maxLength)){
            if(obj.value.length > maxLength-1){
				var oTR = document.selection.createRange();
                oTR.moveStart("character", -1*(obj.value.length-maxLength));
                oTR.text = "";
            }
        }
    },1)

}

function taBeforePaste(){
	var obj = event.srcElement;
	var maxLength = obj.maxlength;
	if(!isNaN(maxLength))
        event.returnValue = false;
}

function taPaste(){
	var obj = event.srcElement;
	var maxLength = obj.maxlength;
	if(!isNaN(maxLength)){
        event.returnValue = false;
        maxLength = parseInt(maxLength);
        var oTR = document.selection.createRange();
        var iInsertLength = maxLength - obj.value.length + oTR.text.length;
        var sData = window.clipboardData.getData("Text").substr(0, iInsertLength);
        oTR.text = sData;
    }
}

//------------------------------------------textarea实现maxlength控制--------------------------------------//

/**
 * 给select控件增加项
 * @param id		select的ID
 * @param text		item的显示值
 * @param value		item的实际值
 * @param selected	是否选中
 * @return
 */
function AddComboBoxItem(id,text,value,selected){
	if(selected){
		$("<option value='"+value+"' selected>"+text+"</option>").appendTo("#"+id); 
	}
	else{
		$("<option value='"+value+"'>"+text+"</option>").appendTo("#"+id); 
	}
}
