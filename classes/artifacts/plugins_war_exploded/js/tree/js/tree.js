jQuery.fn.extend({
	initreecontainer:function(startIndent, childIndent) {
		var cwidth = this.width();
		this.children().width((cwidth) * this.find(".tree").length).children(".tree").width(cwidth - 7).each(function() {
			$(this).initree(startIndent, childIndent)
		});
		this.bind("slide", function(e, idx) {
			$(this).children().animate({left:-idx * (cwidth)}, 600);
		});
		return this
	},

	initree:function(startIndent, childIndent) {
		var maxwidth = 0;
		var $root = this.children("dl");
		$root.css("visibility","hidden");
		this.find(".item").each(function() {
			var itemwidth = 0;
			var depth = $(this).parents(".tree dl").length;
			var date = new Date();
			//var guid = Math.random() * date.getUTCMilliseconds().toString();
			itemwidth = depth * childIndent + startIndent + 37 + 8 + $(this).find(".presenter").width();
			//$(this).attr("guid", guid);
			if (!$(this).is(":only-child")) {
				if($(this).hasClass("open")){
					//$($(this).parents("dl"),$root).show();
					//$(this).next("dl").show();
					$(this).find(".node").addClass("exp");
				}
				$(this).find(".node").css("margin-left", depth * childIndent + startIndent + "px").addClass("isparent");
				$(this).click(function() {
					$(this).blur();
					$(this).find(".isparent").toggleClass("exp");
					$(this).next("dl").slideToggle(300);
				});
			} else {
				$(this).find(".node").css("margin-left", depth * childIndent + startIndent + "px");
				/*$(this).click(function() {
					$relativeTab.trigger("addnewtab", [$(this).text(),$(this).attr("url"),guid,true]);
				});*/
			}
			if (itemwidth > maxwidth) maxwidth = itemwidth;
		});
		if (this.width() <= maxwidth) {
			$root.width(maxwidth);
		}
		$root.find(".close").next("dl").hide();
		$root.css("visibility","visible")
		//---------------------------临时-------------------------
		//$relativeTab.find("li").eq(0).attr("guid", $(this).find("dl dl").eq(0).children(0).children().attr("guid"));
		//$dm.repanelgroup.children(":first").attr("guid", $(this).find("dl dl").eq(0).children(0).children().attr("guid"));
		//--------------------------------------------------------
		return this
	}
});
(function($) {
	$.fn.simpTree = function(settings,callbackFunc) {
		var treecontainer=this;
		var parentnodes = [];
		var depth = [];
		var txt = "<div class='tree'><dl>";
		this.append("<div class='treegroup'></div>");
		$.ajax({
			url:settings.url,
			type:"POST",
			cache:false,
			//data:ordermodel,
			dataType: "json",
			success:function(json) {
				var i = 0;
				parentnodes.push(json);
				while (parentnodes.length != 0) {
					if(parentnodes[parentnodes.length - 1][i].children) txt+="<dd>";
					var title = (parentnodes[parentnodes.length - 1][i].data.constructor == String) ? parentnodes[parentnodes.length - 1][i].data : parentnodes[parentnodes.length - 1][i].data.title;
					var attr="";
					var ico=(parentnodes[parentnodes.length - 1][i].data.attributes.ico)?parentnodes[parentnodes.length - 1][i].data.attributes.ico:"ico_app";
					if(parentnodes[parentnodes.length - 1][i].data.attributes){
						for(var key in parentnodes[parentnodes.length - 1][i].data.attributes){
							attr+=" "+key+"=\""+parentnodes[parentnodes.length - 1][i].data.attributes[key]+"\"";
						}
						if(!parentnodes[parentnodes.length - 1][i].data.attributes.href){
							attr+=" href=\"javascript:void(0)\"";
						}
					}
					if (parentnodes[parentnodes.length - 1][i].children) {
						var state=""
						if(parentnodes[parentnodes.length - 1][i].state=="open") {state=" open"} else {state=" close"};
						txt+="<a"+attr+" class='item"+state+"'><div class='itembody'><span class='node'></span><span class='ico "+ico+"'></span><span class='presenter'>"+title+"</span></div></a><dl>";
						parentnodes.push(parentnodes[parentnodes.length - 1][i].children);
						if (parentnodes.length - 1 > depth.length) {
							depth.push(i);
						}
						i = 0;
					} else {
						i++;
						txt+="<dd><a"+attr+" class='item'><div class='itembody'><span class='node'></span><span class='ico "+ico+"'></span><span class='presenter'>"+title+"</span></div></a></dd>";
						while (i >= parentnodes[parentnodes.length - 1].length) {
							parentnodes.pop();
							if (parentnodes.length == 0) break;
							depth[parentnodes.length - 1]++;
							i = depth[parentnodes.length - 1];
							depth.pop();
							txt+="</dl></dd>";
						}
					}
				}
				txt+="</dl></div>";
				treecontainer.children().append(txt);
				treecontainer.initreecontainer(8, 16);
				if(callbackFunc) callbackFunc();
			}
		});
		return this;
	};

	$.fn.xtree = function(settings) {
		var parentnodes = [];
		var depth = [];
		var txt = "";
		$.ajax({
			url:settings.url,
			type:"POST",
			cache:false,
			//data:ordermodel,
			dataType: "json",
			success:function(json) {
				var i = 0;
				parentnodes.push(json);
				while (parentnodes.length != 0) {
					var title = (parentnodes[parentnodes.length - 1][i].data.constructor == String) ? parentnodes[parentnodes.length - 1][i].data : parentnodes[parentnodes.length - 1][i].data.title;
					txt += "/" + title+"";
					if (parentnodes[parentnodes.length - 1][i].children) {
						parentnodes.push(parentnodes[parentnodes.length - 1][i].children);
						if (parentnodes.length - 1 > depth.length) {
							depth.push(i);
						}
						i = 0;
					} else {
						i++;
						while (i >= parentnodes[parentnodes.length - 1].length) {
							parentnodes.pop();
							if (parentnodes.length == 0) break;
							depth[parentnodes.length - 1]++;
							i = depth[parentnodes.length - 1];
							depth.pop();
						}
					}
				}
				alert(txt);
			}
		});
		return this
	}
})(jQuery);