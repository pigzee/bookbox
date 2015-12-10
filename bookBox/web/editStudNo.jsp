<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>修改学号</title>
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

<table width="90%" border="0" cellpadding="" cellspacing="0">
<tr>
<td>
<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
  <tr>
    <td width="32%" align="right" class="bgc_gray02"> 原学号：</td>
    <td width="68%" align="left" class="bgc_white"><input name="oldStuoNo" id="oldStudNo" type="text" class="text_03" /></td>
  </tr>
  <tr>
    <td align="right" class="bgc_gray02">新学号：</td>
    <td align="left" class="bgc_white"><input name="newStudNo" id="newStudNo" type="text" class="text_03"/>&nbsp;</td>
  </tr>
</table>
</td></tr>
</table>
<br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td align="center">
  <input type="button" name="editStudBtn" value="修改" onclick="check();"/>&nbsp;&nbsp;
	 <input name="cancel" type="button" value="返回" onClick="history.back(-1)"/>
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
	   		alert("原学号不能为空");
		    return false;
		 }
		 if(newStudNo=="") {
	   		alert("新学号不能为空");
		    return false;
		   }
		 var str = '原学号为' +　oldStudNo　+ '\n' + '新学号为' +  newStudNo + '\n确认提交？';
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
						   	alert("保存失败;"+XmlHttpRequest.responseText);
				   }
			 });
		 }else{
		 	 return false;
		 }
	}
</script>