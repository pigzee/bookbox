<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>�ɷ�</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datePicker/ui.datepicker.css" type="text/css" media="screen" />
		<!-- Include jQuery -->
		<script src="datePicker/jquery-1.2.6.js" type="text/javascript"></script>	
		<!-- Include Core Datepicker JavaScript -->
		<script src="datePicker/ui.datepicker.js" type="text/javascript"></script>	
		<script src="datePicker/datepicker-zh-cn.js" type="text/javascript"></script>	
<script language="javascript">
jQuery(function($){
				$('#signal_day').datepicker({
						yearRange: '1900:2099', //ȡֵ��Χ.
						showOn: 'both', //������ͼƬ��ť������ʹ�������ؼ���
						buttonImage: 'datePicker/calendar.gif', //�����ؼ��İ�ť
						buttonImageOnly: true,
						showButtonPanel: true
					});	
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
				
function pay(studNo){
	if (!confirm("ȷ��Ҫ������")) return false;	
	$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=pay&studNo="+ studNo,
			   dataType: 'json',
			   success: function (data, textStatus) {	
			 			$("#" + studNo).attr("value","�ѽɷ�");
			   		  	$("#" + studNo).attr("disabled","true");
			   		  	alert("�ɷѳɹ���");
	            },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
			   });
}

function p_del() {
	var msg = "ȷ��Ҫɾ����\n\n��ȷ�ϣ�";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}

function validate() 
	{ 
	   f=document.reg_form;    
	   if(f.boxName.value.length>25){	       
		   alert("�������Ʋ�����25���ַ���");
		   f.boxName.focus();
		   return false;
	   }
	   if(null == f.boxName.value ||f.boxName.value.length==0){	       
		   alert("�������Ʋ���Ϊ�գ�");
		   f.boxName.focus();
		   return false;
	   }
	}
	
	function selectSigDay(){
			if(!checkDate(document.getElementById("signal_day").value)){
				alert("���ڴ���");
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
					var s = "������շ�Ϊ" + data.sum + "Ԫ"
					$("#dayPay").append(s);
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
		 });
	}
	function selectPart(){
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
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=getPartTime&beginTime=" + fir + "&endTime=" + sec,
			   dataType: 'json',
			   success: function (data, textStatus) {
					$("#PartPay").empty();
					var s = "���ʱ����շ�Ϊ" + data.sum + "Ԫ"
					$("#PartPay").append(s);
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
		 });
	}
	
//�ж����ڸ�ʽYYYY-MM-DD 
//�����Ĳ���ϵͳ 

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
		newMonth   =   dateStr.substring(dateStr.indexOf( "��")+1,dateStr.indexOf( "��")); 
		
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
<s:if test="${!empty requestScope.msg}">
	<script type="text/javascript">
	alert("${requestScope.msg}");
	</script>
</s:if>
</head>


<body bgcolor="#FFFFFF">
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">�û�����</td>
      </tr>
    </table></td>
  </tr>
</table>


<form name="reg_form" action="payBoxManage.action" method="post">
<table width="96%" class="box_search">
<tr>
<td width="20%" height="30" class="bgi_mian_search">��ѧ�Ž���������</td>
<td width="10%">
	<input type="text" id="studNo" name="studNo" value="" />
</td>
<td width="25%">&nbsp;<a href="#"onClick="JavaScript:document.reg_form.submit();"><img src="image/serach_button.gif" width="63" height="19" border="0"></a></td>
<td width="15%">&nbsp;</td>
<td width="30%" align="center" style="color:#bbb06e">&nbsp;</td>
</tr>
</table>
</form>

<display:table name="allBookInfoes" pagesize="10" id="row" export="true"
   requestURI="payBoxManage.action" class="list" >
   <display:column title="���"><%=pageContext.getAttribute("row_rowNum")%></display:column>
   <display:column property="studNo" title="ѧ����"/>
   <display:column property="studName" title="ѧ������"/>
   <display:column property="boxPlace" title="����λ��"/>
   <display:column property="bookTime" title="ԤԼʱ��"/>
   <display:column property="payTime" title="����ʱ��"/>
</display:table>

<table width="90%" border="0" cellpadding="" cellspacing="0">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
				  <tr>
				    <td width="40%" align="left" class="bgc_gray02">
				    	<font color="#FF0000">*</font>&nbsp;���ڣ�
				    	<input type="text" id="signal_day" value="">
				    	<input type="button"  value="�鿴" onclick="selectSigDay()"/>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				  		<div id="dayPay" style="border:1px;" >�շѲ�ѯ</div>
				  	</td>
				  </tr>
				  <tr>
				    <td width="20%" align="left" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp;���ڣ�
				    	<input type="text" id="beginTime" value="">
							��
						<input type="text" id="endTime" value="">
						<input type="button"  value="�鿴" onclick="selectPart()"/>
					</td>
				  </tr>
				  <tr>
				  	<td>
				  		<div id="PartPay" style="border:1px" >�շѲ�ѯ</div>
				  	</td>
				  </tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>