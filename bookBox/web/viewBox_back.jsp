<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%String path = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>�鿴������״</title>
	<link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="js/jquery-jtemplates.js"></script>
    <style type="text/css">
       body{
          font-size:13px;
       }
       .style_3{
       margin:0 auto;
       }
       .style_2{
         float:left;
         clear:both;
         margin:2px 0 0 0;
         padding:5px;
       }
    </style>
</head>
  
	<body onload="init();">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td><table width="502" height="44" border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td width="68" height="44"><img src="image/tit_bgi01.gif" width="68" height="44"></td>
		        <td width="434" valign="top" class="bgi_mian_tit">�鿴������״</td>
		      </tr>
		    </table></td>
		  </tr>
		</table>
		
		<table width="96%" height="20" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td width="661"></td>
		  </tr>
		</table>
		
		<fieldset style="width:80%">
			<legend>����չʾ</legend>
				
			<textarea id="cate_temlate" style="display:none;align:left;" >ѡ��¥����ࣺ
		    	<select id="category_select"  onChange="changeFloor()">
					 {#foreach $T.cates as record}
					 <option value="{$T.record[0]}">{$T.record[1]}</option>
					 {#/for}
				</select>
		    </textarea>
		    
		    <textarea id="box_template" style="display:none">
				<table>
					{#foreach $T.rows as record}
					<tr>
							<td>{$T.record.rowName}</td>
							{#foreach $T.record.boxes as boxes}
							<td><input name="btn" class="{$T.boxes.status}"  id="{$T.boxes.boxId}" type="button" style="height:30px;" 
										value={$T.boxes.boxName} style="width:30px" title={$T.boxes.boxName} /></td>
							{#/for}
					</tr>
					{#/for}
				</table>
			</textarea>
				<div>
			       <div id="category_div" style="text-align:left;padding-left:35px;" ></div>	
			       <div id="box_list" style="padding:7px;" ></div>
			       
			    </div>		    
		</fieldset>
		
		<fieldset style="width:80%">
			<legend>����ͳ��</legend>
			<div id="cateBoxInfo"></div>
			<div id="allBoxInfo"></div>
		</fieldset>
	    
  </body>
</html>

<script>
	function init(){
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=init",
			   dataType: 'json',
			   success: function (data, textStatus) {	
						// ����ģ��
						$("#category_div").setTemplateElement("cate_temlate");
						// ��ģ���������
						$("#category_div").processTemplate(data);
						var selectObj = document.getElementById("category_select");
						listBox(selectObj.value);
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
			   });
	
	}
	
		function listBox(floorId){
			if(null == floorId || "" == floorId){
				document.getElementById('box_list')
	 				.innerHTML == "<div  style= 'border-top-width: 1px;border-top-style: dashed;border-top-color: #bbbbbb;padding-top:5px' >&nbsp;&nbsp;&nbsp;û��ƥ�������</div>"
				return false;
			}
			
			$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=viewBox&floorId=" + floorId,
			   dataType: 'json',
			   success: function (data, textStatus) {
			   			if('undefined' == typeof(data.rows)){
			   				$("#box_list").empty();
			   				var s="�˷��໹û������";
			   				$("#box_list").append(s);
			   			}else{
			   				// ����ģ��
							$("#box_list").setTemplateElement("box_template");
							// ��ģ���������
							$("#box_list").processTemplate(data);
			   			}
						$("#cateBoxInfo").	empty();
						var s = "";
						s += "�����๲������" + data.cateBoxCount + "," + 
							  "�ѱ�ԤԼ������" +��data.cateHasBookedCount + "," +
							  "�Ѹ��ѵ�����" + data.cateHasPayedCount +"," +
							  "ʣ�������" + data.cateLastBoxCount ;
						document.getElementById("cateBoxInfo").innerHTML= s; 
						
						$("#allBoxInfo").empty();
						s = "";
						s += "ͼ��ݹ�������" + data.allBoxCountCount + "," + 
							  "�ѱ�ԤԼ������" +��data.allHasBookedCount + "," +
							  "�Ѹ��ѵ�����" + data.allHasPayedCount +"," +
							  "ʣ�������" + data.allLastBoxCount ;
						document.getElementById("allBoxInfo").innerHTML= s; 	 
						$("input[class='1']").attr("disabled", true);
						$("input[class='2']").attr("disabled", true);
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
			});
	}
	
	function changeFloor(){
			var selectObj = document.getElementById("category_select");
			listBox(selectObj.value)
	}

</script>
