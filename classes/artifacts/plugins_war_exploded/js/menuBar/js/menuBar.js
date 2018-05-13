jQuery.fn.extend({
	menuBarInit:function(){
		var maintab=this;
		this.find("li").click(function(){
			$(this).children("a").blur();
			$(this).addClass("sel").siblings().removeClass("sel");
//			var idx=maintab.find("li").index($(this));
		})
	}
});