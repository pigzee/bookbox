<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>学生信息</title>
<%String path = request.getContextPath();%>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>
	<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>

</head>
  
<body>
	<table width="96%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="68" height="44"><img src="<%=path %>/image/tit_bgi01.gif" width="68" height="44"></td>
	        <td width="434" valign="top" class="bgi_mian_tit">学生信息</td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
	
	<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width="661"></td>
	  </tr>
	</table>
  <fieldset style="width:70%">
    <legend>个人信息</legend>
    <table>
	    <tr>
		    <td align="center"><font color="#FF0000">*</font>&nbsp;
				姓名：
				<input type="text" id="studName" name="studName" value="" />
				（<font color="#FF0000">*</font>&nbsp;为必填项）
		    </td>
	     </tr>
	     <tr>
		    <td align="center"><font color="#FF0000">*</font>&nbsp;
				学号：
				<input type="text" id="studNo" name="studNo" value="" />
				（<font color="#FF0000">*</font>&nbsp;为必填项）
		    </td>
	     </tr>
		 
	    <tr>
		    <td align="center">
				<input type="button" value="提交" name ="btn" onclick="submit();" />
		    </td>
	    </tr>
	    
    </table>
  </fieldset>
</body>
</html>


<script language="JavaScript">
	function submit(){     
   	 	var studNo = $.trim($('#studNo').val());
		var studName = $.trim($('#studName').val());
		if(studNo.length == 0){
			alert('学号不能为空');
			$('#studName').focus();
			return false;
		}
		if(studName.length == 0){
			alert('学生姓名不能为空');
			$('#studNo').focus();
			return false;
		}
		if(studNo.length > 20){
			alert('学号太长，最多20个字符');
			$('#studNo').focus();
			return false;
		}
		if(studName.length > 20){
			alert('姓名太长，最多20个字符');
			$('#studName').focus();
			return false;
		}
		
		var str = '姓名为' +　studName　+ '\n' + '学号为' +  studNo + '\n确认提交？';
		 if(confirm(str))	{
		 	$.ajax({
				   type: "get",
				   url: "bookBoxAction.jsp",
				   data: "method=addStudRecord&studName=" + studName + '&studNo=' + studNo,
				   dataType: 'json',
				   success: function (data, textStatus) {
				   			alert(data.result);
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