var sortedOn = 0;  
  
function SortTable(sortOn) {  
  
var table = document.getElementById('sortable');  
var tbody = table.getElementsByTagName('tbody')[0];  
var rows = tbody.getElementsByTagName('tr');  
  
var rowArray = new Array();  
/*for (var i=0, length=rows.length; i<length; i++) {  
rowArray[i] = new Object;  
rowArray[i].oldIndex = i;  
rowArray[i].value = rows[i].getElementsByTagName('td')[sortOn].firstChild.nodeValue;  
}  */
$("#sortable  tbody tr").each(function(i){
	rowArray[i] = new Object;  
	rowArray[i].oldIndex = i; 
	rowArray[i].value = $(this).children('td').eq(sortOn).html();
 }); 

if (sortOn == sortedOn) { rowArray.reverse(); }  
else {  
sortedOn = sortOn;  
/* 
Decide which function to use from the three:RowCompareNumbers, 
RowCompareDollars or RowCompare (default). 
For first column, I needed numeric comparison. 
*/  
if (sortedOn == 0) {  
rowArray.sort(RowCompareNumbers);  
}  
else {  
rowArray.sort(RowCompare);  
}  
}  
  
var newTbody = document.createElement('tbody');  
for (var i=0, length=rowArray.length ; i<length; i++) {  
newTbody.appendChild(rows[rowArray[i].oldIndex].cloneNode(true));  
}  
  
table.replaceChild(newTbody, tbody);  
}  
  
function RowCompare(a, b) {   
  
var aVal = a.value;  
var bVal = b.value;  
return (aVal == bVal ? 0 : (aVal > bVal ? 1 : -1));  
}  
// Compare number  
function RowCompareNumbers(a, b) {  
  
var aVal = parseInt( a.value);  
var bVal = parseInt(b.value);  
return (aVal - bVal);  
}  
// compare currency  
function RowCompareDollars(a, b) {  
  
var aVal = parseFloat(a.value.substr(1));  
var bVal = parseFloat(b.value.substr(1));  
return (aVal - bVal);  
}  
 