jQuery.fn.extend({
	tabPanelInit:function() {
		var repanelgroup = this.children(".repanelgroup");
		var widtharr = [];
		var show = this.find(".show");
		var ulobj = show.find("ul");
		var subtab = this.children(".subtab");
		var leftbtn = this.find(".leftbtn");
		var rightbtn = this.find(".rightbtn");
		var extbtn=this.find(".extbtn");
		var ulwidth = 0;

		var ghostpanel=$("<div id='ghostpanel'></div>").appendTo($(document.body));
		var cellgroup = $("<dl class='cellgroup'></dl>").appendTo(ghostpanel);
		var groupwidth;
		var isIE6=($.browser.msie && $.browser.version == 6);

		var getnextdis = function() {
			var offx = Math.abs(ulobj.css("left").split("px")[0]);
			var overwidth = 0;
			var nextdis = 0;
			var screenwidth = show.width();
			for (var i = 0; i < widtharr.length; i++) {
				overwidth += widtharr[i];
				if (overwidth > offx + screenwidth) {
					nextdis = overwidth - (offx + screenwidth);
					break;
				}
			}
			return nextdis;
		};

		var closepanel = function() {
			if (ghostpanel.is(":visible")) {
				ghostpanel.hide();
				if(isIE6){
					cellgroup.css("height","auto").children().css("float", "left");
				}
				$(document.body).unbind("mousedown", closepanel);
			}
		};

		var getuloffx = function() {
			return parseInt(ulobj.css("left").split("px")[0]);
		};

		var sumdisleft = function(dis) {
			var offx = getuloffx();
			if (ulwidth >= (dis + show.width() - offx)) {
				return dis;
			} else {
				if (ulwidth + offx <= show.width()) {
					return 0;
				} else {
					return ulwidth - show.width() + offx;
				}
			}
		};
		var sumdisright = function(dis) {
			var offx = getuloffx();
			if (offx >= 0) {
				return 0;
			} else {
				if (-offx < dis) {
					return -offx;
				} else {
					return dis;
				}
			}
		};

		var updateulwidth = function(width) {
			ulobj.width(width);

		};

		var tab = {
			tabitem:null,
			init:function() {
				if (this.tabitem == null) return;
				ulwidth += this.tabitem.outerWidth() + 1;

				var relnum = this.tabitem.attr("guid");
				this.tabitem.hover(function() {
					$(this).addClass("hov");
				}, function() {
					$(this).removeClass("hov");
				})
						.click(function() {
					repanelgroup.children(".repanel[guid=" + relnum + "]").show().siblings(":visible").hide();
					if(!$(this).hasClass("sel")){
						$(this).addClass("sel").siblings(".sel").removeClass("sel");
						//$(window).trigger("resize");
					}
				}).find(".delico").hover(function() {
					$(this).addClass("hov");
				}, function() {
					$(this).removeClass("hov");
				}).click(function() {
					var parentli = $(this).closest("li");
					var idx = ulobj.children().index(parentli);
					var parentwidth = parentli.outerWidth() + 1;

					if (!parentli.is(":only-child")) {
						var relpanel = repanelgroup.children(".repanel[guid=" + relnum + "]");
						$(this).hide();
						if (parentli.is(":last-child")) {
							parentli.animate({width:'0px',opacity:'0'}, 300, function() {
								if ($(this).hasClass("sel")) {
									$(this).removeClass("sel").prev().addClass("sel");
									relpanel.prev().show().siblings(":visible").hide();
									$(window).trigger("resize");
									cellgroup.children("[guid='"+relnum+"']").prev().addClass("sel")
								}
								relpanel.remove();
								$(this).remove();
								ulwidth -= parentwidth;
								updateulwidth(ulwidth);
								widtharr.pop();
								cellgroup.children("[guid='"+relnum+"']").remove();
							});
						} else {
							parentli.animate({width:'0px',opacity:'0'}, 300, function() {
								if ($(this).hasClass("sel")) {
									$(this).removeClass("sel").next().addClass("sel");
									relpanel.next().show().siblings(":visible").hide();
									cellgroup.children("[guid='"+relnum+"']").next().addClass("sel");
								}
								relpanel.remove();
								$(this).remove();
								ulwidth -= parentwidth;
								updateulwidth(ulwidth);
								widtharr.splice(idx, 1);
								cellgroup.children("[guid='"+relnum+"']").remove();
							});
						}
					}
					return false;
				});
			},

			addcontent:function(path, relnum, useiframe) {
				if (useiframe) {
					var panel = $("<div class='repanel'><iframe width='100%' height='100%' frameborder='0' allowtransparency='true'></iframe></div>");
					repanelgroup.children().hide();
					repanelgroup.append(panel);
					panel.attr("guid", relnum);
					var dom = path;
                    
                    // 加随机码，避免缓存
                    var flag = false;
                    for(var i = 0; i < dom.length; i++){
                        var chr = dom.charAt(i);
                        if(chr == "?"){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        dom += "?radom=" + (new Date()).getTime();
                    } else {
                        dom += "&radom=" + (new Date()).getTime();
                    }
                    
					panel.children("iframe").attr("src", dom);
				} else {
					$.ajax({type:'POST',dataType: "html",cache:false,url:path,data:'',success:function(html) {
						var panel = $("<div class='repanel'></div>");
						repanelgroup.children().hide();
						repanelgroup.append(panel);
						panel.attr("guid", relnum);
						var dom = $(html).children();
						panel.append(dom);
					}});
				}
			}
		};

		var closeother=$("<dd>关闭其他标签</dd>").hovereff("hov").mousedown(function(){
			var selguid=ulobj.children(".sel").attr("guid");
			ulobj.children(":not(.sel)").remove();
			$(this).siblings("dd:not(.sel)").remove();
			repanelgroup.children(".repanel:not([guid='"+selguid+"'])").remove();
			ulwidth=ulobj.children(".sel").outerWidth()+1;
			updateulwidth(ulwidth);
			widtharr=[ulwidth];
			ulobj.css("left","0px");
			ghostpanel.hide();
		});
		cellgroup.append(closeother);
		cellgroup.append("<dt></dt>");
		cellgroup.mousedown(function(){
			return false;
		});

		leftbtn.addClass("active").hovereff("btnhov").bind("click", function() {
			ulobj.animate({left:(getuloffx() + sumdisright(show.width())) + "px"}, 500);
			return false;
		});
		rightbtn.addClass("active").hovereff("btnhov").bind("click", function() {
			ulobj.animate({left:(getuloffx() - sumdisleft(show.width())) + "px"}, 500);
			return false;
		});
		extbtn.addClass("active").hovereff("btnhov").bind("click",function(){
			var postop=$(this).offset().top+$(this).height();
			if(cellgroup.children().length>=12){
				groupwidth+=17;
				if(isIE6) cellgroup.height(250);
			}
			cellgroup.width(groupwidth).children("[guid='"+ulobj.children(".sel").attr("guid")+"']").addClass("sel").siblings(".sel").removeClass("sel");
			ghostpanel.css("top",postop+"px").fadeIn("fast");
			$(document.body).bind("mousedown", closepanel);
			$("iframe").each(function(){
				$(this.contentWindow.document).bind("mousedown",closepanel);
			});
			if ($.browser.msie) cellgroup.children().css("width", "auto");
			if (isIE6) cellgroup.children().css("float", "none");
		});

		this.bind("addnewtab", function(e, tabname, taburl, guid, useiframe) {
			if (taburl == null) return;
			var tabs = subtab.find("li");
			var num = tabs.length;
			for (var i = 0; i < num; i++) {
				if (tabs.eq(i).attr("guid") == guid) {
					tabs.eq(i).addClass("sel").siblings(".sel").removeClass("sel");
					repanelgroup.children(".repanel[guid=" + guid + "]").show().siblings(":visible").hide();
					$(window).trigger("resize");
					return;
				}
			}

			var newtab = tab;
			var cellitem = $("<dd guid='"+guid+"'><span class='delico'></span>" + tabname + "</dd>").hovereff("hov");
			cellitem.mousedown(function(){
				$(this).addClass("sel").siblings(".sel").removeClass("sel");
				ulobj.children("[guid='"+guid+"']").click();
				return false;
			}).children("span").hovereff("hov").mousedown(function(){
				ulobj.children("[guid='"+guid+"']").find(".delico").click();
				return false;
			});
			newtab.tabitem = $("<li class='sel' guid='"+guid+"'><div class='itembody'><span class='delico'></span><div class='presenter'>"+tabname+"</div></div></li>");
			//newtab.tabitem = ulobj.children().eq(0).clone();
			//newtab.tabitem.addClass("sel").find(".presenter").text(tabname);
			//newtab.tabitem.attr("guid", guid);
			cellgroup.css("width","auto").append(cellitem);
			groupwidth=ghostpanel.outerWidth()-3;

			newtab.addcontent(taburl, guid, useiframe);
			ulobj.css("width", "50000px");//important for ie
			ulobj.append(newtab.tabitem);
			newtab.tabitem.siblings(".sel").removeClass("sel");
			newtab.init();
			widtharr.push(newtab.tabitem.outerWidth() + 1);
			updateulwidth(ulwidth);

			if (ulwidth > show.width()) {
				ulobj.animate({left:-(ulwidth - show.width()) + "px"}, 300);
			}
			e.stopPropagation();
		});

		this.bind("deletetab", function(e, guid) {
			var parentli = ulobj.children("li[guid=" + guid + "]");
			var idx = ulobj.children().index(parentli);
			var parentwidth = parentli.outerWidth() + 1;

			if (!parentli.is(":only-child")) {
				var relpanel = repanelgroup.children(".repanel[guid=" + guid + "]");
				parentli.find(".delico").hide();
				if (parentli.is(":last-child")) {
					parentli.animate({width:'0px',opacity:'0'}, 300, function() {
						if ($(this).hasClass("sel")) {
							$(this).removeClass("sel").prev().addClass("sel");
							relpanel.prev().show().siblings(":visible").hide();
							$(window).trigger("resize");
						}
						relpanel.remove();
						$(this).remove();
						ulwidth -= parentwidth;
						updateulwidth(ulwidth);
						widtharr.pop();
					});
				} else {
					parentli.animate({width:'0px',opacity:'0'}, 300, function() {
						if ($(this).hasClass("sel")) {
							$(this).removeClass("sel").next().addClass("sel");
							relpanel.next().show().siblings(":visible").hide();
						}
						relpanel.remove();
						$(this).remove();
						ulwidth -= parentwidth;
						updateulwidth(ulwidth);
						widtharr.splice(idx, 1);
					});
				}
			}
			e.stopPropagation();
		});

		subtab.find("li").each(function() {
			var tabobj = tab;
			tabobj.tabitem = $(this);
			tabobj.init();
			widtharr.push($(this).outerWidth() + 1);
			var guid=$(this).attr('guid')
			var cellitem = $("<dd guid='"+guid+"'><span class='delico'></span>" + $(this).find('.presenter').text() + "</dd>").hovereff("hov");
			cellitem.mousedown(function(){
				$(this).addClass("sel").siblings(".sel").removeClass("sel");
				ulobj.children("[guid='"+guid+"']").click();
				return false;
			}).children("span").hovereff("hov").mousedown(function(){
				ulobj.children("[guid='"+guid+"']").find(".delico").click();
				return false;
			});
			cellitem.appendTo(cellgroup);
			groupwidth=ghostpanel.outerWidth()-3;
		});
		updateulwidth(ulwidth);
		this.attr("tabinit", true);
		return this;
	},

	tabAdd:function(tabname, taburl, guid, useiframe) {
		if (this.attr("tabinit")) {
			this.trigger("addnewtab", [tabname,taburl,guid,useiframe]);
		}
		//return this;
	},

	tabDelete:function(guid) {
		if (this.attr("tabinit")) {
			this.trigger("deletetab",[guid]);
		}
		return this;
	},

	tabInit:function(){
		return this.each(function(){
			if(!$(this).hasClass("tab")) $(this).addClass("tab");
			var tab=$(this);
			var strhead="<ul class='head'>";
			var tabody=$("<div class='tabody'></div>");
			var index=0;
			$(this).children("div").each(function(i){
				if($(this).attr("active")=='true') index=i;
				var label=($(this).attr("label"))?$(this).attr("label"):$(this).attr("title");
				strhead+="<li tabclick='"+(($(this).attr("tabclick")) ? $(this).attr("tabclick"):"")+"'><div class='itembody'><div class='presenter'>"+label+"</div></div></li>";
				tabody.append($(this));
				$(this).wrap("<div class='panel'></div>");
			});
			strhead+="</ul>";
			var objhead = $(strhead);
			objhead.children("li").each(function(i){
				if(i==index){
					$(this).addClass("sel");
					tabody.children(":eq("+i+")").css("display","block");
				}
				$(this).click(function(){
					$(this).addClass("sel").siblings(".sel").removeClass("sel");
					//tabody.children(":eq("+i+")").show().siblings(":visible").hide();
					tabody.children(":visible").hide();
					tabody.children(":eq("+i+")").show();
					if($(this).attr("tabclick")!=""){
						var funcname=$(this).attr("tabclick");
						//eval(funcname+"()");
						eval(funcname);
					}
				});
			});
			objhead.appendTo(tab);
			tabody.appendTo(tab);
		});
	}
});
