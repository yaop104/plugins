jQuery.fn.extend({
	dpListInit:function() {
		var isie6 = ( $.browser.msie && $.browser.version == 6) ? true : false;
		var zdx = 200;
		this.each(function() {
			var dplist = $("<div class='dplist'><div class='item'><div class='itembody'></div><dl class='cellgroup'></dl></div></div>");
			var objdplist = $(this);
			var cellgroup = dplist.find(".cellgroup");
			var cellitem = dplist.find(".item");
			
			// modify by yhb:
			// $(this).css("width") 取值有问题，故给一个固定值46
			// var dpwidth = ($(this).css("width"))?parseInt($(this).css("width").split("px")[0]):$(this).width();
			var dpwidth = 46;
			dplist.width(dpwidth + 6).find(".item").hovereff("hov");
			if (isie6) dplist.find(".itembody").width(dpwidth - 22);
			cellgroup.width(dpwidth + 4);
			$(this).hide();
			if ($(this).is("select") && !$(this).attr("init")) {
				dplist.find(".itembody").text($(this).children(":selected").text());
				//				dplist.find(".itembody").text($(this).val());
				var selectidx = $(this).children().index($(this).children(":selected"));
				var numchild = $(this).children("option").length;
				var cellwidth = 0;
				if ($.browser.msie) {
					if (numchild > 8) {
						cellgroup.height(160);
					}
				}
				for (var i = 0; i < numchild; i++) {
					var cell = $("<dd>" + $(this).children().eq(i).text() + "</dd>");

					if (i == selectidx) {
						cell.addClass("sel");
					}
					cell.hovereff("hov").click(function() {
						var idx=dplist.find("dd").index($(this));
						if ($(this).parent().siblings(".itembody").text() != $(this).text()) {
							$(this).parent().siblings(".itembody").text($(this).text());
							$(this).parent().hide();
							$(this).addClass("sel").siblings().removeClass("sel");
							objdplist[0].selectedIndex = idx;
							objdplist.trigger("change", [$(this).text(),objdplist.children().eq(idx).attr("value")]);
						}
						return false;
					});
					cellgroup.append(cell);
				}
				dplist.click(function() {
					if (dplist.offset().top + cellgroup.height() + dplist.height() > $(document.body).height()) {
						cellgroup.css("top", cellgroup.height() * -1 - 2);
					} else {
						cellgroup.css("top", "20px");
					}
					if (cellgroup.is(":hidden")) {
						cellitem.css({"z-index": zdx++,"position":"relative"});
					} else {
						cellitem.css({"z-index":100,"position":"static"});
					}
					cellgroup.toggle();
					if (cellgroup.is(":visible")) {
						var offSet = $('dd.sel', cellgroup).offset().top - cellgroup.offset().top;
						var fout = function() {
							$(".dplist .cellgroup").hide();
							cellitem.css({"z-index":1,"position":"static"});
						};
						cellgroup.animate({scrollTop: offSet});
						$(document).bind("click", fout);
						return false;
					}
				});
				objdplist.bind("clear",function(){
					$(this).next(".dplist").find(".itembody").text("").next().children(".sel").removeClass("sel");
				});
				$(this).after(dplist[0]);
				objdplist.attr("init", true);
			}
		});
		return this;
	},

	dpClear:function(){
		if($(this).attr("init")){
			$(this).trigger("clear");
		}
	},

	hovereff:function(classname) {
		this.hover(function() {
			$(this).addClass(classname);
		}, function() {
			$(this).removeClass(classname);
		});
		return this;
	},

	radioInit:function() {
		var radiogroup = this;
		this.each(function() {
			var radiotarget = $(this);
			radiotarget.hide();
			var jradio = $("<input type='button' class='jradio' value='' />");
			if (radiotarget.is(":checked")) jradio.addClass("chk");
			jradio.click(function() {
				$(this).blur();
				radiotarget.trigger("click");
				var radioname = radiotarget.attr("name");
				if (radiotarget.is(":checked")) {
					radiogroup.filter("[name='" + radioname + "']").each(function() {
						$(this).next(".jradio").removeClass("chk");
					});
					jradio.addClass("chk");
				}
			});
			$(this).after(jradio);
		});
		return this;
	},

	checkboxInit:function() {
		this.each(function() {
			var ckboxarget = $(this);
			var label=(ckboxarget.next().is("label")&&ckboxarget.next().attr("for")==ckboxarget.attr("id"))? ckboxarget.next():null;
			ckboxarget.hide();
			var jckbox = $("<input class='jcheckbox' type='button' value='' />");
			if(label){label.click(function(){
				if(ckboxarget.is(":checked")){
					ckboxarget[0].checked=false;
					jckbox.removeClass("chk");
				}else{
					ckboxarget[0].checked=true;
					jckbox.addClass("chk");
				}
				return false;
			});}
			if (ckboxarget.is(":checked")) jckbox.addClass("chk");
			jckbox.click(function() {
				$(this).blur();
				ckboxarget.trigger("click");
				if (ckboxarget.is(":checked")) {
					jckbox.addClass("chk");
				} else {
					jckbox.removeClass("chk");
				}
			});
			$(this).after(jckbox);
		});
		return this;
	},

	reCheck:function() {
		var obj = this.next(".jcheckbox");
		if (this.is(":checked")) {
			obj.addClass("chk");
		} else {
			obj.removeClass("chk");
		}
		return this;
	}
});
