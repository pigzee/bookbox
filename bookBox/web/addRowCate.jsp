<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>��������ҳ</title>
<%String path = request.getContextPath();%>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>

</head>
  
  <body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="<%=path %>/image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">��������</td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>

<form name="reg_form" action="addRowCate.action" onSubmit="return validate();"  method="post">
<s:hidden name="floorCateId" value='<%=request.getParameter("floorCateId") %>'/>
<table width="90%" border="0" cellpadding="" cellspacing="0">
<tr>
<td>
<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
  <tr>
    <td width="20%" align="right" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp;������</td>
    <td width="80%" align="left" class="bgc_white"><input name="cateName" type="text" class="text_08" id="text" value=""/> 
    ��<font color="#FF0000">*</font>&nbsp;Ϊ�����</td>
  </tr>
  
</table>
</td></tr>
</table>


<table width="96%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input type="submit" name="Submit" value="����" class="text_05" >
    &nbsp;&nbsp;<input type="button" name="button" value="ȡ��" class="text_05" onClick="window.location.href='showRowCateManage.action'">
    
    </td>
  </tr>
</table>
</form>

</body>
<script language="JavaScript">
function validate() 
{ 
   f=document.reg_form; 
   if(!legicStr(f.cateName.value)) {
   		alert("�������ж��ţ�С���ţ������źͷ����ţ�");
   		f.cateName.focus();
	    return false;
   }
    if(null == f.cateName.value ||f.cateName.value.length==0 || trim(f.cateName.value).length ==0)
   {	       
	   alert("�������Ʋ���Ϊ�գ�");
	   f.cateName.focus();
	   return false;
   }
   if(f.cateName.value.length>25)
   {	       
	   alert("�������Ʋ�����25���ַ���");
	   f.cateName.focus();
	   return false;
   }
   
   
}
</script>
</html>