<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<html>

<head>
<title>操作结果</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="46"><img src="<%=path %>/image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>



<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>

<br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td align="center"><A href="<%=path %>/back_login.jsp" target="_parent">网页已过期或您尚未登录，请重新登录。</A></td>
</tr>
</table>

</body>
</html>
