<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<html>

<head>
<title>�趨ԤԼ���������</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>
	<script src="datePicker/jquery-1.2.6.js" type="text/javascript"></script>
</head>

<body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">�趨ԤԼ���������</td>        
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

<form action="editMaxBoxCount.action"  method="post" name="form" onsubmit="return check();">
	<input type="hidden" name="status" value="${edit}" />
	<table width="90%" border="0" cellpadding="" cellspacing="0" >
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
				  <tr>
				    <td width="32%" align="right" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp; ����ԤԼ�������������</td>
				    <td width="68%" align="left" class="bgc_white"><input name="maxBoxCount" type="text" id="maxBoxCount"
				    	 class="text_06" value="<s:property value='${adminOper.maxBoxCount}'/>"/>��
				      &nbsp;(����0,����С��100)
				  </tr>
				</table>
			</td>
		</tr>
	</table>

<table width="96%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input type="submit" name="Submit" value="�޸�">&nbsp;&nbsp;
	 <input name="cancel" type="button" value="����" onClick="history.back(-1)"/></td>
  </tr>
</table>
</form>
</body>
	<!--������֤��������棬����Ϊcheck  -->
	<!--��ȱ�ٶ�ÿ�����Եĳ��ȵ��ж� -->
	<script language="javascript">
      function check()
      {
      
         var fm=document.form	
	     if(isNaN(fm.maxBoxCount.value) || fm.maxBoxCount.value < 0 
	     	|| $.trim(fm.maxBoxCount.value) == "")
	     {
	       alert("��������Ϊ�գ����ұ�������������")
	       fm.maxBoxCount.focus()
		   return false	     
	     }   
	     if(fm.maxBoxCount.value > 100){
	       alert("��������̫�࣡")
	       fm.maxBoxCount.focus()
		   return false	
		 }
		 $("#maxBoxCount").val($.trim(fm.maxBoxCount.value));
	     return true
	  }
</script>
</html>
