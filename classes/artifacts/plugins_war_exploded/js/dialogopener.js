/*回调函数*/
var callFunc;
/*打开窗口*/
function innerWindowOpen(title, width, height, modal) {
    $('#dialog').dialog({
        title:title,
        autoOpen: false,
        width:width,
        height:height,
        modal:modal
    });
    $('#dialog').dialog('open');
}

function innerWindowClose() {
    $('#dialogIframe').attr("src", "");
    $('#dialog').dialog('close');
}

/*回调函数*/
var childDialogCallFunc;
/*打开窗口*/
function childDialogInnerWindowOpen(title, width, height, modal) {
    $('#childDialog').dialog({
        title:title,
        autoOpen: false,
        width:width,
        height:height,
        modal:modal
    });
    $('#childDialog').dialog('open');
}

function innerWindowCloseChild() {
    $('#childDialogIframe').attr("src", "");
    $('#childDialog').dialog('close');
}





// 弹出层对话框，用iframe实现
function openDialog(title, url, width, height, callF, modal) {
	if (modal == null) {
		modal = true;
	}
    var random_url = url;
	if(url.indexOf("?") == -1){
		random_url = url + "?random=" + (new Date()).getTime();
	} else {
		random_url = url + "&random=" + (new Date()).getTime();
	}

    callFunc = callF;
	innerWindowOpen(title, width, height, modal);
    $('#dialogIframe', document).attr("src", random_url);
}

//弹出层对话框，用iframe实现
function openChildDialog(title, url, width, height, callF, modal) {
	if (modal == null) {
		modal = true;
	}

  var random_url = url;
	if(url.indexOf("?") == -1){
		random_url = url + "?random=" + (new Date()).getTime();
	} else {
		random_url = url + "&random=" + (new Date()).getTime();
	}

  childDialogCallFunc = callF;
  childDialogInnerWindowOpen(title, width, height, modal);
  $('#childDialogIframe', document).attr("src", random_url);
}
