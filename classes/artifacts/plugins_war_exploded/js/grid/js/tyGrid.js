jQuery.fn.extend({
	tableInit:function(alternatingRowStyle, mouseOverRowStyle, haveCheckbox, singleSelect) {
		var tyGrid = this;
		var singleSelect = (singleSelect) ? true : false;
		this.each(function() {
			//$(this).children("tbody").children("tr").hovereff(mouseOverRowStyle).filter(":even").addClass(alternatingRowStyle);
			//邹强修改每行都加上alternatingRowStyle 样式
			$(this).children("tbody").children("tr").hovereff(mouseOverRowStyle).addClass(alternatingRowStyle);
			if (haveCheckbox) {
				if (singleSelect) {
					$(this).find("tr").click(function(e) {
						if($(e.target).parents("td.actor").length>0 || $(e.target).hasClass("actor")) return;
						$(this).children("td:first").children(".gridcheckbox").toggleClass("chk");
						$(this).toggleClass("sel").siblings(".sel").removeClass("sel").children("td:first").children(".gridcheckbox").removeClass("chk");
						$(this).closest(".tyGrid").trigger("refreshcache", [$(this).attr("rowid")]);
					});
				} else {
					$(this).find("tr").click(function(e) {
						if($(e.target).parents("td.actor").length>0 || $(e.target).hasClass("actor")) return;
						$(this).children("td:first").children(".gridcheckbox").toggleClass("chk");
						$(this).toggleClass("sel");
						$(this).closest(".tyGrid").trigger("addtocache", [$(this).attr("rowid")]);
						e.preventDefault();
						
						// 2011-8-12 added by fanzhen 每次点击事件时，刷新隐藏域pre_condition中的预置条件值
						if (tyGrid.existPreCondition()) {
							var rowid = $(this).attr("rowid");
							var preConditionValue = $("input[name='pre_condition']").val();
							if ($(this).attr("class").indexOf("sel") != -1) {
								// to selected
								if (preConditionValue.indexOf(rowid) == -1) {
									// 有可能同一处会出现多个逗号，但不影响后续使用它pre_condition的值
									// 因为使用了split分割成数组
									preConditionValue += "," + rowid + ",";
								}
							} else {
								// cancel selected
								if (preConditionValue.indexOf(rowid) != -1) {
									// replace方法得使用正则表达式传参，故不用它
									var start = preConditionValue.indexOf(rowid);
									var end = preConditionValue.indexOf(rowid) + rowid.length;
									var temp = preConditionValue.substr(0,start);
									temp += preConditionValue.substr(end,preConditionValue.length);
									preConditionValue = temp;
								}
							}
							$("input[name='pre_condition']").val(preConditionValue);
						}
						
						
					});
				}
			}
		});
		return this;
	},

	girdInit:function(alternatingRowStyle, mouseOverRowStyle, requesturl, jsonColData, total, currentpage, tyGridWidthFunc, tyGridHeightFunc,waittingFunc) {
		var tyGrid = this;
		var withcheckbox = jsonColData.withCheckbox;
		var colmodel = jsonColData.colModel;

		var pagesize = jsonColData.pagesize;
		var singleSelect = (jsonColData.singleSelect) ? true : false;
		var totalrcd = total;
		var ordermodel = {};
		var $gridwrapper = this.find(".wrapper");
		var $grid = this.find(".grid");
		var $stheader = this.find(".stheader");
		var $rowline = this.find(".rowline");
		var $cache = this.find(".cache");
		var resizeable=true;

		var $pager = this.find(".pager");
		var $firstrcd = $pager.find(".firstrcd");
		var $endrcd = $pager.find(".endrcd");
		var $totalrcd = $pager.find(".totalrcd");
		var $pagesize = $pager.find(".pagesize");//select
		var $firstpage = $pager.find(".firstpage");
		var $prevpage = $pager.find(".prevpage");
		var $nextpage = $pager.find(".nextpage");
		var $endpage = $pager.find(".endpage");
		var $currentpagenum = $pager.find(".currentpagenum");//input
		var $totalpagenum = $pager.find(".totalpagenum");
		var $jump = $pager.find(".jump");

		var totalwidth = 0;
		var gridcellspacing = 11;

		var thheight;
		var pagerheight;

		var getfieldstr = function(colmodel) {
			var num = colmodel.length;
			var strarray = [];
			for (var i = 0; i < num; i++) {
				strarray.push(colmodel[i].name);
			}
			return strarray.join();
		};

		var initgridlayout = function() {
			var gridwidth = (typeof tyGridWidthFunc == 'function') ? tyGridWidthFunc() : ((tyGridWidthFunc) ? tyGridWidthFunc : tyGrid.offsetParent().width());
			var gridheight = (typeof tyGridHeightFunc == 'function') ? tyGridHeightFunc() : ((tyGridHeightFunc) ? tyGridHeightFunc : tyGrid.offsetParent().height());
			
			if (tyGridWidthFunc == undefined) {
				$gridwrapper.height(getTotalHeight() - tyGrid.offset().top - thheight - pagerheight);
			} else {
				$gridwrapper.height(gridheight - thheight - pagerheight);
			}
			//if ($.browser.msie && $.browser.version == 6) $gridwrapper.width(tyGrid.offsetParent().width());
			//$stheader.width(gridwidth);
			//$pager.width(gridwidth - 30);
			$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
			if(gridwidth!="auto") tyGrid.width(gridwidth);
		};
		
		var initpager = function(total, currentpage, pagesize) {
			var max = (total % pagesize == 0) ? total / pagesize : parseInt(total / pagesize) + 1;
			$firstrcd.text((currentpage > 0) ? (currentpage - 1) * pagesize + 1 : 0);
			if ((currentpage) * pagesize > total) {
				$endrcd.text(total);
			} else {
				$endrcd.text(currentpage * pagesize);
			}
			$totalpagenum.text(max);
			$totalrcd.text(total);
			if (currentpage <= 1) {
				$prevpage.addClass("dis");
				$firstpage.addClass("dis");
				if (currentpage < 1) {
					$nextpage.addClass("dis");
					$endpage.addClass("dis");
				}
			} else {
				if ($prevpage.hasClass("dis")) $prevpage.removeClass("dis");
				if ($firstpage.hasClass("dis")) $firstpage.removeClass("dis");
			}
			if (currentpage == max) {
				$nextpage.addClass("dis");
				$endpage.addClass("dis");
			} else {
				if ($nextpage.hasClass("dis")) $nextpage.removeClass("dis");
				if ($endpage.hasClass("dis")) $endpage.removeClass("dis");
			}
			$currentpagenum.val(currentpage);
		};

		var initgrid = function(condition) {
			var curscrollx = $gridwrapper.scrollLeft();
			if(waittingFunc) waittingFunc(true);
			$.ajax({
				url:requesturl,
				type:"POST",
				cache:false,
				data:condition,
				dataType: "json",
				success:function(json) {
					json = eval("("+json+")");
					initpager(json.total, json.page, ordermodel.pageSize);
					totalrcd = json.total;
					$grid.remove();
					$gridwrapper.prepend(creategrid(json, colmodel, withcheckbox));
					$grid = $gridwrapper.find(".grid");
					$grid.tableInit(alternatingRowStyle, mouseOverRowStyle, withcheckbox, singleSelect);
					randerselect();
					$rowline.find("p").height($grid.height());
					$gridwrapper.scrollLeft(curscrollx);
					if(waittingFunc) waittingFunc(false);
					
					// 2011-8-12 added by fanzhen 重新请求后初始化表格时，需要加初始化预置条件，自动加上预勾选
					if (tyGrid.existPreCondition()) {
						$grid.children().children("tr").each(function() {
							var rowid = $(this).attr("rowid");
							if (tyGrid.isContainPreCondition(rowid)) {
								$(this).addClass("sel");
								$(this).children("td:first").children(".gridcheckbox").addClass("chk");
								if ($cache.text().indexOf(rowid + ',') == -1) {
									$cache.append(rowid + ",");
								}
							}
						});
					}
					// 2011-8-15 added by fanzhen 重置header_chkbox的选中状态，先刷新此checkbox状态
					if ($("input[name='header_chkbox']") != undefined && $("input[name='header_chkbox']").val() != undefined) {
						$("input[name='header_chkbox']").removeClass("chk");
					}
				}
			});
		};

		var randerselect = function() {
			var selectarr = tyGrid.getAllSelected().split(',');
			for (var i = 0; i < selectarr.length; i++) {
				$grid.children("tbody").children("tr").not(".sel").each(function() {
					if ($(this).attr("rowid") == selectarr[i]) {
						$(this).addClass("sel").children(":eq(0)").children(".gridcheckbox").addClass("chk");
					}
				});
			}
		};

		var creategrid = function(json, colmodel, withcheckbox) {
			var headerArr=[];
			$.each(colmodel, function(i, col) {
				headerArr.push(col.name);
			});

			var strhtml = "<table border='0' cellpadding='0' cellspacing='0' class='grid' ><tbody>";
			$.each(json.rows, function(i, row) {
				var strtr = "<tr rowid='" + row.id + "'>";
				if (withcheckbox) strtr += "<td align='center'><input type='button' class='gridcheckbox' value='' /></td>";
				var len = colmodel.length;
				for (var n = 0; n < len; n++) {
					var item = row.cell[n];
					if (item == undefined) item = "";
					if (colmodel[n].renderfunc) item = colmodel[n].renderfunc(row.id, row.cell, n, headerArr);
					var relativeTh = $stheader.find("th[rel='" + n + "']");
					
					// added by fanzhen 2011-8-20  重置数据后，列的宽度与头宽保持一样
					// 但多个表格时，取_widthProperty值可能为0，所以存在第三句话
					var _widthProperty = relativeTh.children("div").css("width");
					_widthProperty = _widthProperty.substr(0,_widthProperty.length - 2);
					_widthProperty = (_widthProperty == 0?colmodel[n].width:_widthProperty);
					var _width = new Number(_widthProperty) + 9;
					strtr += "<td "+((colmodel[n].stopPropagation)?'class="actor"':'')+" align='" + relativeTh.attr("align") + "' "+((colmodel[n].hidden)?'style="display:none;"':'')+"><div style='width:" + _width + "px'>" + item + "</div></td>";
					
					// modify by yhb: 
					// relativeTh.children("div").width()取值为0；故改用colmodel[n].width来获取列宽
					//还原修改表格错乱导致双表格 无法看清楚的问题
					// strtr += "<td "+((colmodel[n].stopPropagation)?'class="actor"':'')+" align='" + relativeTh.attr("align") + "' "+((colmodel[n].hidden)?'style="display:none;"':'')+"><div style='width:" + (colmodel[n].width + 9) + "px'>" + item + "</div></td>";
					// nodify by liuq 如按上述方法修改，则在界面先拉伸表格宽度，然后再排序，会造成表格格式错乱
					// strtr += "<td "+((colmodel[n].stopPropagation)?'class="actor"':'')+" align='" + relativeTh.attr("align") + "' "+((colmodel[n].hidden)?'style="display:none;"':'')+"><div style='width:" + (relativeTh.children("div").width() + 9) + "px'>" + item + "</div></td>";
				}
				strtr += "</tr>";
				strhtml += strtr;
			});
			strhtml += "</tbody></table>";
			return strhtml;
		};

		var ghostbag = $("<div class='tyGrid' style='position:absolute;left:0;top:0;visibility:hidden;width:100px;height:100px;overflow:hidden;'></div>").appendTo($(document.body));
		thheight = $stheader.clone().appendTo(ghostbag).end().outerHeight();
		pagerheight = $pager.clone().appendTo(ghostbag).end().outerHeight();
		ghostbag.remove();
		initgridlayout();
		ordermodel.pageSize = pagesize;
		if(jsonColData.fieldOrder) ordermodel.fieldOrder=jsonColData.fieldOrder;

		ordermodel.currentNumber = currentpage;
		ordermodel.fields = getfieldstr(colmodel);
		$(window).resize(function() {
			if (tyGrid.is(":visible")) {
				tyGrid.trigger("resize");
			}
		});

		//todo:page start
		$pagesize.val(pagesize).dpListInit();
		$pagesize.change(function(e, param) {
			ordermodel.pageSize = param;
			ordermodel.currentNumber = 1;
			initgrid(ordermodel);
		});
		$firstpage.click(function() {
			ordermodel.currentNumber = 1;
			initgrid(ordermodel);
		});
		$endpage.click(function() {
			ordermodel.currentNumber = (totalrcd % ordermodel.pageSize == 0) ? totalrcd / ordermodel.pageSize : parseInt(totalrcd / ordermodel.pageSize) + 1;
			initgrid(ordermodel);
		});
		$prevpage.click(function() {
			ordermodel.currentNumber--;
			if (ordermodel.currentNumber == 0) {
				ordermodel.currentNumber = 1;
			} else {
				initgrid(ordermodel);
			}
		});
		$nextpage.click(function() {
			ordermodel.currentNumber++;
			var max = (totalrcd % ordermodel.pageSize == 0) ? totalrcd / ordermodel.pageSize : parseInt(totalrcd / ordermodel.pageSize) + 1;
			if (ordermodel.currentNumber > max) {
				ordermodel.currentNumber = max;
			} else {
				initgrid(ordermodel);
			}
		});
		$jump.click(function() {
			if (parseInt($currentpagenum.val()) == $currentpagenum.val() && $currentpagenum.val() > 0 && $currentpagenum.val() <= parseInt($totalpagenum.text())) {
				ordermodel.currentNumber = $currentpagenum.val();
				initgrid(ordermodel);
			} else {
				alert("请输入不大于当前总页数的正整数值");
			}
		});
		initpager(total, currentpage, pagesize);
		//todo:page end


		if (withcheckbox) {
			$stheader.find(".gridcheckbox").click(function() {
				if (!$(this).hasClass("chk")) {
					$(this).addClass("chk");
					$grid.children().children("tr").addClass("sel").each(function() {
						var rowid = $(this).attr("rowid");
						var txt = $cache.text();
						$(this).children("td:first").children(".gridcheckbox").addClass("chk");
						if ($cache.text().indexOf(rowid + ',') == -1) {
							$cache.append(rowid + ",");
						}
					});
				} else {
					$(this).removeClass("chk");
					$grid.children().children("tr").removeClass("sel").each(function() {
						var rowid = $(this).attr("rowid");
						var txt = $cache.text();
						$(this).children("td:first").children(".gridcheckbox").removeClass("chk");
						if ($cache.text().indexOf(rowid + ',') != -1) {
							$cache.text(txt.replace(rowid + ',', ''));
						}
					});
				}
			});
		}
		//todo:bind events
		$(this).bind("reload", function(e) {
			initgrid(ordermodel);
		});
		$(this).bind("query", function(e, params) {
			if (params.searchParams) ordermodel = jQuery.extend(ordermodel, params.searchParams);
			if (params.pageSize) ordermodel.pageSize = params.pageSize;
			if (params.currentNumber) ordermodel.currentNumber = params.currentNumber;
			if (params.fieldOrder) ordermodel.fieldOrder = params.fieldOrder;
			initgrid(ordermodel);
		});
		$(this).bind("resize", function(e) {
			if(resizeable) initgridlayout();
		});
		$(this).bind("addtocache", function(e, id) {
			var txt = $cache.text();
			if ($cache.text().indexOf(id + ',') == -1) {
				$cache.append(id + ",");
			} else {
				$cache.text(txt.replace(id + ',', ''));
			}
		});
		$(this).bind("refreshcache", function(e, id) {
			if ($cache.text().indexOf(id + ',') == -1) {
				$cache.text(id + ",");
			} else {
				$cache.empty();
			}
		});
		$(this).bind("setsize",function(e,newWidth,newHeight){
			if(newWidth!="auto"){
				tyGrid.width(newWidth);
			}else{
				tyGrid.css("width","auto");
			}
			$gridwrapper.height(newHeight-thheight-pagerheight);
			initgridlinepos();
			$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
			resizeable=false;
		});
		//todo:init th
		if(ordermodel.fieldOrder){
			$stheader.find("th[name='"+ordermodel.fieldOrder.split(" ")[0]+"']").addClass(ordermodel.fieldOrder.split(" ")[1]);
		}
		$stheader.find("th[sortable='true']").each(function() {
			$(this).click(function() {
				$stheader.find("th").not($(this)).attr("class", "");
				var sfd = $(this).attr("fieldName");
				if(!sfd || sfd == "undefined"){
					sfd = ProName2FieldName($(this).attr("name"));
				}
				
				// added by fanzhen 2011-8-20 reset the header div css for displaying asc.png or desc.png
				$(this).parent().children("th").each(function () {
					if ($(this).attr("fieldName") != undefined && $(this).attr("fieldName") != "undefined") {
						$(this).children("div").removeClass("asc_div");
						$(this).children("div").removeClass("desc_div");
					}
				});
				
				if ($(this).hasClass("asc")) {
					// added by fanzhen 2011-8-20
					$(this).children("div").addClass("desc_div");
					
					$(this).removeClass("asc").addClass("desc");
					ordermodel.fieldOrder = sfd + " desc";
					initgrid(ordermodel);
				} else {
					// added by fanzhen 2011-8-20
					$(this).children("div").addClass("asc_div");
					
					$(this).removeClass("desc").addClass("asc");
					ordermodel.fieldOrder = sfd + " asc";
					initgrid(ordermodel);
				}
			});
		});

		$grid.tableInit(alternatingRowStyle, mouseOverRowStyle, withcheckbox, singleSelect);
		$grid.children().children("tr:first").each(function() {
			if (withcheckbox) totalwidth += $stheader.find("th:has(.gridcheckbox)").outerWidth();
			$(this).children("td").each(function(i) {
				var headerwidth = $stheader.find("th[rel='" + i + "']").width();
				//$(this).wrapInner("<div style='width:" + headerwidth + "px" + "'></div>");
				var $P = $("<p></p>");
				if($stheader.find("th[rel='" + i + "']").css("display")!="none"){
					totalwidth += headerwidth + gridcellspacing - 5;
					$P.css({"left":(totalwidth - 10) + "px","height":($grid.height()) + "px"});
				}else{
					$P.css("display","none");
				}
				$rowline.append($P);
			});
			totalwidth = 0;
			if (withcheckbox) $rowline.children("p:last").remove();
		});

		$gridwrapper.scroll(function() {
			$stheader.children().css("left", -($(this).scrollLeft()) + "px");
			//$rowline.find("p").css("top", $(this).scrollTop() + "px");
		});

		var initgridlinepos = function() {
			var totalleft = 0;
			if (withcheckbox) {
				totalleft += $stheader.find("th:has(.gridcheckbox)").outerWidth();
			}
			$rowline.find("p").each(function(i) {
				if($stheader.find("th[rel='" + i + "']").css("display")!="none"){
					totalleft += $stheader.find("th[rel='" + i + "']").children("div").width() + gridcellspacing + 9;
				}
				$(this).css("left", (totalleft - 10) + "px");
			});
		};

		$rowline.children("p").each(function() {
			var pp = $(this);
			var startdrag = false;
			var old = 0;
			var w = 0;
			var offx = 0;
			var offxnew = 0;
			var idx = $rowline.children("p").index(pp);
			var relativeTh = $stheader.find("th[rel='" + idx + "']");
			var relativeThWidth = relativeTh.children("div").width();
			var thgroupwidth = $stheader.children().width();
			if (withcheckbox) idx = idx + 1;
			$(this).hover(function() {
				$(this).addClass("hov");
			}, function() {
				$(this).removeClass("hov");
			}).mousedown(function(e) {
				w = parseInt($(this).css("left").split("px")[0]);
				offx = w;
				old = e.pageX;
				start = e.pageX;
				startdrag = true;
				$rowline.children(".block").width($grid.width()).height($grid.height()).css("opacity", 0).show();

				$(document).bind("mousemove", function(e1) {
					if (startdrag) {
						var st = e1.pageX;
						w += (st - old);
						//$("#test").text(w);
						pp.css("left", w + "px");
						old = st;
					}
				}).bind("mouseup", function() {
					offxnew = parseInt(pp.css("left").split("px")[0]);
					var relthwidth = relativeTh.children("div").width();
					var distance = relthwidth + (offxnew - offx);
					if (distance < 20) distance = 20;
					relativeTh.children().width(distance);
					$grid.children().children("tr").each(function() {
						$(this).children("td").eq(idx).children("div").width(distance + 9);
					});
					$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
					startdrag = false;
					$(this).unbind("mousemove").unbind("mouseup");
					initgridlinepos();
					$rowline.children(".block").hide();
					old = 0;
				});
				return false;
			});
		});

		// 2011-8-12 added by fanzhen 初始表格时第一次执行到，初始化预置条件，自动加上预勾选
		if (tyGrid.existPreCondition()) {
			$grid.children().children("tr").each(function() {
				var rowid = $(this).attr("rowid");
				if (tyGrid.isContainPreCondition(rowid)) {
					$(this).addClass("sel");
					$(this).children("td:first").children(".gridcheckbox").addClass("chk");
					if ($cache.text().indexOf(rowid + ',') == -1) {
						$cache.append(rowid + ",");
					}
				}
			});
		}
		
		return this;
	},

	tyGrid:function(jsonColData, url, tyGridWidthFunc, tyGridHeightFunc,waittingFunc) {
		var tyGrid = this;
		var withcheckbox = jsonColData.withCheckbox;
		var singleSelect = (jsonColData.singleSelect) ? true : false;
		var colmodel = jsonColData.colModel;
		var pagesize = jsonColData.pagesize;
		var mergecol = (jsonColData.mergecol) ? jsonColData.mergecol : [];
		var ismulitheader = (jsonColData.mergecol) ? true : false;
		var strhtml = "<div class='stheader'><table border='0' cellpadding='0' cellspacing='0'><tbody>";
		var rowspan = (ismulitheader) ? " rowspan='2'" : " rowspan='1'";
		var ordermodel = {};

		var headerArr=[];

		var getfieldstr = function(colmodel) {
			var num = colmodel.length;
			var strarray = [];
			for (var i = 0; i < num; i++) {
				strarray.push(colmodel[i].name);
			}
			return strarray.join();
		};

		var checkheaderidx = function(num, array) {
			var isin = {havefa:false,father:"",silbing:0,min:0};
			for (var n = 0; n < array.length; n++) {
				if (num >= array[n].cross[0] && num <= array[n].cross[1]) {
					isin.havefa = true;
					isin.silbing = array[n].cross[1] - array[n].cross[0] + 1;
					isin.min = array[n].cross[0];
					isin.father = array[n].display;
					break;
				} else {
					isin.havefa = false;
				}
			}
			return isin;
		};

		ordermodel.pageSize = pagesize;
		ordermodel.currentNumber = 1;
		ordermodel.fields = getfieldstr(colmodel);
		if (jsonColData.fieldOrder) ordermodel.fieldOrder = jsonColData.fieldOrder;

		var t1 = "<tr>";
		var t2 = "<tr>";
		var strqueue = "";
		if (withcheckbox) {
			if (singleSelect) {
				t1 += "<th style='padding-right:5px;' align='center' " + rowspan + "><input type='button' class='gridcheckbox' value='' style='visibility:hidden;' /></th>";
			} else {
				
				// 2011-8-12 added by fanzhen 是否存在预置条件，如存在，则隐藏起来
				// 并且给头信息中的checkbox加了名称header_chkbox，用于每次翻页的时候重置它的选中
				if (jsonColData.preCondition != undefined) {
					t1 += "<th style='padding-right:5px;' align='center' " + rowspan + "><input type='button' class='gridcheckbox' value='' name='header_chkbox' /><input type='hidden' name='pre_condition' value='" + jsonColData.preCondition + "' /></th>";
				} else {
					t1 += "<th style='padding-right:5px;' align='center' " + rowspan + "><input type='button' class='gridcheckbox' value='' name='header_chkbox' /></th>";
				}
			}
		}
		$.each(colmodel, function(i, col) {
			var strhidden=(col.hidden)? "style='display:none;'":"";
			if (checkheaderidx(i, mergecol).havefa && ismulitheader) {
				if (i == checkheaderidx(i, mergecol).min) {
					t1 += "<th colspan='" + checkheaderidx(i, mergecol).silbing + "' align='center'>" + checkheaderidx(i, mergecol).father + "</th>";
				}
				
				// added by fanzhen 2012-02-13
				// 如存在排序列，则加下鼠标指针样式
				if (col.sortable != undefined && col.sortable == true) {
					t2 += "<th "+strhidden+" rel='" + i + "' name='" + col.name + "' align='" + col.align + "' sortable='" + col.sortable + "' fieldName='"+ col.fieldName +"'><div style='width:" + col.width + "px;cursor:pointer;'>" + col.display + "</div></th>";
				} else {
					t2 += "<th "+strhidden+" rel='" + i + "' name='" + col.name + "' align='" + col.align + "' sortable='" + col.sortable + "' fieldName='"+ col.fieldName +"'><div style='width:" + col.width + "px;'>" + col.display + "</div></th>";
				}
				
				headerArr.push(col.name);
			} else {
				// added by fanzhen 2012-02-13
				// 如存在排序列，则加下鼠标指针样式
				if (col.sortable != undefined && col.sortable == true) {
					t1 += "<th "+strhidden+" rel='" + i + "' name='" + col.name + "'" + rowspan + " align='" + col.align + "' sortable='" + col.sortable + "' fieldName='"+ col.fieldName +"'><div style='width:" + col.width + "px;cursor:pointer;'>" + col.display + "</div></th>";
				} else {
					t1 += "<th "+strhidden+" rel='" + i + "' name='" + col.name + "'" + rowspan + " align='" + col.align + "' sortable='" + col.sortable + "' fieldName='"+ col.fieldName +"'><div style='width:" + col.width + "px;'>" + col.display + "</div></th>";
				}
				
				headerArr.push(col.name);
			}
			strqueue += col.name + ",";
		});
		t1 += "</tr>";
		t2 += "</tr>";
		strhtml += t1 + t2;
		strhtml += "</tbody></table></div>";

		strhtml += "<div class='wrapper'>";

		strhtml += "<table border='0' cellpadding='0' cellspacing='0' class='grid'><tbody>";

		if(waittingFunc){
			waittingFunc(true);
		}

		$.ajax({
			url:url,
			type:"POST",
			cache:false,
			data:ordermodel,
			dataType: "json",
            async:false,
			success:function(json) {
				if(waittingFunc) waittingFunc(false);
				if (json != null && json != undefined) {
					
				
	                if(json.error_message) {
	                    alert("操作失败：" + json.error_message);
	                } else {
	                    $(".pager",tyGrid).show();
	                    json = eval("("+json+")");
	                    $.each(json.rows, function(i, row) {
	                        var strtr = "<tr rowid='" + row.id + "'>";
	                        if (withcheckbox) strtr += "<td align='center'><input type='button' class='gridcheckbox' value='' /></td>";
	                        /*$.each(row.cell, function(n, item) {
	                         strtr += "<td align='" + colmodel[n].align + "'><div style='width:" + (colmodel[n].width + 9) + "px'>" + item + "</div></td>";
	                         });*/
	                        var len = colmodel.length;
	                        for (var n = 0; n < len; n++) {
	                            var item = row.cell[n];
	                            if (item == undefined) item = "";
	                            if (colmodel[n].renderfunc) item = colmodel[n].renderfunc(row.id, row.cell, n, headerArr);
	                            strtr += "<td "+((colmodel[n].stopPropagation)?'class="actor"':'')+" align='" + colmodel[n].align + "' "+((colmodel[n].hidden)?'style="display:none;"':'')+"><div style='width:" + (colmodel[n].width + 9) + "px'>" + item + "</div></td>";
	                        }
	                        strtr += "</tr>";
	                        strhtml += strtr;
	                    });
	                    strhtml += "</tbody></table><div class='rowline'><div class='block'></div></div></div><div class='cache'></div>";
	                    tyGrid.prepend(strhtml).girdInit("alternative", "hov", url, jsonColData, json.total, json.page, tyGridWidthFunc, tyGridHeightFunc,waittingFunc);
	                }
				} else {
					alert("操作失败，服务端查询数据出现异常");
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				if(waittingFunc) waittingFunc(false);
				if(textStatus==null){
					alert("操作失败！");
				}else{
					alert("由于网络问题，请重试");
				}
			}
		});
		return this;
	},

	// 2011-8-12 added by fanzhen 是否存在预置条件
	existPreCondition:function () {
		var obj = $("input[name='pre_condition']");
		if (obj != undefined && obj.val() != undefined) {
			return true;
		}
		return false;
	},
	
	// 2011-8-12 added by fanzhen 判断指定值是否存在于预置条件中
	isContainPreCondition:function (value) {
		var obj = $("input[name='pre_condition']");
		if (obj != undefined && obj.val() != undefined) {
			var preArray = obj.val().split(",");
			for (var i = 0 ; i < preArray.length;i++) {
				if (($.trim(preArray[i]) == $.trim(value)) && $.trim(preArray[i]).length > 0 && $.trim(value).length > 0) {
					return true;
				}
			}
		}
		return false;
	},
	
	getAllSelected:function() {
		if (this.hasClass("tyGrid")) {
			var str = this.find(".cache").text();
			return str.substr(0, str.length - 1);
		}
	},

	getCurrentSelected:function() {
		if (this.hasClass("tyGrid")) {
			var temp = [];
			var grid = this.find(".grid");
			grid.children("tbody").children(".sel").each(function() {
				temp.push($(this).attr("rowid"));
			});
		}
		return temp.join(",");
	},

	getRowIndex:function(rowName){
		if (this.hasClass("tyGrid")) {
			var $stheader=this.find(".stheader");
			if($stheader.find("th[name='"+rowName+"']").length>=1){
				return $stheader.find("th[name='"+rowName+"']").eq(0).attr("rel");
			}else{
				return -1;
			}
		}
	},
	
	getCurrentSelectedCol:function(colIndex) {
		if (this.hasClass("tyGrid")) {
			var temp = [];
			var grid = this.find(".grid");
			grid.children("tbody").children(".sel").children(":eq("+colIndex+")").each(function() {
				temp.push($(this).text());
			});
		}
		return temp.join(",");
	},

	del:function(url, pks) {
		// added by fanzhen 解决中文传参乱码问题，但不能对url串中的/、?进行转码
		url = encodeURI(url);
		
		var tyGrid = this;
		jQuery.ajax({
			url:url,
			type:"POST",
			cache:false,
			dataType: "json",
			success:function(data) {
				alert(data.message);
				tyGrid.trigger("query", [
					{}
				]);
				tyGrid.updateSelected(pks);
				tyGrid.find(".stheader").find("th:first").children(".gridcheckbox").removeClass("chk");
			}
		});
	},

	updateSelected:function(str) {

		if (this.hasClass("tyGrid")) {
			var txt = this.find(".cache").text();
			var pksArray = str.split(",");
			for (var i = 0; i < pksArray.length; i++) {
				txt = txt.replace(pksArray[i] + ',', '');
			}
			this.find(".cache").text(txt);
		}
	},

	// 2011-8-12 added by fanzhen 实上此方法未使用，或屏掉
//	listGrid:function() {
//		this.each(function() {
//			var listgrid = $(this);
//			var $stheader = $("<div class='stheader' style='height:24px'><table cellpadding='0' cellspacing='0' border='0'><tbody></tbody></table></div>");
//			var $gridwrapper = $("<div class='wrapper'><div class='rowline'><div class='block'></div></div></div>");
//			var $rowline = $gridwrapper.children(".rowline");
//			var $grid = $(this).children(".grid");
//			var totalwidth = 0;
//
//			var initgridlinepos = function() {
//				var totalleft = 0;
//				$rowline.find("p").each(function(i) {
//					totalleft += $stheader.find("th:eq(" + i + ")").outerWidth();
//					$(this).css("left", (totalleft - 10) + "px");
//				});
//				var pheight=(parseInt($rowline.find("p:last").css("left").split("px")[0])+19>=$gridwrapper.width())?$gridwrapper.height()-17:$gridwrapper.height();
//				$rowline.find("p").each(function() {
//					$(this).height(pheight);
//				});
//			};
//
//			var initgridlayout = function() {
//				var gridwidth = (listgrid.attr("width")) ? parseInt(listgrid.attr("width")) : 100;
//				var gridheight = (listgrid.attr("height")) ? parseInt(listgrid.attr("height")) : 100;
//				if(gridwidth!=100) listgrid.width(gridwidth);
//				$gridwrapper.height(gridheight - $stheader.outerHeight());
//				if (!listgrid.attr("width")) {
//					//$gridwrapper.css("width","100%");
//					$stheader.css("width", "100%");
//				} else {
//					$gridwrapper.width(gridwidth);
//					$stheader.width(gridwidth);
//				}
//
//				$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
//				//tyGrid.width(gridwidth);
//			};
//
//			initgridlayout();
//			$(this).append($stheader).append($gridwrapper.prepend($grid));
//			$grid.find("tr:has(th)").clone().andSelf().appendTo($stheader.find("tbody"));
//
//			$stheader.find("tr:first").addClass("xrow").children().each(function() {
//				$(this).empty();
//			});
//			$stheader.find("tr:first").clone().prependTo($grid.children("tbody"));
//			$stheader.find("tr:eq(1)").children("th").each(function(i) {
//				//$grid.children("tbody").children("tr:first").children(":eq(" + i + ")").replaceWith("<td width='" + $(this).attr("width") + "'></td>");
//				$(this).removeAttr("width");
//			});
//
//			$grid.tableInit("alternative", "hov", false);
//			
//			$grid.children().children("tr:first").each(function() {
//				$(this).children().each(function(i) {
//					var headerwidth = $stheader.find("th:eq(" + i + ")").outerWidth();
//					var $P = $("<p></p>");
//					//$(this).wrapInner("<div style='width:" + headerwidth + "px" + "'></div>");
//					totalwidth += headerwidth;
//					$P.css({"left":(totalwidth - 10) + "px","height":($gridwrapper.height()) + "px"});
//					$rowline.append($P);
//				});
//				totalwidth = 0;
//			});
//
//			$gridwrapper.scroll(function() {
//				$stheader.children().css("left", -($(this).scrollLeft()) + "px");
//				$rowline.css("top",$(this).scrollTop()+"px")
//			});
//
//			initgridlinepos();
//			$rowline.children("p").each(function() {
//				var pp = $(this);
//				var startdrag = false;
//				var old = 0;
//				var w = 0;
//				var offx = 0;
//				var offxnew = 0;
//				var idx = $rowline.children("p").index(pp);
//				var relativeTh = $stheader.find("tr:first").children("th:eq(" + idx + ")");
//				var relativeThWidth = relativeTh.outerWidth();
//				var thgroupwidth = $stheader.children().width();
//				$(this).hover(function() {
//					$(this).addClass("hov");
//				}, function() {
//					$(this).removeClass("hov");
//				}).mousedown(function(e) {
//					w = parseInt($(this).css("left").split("px")[0]);
//					offx = w;
//					old = e.pageX;
//					start = e.pageX;
//					startdrag = true;
//					$rowline.children(".block").width($grid.width()).height($rowline.find("p:last").height()).css("opacity", 0).show();
//
//					$(document).bind("mousemove", function(e1) {
//						if (startdrag) {
//							var st = e1.pageX;
//							w += (st - old);
//							//$("#test").text(w);
//							pp.css("left", w + "px");
//							old = st;
//						}
//					}).bind("mouseup", function() {
//						offxnew = parseInt(pp.css("left").split("px")[0]);
//						var relthwidth = relativeTh.width();
//						var distance = relthwidth + (offxnew - offx);
//						if (distance < 20) distance = 20;
//						relativeTh.attr("width", distance);
//						$grid.children().children("tr:first").children().eq(idx).attr("width", distance);
//						$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
//						startdrag = false;
//						$(this).unbind("mousemove").unbind("mouseup");
//						initgridlinepos();
//						$rowline.children(".block").hide();
//						$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
//						old = 0;
//					});
//					return false;
//				});
//			});
//
//			listgrid.bind("setsize",function(e,newWidth,newHeight){
//				if(newWidth!="auto") {
//					$stheader.width(newWidth);
//					$gridwrapper.width(newWidth);
//					listgrid.width(newWidth);
//				}else{
//					$stheader.css("width","auto");
//					$gridwrapper.css("width","auto");
//					listgrid.css("width","auto");
//				}
//				$gridwrapper.height(newHeight-$stheader.outerHeight());
//				initgridlinepos();
//				$stheader.children().css("left", -($gridwrapper.scrollLeft()) + "px");
//			})
//		});
//		return this;
//	},

	setSize:function(width,height){
		this.trigger("setsize",[width,height]);
		return this;
	}
});

(function($) {
	var theader;
	$.fn.zz = function() {
		i++;
	};
	$.fn.changei = function() {
		alert(i);
	}
	$.fn.zz.aaa = function() {
		alert(this);
	}
})(jQuery);


(function(jQuery) {

	function FormatData(valid, dec, group, neg) {
		this.valid = valid;
		this.dec = dec;
		this.group = group;
		this.neg = neg;
	}

	;

	function formatCodes(locale) {
		// format logic goes here
		return new FormatData(valid, dec, group, neg);
	}

	;

	jQuery.fn.parse = function(options) {

		var options = jQuery.extend({}, jQuery.fn.parse.defaults, options);

		var formatData = formatCodes(options.locale.toLowerCase());

		var valid = formatData.valid;
		var dec = formatData.dec;
		var group = formatData.group;
		var neg = formatData.neg;

		var array = [];
		this.each(function() {

			var text = new String(jQuery(this).text());
			if (jQuery(this).is(":input"))
				text = new String(jQuery(this).val());


			// now we need to convert it into a number
			var number = new Number(text.replace(group, '').replace(dec, ".").replace(neg, "-"));
			array.push(number);
		});

		return array;
	};

	jQuery.fn.format = function(options) {

		var options = jQuery.extend({}, jQuery.fn.format.defaults, options);

		var formatData = formatCodes(options.locale.toLowerCase());

		var valid = formatData.valid;
		var dec = formatData.dec;
		var group = formatData.group;
		var neg = formatData.neg;

		return this.each(function() {
			var text = new String(jQuery(this).text());
			if (jQuery(this).is(":input"))
				text = new String(jQuery(this).val());

			// formatting logic goes here

			if (jQuery(this).is(":input"))
				jQuery(this).val(returnString);
			else
				jQuery(this).text(returnString);
		});
	};

	jQuery.fn.parse.defaults = {
		locale: "us"
	};

	jQuery.fn.format.defaults = {
		format: "#,###.00",
		locale: "us"
	};

})(jQuery);