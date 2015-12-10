<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>续约时间设定</title>
<%String path = request.getContextPath();%>
<link href="css/right.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" href="datePicker/ui.datepicker.css" type="text/css" media="screen" />
		<!-- Include jQuery -->
		<script src="datePicker/jquery-1.2.6.js" type="text/javascript"></script>	
		<!-- Include Core Datepicker JavaScript -->
		<script src="datePicker/ui.datepicker.js" type="text/javascript"></script>	
		<script src="datePicker/datepicker-zh-cn.js" type="text/javascript"></script>	
		<!-- Attach the datepicker to dateinput after document is ready -->
		<script type="text/javascript" src="js/JsUtil.js"></script>
		<script type="text/javascript">
				jQuery(function($){
					$('#beginTime').datepicker({
						yearRange: '1900:2099', //取值范围.
						showOn: 'both', //输入框和图片按钮都可以使用日历控件。
						buttonImage: 'datePicker/calendar.gif', //日历控件的按钮
						buttonImageOnly: true,
						showButtonPanel: true
					});
					$('#endTime').datepicker({
						yearRange: '1900:2099', //取值范围.
						showOn: 'both', //输入框和图片按钮都可以使用日历控件。
						buttonImage: 'datePicker/calendar.gif', //日历控件的按钮
						buttonImageOnly: true,
						showButtonPanel: true
					});
				});
		</script>
</head>
  
<body>
	<table width="96%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="68" height="44"><img src="<%=path %>/image/tit_bgi01.gif" width="68" height="44"></td>
	        <td width="434" valign="top" class="bgi_mian_tit">续约时间设定</td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
	
	<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width="661"></td>
	  </tr>
	</table>

<form style="width:80%">
	  <fieldset>
	    <legend>预约情况</legend>
	    <table>
		    <tr>
			    <td align="center">
					预约时间：
					<input type="text" id="bookBeginTime" name="bookBeginTime" value="${adminOper.bookBeginTime}" disabled/>到
					<input type="text" id="bookEndTime" name="bookEndTime" value="${adminOper.bookEndTime}" disabled/>			   
			    </td>
		     </tr>
			 <tr>
			    <td>
			   	 <font color="red">续约时间要早于预约时间！</font>
			    </td>
		    </tr>
	    </table>
	  </fieldset>
	  
	  <fieldset>
	  	<legend>续约时间设定</legend>
		<table width="90%" border="0" cellpadding="" cellspacing="0">
	
			<input type="hidden" id="conBeginTime" name="conBeginTime" value="${adminOper.conBeginTime}" disabled/>
			<input type="hidden" id="conEndTime" name="conEndTime" value="${adminOper.conEndTime}" disabled/>
			
			<tr>
				<td>
					<font color="red">续费前需要把所有的箱子状态都清零，请点击清零按钮。其他时间请不要点击此按钮。
						<input type="button"  style="height:20px;width:90px;border:1px solid #666666"
							 value="箱子状态清零" onclick="clearBoxStatus();" />
					</font>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
					  <tr>
					    <td width="20%" align="left" class="bgc_gray02">
					    	<input type="text" id="beginTime" value="${adminOper.conBeginTime}">
								到
							<input type="text" id="endTime" value="${adminOper.conEndTime}">
							<input type="button"  value="时间设定" onclick="bookTime();"/>
						</td>
					  </tr>
					</table>
				</td>
			</tr>
		</table>
	  </fieldset>
	</form>
</body>
</html>


<script language="JavaScript">

	function bookTime(){
	    var conFir = document.getElementById("conBeginTime").value;
		var conSec = document.getElementById("conEndTime").value
		var bookFir = document.getElementById("bookBeginTime").value;
		var bookSec = document.getElementById("bookEndTime").value
		var fir = document.getElementById("beginTime").value;
		if(!checkDate(fir)){
			alert("第一个日期错误2");
			$("#beginTime").focus();
			return false;
		}
		var sec = document.getElementById("endTime").value;
		if(!checkDate(sec) ){
			alert("第二个日期错误");
			$("#endTime").focus();
			return false;
		}
		if(compareDay(fir,sec)<0){
			alert("第一个日期要不能晚于第二个日期!");
			$("#beginTime").focus();
			return false;
		}
		if(conFir == fir && conSec == sec){
			alert("日期没有改变!");
			$("#beginTime").focus();
			return false;
		}
		if(compareDay(sec,bookFir)<0 || compareDay(sec,bookSec)<0 || sec == bookFir){
			alert("第二个日期要早于预约时间!");
			$("#endTime").focus();
			return false;
		}
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=setConTime&beginTime=" + fir + "&endTime=" + sec,
			   dataType: 'json',
			   success: function (data, textStatus) {
						if(data.result == "true" ||data.result == true){
							alert("时间设定成功！");
						}else{
							alert("时间设定失败！");
						}
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("保存失败;"+XmlHttpRequest.responseText);
			   }
		 });
	}
	

 
function clearBoxStatus(){
	if( confirm("只能在箱子使用到期，学生再次需要续约预约才能清零，确认清零？")){
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=clearBoxStatus",
			   dataType: 'json',
			   success: function (data, textStatus) {
						if(data.result == "true" ||data.result == true){
							alert("清零成功！");
						}else{
							alert("箱子数量为空，或者清零失败！");
						}
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("清零失败;"+XmlHttpRequest.responseText);
			   }
		 });
	} 
	
}
	
</script>