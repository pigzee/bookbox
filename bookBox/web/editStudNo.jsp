<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>�޸�ѧ��</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
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

<table width="90%" border="0" cellpadding="" cellspacing="0">
<tr>
<td>
<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
  <tr>
    <td width="32%" align="right" class="bgc_gray02"> ԭѧ�ţ�</td>
    <td width="68%" align="left" class="bgc_white"><input name="oldStuoNo" id="oldStudNo" type="text" class="text_03" /></td>
  </tr>
  <tr>
    <td align="right" class="bgc_gray02">��ѧ�ţ�</td>
    <td align="left" class="bgc_white"><input name="newStudNo" id="newStudNo" type="text" class="text_03"/>&nbsp;</td>
  </tr>
</table>
</td></tr>
</table>
<br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td align="center">
  <input type="button" name="editStudBtn" value="�޸�" onclick="check();"/>&nbsp;&nbsp;
	 <input name="cancel" type="button" value="����" onClick="history.back(-1)"/>
</td>
</tr>
</table>

</body>
</html>
<script>
	function check(){
		 var oldStudNo = $('#oldStudNo').val();
		 var newStudNo = $('#newStudNo').val();
         if(oldStuoNo=="") {
	   		alert("ԭѧ�Ų���Ϊ��");
		    return false;
		 }
		 if(newStudNo=="") {
	   		alert("��ѧ�Ų���Ϊ��");
		    return false;
		   }
		 var str = 'ԭѧ��Ϊ' +��oldStudNo��+ '\n' + '��ѧ��Ϊ' +  newStudNo + '\nȷ���ύ��';
		 if(confirm(str))	{
		 	$.ajax({
				   type: "POST",
				   url: "bookBoxAction.jsp",
				   data: "method=editStudNo&oldStudNo=" + oldStudNo + '&newStudNo=' + newStudNo,
				   dataType: 'json',
				   success: function (data, textStatus) {
				   			alert(data.result)
							window.location.reload();
		           },
		           error:function(XmlHttpRequest,textStatus, errorThrown){
						   	alert("����ʧ��;"+XmlHttpRequest.responseText);
				   }
			 });
		 }else{
		 	 return false;
		 }
	}
</script>