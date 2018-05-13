					/*
						Author:yp
						系统工具类
					*/
//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//
//


var yao={};
yao.sysUtil = {
		tab : function(el){
			if($(el)) $(el).className='select';
		}
		,isN : function(v){
			v+='';
			if(!v || v=='null') return true;
			else return false;
		}
		,url : function(url){
			location.href=url;
		}
		,text : function(el){//下拉框
			el=$(el);
			if(el.selectedIndex==-1) return '';
			else return el.options[el.selectedIndex].text;
		}
		,openWin : function(url,width,height){
			width=width||800,height=height||600;
			var left = (screen.width - width)/2,top = (screen.height - height)/2;
			style="toolbar=no,location=no,directories=no,status=no,resizable=yes,scrollbars=yes,left="+left+",top="+top+",width="+width+",height="+height;
			return window.open(url,'', style);
		}
		,isPhone : function(phone,allownull){
			var len=phone.length;
			if(len==0){
				if(allownull) return true;
				else return false;
			}else{
				var p1 = /^((0\d{2,3}))?(\d{7,8})$/;
				return p1.test(phone);
			}
		}
		,isMobilePhone : function(mobilePhone,allownull){
			var len=mobilePhone.length;
			if(len==0){
				if(allownull)return true;
				else return false;
			}else{
				var p2 = /^(13[0-9]|14[0-9]|15[0-9]|17[0|7]|18[0|1|2|3|4|5|6|7|8|9])\d{8}$/;
				return p2.test(mobilePhone);
			}
		}
		,isEmail : function(mail){
			var strr;
			re=/(\w+@\w+\.\w+)(\.{0,1}\w*)(\.{0,1}\w*)/i;
			re.exec(mail);
			if(RegExp.$3!=''&&RegExp.$3!='.'&&RegExp.$2!='.'){
				strr=RegExp.$1+RegExp.$2+RegExp.$3
			}else{
				if(RegExp.$2!=''&&RegExp.$2!='.') strr=RegExp.$1+RegExp.$2
				else strr=RegExp.$1
			}
			if(strr!=mail) return false;
			return true;
		}
		,isZipCode : function(zipcode,allownull){
			var len=zipcode.length;
			if(len==0){
				if(allownull) return true;
				else return false;
			}else if(len != 6){
				return false;
			}else if(!yao.sysUtil.isNum(zipcode)){
				return false;
			}else return true;
		}
		,isNum : function(num){
		     var r=num.match(/^\d{0,255}$/);
		     if (r==null) return false;
		     else return true;
		}
		,isEmpty : function(name, msg){
			var val = $('#' + name).val();
			if (val == null || val.length == 0) {
				art.dialog({
					title : '提示', lock : true, background : 'white', opacity : 0.2,
					content : msg,
					button : [{
						name : '确定',
						callback : function() {
							try {
								$("#" + name).focus();
							} catch (e) {}
						},
						focus : true
					}]
				});
				return false;
			}
			return true;
		}
		/**
		 * 表单检验
		 * @param name 表单id名称
		 * @param msg 消息名称
		 * @param inputType 表单类型（text,select,radio,checkbox）
		 * @param tab 选项卡
		 * @param max 最大长度
		 * @param min 最小长度（min: -1=可为空）
		 * @returns {Boolean}
		 */
		,isInput : function(name, msg, max, min){
			var inputType = document.getElementById(name).getAttribute("type");
			var val = document.getElementById(name).value;
			val = (val == null) ? "" : val;
			if(min <= 0){//可为空，不能超过max长度
				if(val.length > max){
					msg = msg + "不能超过"+max+"个字符";
					art.dialog({
						title : '提示', lock : true, background : 'white', opacity : 0.2,
						content : msg + "不能超过"+max+"个字符",
						button : [{
							name : '确定',
							callback : function() {
								try {
									$("#" + name).focus();
								} catch (e) {}
							},
							focus : true
						}]
					});
					return false;
				}
			}else if(min > 0){//不为空，不能超过max长度，不能小于min长度
				if(val.length < min || val.length > max){
					if(inputType == "select-one"){
						msg = "请选择" + msg;
					}else{
						msg = msg + "不能超过"+max+"个字符，不能小于"+min+"个字符";
					}
					art.dialog({
						title : '提示', lock : true, background : 'white', opacity : 0.2,
						content : msg,
						button : [{
							name : '确定',
							callback : function() {
								try {
									$("#" + name).focus();
								} catch (e) {}
							},
							focus : true
						}]
					});
					return false;
				}
			}
			return true;
		}
		,isPwd : function(pwd){//以字母开头，长度在6-18之间，只能包含字符、数字和下划线
			var r=pwd.match(/^[a-zA-Z]\w{5,17}$/);
			if (r==null) return false;
			else return true;
		}
		,toggleDisplay : function (btnId, value) {
		    var currentBtn = document.getElementById(btnId);
		  //style中的display属性
		    if (currentBtn.style.display != 'none'){
		    	currentBtn.style.display = 'none';
		    }else{
		    	currentBtn.style.display = value || '';
		    }
		    return true;
		}
		,generateMixed : function (n, chars) {
			var len = 35;;
			if(chars.length == 0){
				chars = ['0','1','2','3','4','5','6','7','8','9'];
				len = 10;
			}
		     var res = '';
		     for(var i = 0; i < n ; i ++) {
		         var id = Math.ceil(Math.random()*len);
		         res += chars[id];
		     }
		     return res;
		}
		,clearSelect : function (id, cids){
			var pselect_ = '';
			var select_  = '';
			if(id == ''){
				return false;
			}else{
				//清空当前select
				select_ = id;
				select_.options.length = 0;
				select_.options.add(new Option(' -- 请选择 -- ','-1'));
				
				//清空子select
				if(cids != ''){
					for (x in cids){
						cids[x].options.length = 0;
						cids[x].options.add(new Option(' -- 请选择 -- ','-1'));
					}
				}
				return true;
			}
			
			
		}
		,clearform : function (formid){
			document.getElementById(formid).reset();
		}
		,artsetdata : function (dataid){
//			art.dialog.data('"' + dataid + '"', document.getElementById(dataid).value);
			art.dialog.data(dataid, document.getElementById(dataid).value);
		}
		,artgetdata : function (dataid){
			document.getElementById(dataid).value = art.dialog.data(dataid);
		}
		,artorigin : function (){
			var parent = artDialog.open.origin;
			return parent;
		}
		,artclose : function(){
			art.dialog.close();
		}
		,artalert : function(title, id, content){
			if(id == ''){id = 'testID';}
			if(title == ''){title = '提示';}
			if(content == ''){content = '请确认';}
			art.dialog({
				title: title, id: id, lock:true, background: 'white', opacity: 0.2,
			    content: content,
			    button: [
			        {
			            name: '确定',
			            callback: function () {
			            },
			            focus: true
			        },
			        {
			            name: '取消'
			        }
			    ]
			});
		}
		,artopen : function(url, title, w, h){
			if(url == ''){yao.sysUtil.artalert('', '', '未找到目标地址');return false;}
			if(title == ''){title = '新窗口';}
			if(width == ''){w = 550;}
			if(height == ''){h = 455;}
			art.dialog.open(url, {title: title, width: w, height: h, lock:true, background: 'white', opacity: 0.2});
		}
}