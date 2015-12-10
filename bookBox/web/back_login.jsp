<%@ page language="java"  contentType="text/html;charset=gb2312"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>箱子预定系统后台管理</title>
<style type="text/css">
<!--
.title{
      color:#669900; font-family: "华文行楷";
	  font-size: 36px; font-weight:700 ;
}
.STYLE1 {
	font-size: 14px;
	color: #FFFFFF;
}
body {
	margin-top:0;
	margin-bottom:0;
	background-color: #fff;
}
input {
	font-family: "宋体";
	font-size: 14px;
	border: 1px solid #FFFFFF;
}
a:link {
	color: #FFFFFF;
}
a:visited {
	color: #FFFFFF;
}
a:hover {
	color: #FFFFFF;
}
a:active {
	color: #FFFFFF;
}
table td span{
    font-size:13px;
    color:#000;
}
.style_1{
    font-size:13px;
    height:24px;
    width:42px;
    line-height:24px;
}
-->
</style>
</head>

<body>

<table border="0" align="center" cellpadding="0" cellspacing="0" height="100%">
  <tr>
    <td rowspan="4">
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="300" height="182" align="center" valign="middle" background="image/backgroud.gif">
		<form action="loginBack.action"  method="post" name="form">
      <table align="center">  
        <tr>
            <td  align="right" ><span>用户名:</span></td> 
            <td  ><input name="adminName" type="text" length="15" /></td>
            <td  ></td>
        </tr>
	    <tr>
	        <td align="right"><span>密码:</span></td>
	        <td><input name="adminPsw" type="password" length="15"/></td>
	        <td></td>
	    </tr>
	    	    <tr>
	        <td> </td>
	        </tr>
	    <tr>
	        <td> </td>
	        <td colspan="2" align="center"><input name="submit" type="Submit" value="登录" class="style_1"/>&nbsp;&nbsp;&nbsp;
	        <input name="reset" type="reset" value="重置" class="style_1"/></td>
	    </tr> 
        <tr>
	        <td colspan="3" align="center"><font color="#FF0000"  frot-size="12px" align="left"><s:actionmessage/></font></td>
	        
        </tr> 	      
       
       </table>	
       </form>
		</td>
      </tr>
    </table>
	
	</td>
  </tr>
</table>


</body>

</html>
