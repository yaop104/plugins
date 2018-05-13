jQuery.fn.extend({
	toolBarInit:function() {
		var cbar = this;
		//		var extendarea=this.children(".extendarea");
		var isextend = false;
		var controlObjs = {
			searchobj:this.find(".bar_search"),
			createobj:this.find(".bar_create"),
			resetpwdobj:this.find(".bar_resetpwd"),
			massdelobj:this.find(".bar_massdel"),
			selectallobj:this.find(".bar_selectall"),
			blankallobj:this.find(".bar_blankall"),
			refreshobj:this.find(".bar_refresh"),

			init:function() {
				/*extendarea.load(this.searchobj.attr("rel"));
				 this.searchobj.click(function(){
				 $(this).toggleClass("sel");
				 extendarea.slideToggle("normal");
				 });*/
				var $liobjs = cbar.find("li[rel]");
				var extnum = $liobjs.length;
				cbar.offsetParent().click(function() {                 //$dm.repanelgroup
					if (isextend) {
						$(".extendarea:visible").slideUp("normal");
						$liobjs.filter(":visible").removeClass("sel");
					}
				});
				for (var i = 0; i < extnum; i++) {
					var $extarea = $("<div class='extendarea'></div>");
					$extarea.click(function(e) {
						e.stopPropagation();
					});
					$extarea.load($liobjs.eq(i).attr("rel"));
					cbar.append($extarea);
				}
				$liobjs.each(function(i) {
					$(this).click(function() {
						$(this).toggleClass("sel").siblings().removeClass("sel");
						cbar.children(".extendarea").eq(i).siblings(".extendarea").slideUp("fast", function() {
							cbar.children(".extendarea").eq(i).slideToggle("normal", function() {
								if (cbar.children(".extendarea").eq(i).is(":visible")) {
									isextend = true;
								} else {
									isextend = false;
								}
							});
						});
						return false;
					});
				});

				this.refreshobj.click(function(){
					
				})
			}
		};
		controlObjs.init();
		return this;
	}
});