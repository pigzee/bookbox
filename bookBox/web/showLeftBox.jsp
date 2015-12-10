<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page buffer = "16kb" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%String path = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>查看剩余箱子</title>
<link href="css/right.css" rel="stylesheet" type="text/css" />

</head>
  
  <body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">查看剩余箱子</td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="661"></td>
  </tr>
</table>


<form name="reg_form" action="showLeftBox.action" method="post">
	<table width="90%" border="0" cellpadding="" cellspacing="0">
		<tr>
			<td>
			 楼层分类：<s:select list="allFloorCates" id="floorCates" name="floorCateId" listValue="cateName" listKey="floorCateId" theme="simple"></s:select>
			 <input type="submit" name="submit" value="查看"/>
			</td>
		</tr>
	</table>
</form>

<display:table name="bookInfos" pagesize="10" id="row" export="true"
 	  requestURI="showLeftBox.action" class="list" >
   <display:column title="序号"><%=pageContext.getAttribute("row_rowNum")%></display:column>
   <display:column property="boxPlace" title="箱子位置"/>
</display:table>



</body>
</html>