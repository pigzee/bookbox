<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>ѧ����Ϣ</title>
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
	        <td width="434" valign="top" class="bgi_mian_tit">ѧ����Ϣ</td>
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
    <legend>������Ϣ</legend>
    <table>
	    <tr>
		    <td align="center"><font color="#FF0000">*</font>&nbsp;
				������
				<input type="text" id="studName" name="studName" value="" />
				��<font color="#FF0000">*</font>&nbsp;Ϊ�����
		    </td>
	     </tr>
	     <tr>
		    <td align="center"><font color="#FF0000">*</font>&nbsp;
				ѧ�ţ�
				<input type="text" id="studNo" name="studNo" value="" />
				��<font color="#FF0000">*</font>&nbsp;Ϊ�����
		    </td>
	     </tr>
		 
	    <tr>
		    <td align="center">
				<input type="button" value="�ύ" name ="btn" onclick="submit();" />
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
			alert('ѧ�Ų���Ϊ��');
			$('#studName').focus();
			return false;
		}
		if(studName.length == 0){
			alert('ѧ����������Ϊ��');
			$('#studNo').focus();
			return false;
		}
		if(studNo.length > 20){
			alert('ѧ��̫�������20���ַ�');
			$('#studNo').focus();
			return false;
		}
		if(studName.length > 20){
			alert('����̫�������20���ַ�');
			$('#studName').focus();
			return false;
		}
		
		var str = '����Ϊ' +��studName��+ '\n' + 'ѧ��Ϊ' +  studNo + '\nȷ���ύ��';
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
						   	alert("����ʧ��;"+XmlHttpRequest.responseText);
				   }
			 });
		 }else{
		 	 return false;
		 }
		
	}
</script>