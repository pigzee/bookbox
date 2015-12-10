<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<html>

<head>
<title>设定预约最大箱子数</title>
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
        <td width="434" valign="top" class="bgi_mian_tit">设定预约最大箱子数</td>        
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
				    <td width="32%" align="right" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp; 允许预约的最大箱子数：</td>
				    <td width="68%" align="left" class="bgc_white"><input name="maxBoxCount" type="text" id="maxBoxCount"
				    	 class="text_06" value="<s:property value='${adminOper.maxBoxCount}'/>"/>个
				      &nbsp;(大于0,并且小于100)
				  </tr>
				</table>
			</td>
		</tr>
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
	     if(isNaN(fm.maxBoxCount.value) || fm.maxBoxCount.value < 0 
	     	|| $.trim(fm.maxBoxCount.value) == "")
	     {
	       alert("数量不能为空，并且必须是正整数！")
	       fm.maxBoxCount.focus()
		   return false	     
	     }   
	     if(fm.maxBoxCount.value > 100){
	       alert("箱子数量太多！")
	       fm.maxBoxCount.focus()
		   return false	
		 }
		 $("#maxBoxCount").val($.trim(fm.maxBoxCount.value));
	     return true
	  }
</script>
</html>
