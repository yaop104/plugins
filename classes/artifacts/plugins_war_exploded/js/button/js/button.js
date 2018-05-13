/**
 * Created by IntelliJ IDEA.
 * User: catac
 * Date: 2010-6-7
 * Time: 17:17:32
 * To change this template use File | Settings | File Templates.
 */
(function($) {
	$.fn.dpbtn = function(settings) {
		var offx,offy;
		var dpbtn = this;
		var ghost;
		var cellgroup = $("<dl class='cellgroup'></dl>");

		$.each(settings.items, function(i, item) {
			if (item.label != "spliter") {
				var cellitem = $("<dd><span class='ico'></span>" + item.label + "</dd>");
				if (item.disabled) {
					cellitem.addClass("dis").mousedown(function(){
						return false;
					});
				}
				cellitem.not(".dis").hovereff("hov").mousedown(function() {
					cellgroup.appendTo(dpbtn);
					 cellgroup.children().removeClass("hov");
					 ghost.remove();
					if (!item.disabled) {
						item.clickFunc();
					}
				}).children(".ico").addClass(item.ico);
			} else {
				var cellitem = $("<dt></dt>");
			}
			cellgroup.append(cellitem);
		});
		var closefunc = function() {
			if (ghost.is(":visible")) {
				cellgroup.appendTo(dpbtn);
				cellgroup.children().removeClass("hov");
				ghost.remove();
				$(document.body).unbind("mousedown", closefunc);
			}
		};

		cellgroup.appendTo(dpbtn);
		dpbtn.mousedown(function(e) {
			if (cellgroup.is(":visible")) {
				closefunc();
				return;
			}
			ghost = ($("#ghost").length < 1) ? $("<div id='ghost'></div>").appendTo($(document.body)) : $("#ghost");
			cellgroup.appendTo(ghost);
			var oh = ghost.outerHeight();
			var ow = (ghost.outerWidth() >= dpbtn.outerWidth()) ? ghost.outerWidth() : dpbtn.outerWidth();
			cellgroup.width(ow-4);//4 is border and padding
			//			e.preventDefault();
			var limh = dpbtn.offset().top + dpbtn.outerHeight() + oh;
			var limw = dpbtn.offset().left + ow + 2;

			if (limh < getTotalHeight()) {
				offy = limh - oh - 1;
				//cellgroup.css("border-top", "none");
			} else {
				offy = limh - dpbtn.outerHeight() - 2 * oh + 1;
				//cellgroup.css("border-bottom", "none");
			}
			if (limw > getTotalWidth()) {
				offx = getTotalWidth() - ow - 2;
			} else {
				offx = dpbtn.offset().left;
			}
			ghost.css({"top":offy + "px","left":offx + "px"});
			ghost.show();
			if ($.browser.msie) cellgroup.children().css("width", "auto");
			if ($.browser.msie && $.browser.version == 6) cellgroup.children().css("float", "none");

			$(document.body).bind("mousedown", closefunc);
			return false;
		}).focus(function() {
			$(this).blur();
		});
	}
})(jQuery);