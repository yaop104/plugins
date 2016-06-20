
//  全选 formName:form的name值;  checkboxName:checkbox的name值
function selectAll(formName,checkboxName){
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            elements.checked = true;
        } else {
            for (var i=0;i<elements.length;i++){ 
                elements[i].checked = true;
            }
        }
    }
} 

//  不选 formName:form的name值;  checkboxName:checkbox的name值
function selectNone(formName,checkboxName){
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            elements.checked = false;
        } else {
            for (var i=0;i<elements.length;i++){ 
                elements[i].checked = false;
            }
        }
    }
} 

//  反选 formName:form的name值;  checkboxName:checkbox的name值
function selectReverse(formName,checkboxName){
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            if(elements.checked == true){
                elements.checked = false;
            } else {
                elements.checked = true;
            }
        } else {
            for (var i=0;i<elements.length;i++){ 
                var e = elements[i];
                if(e.checked == true){
                    e.checked = false;
                } else {
                    e.checked = true;
                }
            }
        }
    }
} 

//  是否有checkbox选中 formName:form的name值;  checkboxName:checkbox的name值
function isSelect(formName,checkboxName){ 
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            if(elements.checked == true){
                return true;
            }
        } else {
            for (var i=0;i<elements.length;i++){ 
                var e = elements[i];
                if(e.checked == true){
                    return true;
                }
            }
        }
    }
  
    return false;
}


//  得到选中checkbox的值 formName:form的name值;  checkboxName:checkbox的name值
function getSelects(formName,checkboxName){
    var reStr = "";
  
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            if(elements.checked == true){
                reStr = elements.value;
            }
        } else {
            for (var i=0;i<elements.length;i++){ 
                var e = elements[i];
                if(e.checked == true){
                    if(reStr == ""){
                        reStr = reStr + e.value;
                    } else {
                        reStr = reStr + "," + e.value;
                    }
                }
            }
        }
    }
  
    return reStr;
}

//  得到所有checkbox的值 formName:form的name值;  checkboxName:checkbox的name值
function getAllSelects(formName,checkboxName){
    var reStr = "";
  
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            reStr = elements.value;
        } else {
            for (var i=0;i<elements.length;i++){ 
                var e = elements[i];
                if(reStr == ""){
                    reStr = reStr + e.value;
                } else {
                    reStr = reStr + "," + e.value;
                }
            }
        }
    }
  
    return reStr;
}


//  全选或反选 checkboxAllName:全选/全不选checkbox的name值;  formName:form的name值;  checkboxName:checkbox的name值
function selectCheckbox(checkboxAllName,formName,checkboxName){
    var checkboxObj = getCheckboxByName(checkboxAllName);
    if(checkboxObj != null){

        if(checkboxObj.checked == true){
            selectAll(formName,checkboxName);
        } else {
            selectNone(formName,checkboxName);
        }

    }
}

//  选中checkbox的数量 formName:form的name值;  checkboxName:checkbox的name值
function getSelectNum(formName,checkboxName){
    var count = 0;
    var form = getFormByName(formName);
    var elements = form.elements[checkboxName];
    if(elements != null){
        if(!elements.length){
            if(elements.checked == true){
                count = count + 1;
            }
        } else {
            for (var i=0;i<elements.length;i++){ 
                var e = elements[i];
                if(e.checked == true){
                    count = count + 1;
                }
            }
        }
    }

    return count;
}

//  获取指定名称的form对象
function getFormByName(name){
    var tags = document.getElementsByTagName("form");
    if(tags.length){
        for(var i = 0; i < tags.length; i++){
            if(tags[i].name == name){
                return tags[i];
            }
        }
    } else {
        if(tags.name = name){
            return tags;
        } else {
            return null;
        }
    }
}

//  获取指定名称的checkbox对象
function getCheckboxByName(name){
    var tags = document.getElementsByTagName("input");
    if(tags.length){
        for(var i = 0; i < tags.length; i++){
            if(tags[i].type == "checkbox" && tags[i].name == name){
                return tags[i];
            }
        }
    } else {
        return null;
    }
}