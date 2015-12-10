<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>箱子收费查询</title>
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
				$('#signal_day').datepicker({
						yearRange: '1900:2099', //取值范围.
						showOn: 'both', //输入框和图片按钮都可以使用日历控件。
						buttonImage: 'datePicker/calendar.gif', //日历控件的按钮
						buttonImageOnly: true,
						showButtonPanel: true
					});	
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
        <td width="434" valign="top" class="bgi_mian_tit">箱子收费查询</td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>


<form name="reg_form" action="priceManage.action" method="post" style="width:80%">
	<fieldset>
		    <legend>收费情况</legend>
		    <table width="90%" border="0" cellpadding="" cellspacing="0">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
					  <tr>
					    <td width="20%" align="left" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp;日期：
					    	<input type="text" name ="beginTime" id="beginTime" value="${beginTime}">
								到
							<input type="text" name ="endTime" id="endTime" value="${endTime}">
							<a href="#"><input type="button"  value="查看" onclick="selectPart();"/></a>
						</td>
						
					  </tr>
					  <tr>
					  	<td>
					  		<div id="PartPay" style="border:1px;" >总收费额为：${sum}元</div>
					  	</td>
					  </tr>
					 
					</table>
				</td>
			</tr>
		</table>
		<display:table varTotals="sumurationMap" name="allBookInfoes" pagesize="10" id="row" export="true"
		   requestURI="priceManage.action" class="list">
		   <display:column title="序号"><%=pageContext.getAttribute("row_rowNum")%></display:column>
		   <display:column property="studNo" title="学生号"/>
		   <display:column property="studName" title="学生姓名"/>
		   <display:column property="college" title="学院"/>
		   <display:column property="studClass" title="班级"/>
		   <display:column property="phone" title="电话号码"/>
		   <display:column property="boxPlace" title="箱子位置"/>
		   <display:column property="bookTime" title="预约时间"/>
		   <display:column property="payTime" title="付费时间"/>
		   <display:column property="currBoxprice" title="价钱(元)" total="true"/>
		   <display:footer >
			<tr>
			<td></td>
			<td>total:</td>
			<td><fmt:formatNumber value="${sumurationMap.column10}" pattern="#,###.##"/>元</td>
			</tr>
			</display:footer>
		   
		   
		</display:table>
	</fieldset>
</form>



</body>
</html>


<script language="JavaScript">
	/*
	function selectSigDay(){
			if(!checkDate(document.getElementById("signal_day").value)){
				alert("日期错误");
				$("#signal_day").focus();
				return false;
			}
			$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=getSignTime&signTime=" + document.getElementById("signal_day").value,
			   dataType: 'json',
			   success: function (data, textStatus) {
			   		$("#dayPay").empty();
					var s = "这天的收费为" + data.sum + "元"
					$("#dayPay").append(s);
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("保存失败;"+XmlHttpRequest.responseText);
			   }
		 });
	}
	*/
	function selectPart(){
		var fir = document.getElementById("beginTime").value;
		if(!checkDate(fir)){
			alert("第一个日期错误");
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
		document.reg_form.submit();
	}
	
//判断日期格式YYYY-MM-DD 
//限中文操作系统 

function   checkDate(inputDate) 
{ 
	if(inputDate!= " ") 
	{ 
		var   reg   =   /^(\d{4})\-(\d{2})\-(\d{2})$/; 
		
		if(reg.exec(inputDate)==null)   
		{ 
			return   false; 
		} 
		var   year   =   RegExp.$1; 
		var   month   =   RegExp.$2; 
		var   day   =   RegExp.$3; 
		var   d   =   new   Date(month+ "/ "+day+ "/ "+year); 
		
		var   dateStr   =   d.toLocaleString(); 
		newMonth   =   dateStr.substring(dateStr.indexOf( "年")+1,dateStr.indexOf( "月")); 
		
		if(newMonth!=   parseInt(month,10).toString()) 
		{ 
			return   false; 
		}else 
		{ 
			return   true; 
		} 
	} 
} 
 

	
</script>