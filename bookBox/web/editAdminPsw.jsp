<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>修改密码</title>
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
        <td width="434" valign="top" class="bgi_mian_tit">修改密码</td>
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
    <td width="32%" align="right" class="bgc_gray02">原密码：</td>
    <td width="68%" align="left" class="bgc_white"><input name="oldPassword" type="password" class="text_03" /></td>
  </tr>
  <tr>
    <td align="right" class="bgc_gray02">新密码：</td>
    <td align="left" class="bgc_white"><input name="newPassword" type="password" class="text_03"/>&nbsp;密码长度必须为6-10位</td>
  </tr>
  <tr valign="top">
    <td align="right" class="bgc_gray02">确认新密码：</td>
    <td align="left" class="bgc_white"><input name="reNewPassword" type="password" class="text_03"/>&nbsp;两次输入密码必须相同</td>
  </tr>  
</table>
</td></tr>
</table>
<br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td align="center">
  <input type="submit" name="submit" value="修改"/>&nbsp;&nbsp;
	 <input name="cancel" type="button" value="返回" onClick="history.back(-1)"/>
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
	   		alert("不允许含有逗号，小括号，总括号和方括号！");
	   		fm.newPassword.focus();
		    return false;
		   }	
	     if(fm.oldPassword.value=="")
	     {
		   alert("密码不能为空！")
		   fm.oldPassword.focus()
		   return false
	     }
	     if(fm.newPassword.value=="")
	     {
	       alert("新密码不能为空！")
	       fm.newPassword.focus()
		   return false	     
	     }
	     if(fm.reNewPassword.value=="")
	     {
	       alert("请再次输入新密码！")
	       fm.reNewPassword.focus()
		   return false	     
	     }
	     if(fm.newPassword.value!=fm.reNewPassword.value)
	     {
	       alert("两次输入的密码应该相同！")
	       fm.newPassword.focus()
		   return false	     
	     } 
	     if(fm.newPassword.value.length<6||fm.newPassword.value.length>10)
	     {	       
		   alert("密码长度最短6位，最长10位！")
		   fm.newPassword.focus()
		   return false
	     }	    	     
	  }
 </script>
</html>
