<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%String path = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>���ӹ���ҳ</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="js/jquery-jtemplates.js"></script>
<SCRIPT LANGUAGE=javascript>

function p_del() {
var msg = "ȷ��Ҫɾ����\n\n��ȷ�ϣ�";
if (confirm(msg)==true){
return true;
}else{
return false;
}
}

</SCRIPT>
	<s:if test="${!empty requestScope.message}">
	<script type="text/javascript">
	alert("${requestScope.message}");
	</script>
	</s:if>
</head>
  
  <body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="<%=path %>/image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">���ӹ���</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" height="10" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="661"><img src="<%=path %>/image/dot_01.gif" width="1" height="1"></td>
        </tr>
</table>


<table width="96%" height="30" border="0" cellpadding="0" cellspacing="0">
<s:hidden name="rowCateId" value="${rowCateId}"/>
    <tr>
    <td align="right">
      <input type="text" size="7" value="������" id="box_size" />
      <input type="button" name="button22" value="��������" onclick="createBox('${rowCateId}');" />
      <input name="button22" type="button" value="��������" onClick="window.location.href='<%=path %>/addBox.jsp?rowCateId=${rowCateId}'"/>
    </td>
  </tr>
</table>


<display:table name="allBoxes" pagesize="10" id="row"
   requestURI="showBoxManage.action" class="list" decorator="edu.hljcu.displaytag.BoxWrapper">
   <display:column title="���" ><%=pageContext.getAttribute("row_rowNum")%></display:column>
   <display:column property="boxName" title="���Ӻ�" />
   <display:column property="editLink" title="����"/>
</display:table>

<table width="96%" height="30" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="center">
      <input type="button" name="button22" value="����¥�����ҳ��" onclick="window.location.href='<%=path %>/showFloorCateManage.action'" />
      <input name="button22" type="button" value="�����źŷ���ҳ��" onClick="window.location.href='<%=path %>/showRowCateManage.action?rowCateId=${rowCateId}'"/>
    </td>
  </tr>
</table>
</body>
</html>

<script>
//TODO ��֤���ֺ͵��ȡ�����źš�
	$("#box_size").click( function () { if($("#box_size").val() == '������')  $("#box_size").val('');} );
	function testisNum()
    {
    	var s = $("#box_size").val();
         if(s!="")
         {
            if(isNaN(s))
             {
              	alert("ֻ����������������ȷ�ϣ�");
				return false;
             }else{
             	if(s > 100){
             		alert("����ҪС��100�����������룡");
					return false;
             	}
             }
         }else{
         	alert("����Ϊ�գ���������������");
			return false;
         }
         return true;
     }	
		
	function createBox(rowCateId){
		if(!testisNum()){
				$("#row_size").focus();
				return false;
		};
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=createBox&rowCateId=" + rowCateId +
			   			"&boxSize=" + document.getElementById("box_size").value,
			   dataType: 'json',
			   success: function (data, textStatus) {
			   			alert(data.result);
			   			window.location.reload();
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
		 });
	}

</script>
