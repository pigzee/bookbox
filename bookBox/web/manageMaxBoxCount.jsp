<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%String path = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>查看预约最大箱子数</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />

</head>
  
  <body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">查看预约最大箱子数</td>
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
    <td width="32%" align="right" class="bgc_gray02">允许预约的最大箱子数：</td>
    <td width="68%" align="left" class="bgc_white">${adminOper.maxBoxCount}个</td>
  </tr>
</table>
</td>
</tr>

</table>
<br>
<input name="editName" class="text_05" type="button" value="修改个数"
	 onClick="window.location.href='showAdminById.action?adminOperId=${adminOper.adminOperId}&edit=editMaxBoxCount'"/>
<br>

</body>
</html>