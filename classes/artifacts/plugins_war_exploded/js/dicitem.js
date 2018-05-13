//------查看详情页面(view.jsp)字典值转换方法：
//------1、无视字典代码完成view功能；
//------2、在view.jsp页面增加脚本
//------    $(document).ready(function() {
//------		// 该url根据你的业务路径决定，
//------		var actionUrl = "<s:url value="/biz/case/selectDic.action" encode="false"/>";   
//------		changeCode2Value(actionUrl);
//------	});
//------3、用特定span包围待转换的项，name与catalogid必须存在，name属性值不能改
//------	<tr>
//------		<td>性别</td>
//------		<td><span name="needChange" catalogid="SEX">${person.sex}</span></td>
//------	</tr>
//------4、定义第2步中用到的action，在当前action配置处增加; （仅需配置）
//------	<package name="biz.case" namespace="/biz/case" extends="default">
//------		<action name="selectDic" class="dictionaryAction" method="selectAll">
//------			<result name="success" type="json">  
//------				<param name="root">jsonData</param> 
//------			</result>
//------		</action>
//------	</package>
//------
//------此方法，仅能解析字典代码，其他有独立表如区域、分辨率等，不能这样做
/**
 * 将字典代码转为实际值
 * 页面上名称为needChange的SPAN元素，如果指定catalogId，则转换其内容
 * @param actionUrl 取字典项的action
 */
function changeCode2Value(actionUrl){
	var dicItems = findDics(actionUrl);
	jQuery.each($("span[name='needChange']"), function(i, n){
		var catalogId = $(n).attr("catalogid");
		var dicCode = $(n).text();
		if(catalogId && dicCode){
			var value = dicItems.get(catalogId+","+dicCode);
			$(n).text(value);
		}
	}); 
}

//------为页面的select添加项方法：
//------1、无视代码完成update.jsp(insert.jsp同理)；
//------2、在update.jsp页面增加脚本
//------    $(document).ready(function() {
//------		// 该url根据你的业务路径决定，
//------		var actionUrl = "<s:url value="/biz/case/selectDic.action" encode="false"/>";   
//------		listDicItems(actionUrl);
//------	});
//------3、给select增加fromDic、catalogid、value、hasBlankItem属性
//------	  <select class="dropdownlist" id="valid_flag" name="user.validFlag" fromDic="true" catalogid="STATUS" val="${user.validFlag}" hasBlankItem=true tip="状态"></select>
//------4、定义第2步中用到的action，在当前action配置处增加; （仅需配置）
//------	<package name="biz.case" namespace="/biz/case" extends="default">
//------		<action name="selectDic" class="dictionaryAction" method="selectAll">
//------			<result name="success" type="json">  
//------				<param name="root">jsonData</param> 
//------			</result>
//------		</action>
//------	</package>
//------
//------此方法，仅能解析字典代码，其他有独立表如区域、分辨率等，不能这样做
/**
 * 为页面的select添加项
 * 页面上属性fromDic为true的SELECT元素，如果指定catalogId，则为其添加项，数据来自字典
 * @param actionUrl 	取字典项的action
 */
function listDicItems(actionUrl){
	var dicItems = findDicItems(actionUrl);
	jQuery.each($("select[fromDic='true']"), function(i, n){
		var id = $(n).attr("id");
		var catalogId = $(n).attr("catalogid");
		var value = $(n).attr("val");
		if(catalogId){
			$("#"+id).empty();
			if($(n).attr("hasBlankItem")=="true"){
				AddComboBoxItem(id,"**请选择**","");
			}
			var cata = dicItems.get(catalogId);
			jQuery.each(cata, function(i, n){
				if(value==n.dicCode){
					AddComboBoxItem(id,n.dicValue,n.dicCode,true);
				}
				else{
					AddComboBoxItem(id,n.dicValue,n.dicCode);
				}
			});
		}
	}); 
}

/**
 * 查找字典所有字典项
 * @param actionUrl 查找所有字典项的action
 * @return
 */
function findDics(actionUrl){
	var dicItems = new Map();
	jQuery.ajax({
        url:actionUrl,
        type:"POST",
        cache:false,
        async:false,
        dataType: "json",
        success:function(json, textStatus) {
			json = jQuery.evalJSON(json);
			jQuery.each(json, function(i, n){
				dicItems.put(n.catalogId+","+n.dicCode, n.dicValue);
			}); 
        }
    });
	
	return dicItems;
}

/**
 * 查找字典所有字典项
 * 建立catalogId为键，字典项Map为值的Map
 * 字典项Map：dicCod为键，dicValue为值
 * @param actionUrl 查找所有字典项的action
 * @return
 */
function findDicItems(actionUrl){
	var dicItems = new Map();
	jQuery.ajax({
        url:actionUrl,
        type:"POST",
        cache:false,
        async:false,
        dataType: "json",
        success:function(json, textStatus) {
			json = jQuery.evalJSON(json);
			var count = getLength(json)
			// 当前字典分类id
			var curCatalogId = "";
			var cata = null;
			jQuery.each(json, function(i, n){
				if(n.catalogId!=curCatalogId){
					if(cata){
						dicItems.put(curCatalogId, cata);
					}
					cata = new Array();
					curCatalogId = n.catalogId;
				}
				cata.push(n);
				if(i==count-1){
					dicItems.put(curCatalogId, cata);
				}
			}); 
        }
    });
	
	return dicItems;
}

// 获取json对象的一级元素个数
function getLength(json) {
	var count = 0;
	for(key in json) count++;
	return count;
} 
