<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>ѧ����Ϣ</title>
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
	        <td width="434" valign="top" class="bgi_mian_tit">ѧ����Ϣ</td>
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
	    <legend>������Ϣ</legend>
	    <table>
		    <tr>
			    <td align="center">&nbsp;
					ѧԺ��
					<input type="text" id="college" name="college" value="${bookInfo.college}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </td>
		     </tr>
		     <tr>
			    <td align="center"><font color="#FF0000">*</font>&nbsp;
					�༶��
					<input type="text" id="studClass" name="studClass" value="${bookInfo.studClass}" />
					��<font color="#FF0000">*</font>&nbsp;Ϊ�����
			    </td>
		     </tr>
			 <tr>
			    <td align="center"><font color="#FF0000">*</font>&nbsp;
			   	 	�绰���룺<input type="text" id="phone" name="phone" value="${bookInfo.phone}" />
					��<font color="#FF0000">*</font>&nbsp;Ϊ�����
			    </td>
		    </tr>
		    
		    <tr>
			    <td align="center">
					&nbsp;
			    </td>
		    </tr>
		    <tr>
			    <td align="center">
					<input type="submit" value="��һ��" name ="btn" />
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
   		alert("�������ж��ţ�С���ţ������źͷ����ţ�");
   		f.college.focus();
	    return false;
   }
   if(f.college.value.length>50)
   {	       
	   alert("ѧԺ������50���ַ���");
	   f.college.focus();
	   return false;
   }
	
	if(!legicStr(f.studClass.value)) {
   		alert("�������ж��ţ�С���ţ������źͷ����ţ�");
   		f.college.focus();
	    return false;
   }
    if(null == f.studClass.value ||f.studClass.value.length==0 || trim(f.studClass.value).length ==0)
   {	       
	   alert("�༶����Ϊ�գ�");
	   f.studClass.focus();
	   return false;
   }
   if(f.studClass.value.length>50)
   {	       
	   alert("�༶������50���ַ���");
	   f.studClass.focus();
	   return false;
   }	
	
	if (isMobile(f.phone.value)|| isTel(f.phone.value))  {   
            return true;  
        } 
        else {  
            alert("��������ȷ���ֻ������绰����\n\n����:13916752109��3614072"); 
            f.phone.focus();
            return false;        
        }       
   	
	}
</script>