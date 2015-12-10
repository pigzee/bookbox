<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<html>

<head>
<title>修改管理员名称</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>
</head>

<body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">修改管理员名称</td>        
      </tr>      
    </table></td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
        <font color="#FF0000" font-size="12px" align="left"><s:actionmessage/></font>
    </td>
  </tr>
</table>

<form action="editAdminName.action"  method="post" name="form" onsubmit="return check();">
<table width="90%" border="0" cellpadding="" cellspacing="0" >
<tr>
<td>
<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
  <tr>
    <td width="32%" align="right" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp; 用户名：</td>
    <td width="68%" align="left" class="bgc_white"><input name="adminName" type="text" class="text_06" value="<s:property value="adminOper.adminName"/>"/>
      &nbsp;用户名长度4-10位，<font color="#FF0000">*</font>&nbsp;为必填项</td>
  </tr>
</table>
</td></tr>
</table>

<table width="96%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input type="submit" name="Submit" value="修改">&nbsp;&nbsp;
	 <input name="cancel" type="button" value="返回" onClick="history.back(-1)"/></td>
  </tr>
</table>
</form>
</body>
	<!--以下验证放在最后面，命名为check  -->
	<!--还缺少对每个属性的长度的判定 -->
	<script language="javascript">
      function check()
      {
         var fm=document.form	
          if(!legicStr(fm.adminName.value)) {
	   		alert("不允许含有逗号，小括号，总括号和方括号！");
	   		fm.adminName.focus();
		    return false;
		   }
	     if(fm.adminName.value=="")
	     {
		   alert("用户名不能为空！")
		   fm.adminName.focus()
		   return false
	     }
	     
	     if(fm.adminName.value.length<4||fm.adminName.value.length>10)
	     {
	       alert("用户名长度4-10个字符！")
	       fm.adminName.focus()
		   return false	     
	     }   
	     return true
	  }
</script>
</html>
