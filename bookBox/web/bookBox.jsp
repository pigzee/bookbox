<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ԤԼ����</title>
    <link href="css/right.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="js/jquery-jtemplates.js"></script>
    <style type="text/css">
       body{
          font-size:13px;
          align:center;
       }
       .style_2{
         float:left;
         clear:both;
         width:100%;
         margin:2px 0 0 0;
         padding:5px;
       }
    </style>
  </head>
  
  <body onload="init();">
  		<div style="font-weight:bold;padding:10px;"><font >����ԤԼҳ��</font></div>
  		<table style="width:70%;align:center;align-text:left;">
  			<tr>
	  			<td style="width:40%;align:left">
		  			<fieldset id="bbfs">
		  			<legend>�ɷѵ�����</legend>
			  			<table>
			  				<tr>
			  					<td>
									<b><font color="red">���ֻ��ѡ��${adminOper.maxBoxCount}������</font></b><br />
									<font id="payedBox">û�нɷѼ�¼</font>
			  					</td>
			  				</tr>
			  			</table>
		  			</fieldset>
	  			</td>
	  			<td style="width:60%;align:right"> 
		  			<fieldset id="pbfs">
			  			<legend>ԤԼ������</legend>
			  			<table>
			  				<tr>
			  					<td>
			  						<b><font color="red">ԤԼʱ���ֹ��${adminOper.bookEndTime}</font></b><br />
									<font id="bookedBox">û��ԤԼ��¼</font>
			  					</td>
			  				</tr>
			  			</table>
		  			</fieldset>
	  			</td>
  			</tr>
	  		
	  			
  		</table>
  		
  		<fieldset style="width:70%;align:center;align-text:left;">
  			<legend>����ԤԼ</legend>
  			
			   <textarea id="cate_temlate" style="display:none;">ѡ��¥����ࣺ
			    	<select id="category_select"  onChange="changeFloor()">
					 {#foreach $T.cates as record}
					 <option value="{$T.record[0]}">{$T.record[1]}</option>
					 {#/for}
					</select>
			    </textarea>
			    
			    <textarea id="box_template" style="display:none;align:left">
					<table>
						{#foreach $T.rows as record}
						<tr>
								<td style="font-size:13px;">{$T.record.rowName}</td>
								{#foreach $T.record.boxes as boxes}
								<td><input name="btn" class="{$T.boxes.status}"  id="{$T.boxes.boxId}" type="button" style="height:30px;" 
											value={$T.boxes.boxName} style="width:30px" title={$T.boxes.boxName}  onclick="selectBox('{$T.boxes.rowName}','{$T.boxes.boxName}','{$T.boxes.boxId}');"/></td>
								{#/for}
						</tr>
						{#/for}
					</table>
				</textarea>
			
				<div >
			       <div id="category_div" style="align:left;text-align:left;padding-left:25px;"></div>
			       <div id="box_list" style="text-align:left;padding-left:25px;"></div>
			    </div>
  			
  		</fieldset>
    
  </body>
</html>
<script>

	var flag = true;//�û�Ԥ��״̬
	var  boxId = "";
	function init(){
		<%
		if(session.getAttribute("studNo") == null || "".equals(session.getAttribute("studNo")) || 
				session.getAttribute("studName") == null || "".equals(session.getAttribute("studName"))){
		%>
			window.location.href="login.jsp";
		<%}%>
		
		$.ajax({
			   type: "POST",
			   url: "bookBoxAction.jsp",
			   data: "method=init",
			   dataType: 'json',
			   success: function (data, textStatus) {	
			  			 $("#bookedBox").empty();
			  			 $("#payedBox").empty();
			   			if(undefined != typeof(data.boxes) && "undefined" != typeof(data.boxes)){
			   				var b = "";//ԤԼ��
			   				var p = "";//����ѵ�
			   				var m = n = 0;
			   				for(var i=0;i<data.boxes.length;i++){  
			   					var box = data.boxes[i];
			   					//ԤԼ�ķ�һ��
			   					if(box.status == 1){
			   						m++;
			   						b += "<input type='button'  border=1px solid #666666 style='width:250' title='ȡ�� "+ box.boxPlace +
			   								"' value='ȡ�� "+ box.boxPlace +"' onclick='cancelBox("+box.bookInfoId+","+box.boxId+",\""+ box.boxPlace +"\");'>";
			   						if(m%2 == 0){
			   							b += "<br />"
			   						}
			   					}else if(box.status == 2){
			   						n++;
			   						p += "<input type='button'  style='width:200' border=1px solid #666666 value='" + box.boxPlace + "' disabled />";
			   						if(n%2 == 0){
			   							p += "<br />"
			   						}
			   					}
			   				}
			   				if(b==""){
			   					 b = "��ʱ��û��ԤԼ";
			   				}
			   				if(p==""){
			   					 p = "��ʱ��û�нɷ�";
			   				}
			   				$("#bookedBox").append(b);
			   				$("#payedBox").append(p);
			   				
			   				var hs = $("#bbfs").height() > $("#pbfs").height() ? $("#bbfs").height() : $("#pbfs").height();
							$("#bbfs").height(hs);
							$("#pbfs").height(hs);
			   			}
			        
			   /*
			   			if(undefined != typeof(data.bookStatus)){
			   			
			   				
			   			
			   				var boxPlace = data.boxPlace;
			   				boxId = data.boxId;
			   				if("1" == data.bookStatus){
			   					$("#boxPlace").empty();
			   					$("#boxPlace").append("����ԤԼʱ���ֹ��<font color='red'>"+data.endTime+"</font>���鿴ѧУ��վ�����ڽɷ��ڼ�ȥ���ѣ�����ϵͳȡ������ԤԼ��"+
			   											"</br></br>��Ԥ����������<font color='red'>" +��boxPlace + "</font>");
			   					$("#cancelBox").show();
			   				}else if("2" == data.bookStatus){
			   					$("#boxPlace").empty();
			   					$("#boxPlace").append("����ԤԼʱ���ֹ��<font color='red'>"+data.endTime+"</font>�鿴ѧУ��վ�����ڽɷ��ڼ�ȥ���ѣ�����ϵͳȡ������ԤԼ��"+
			   											"</br></br>���ɷѵ�������<font color='red'>" +��boxPlace +" ,���Ѿ����ѣ������޸ģ�</font>");
			   					$("#cancelBox").hide();
			   				}else{
			   					$("#boxPlace").empty();
			   					$("#boxPlace").append("����ԤԼʱ���ֹ��<font color='red'>"+data.endTime+"</font></br></br>");
			   					$("#cancelBox").hide();
			   				}
			   			}else{
			   				$("#boxPlace").empty();
			   				$("#cancelBox").hide();
			   			}
			   			*/
						// ����ģ��
						$("#category_div").setTemplateElement("cate_temlate");
						// ��ģ���������
						$("#category_div").processTemplate(data);
					//	flag = data.boxStatus;
						var selectObj = document.getElementById("category_select");
						listBox(selectObj.value);
						
						
				
				
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
		 });
	
	}
	
	
	function changeFloor(){
		//	alert(obj.options[obj.selectedIndex].innerText +  "--" + obj.options[obj.selectedIndex].value);
			var selectObj = document.getElementById("category_select");
			listBox(selectObj.value)
		//	alert( cate.options[cate.selectedIndex].text)
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
			   data: "method=listBox&floorId=" + floorId,
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
						
						//����ѡ�е�����Ϊ��ɫ
						if(flag){
						//	$("input[name='btn']").attr("disabled", true);
							if(undefined != typeof(data.boxIds) && "undefined" != typeof(data.boxIds)){
								for(var i=0;i<data.boxIds.length;i++){  
									var boxId = data.boxIds[i];
									if(null != document.getElementById(boxId.boxId)){
										document.getElementById(boxId.boxId).style.backgroundColor="red";
									}
								}
							}	
							$("input[class='1']").attr("disabled", true);
							$("input[class='2']").attr("disabled", true);
						}	
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("����ʧ��;"+XmlHttpRequest.responseText);
			   }
			});
	
	}
	
	function selectBox(rowName,boxName,boxId){
		var selectObj = document.getElementById("category_select");
		var result = window.confirm("ȷ��ѡ��"+ selectObj.options[selectObj.selectedIndex].text+"��" + rowName+ "����" + boxName +��"�����ӣ�");
		if(result){
			//�����ĵط���1���޸�����״̬��2������ѧ��������
			$.ajax({
			   type: "post",
			   url: "bookBoxAction.jsp",
			   data: "method=bookBox&boxId="+ boxId ,
			   dataType: 'json',
			   success: function (data, textStatus) {
			   			if(data.result == "success"){
						//	document.getElementById(boxId).style.backgroundColor="red";
						//	$("input[name='btn']").attr("disabled", true);
						//	flag=true;
							alert("ԤԼ�ɹ���");
							init();
			   			}else {
							alert(data.result);
			   				init();
			   				return false;
						}
	           },
	           error:function(XmlHttpRequest,textStatus, errorThrown){
					   	alert("session���ڣ�ԤԼʧ�ܣ������µ�¼!");
			   }
			   });
			
		}
	}
	
	function cancelBox(bookInfoId,boxId,boxPlace){
		if(confirm("ȷ��ȡ�� "+ boxPlace +"?")){
			//��ԭ����boxIdΪ��
			//����status״̬Ϊ���bookTimeΪ""
			$.ajax({
				   type: "post",
				   url: "bookBoxAction.jsp",
				   data: "method=cancelBookBox&boxId="+ boxId + "&bookInfoId=" + bookInfoId ,
				   dataType: 'json',
				   success: function (data, textStatus) {
				   			if(data.result == "true" || true == data.result){
								alert("ȡ���ɹ���");
								init();
				   			}else {
								alert(data.result);
				   				init();
				   				return false;
							}
		           },
		           error:function(XmlHttpRequest,textStatus, errorThrown){
						   	alert("����ʧ��;"+XmlHttpRequest.responseText);
				   }
			 });
		}	
		
	}
</script>