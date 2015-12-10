<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="edu.hljcu.service.IBookBoxSrv"%>
<%@page import="edu.hljcu.common.StaticUtil"%>
<%@page import="edu.hljcu.hibernate.all.AdminOper"%>
<%@page import="edu.hljcu.hibernate.all.FloorCate"%>
<%@page import="edu.hljcu.hibernate.all.RowCate"%>
<%@page import="edu.hljcu.hibernate.all.Box"%>
<%@page import="edu.hljcu.hibernate.all.BookInfo"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="net.sf.json.JSONObject" %>
<%@page import="java.util.regex.Matcher" %>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.sql.ResultSet"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>BookBoxAction.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<%
  		
  	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("utf-8");

	String method = request.getParameter("method");
	WebApplicationContext wac = WebApplicationContextUtils
	.getRequiredWebApplicationContext(session
	.getServletContext());
	IBookBoxSrv bookBoxSrv = (IBookBoxSrv) wac.getBean("BookBoxSrvProxy");
	String studNo = (String)session.getAttribute("studNo");
	String studName = (String)session.getAttribute("studName");

	//��ʼ��ѡ�����ӽ���
	if(method.equals("init")){
		List<FloorCate> allCates = bookBoxSrv.getAllFloorCate();
		List cateList = new ArrayList();
		for(FloorCate cate : allCates){
			String[] cates = new String[2];
			cates[0] = cate.getFloorCateId()+"";
			cates[1] = cate.getCateName();
			cateList.add(cates);
		}
		JSONObject jo = new JSONObject();
		jo.put("cates",cateList);
		//TODO �ж��Ƿ񳬹����޶�ֵ���Ժ��
		//ȥ�����ܵ�����ӵ�����
	//	jo.put("boxStatus",bookBoxSrv.hasBookOrPayBox(studNo));
		//String timeStatus = "";
		AdminOper adminOper = bookBoxSrv.getAdmin();
		if(bookBoxSrv.judgeBookTime()){
		//	timeStatus = "bookTime";
			jo.put("endTime",adminOper.getBookEndTime().toString());
		}else if(bookBoxSrv.judgeConTime()){
		//	timeStatus = "conTime";
			jo.put("endTime",adminOper.getConEndTime().toString());
		}else{
		//	timeStatus = "fail";
			jo.put("endTime","");
		}
		//jo.put("timeStatus",timeStatus);
        List<BookInfo> bookInfos = bookBoxSrv.findInfoByStudNo(studNo);
		if(null != bookInfos && bookInfos.size() > 0){
			//ƴ��json
			StringBuffer sb = new StringBuffer("");
			sb.append("[");
			for(BookInfo bi : bookInfos){
				if(null != bi && !"".equals(bi.getStatus()) && null != bi.getBookTime() && !"NULL".equals(bi.getBookTime()) 
						&& !"null".equals(bi.getBookTime()) && !"".equals(bi.getBookTime()) 
						&& !StaticUtil.BOOKINFO_STATUS_HASCON.equals(bi.getStatus()) && null != bi.getBox()){
					//box���״̬��Ϊ0
					if(!StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(
							bookBoxSrv.findBoxById(bi.getBox().getBoxId()+"").getStatus())){
						sb.append("{\"boxId\":\"").append(bi.getBox().getBoxId()+"").append("\",")
						  .append("\"bookInfoId\":\"").append(bi.getBookInfoId()+"").append("\",")
						  .append("\"status\":\"").append(bi.getStatus()).append("\",")
						  .append("\"boxPlace\":\"").append(bi.getBoxPlace()).append("\"},");
					}
				}
			}
			if(sb.toString().endsWith(",")){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]");
			jo.put("boxes",sb.toString());
		}
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}
	
	else if(method.equals("listBox")){
		String  floorId = request.getParameter("floorId");
		List<RowCate> rowCates = bookBoxSrv.findRowByFloorId(floorId);

		StringBuffer sb = new StringBuffer("");
		if(null != rowCates && rowCates.size() > 0){ 

			sb.append("{\"rows\":[" );
			for(int i=0;i<rowCates.size();i++){
				List<Box> boxArr = bookBoxSrv.findBoxByRowId(rowCates.get(i).getRowCateId().toString());
				sb.append("{\"rowName\":\"" + rowCates.get(i).getCateName() + "\",");
				sb.append("\"boxes\":[");
				if(boxArr.size() > 0){
					String[] rows = new String[boxArr.size()];
					for(int k=0;k<boxArr.size();k++){
						rows[k] = "{\"rowName\":\"" + rowCates.get(i).getCateName()+"\",\"boxName\":\"" + boxArr.get(k).getBoxName() + "\",\"boxId\":\"" + boxArr.get(k).getBoxId().toString() + "\",\"status\":\"" + boxArr.get(k).getStatus() + "\"},";
						sb.append(rows[k]);
					}
					sb.deleteCharAt(sb.length()-1);
				}	
				sb.append("]},");
			}
			if(',' == sb.charAt(sb.length()-1)){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("],");	
		}else{
			sb.append("{");
		}
			
		//�趨��ѧ��ѡ�е�����״̬	
		List<BookInfo> bookInfos = bookBoxSrv.findInfoByStudNo(studNo);
		if(null != bookInfos && bookInfos.size() > 0){
			//ƴ��json
			sb.append("\"boxIds\":[");
			for(BookInfo bi : bookInfos){
				if(null != bi && !"".equals(bi.getStatus()) && (null != bi.getBookTime() && !"NULL".equals(bi.getBookTime()) 
						&& !"null".equals(bi.getBookTime()) && !"".equals(bi.getBookTime())) 
						&& (StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus()) || StaticUtil.BOOKINFO_STATUS_HASBOOK.equals(bi.getStatus()) )){
					if(!StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(
							bookBoxSrv.findBoxById(bi.getBox().getBoxId()+"").getStatus())){
						sb.append("{\"boxId\":\"").append(bi.getBox().getBoxId()).append("\"},");
					}
				}
			}
			if(sb.toString().endsWith(",")){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]");
		}else{
			sb.append("\"boxIdes\":[]");
		}
		sb.append("}");
		response.getWriter().write(sb.toString());
		response.getWriter().close();
	}else if(method.equals("bookBox")){
		String result = "success";
		String boxId = request.getParameter ("boxId");
		//�������״̬����0˵��������ѡ�У����������ѡ���񷵻�null
		Box box = bookBoxSrv.getBoxById(boxId);
        //lj 2015 / 12 / 4 15:01
        if (box != null) {
            if (StaticUtil.BOX_BOOK_STATUS_HASBOOK.equals(box.getStatus())
                    || StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(box.getStatus())) {
                result = "�����ѱ�����ѡ�У�������ѡ��";
            }

        }
		//BookInfo bookInfo = bookBoxSrv.getBookedBoxByNo(studNo);
		List<BookInfo> bookInfos = bookBoxSrv.findInfoByStudNo(studNo);
		JSONObject jo = new JSONObject();
		if(null != box){
			//������ѧ���Ƿ��Ѿ�Ԥ����
		    result = bookBoxSrv.setBookBox(boxId,bookInfos,studNo,studName);
			jo.put("result",result);
			response.getWriter().write(jo.toString());
			response.getWriter().close();
		}else{
			result = "ԤԼ�������⣡";
			jo.put("result",result);
			response.getWriter().write(jo.toString());
			response.getWriter().close();
		}
	}else if(method.equals("viewBox")){
		String  floorId = request.getParameter("floorId");
		List<RowCate> rowCates = bookBoxSrv.findRowByFloorId(floorId);
		StringBuffer sb = new StringBuffer("");
		if(null != rowCates && rowCates.size() > 0){ 

			sb.append("{\"rows\":[");
			for(int i=0;i<rowCates.size();i++){
				List<Box> boxArr = bookBoxSrv.findBoxByRowId(rowCates.get(i).getRowCateId().toString());
				sb.append("{\"rowName\":\"" + rowCates.get(i).getCateName() + "\",");
				sb.append("\"boxes\":[");
				if(boxArr.size() > 0){
					String[] rows = new String[boxArr.size()];
					for(int k=0;k<boxArr.size();k++){
						rows[k] = "{\"rowName\":\"" + rowCates.get(i).getCateName()+"\",\"boxName\":\"" + boxArr.get(k).getBoxName() + "\",\"boxId\":\"" + boxArr.get(k).getBoxId().toString() + "\",\"status\":\"" + boxArr.get(k).getStatus() + "\"},";
						sb.append(rows[k]);
					}
					sb.deleteCharAt(sb.length()-1);
				}	
				sb.append("]},");
			}
			if(',' == sb.charAt(sb.length()-1)){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("],");	
		}else{
			sb.append("{");
		}
			/*
		BookInfo bi = bookBoxSrv.findInfoByStudNo(studNo);
		if(null == bi || null == bi.getBox() || StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK.equals(bi.getStatus())){
			sb.append("\"status\":\"" + StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK+
						"\",\"boxId\":0," );
		}else{
			sb.append("\"status\":\"" + bi.getStatus() + 
						"\",\"boxId\":" + bi.getBox().getBoxId() + ",");
		}
		*/
		int[] boxCountArrByFloor = bookBoxSrv.getBoxCountArrByFloor(floorId);
		int[] boxCountArr = bookBoxSrv.getBoxCountArr();
		sb.append("\"cateBoxCount\":" + boxCountArrByFloor[0] + ",");
		sb.append("\"cateHasBookedCount\":" + boxCountArrByFloor[1] + ",");
		sb.append("\"cateHasPayedCount\":" + boxCountArrByFloor[2] + ",");
		sb.append("\"cateLastBoxCount\":" + boxCountArrByFloor[3] + ",");
		sb.append("\"allBoxCountCount\":" + boxCountArr[0] + ",");
		sb.append("\"allHasBookedCount\":" + boxCountArr[1] + ",");
		sb.append("\"allHasPayedCount\":" + boxCountArr[2] + ",");
		sb.append("\"allLastBoxCount\":" + boxCountArr[3] + "}");
		
		response.getWriter().write(sb.toString());
		response.getWriter().close();
	}else if(method.equals("pay")){
		String bookInfoId = request.getParameter("bookInfoId");
		String result = "success";
		BookInfo bookInfo = bookBoxSrv.getBookInfoById(bookInfoId);
		if(null != bookInfo){
			bookBoxSrv.editBookStatus(bookInfo,StaticUtil.BOOKINFO_STATUS_HASPAY);
		}else{
			result = "����ʧ�ܣ�";
		}
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("createRow")){
		String floorCateId = request.getParameter("floorCateId");
		String rowSize = request.getParameter("rowSize");
		String result = "success";
		if(!bookBoxSrv.autoAddRow(floorCateId, rowSize)){
			result = "�Զ�����ʧ�ܣ�";
		}
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("createBox")){
		String rowCateId = request.getParameter("rowCateId");
		String boxSize = request.getParameter("boxSize");
		String result = "success";
		if(!bookBoxSrv.autoAddBox(rowCateId,boxSize)){
			result = "�Զ�����ʧ�ܣ�";
		}
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("getSignTime")){
		String signTime = request.getParameter("signTime");
		float sum = 0;
		sum = bookBoxSrv.getSumPrice(bookBoxSrv.findBySignTime(signTime));
		JSONObject jo = new JSONObject();
		jo.put("sum",sum);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("getPartTime")){
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		float sum = 0;
		sum = bookBoxSrv.getSumPrice(bookBoxSrv.findByPartTime(beginTime,endTime));
		JSONObject jo = new JSONObject();
		jo.put("sum",sum);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("setBookTime")){
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		boolean result = bookBoxSrv.setBookTime(beginTime, endTime);
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("setConTime")){
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		boolean result = bookBoxSrv.setConTime(beginTime, endTime);
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("conBox")){
		String boxId = request.getParameter("boxId");
		String result = "��Լʧ��";
		String maxBoxCount = bookBoxSrv.getAdmin().getMaxBoxCount();
		if(null != maxBoxCount && !"".equals(maxBoxCount) && isNumeric(maxBoxCount)){
			if(bookBoxSrv.getConBookedAndPayedCount(studNo) > Integer.valueOf(maxBoxCount)){
				result = "�����Լ" + maxBoxCount + "������";
			}else{
				BookInfo bi = bookBoxSrv.findInfoByStudNoAndBoxId(studNo,boxId);
				if(null != bi){
					if( bookBoxSrv.editBookStatus(
							bi,StaticUtil.BOOKINFO_STATUS_HASCON)){
						result = "��Լ�ɹ�";
					}
				}
			}
		}else{
			int sum = bookBoxSrv.getConBookedAndPayedCount(studNo);
			if(sum > 0){
				result = "�����Լ1������";
			}else{
				BookInfo bi = bookBoxSrv.findInfoByStudNoAndBoxId(studNo,boxId);
				if(null != bi){
					if( bookBoxSrv.editBookStatus(
							bi,StaticUtil.BOOKINFO_STATUS_HASCON)){
						result = "��Լ�ɹ�";
					}
				}
			}
		}
		
		
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("cancelBookBox")){
		String boxId = request.getParameter("boxId");
		String bookInfoId = request.getParameter("bookInfoId");
		BookInfo bookInfo = bookBoxSrv.getBookInfoById(bookInfoId);
		if(null != bookInfo && StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bookInfo.getStatus())){
			JSONObject jo = new JSONObject();
			jo.put("result","�Ѿ��ɷѲ���ȡ����");
			response.getWriter().write(jo.toString());
			response.getWriter().close();
		}else if(null != bookInfo){
			boolean result = bookBoxSrv.cancelBookBox(boxId,bookInfo.getBookInfoId()+"");
			JSONObject jo = new JSONObject();
			jo.put("result",result);
			response.getWriter().write(jo.toString());
			response.getWriter().close();
		}else{
			JSONObject jo = new JSONObject();
			jo.put("result","���ڳ�ʱ��Ԥ�����ɷѣ�����Ա�Ѿ�ɾ�������ԤԼ��");
			response.getWriter().write(jo.toString());
			response.getWriter().close();
		}
	}else if(method.equals("deleteBookNoPayRec")){
		boolean result = bookBoxSrv.deleteBookNoPayRec();
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}else if(method.equals("clearBoxStatus")){
		boolean result = bookBoxSrv.clearBoxStatus();
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}
		/*
	else if(method.equals("editStudNo")){
		String oldStudNo = request.getParameter("oldStudNo");
		String newStudNo = request.getParameter("newStudNo");
		String result = "";
		int i = bookBoxSrv.editStudNo(oldStudNo.trim(), newStudNo.trim());
		if(1 == i){
			result = "�޸ĳɹ�";
		}else if(0 == i){
			result = "������ "+ oldStudNo + " ѧ��";
		}else if(-1 == i){
			result = "�����ѧ��Ϊ�գ�����������";
		}		
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}
	
	else if(method.equals("addStudRecord")){
		String name = new String(request.getParameter("studName").getBytes("ISO8859_1"), "GBK").trim();
		String no = new String(request.getParameter("studNo").getBytes("ISO8859_1"), "GBK").trim();
		String result = "";
		int i= bookBoxSrv.addStudRecord(name, no);
		if(i == 1){
			result = "��ӳɹ�";	
		}else if(i == 0){
			result = "���ڴ��û�";
		}else{
			result = "ѧ�Ż�������Ϊ����";
		}
		JSONObject jo = new JSONObject();
		jo.put("result",result);
		response.getWriter().write(jo.toString());
		response.getWriter().close();
	}
		*/
%>
<%!
	public boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
			return true;
	} 


%>
  </body>
</html>
