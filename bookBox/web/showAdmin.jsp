<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%String path = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>管理员管理</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />

</head>
  
  <body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">管理员信息管理</td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>

<table width="90%" border="0" cellpadding="" cellspacing="0">
<tr>
<td>
<table width="96%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray" align="center">
  <tr>
    <td width="32%" align="right" class="bgc_gray02">用户名：</td>
    <td width="68%" align="left" class="bgc_white">${adminOper.adminName}</td>
  </tr>
  <tr>
	  <td width="32%" align="right" class="bgc_gray02">密码：</td>
	  <td colspan="2" class="bgc_white"> ${adminOper.adminPsw}</td>
  </tr>
</table>
</td>
</tr>

</table>
<br>
<input name="editName" class="text_05" type="button" value="修改用户名"
	 onClick="window.location.href='showAdminById.action?adminOperId=${adminOper.adminOperId}&edit=editName'"/>
<input name="editPsw" class="text_05" type="button" value="修改密码"
	 onClick="window.location.href='showAdminById.action?adminOperId=${adminOper.adminOperId}&edit=editPsw'"/>
<br>

</body>
</html>