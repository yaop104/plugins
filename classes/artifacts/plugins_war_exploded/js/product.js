/*!
 * A js library for EOP product function.
 * 
 * Created by lxl, on 2013-08-08
 *
 * 
 */

//解析付费方式
function decodeProductFeeType(feeType){
	var feeTypeStr = "";
	if(feeType == 1){
		feeTypeStr = "按次";
	}else if(feeType == 2){
		feeTypeStr = "按周期";
	}
	return feeTypeStr;
}

//解析付费周期
function decodeFeePeriod(unit){
	var feePeriod = "";
	if(unit == 1){
		feePeriod="/月";
	}else if (unit == 2) {
		feePeriod="/周";
	}else if (unit == 3) {
		feePeriod="/天";
	}else if (unit == 4) {
		feePeriod="/次";
	}else if (unit == 5) {
		feePeriod="/次";
	}
	return feePeriod;
}

//解析用户类型
function decodeUserType(userType){
	var userTypeStr = "";
	if(userType == 0){
		userTypeStr = "机构";
	}else if(userType == 1){
		userTypeStr = "个人";
	}
	return userTypeStr;
}

//解析定购状态
function decodeSubscribeStaus(status){
	var str = "未知";
	if (status == 1) {
		str = "待确认";
	} else if (status == 2) {
		str = "正常";
	} else if (status == 3) {
		str = "取消";
	} else if (status == 4) {
		str = "停止购买";
	}
	return str;
}

function decodeSubStaus(status){
	var str = "未知";
	if (status == '0') {
		str = "未入库";
	} else if (status == '1') {
		str = "已入库";
	}
	return str;
}