<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>学生信息</title>
<%String path = request.getContextPath();%>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>
	<link rel="alternate stylesheet" type="text/css" href="thickbox/thickbox.css" title="" />
	<script src="thickbox/jquery-1.1.3.1.pack.js" type="text/javascript"></script>
	<script src="thickbox/thickbox-compressed.js" type="text/javascript"></script>
</head>
  
<body>
	<table width="96%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="68" height="44"><img src="<%=path %>/image/tit_bgi01.gif" width="68" height="44"></td>
	        <td width="434" valign="top" class="bgi_mian_tit">学生信息</td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
	
	<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width="661"></td>
	  </tr>
	</table>
	<form name="reg_form" action="editStudInfo.action" onSubmit="return validate();"  method="post">
	  <fieldset style="width:70%">
	    <legend>个人信息</legend>
	    <table>
		    <tr>
			    <td align="center">&nbsp;
					学院：
					<input type="text" id="college" name="college" value="${bookInfo.college}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </td>
		     </tr>
		     <tr>
			    <td align="center"><font color="#FF0000">*</font>&nbsp;
					班级：
					<input type="text" id="studClass" name="studClass" value="${bookInfo.studClass}" />
					（<font color="#FF0000">*</font>&nbsp;为必填项）
			    </td>
		     </tr>
			 <tr>
			    <td align="center"><font color="#FF0000">*</font>&nbsp;
			   	 	电话号码：<input type="text" id="phone" name="phone" value="${bookInfo.phone}" />
					（<font color="#FF0000">*</font>&nbsp;为必填项）
			    </td>
		    </tr>
		    
		    <tr>
			    <td align="center">
					&nbsp;
			    </td>
		    </tr>
		    <tr>
			    <td align="center">
					<input type="submit" value="下一步" name ="btn" />
			    </td>
		    </tr>
		    
	    </table>
	  </fieldset>
	  </form>
</body>
</html>


<script language="JavaScript">
	function validate(){
		
	f=document.reg_form; 
	if(!legicStr(f.college.value)) {
   		alert("不允许含有逗号，小括号，总括号和方括号！");
   		f.college.focus();
	    return false;
   }
   if(f.college.value.length>50)
   {	       
	   alert("学院不超过50个字符！");
	   f.college.focus();
	   return false;
   }
	
	if(!legicStr(f.studClass.value)) {
   		alert("不允许含有逗号，小括号，总括号和方括号！");
   		f.college.focus();
	    return false;
   }
    if(null == f.studClass.value ||f.studClass.value.length==0 || trim(f.studClass.value).length ==0)
   {	       
	   alert("班级不能为空！");
	   f.studClass.focus();
	   return false;
   }
   if(f.studClass.value.length>50)
   {	       
	   alert("班级不超过50个字符！");
	   f.studClass.focus();
	   return false;
   }	
	
	if (isMobile(f.phone.value)|| isTel(f.phone.value))  {   
            return true;  
        } 
        else {  
            alert("请输入正确的手机号码或电话号码\n\n例如:13916752109或3614072"); 
            f.phone.focus();
            return false;        
        }       
   	
	}
</script>