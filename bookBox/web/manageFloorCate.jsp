<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%String path = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>分类管理页面</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
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
        <td width="434" valign="top" class="bgi_mian_tit">楼层分类管理</td>
      </tr>
    </table></td>
  </tr>
</table>



<table width="96%" height="30" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="right">
      <input name="button22" type="button" value="增加楼层记录" onClick="window.location.href='<%=path %>/addFloorCate.jsp'"/></td>
  </tr>
</table>


<display:table name="allFloorCates" pagesize="10" id="row"
   requestURI="showFloorCateManage.action" class="list" decorator="edu.hljcu.displaytag.CateWrapper">
   <display:column title="序号"><%=pageContext.getAttribute("row_rowNum")%></display:column>
   <display:column property="cateName" title="楼层名称"/>
   <display:column property="editLink" title="操作"/>
</display:table>

</body>
</html>
