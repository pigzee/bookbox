<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>ԤԼʱ���趨</title>
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
						yearRange: '1900:2099', //ȡֵ��Χ.
						showOn: 'both', //������ͼƬ��ť������ʹ�������ؼ���
						buttonImage: 'datePicker/calendar.gif', //�����ؼ��İ�ť
						buttonImageOnly: true,
						showButtonPanel: true
					});
					$('#endTime').datepicker({
						yearRange: '1900:2099', //ȡֵ��Χ.
						showOn: 'both', //������ͼƬ��ť������ʹ�������ؼ���
						buttonImage: 'datePicker/calendar.gif', //�����ؼ��İ�ť
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
	        <td width="434" valign="top" class="bgi_mian_tit">ԤԼʱ���趨</td>
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
	    <legend>��Լ���</legend>
	    <table>
		    <tr>
			    <td>
				     ��Լʱ�䣺
					<input type="text" id="conBeginTime" name="conBeginTime" value="${adminOper.conBeginTime}" disabled/>��
					<input type="text" id="conEndTime" name="conEndTime" value="${adminOper.conEndTime}" disabled/>
			    </td>
		    </tr>
		   
			 <tr>
			    <td>
			   	 <font color="red">ԤԼʱ��Ҫ������Լʱ�䣡</font>
			    </td>
		    </tr>
	    </table>
	  </fieldset>
	  
	  <fieldset>
	  	<legend>ԤԼʱ���趨</legend>
		<table width="90%" border="0" cellpadding="" cellspacing="0">
			<input type="hidden" id="bookBeginTime" name="bookBeginTime" value="${adminOper.bookBeginTime}" disabled/>
			<input type="hidden" id="bookEndTime" name="bookEndTime" value="${adminOper.bookEndTime}" disabled/>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
					  <tr>
					    <td width="20%" align="left" class="bgc_gray02">
					    	<input type="text" id="beginTime" value="${adminOper.bookBeginTime}">
								��
							<input type="text" id="endTime" value="${adminOper.bookEndTime}">
							<input type="button"  value="ʱ���趨" onclick="bookTime();"/>
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
			alert("��һ�����ڴ���");
			$("#beginTime").focus();
			return false;
		}
		var sec = document.getElementById("endTime").value;
		if(!checkDate(sec) ){
			alert("�ڶ������ڴ���");
			$("#endTime").focus();
			return false;
		}
		if(compareDay(fir,sec)<0){
			alert("��һ������Ҫ�������ڵڶ�������!");
			$("#beginTime").focus();
			return false;
		}
		if(bookFir == fir && bookSec == sec){
			alert("����û�иı�!");
			$("#beginTime").focus();
			return false;
		}
		if(compareDay(fir,conFir)>0 || compareDay(fir,conSec)>0 || fir == conSec){
			alert("��һ������Ҫ������Լʱ��!");
			$("#beginTime").focus();
			return false;
		}
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=setBookTime&beginTime=" + fir + "&endTime=" + sec,
			   dataType: 'json',
			   success: function (data, textStatus) {
						if(data.result == "true" || data.result == true){
							alert("ʱ���趨�ɹ���");
						}else{
							alert("ʱ���趨ʧ�ܣ�");
						}
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
		 });
	}
	
////�ж����ڸ�ʽYYYY-MM-DD
////�����Ĳ���ϵͳ
//
//function   checkDate(inputDate)
//{
//	if(inputDate!= " ")
//	{
//		var   reg   =   /^(\d{4})\-(\d{2})\-(\d{2})$/;
//
//		if(reg.exec(inputDate)==null)
//		{
//			return   false;
//		}
//		var   year   =   RegExp.$1;
//		var   month   =   RegExp.$2;
//		var   day   =   RegExp.$3;
//		var   d   =   new   Date(month+ "/ "+day+ "/ "+year);
//
//		var   dateStr   =   d.toLocaleString();
//		newMonth   =   dateStr.substring(dateStr.indexOf( "��")+1,dateStr.indexOf( "��"));
//
//		if(newMonth!=   parseInt(month,10).toString())
//		{
//			return   false;
//		}else
//		{
//			return   true;
//		}
//	}
//}
 

	
</script>