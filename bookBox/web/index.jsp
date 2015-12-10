<%@ page language="java"  contentType="text/html;charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>箱子收费管理</title>
<link href="css/top.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">
//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
</script>
<SCRIPT>
function BarMove(){
 if (AtMovePic2.style.display==""){
  document.all("AtMovePic2").style.display="none"
  document.all("AtMovePic").style.display=""
  document.all("frmTitle").style.display="none"
 }
 else{
  document.all("AtMovePic2").style.display=""
  document.all("AtMovePic").style.display="none"
  document.all("frmTitle").style.display=""
 }
}
</SCRIPT>


<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}
</style>
</head>

<body STYLE="margin:0px;">

<table width="100%" border="0" cellpadding="0" cellspacing="0" height="5%">  
   <tr>    
    <td  bgcolor="#B0CCDE" height="25" background="page/back/image/bar-100.gif" align="right" style="color:#993300" ><span style="font-size:12px">用户名：</span><%=session.getAttribute("adminName") %> &nbsp;  <A href="logoutBack.action"  target="_top"   style="color:#993300; font-size: 12px">退出</A>&nbsp;&nbsp;	</td>
  </tr>  
</table>
  
<table border=0 cellPadding=0 cellSpacing=0 height="95%" width="100%">
   <tr height="100%">
      <td id=frmTitle width="200" >
           <iframe scrolling="No" frameborder="0" id="leftFrame" name="leftFrame" src="menuAuthority.action" style="HEIGHT: 100%; WIDTH: 100%; "></iframe>
      </td>

      <td width="10">
          <table width="100%" height="100%" cellPadding=0 cellSpacing=0 bgcolor="#8ecoe6">
            <tr>
                <td vAlign="middle" id=AtMovePic style="DISPLAY: none; HEIGHT: 100%; " onclick=BarMove()>
                     <SPAN style="CURSOR: hand"><IMG src="image/pic24.gif" alt="打开左栏" ></SPAN></td>
                <td vAlign="middle" id=AtMovePic2 style="HEIGHT: 100%; " onclick=BarMove()>
                     <SPAN style="CURSOR: hand"><IMG src="image/pic23.gif" alt="关闭左栏" ></SPAN></td>
            </tr>
          </table>
      </td>
 	  <td >
        <iframe frameBorder="0" name="mainFrame" id="mainFrame" scrolling="yes" src="showAdmin.action?status=admin" style="HEIGHT: 100%; WIDTH: 100%; Z-INDEX: 1"></iframe>
      </td>
  </tr>
</table>

</body>
</html>

