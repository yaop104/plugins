$(document).ready(function(){
	if($("#sme_newinput").height()!=null){
		$("#sme_newlist").height($("#main",parent.document.body).height()-$("#search").height()-$("#sme_newinput").height()-20);
	}else{
		$("#sme_newlist").height($("#main",parent.document.body).height()-$("#search").height()-48);
	}
}); 
