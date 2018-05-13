<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- admin/user/update.jsp by hy -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jsp:include page="/inc/common.jsp" />
<title>UpdateAppInfo</title>
</head>
    <body>  
        <h5>     </h5><hr/>  
        <br></br>
        <input id="errorMsg" name="errorMsg" value="${requestScope.errorMsg}" type="hidden"></input>
        <input id="imagePath" name="imagePath" value="${requestScope.imagePath}" type="hidden"></input>
        <input id="imageDBPath" name="imageDBPath" value="${requestScope.imageDBPath}" type="hidden"></input>
        <form action='fileUpload.do' method='post' enctype='multipart/form-data'>
		<input type='file' name='upfile' size='50'> <input
			type='submit' value='确定'> 
            <br></br>
           <div><span style="font-family:华文中宋; color:blue; ">&nbsp;&nbsp;&nbsp;&nbsp;注:图片大小最大不能超过4M!</span></div>
        </form>  
    </body>  
   <script language="javascript">
    $(document).ready(function(){
    	var errorMsg= $("#errorMsg").val(); 
    	if(errorMsg.length!=0){ 
    		    /*获取父页面imgid隐藏域id  */
    		    imgid=artDialog.open.origin.document.getElementById("imgid").value;
    		    /* 图片赋值到父窗口指定页面 */
    		    artDialog.open.origin.document.getElementById(imgid).src=$("#imagePath").val();
    		    artDialog.open.origin.document.getElementById(imgid).value=$("#imageDBPath").val();
    			art.dialog.close();
    	}
    });
    </script>
</html>  