<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<html>

<head>
<title>�趨���Ӽ۸�</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/JsUtil.js"></script>
</head>

<body>
<table width="96%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
        <td width="434" valign="top" class="bgi_mian_tit">�趨���Ӽ۸�</td>        
      </tr>      
    </table></td>
  </tr>
</table>

<table width="96%" height="10" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
        <font color="#FF0000" font-size="12px" align="left"><s:actionmessage/></font>
    </td>
  </tr>
</table>

<form action="editPrice.action"  method="post" name="form" onsubmit="return check();">
<table width="90%" border="0" cellpadding="" cellspacing="0" >
<tr>
<td>
<table width="100%" border="0" cellpadding="5" cellspacing="1" class="bgc_gray">
  <tr>
    <td width="5%" align="right" class="bgc_gray02"><font color="#FF0000">*</font>&nbsp; ��Ǯ��</td>
    <td width="68%" align="left" class="bgc_white"><input name="boxPrice" type="text" class="text_06" value="<s:property value='${adminOper.boxPrice}'/>"/>(Ԫ)
      &nbsp;����0,���Ҳ�������λС��
  </tr>
</table>
</td></tr>
</table>

<table width="96%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input type="submit" name="Submit" value="�޸�">&nbsp;&nbsp;
	 <input name="cancel" type="button" value="����" onClick="history.back(-1)"/></td>
  </tr>
</table>
</form>
</body>
	<!--������֤��������棬����Ϊcheck  -->
	<!--��ȱ�ٶ�ÿ�����Եĳ��ȵ��ж� -->
	<script language="javascript">
      function check()
      {
      
         var fm=document.form	
	     if(isNaN(fm.boxPrice.value) || fm.boxPrice.value < 0 
	     	|| fm.boxPrice.value == "" || isTriDecimal(fm.boxPrice.value))
	     {
	       alert("�۸��������")
	       fm.boxPrice.focus()
		   return false	     
	     }   
	     if(fm.boxPrice.value > 1000){
	       alert("�۸�̫�ߣ�")
	       fm.boxPrice.focus()
		   return false	
		 }
	     return true
	  }
	  
	  function isTriDecimal(value){    
                if(value!=null&&value!=''){    
                    var decimalIndex=value.indexOf('.');    
                    if(decimalIndex=='-1'){    
                        return false;    
                    }else{    
                        var decimalPart=value.substring(decimalIndex+1,value.length);    
                        if(decimalPart.length>2){    
                            return true;    
                        }else{    
                            return false;    
                        }    
                    }    
                }    
                return false;    
            }  
</script>
</html>
