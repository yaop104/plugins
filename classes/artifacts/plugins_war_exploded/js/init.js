$(document).ready(initpage);

function initpage() {
	// textarea限制长度
	$.each($(".textarea"), function(i, n){
		if(typeof $(n).maxlength){
			$(n).bind("keypress", taKeypress); 
			$(n).bind("keydown", taKeydown); 
			$(n).bind("beforepaste", taBeforePaste); 
			$(n).bind("paste", taPaste); 
		}
	}); 
}
