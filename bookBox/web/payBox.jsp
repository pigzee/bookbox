<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>箱子收费管理</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datePicker/ui.datepicker.css" type="text/css" media="screen" />
		<!-- Include jQuery -->
		<script src="datePicker/jquery-1.2.6.js" type="text/javascript"></script>	
		<!-- Include Core Datepicker JavaScript -->
		<script src="datePicker/ui.datepicker.js" type="text/javascript"></script>	
		<script src="datePicker/datepicker-zh-cn.js" type="text/javascript"></script>	
<script language="javascript">
				
function pay(bookInfoId){
	if (!confirm("确定要付费吗？")) return false;	
	$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=pay&bookInfoId="+ bookInfoId,
			   dataType: 'json',
			   success: function (data, textStatus) {	
			 			$("#" + bookInfoId).attr("value","已缴费");
			   		  	$("#" + bookInfoId).attr("disabled","true");
			   		  	alert("缴费成功！");
	            },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("保存失败;"+XmlHttpRequest.responseText);
			   }
			   });
}

function p_del() {
	var msg = "确定要删除吗？\n\n请确认！";
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
		   alert("箱子名称不超过25个字符！");
		   f.boxName.focus();
		   return false;
	   }
	   if(null == f.boxName.value ||f.boxName.value.length==0){	       
		   alert("箱子名称不能为空！");
		   f.boxName.focus();
		   return false;
	   }
	}
	
function deleteBookNoPayRec(){
		if(confirm("确认删除？")){
			$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=deleteBookNoPayRec",
			   dataType: 'json',
			   success: function (data, textStatus) {
			   		if(true == data.result || "true" == data.result){
			   			alert("删除成功！");
			   		}else{
			   			alert("记录为空，无法删除！");
			   		}
			   		window.location.reload();  
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("保存失败;"+XmlHttpRequest.responseText);
			   }
	 		 });
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
        <td width="434" valign="top" class="bgi_mian_tit">箱子收费管理</td>
      </tr>
    </table></td>
  </tr>
</table>


<form name="reg_form" action="payBoxManage.action" method="post">
	<table width="96%" class="box_search">
		<tr>
			<td width="20%" height="30" class="bgi_mian_search">按学号进行搜索：</td>
			<td width="10%">
				<input type="text" id="studNo" name="studNo" value="" />
			</td>
			<td width="25%">&nbsp;<a href="#"onClick="JavaScript:document.reg_form.submit();"><img src="image/serach_button.gif" width="63" height="19" border="0"></a></td>
			<td width="15%">&nbsp;</td>
			<td width="30%" align="left" style="color:#bbb06e"><input type="button" class="text_07" value="清除预约但是没有缴费的记录" onclick="deleteBookNoPayRec();"></td>
		</tr>
	</table>
</form>

<display:table name="allBookInfoes" pagesize="10" id="row"
   requestURI="payBoxManage.action" class="list" decorator="edu.hljcu.displaytag.BookWrapper">
   <display:column title="序号"><%=pageContext.getAttribute("row_rowNum")%></display:column>
   <display:column property="studNo" title="学生号"/>
   <display:column property="studName" title="学生姓名"/>
   <display:column property="college" title="学院"/>
   <display:column property="studClass" title="班级"/>
   <display:column property="phone" title="电话号码"/>
   <display:column property="boxPlace" title="箱子位置"/>
   <display:column property="bookTime" title="预约时间"/>
   <display:column property="payTime" title="付费时间"/>
   <display:column property="editLink" title="操作"/>
</display:table>
</body>
</html>