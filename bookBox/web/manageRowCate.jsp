<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%String path = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>排数分类管理</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="js/jquery-jtemplates.js"></script>
<SCRIPT LANGUAGE=javascript>

function p_del() {
var msg = "确定要删除吗？\n\n请确认！";
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
        <td width="434" valign="top" class="bgi_mian_tit">排数分类管理</td>
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
<s:hidden name="floorCateId" value="${floorCateId}"/>
    <tr>
    <td align="right">
      <input type="text" size="7" value="排号数" id="row_size" />
      <input type="button" name="button22" value="生成排号" onclick="createRow('${floorCateId}');" />
      <input name="button22" type="button" value="增加排数" onClick="window.location.href='<%=path %>/addRowCate.jsp?floorCateId=${floorCateId}'"/></td>
  </tr>
</table>

<display:table name="allRowCates" pagesize="10" id="row"
   requestURI="showRowCateManage.action" class="list" decorator="edu.hljcu.displaytag.RowWrapper">
   <display:column title="序号"><%=pageContext.getAttribute("row_rowNum")%></display:column>
   <display:column property="cateName" title="排号"/>
   <display:column property="editLink" title="操作"/>
</display:table>

<table width="96%" height="30" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="center">
      <input type="button" name="button22" value="返回楼层分类页面" onclick="window.location.href='<%=path %>/showFloorCateManage.action'" />
    </td>
  </tr>
</table>

</body>
</html>

<script>
	$("#row_size").click( function () { if($("#row_size").val() == '排号数')  $("#row_size").val('');} );
	function testisNum()
    {
    	var s = $("#row_size").val();
         if(s!="")
         {
            if(isNaN(s))
             {
              	alert("只能输入正整数，请确认！");
				return false;
             }else{
             	if(s > 100){
             		alert("数字要小于100，请重新输入！");
					return false;
             	}
             }
         }else{
         	alert("不能为空，请输入正整数！");
			return false;
         }
           return true;
     }
	function createRow(floorCateId){
		if(!testisNum()){
			$("#row_size").focus();
			return false;
		};
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=createRow&floorCateId=" + floorCateId +
			   			"&rowSize=" + document.getElementById("row_size").value,
			   dataType: 'json',
			   success: function (data, textStatus) {
			   			alert(data.result);
			   			window.location.reload();
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("保存失败;"+XmlHttpRequest.responseText);
			   }
		 });
	}

</script>
