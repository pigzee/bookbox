<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>�޸�����</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>
</head>

<body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">�޸�����</td>
      </tr>
    </table>
    </td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>

<form action="editAdminPsw.action"  method="post" name="form" onSubmit="return check();">
<table width="90%" border="0" cellpadding="" cellspacing="0">
<tr>
<td>
<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
  <tr>
    <td width="32%" align="right" class="bgc_gray02">ԭ���룺</td>
    <td width="68%" align="left" class="bgc_white"><input name="oldPassword" type="password" class="text_03" /></td>
  </tr>
  <tr>
    <td align="right" class="bgc_gray02">�����룺</td>
    <td align="left" class="bgc_white"><input name="newPassword" type="password" class="text_03"/>&nbsp;���볤�ȱ���Ϊ6-10λ</td>
  </tr>
  <tr valign="top">
    <td align="right" class="bgc_gray02">ȷ�������룺</td>
    <td align="left" class="bgc_white"><input name="reNewPassword" type="password" class="text_03"/>&nbsp;�����������������ͬ</td>
  </tr>  
</table>
</td></tr>
</table>
<br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td align="center">
  <input type="submit" name="submit" value="�޸�"/>&nbsp;&nbsp;
	 <input name="cancel" type="button" value="����" onClick="history.back(-1)"/>
</td>
</tr>
</table>
</form>

</body>
<script language="javascript">
      function check()
      {
         var fm=document.form
         if(!legicStr(fm.newPassword.value)) {
	   		alert("�������ж��ţ�С���ţ������źͷ����ţ�");
	   		fm.newPassword.focus();
		    return false;
		   }	
	     if(fm.oldPassword.value=="")
	     {
		   alert("���벻��Ϊ�գ�")
		   fm.oldPassword.focus()
		   return false
	     }
	     if(fm.newPassword.value=="")
	     {
	       alert("�����벻��Ϊ�գ�")
	       fm.newPassword.focus()
		   return false	     
	     }
	     if(fm.reNewPassword.value=="")
	     {
	       alert("���ٴ����������룡")
	       fm.reNewPassword.focus()
		   return false	     
	     }
	     if(fm.newPassword.value!=fm.reNewPassword.value)
	     {
	       alert("�������������Ӧ����ͬ��")
	       fm.newPassword.focus()
		   return false	     
	     } 
	     if(fm.newPassword.value.length<6||fm.newPassword.value.length>10)
	     {	       
		   alert("���볤�����6λ���10λ��")
		   fm.newPassword.focus()
		   return false
	     }	    	     
	  }
 </script>
</html>
