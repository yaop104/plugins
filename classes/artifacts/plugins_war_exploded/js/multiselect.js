// 将fromSelect中选中的option移动到toSelect中去
function move(from, to) {
    var fromSelect = document.getElementById(from);
    var toSelect = document.getElementById(to);
    if (fromSelect == null || toSelect == null || fromSelect.selectedIndex < 0) {
        alert("请选择一个操作对象");
        return;
    } else {
        var toMove = fromSelect.options[ fromSelect.selectedIndex ];
        var opt = new Option(toMove.text, toMove.value, false, false);
        fromSelect.options[fromSelect.selectedIndex ] = null;
        toSelect.options[toSelect.length] = opt;
        toSelect.selectedIndex = toSelect.length - 1;
        return;
    }
}

// 将fromSelect中选中的option移动到toSelect中去
function moveSelected(from, to) {
    var fromSelect = document.getElementById(from);
    var toSelect = document.getElementById(to);
    if (fromSelect == null || toSelect == null || fromSelect.selectedIndex < 0) {
        alert("请选择一个操作对象");
        return;
    } else {
        var count = 0; //记录一次移动的个数
        var nextIndex = fromSelect.selectedIndex;
        var index = fromSelect.length - 1;
        while (index >= 0) {
            var toMove = fromSelect.options[index];
            if (toMove.selected) {
            	if(((toMove.value).toLowerCase()).indexOf("sp")!=-1){
            		alert("SP权限不允许手动移动");
            		break;
            	}
                var opt = new Option(toMove.text, toMove.value, false, false);
                fromSelect.options[index] = null;
                toSelect.options[toSelect.length] = opt;
                count = count + 1; //移动一条数据则增一
                nextIndex = index - 1;
            }//end if
            index = index - 1;
        }//end while

        //设置选中被移动后的位置上的一条数据
        if (fromSelect.length > 0) {
            //如果要移动的框中还有数据，而没有选中任何一条，则设置成选中第一条
            if (nextIndex < 0) {
                nextIndex = 0;
            }
            fromSelect.options[nextIndex].selected = true;
        }

        //设置被移动的数据为选中状态
        while (count > 0) {
            toSelect.options[toSelect.length - count].selected = true;
            count = count - 1;
        }
        return;
    }
}

// 向上移动
function up(obj_id) {
    var obj = document.getElementById(obj_id);
    if (obj == null || obj.selectedIndex <= 0) {
        return;
    }

    var index = obj.selectedIndex;
    var toMoveX = obj.options[ index - 1 ];
    var toMoveY = obj.options[ index ];
    var optX = new Option(toMoveX.text, toMoveX.value, false, false);
    var optY = new Option(toMoveY.text, toMoveY.value, false, false);
    obj.options[index] = optX;
    obj.options[index - 1] = optY;
    obj.selectedIndex = index - 1;
    
    return;
}

// 向下移动
function down(obj_id) {
    var obj = document.getElementById(obj_id);
    if (obj == null || obj.selectedIndex + 1 >= obj.options.length) {
        return;
    }

    var index = obj.selectedIndex;
    var toMoveX = obj.options[ index ];
    var toMoveY = obj.options[ index + 1 ];
    var optX = new Option(toMoveX.text, toMoveX.value, false, false);
    var optY = new Option(toMoveY.text, toMoveY.value, false, false);
    obj.options[index] = optY;
    obj.options[index + 1] = optX;
    obj.selectedIndex = index + 1;

    return;
}
