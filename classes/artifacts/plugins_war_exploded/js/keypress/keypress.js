document.onkeypress = keyListener;
function keyListener(e) {
	var keyCode = e ? e.which : event.keyCode;
	if (keyCode == 13) {
		// 回车事件
		try {
			if (typeof (eval(serach)) == "function") {
//				list();			
				serach();
			}
		} catch (e) {

		}
	}
}