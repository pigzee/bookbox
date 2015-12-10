<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>续约箱子</title>
<%String path = request.getContextPath();%>
<link href="css/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>

</head>
  
  <body>
<div style="font-weight:bold;padding:10px;"><font >箱子预约页面</font></div>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>

<form name="reg_form" action="addBox.action" onSubmit="return validate();"  method="post">
	<s:hidden name="rowCateId" value='<%=request.getParameter("rowCateId") %>'/>
	<table width="70%" border="0" cellpadding="" cellspacing="0" style="align:center">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray02">
				  <tr>
				    <td width="20%" align="center"  >您只能续约先前的订购的箱子，如果续约请在${adminOper.conEndTime}之前去图书馆缴费。</td>
				  </tr>
				  <tr>
				    <td width="20%" align="center"  >您现在可以续约${adminOper.maxBoxCount}个箱子，谨慎选择续约的箱子。</td>
				  </tr>
				  <tr>
				    <td width="20%" align="center"  >如果想预购新的箱子，请${adminOper.bookBeginTime}到${adminOper.bookEndTime}时间段登陆选择。</td>
				  </tr>
				  <tr>
				    <td width="20%" align="center"  >续约后不缴费，系统在${adminOper.conEndTime}晚上会自动取消。</td>
				  </tr>
				  <!-- 
					  <tr>
					    <td width="20%" align="center" >你想续约<input type="text" size="30" value="${bookInfo.boxPlace}" />吗？</td>
					  </tr>
				   -->
				</table>
			</td>
		</tr>
		
		<tr align="center">
			<td>
				<table style="align:center">
		  			<tr>
			  			<td>
				  			<fieldset>
				  			<legend>待续约</legend>
					  			<table>
									<c:forEach  items="${biNoConBookList}" var="bi"> 
									 	<tr>
									 		<td>
									 			<input type='button'  style='width:250' border=1px title=${bi.boxPlace} solid #666666 value='续约 ${bi.boxPlace}' 
									 				onclick="conBox(${bi.box.boxId})" />
									 		</td>
									 	</tr>
							        </c:forEach> 
					  			</table>
				  			</fieldset>
			  			</td>
			  			<td> 
				  			<fieldset >
					  			<legend>已续约</legend>
					  			<table>
					  				<c:forEach  items="${biHasConList}" var="bi"> 
									 	<tr>
									 		<td>
									 			<input type='button'  style='width:250' border=1px solid #666666 value='${bi.boxPlace}' disabled />
									 		</td>
									 	</tr>
							        </c:forEach> 
					  			</table>
				  			</fieldset>
			  			</td>
			  			<td> 
				  			<fieldset>
					  			<legend>已付费</legend>
					  			<table>
					  				<c:forEach  items="${biHasPayList}" var="bi"> 
									 	<tr>
									 		<td>
									 			<input type='button'  style='width:250' border=1px solid #666666 value='${bi.boxPlace}' disabled />
									 		</td>
									 	</tr>
							        </c:forEach> 
					  			</table>
				  			</fieldset>
			  			</td>
		  			</tr>
		  		</table>
			</td>
		</tr>
	</table>

</form>

</body>
<script language="JavaScript">
function conBox(boxId){
	$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=conBox&boxId=" + boxId,
			   dataType: 'json',
			   success: function (data, textStatus) {
			   			alert(data.result)
						window.location.reload();
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("保存失败;"+XmlHttpRequest.responseText);
			   }
		 });
}
</script>
</html>