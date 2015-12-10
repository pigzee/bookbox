<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.*"%>
<!--************************************* 实例代码开始 *************************************-->
<SCRIPT type=text/javascript>
function switchMenu(curr_id, total_num)
{
	for(var i=0; i<total_num; i++)
	{
		//二级菜单
		var el = document.getElementById('menu_sub_'+i);

		if(el!=null)
		{
			if(!el) return;
	
			//一级菜单前面的图片
			var img = document.getElementById('menu_master_'+i);
	
			if(i == curr_id)
			{
				el.style.display = "block";
				img.src = "image/dot_02on.gif";
			}
			else
			{
				el.style.display = "none";
				img.src = "image/dot_02.gif";
			}
		}
	}
}
</SCRIPT>


<link href="css/left.css" rel="stylesheet" type="text/css">


<DIV id=warp>
	<DIV class=menu_master onClick="javascript:switchMenu(0, 4);">
		<IMG src="image/dot_02.gif" alt=close width="7" height="7"
			id=menu_master_0>
		账户管理
	</DIV>
	<DIV class=sub_menu id=menu_sub_0>
		<UL>
			<LI>
				<A title="" href="showAdmin.action?status=admin"
					target="mainFrame">编辑管理员信息</A>
			</LI>
			<LI>
				<A title="" href="addStudRecord.jsp"
					target="mainFrame">增加学生记录</A>
			</LI>
			<LI>
				<A title="" href="editStudNo.jsp"
					target="mainFrame">修改学生学号</A>
			</LI>
			
		</UL>
	</DIV>
	
	
	
	<DIV class=menu_master onClick="javascript:switchMenu(1, 4);">
		<IMG src="image/dot_02.gif" alt=close width="7" height="7"
			id=menu_master_1>
		箱子管理
	</DIV>
	<DIV class=sub_menu id=menu_sub_1>
		<UL>
			<LI>
				<A title="设定预定最大箱子数" href="showAdmin.action?status=maxBoxCount"
					target="mainFrame">设定预定最大箱子数</A>
			</LI>
			<LI>
				<A title="箱子增删改" href="showFloorCateManage.action"
					target="mainFrame">箱子增删改</A>
			</LI>
			<LI>
				<A title="查看订购现状" href="viewBox_back.jsp"
					target="mainFrame">查看订购现状</A>
			</LI>
			<LI>
				<A title="打印剩余箱子" href="showLeftBox.action"
					target="mainFrame">打印剩余箱子</A>
			</LI>
		</UL>
	</DIV>
	
	
	<DIV class=menu_master onClick="javascript:switchMenu(2, 4);">
		<IMG src="image/dot_02.gif" alt=close width="7" height="7"
			id=menu_master_2>
		收费管理
	</DIV>
	<DIV class=sub_menu id=menu_sub_2>
		<UL>
			<LI>
				<A title="设定箱子价格" href="showPrice.action"
					target="mainFrame">设定箱子价格</A>
			</LI>
			<LI>
				<A title="续约时间设定" href="showAdmin.action?status=conTime"
					target="mainFrame">续约时间设定</A>
			</LI>
			<LI>
				<A title="预约时间设定" href="showAdmin.action?status=bookTime"
					target="mainFrame">预约时间设定</A>
			</LI>
			<LI>
				<A title="箱子收费管理" href="payBoxManage.action"
					target="mainFrame">箱子收费管理</A>
			</LI>
			<LI>
				<A title="箱子收费查询" href="managePrice.jsp"
					target="mainFrame">箱子收费查询</A>
			</LI>

		</UL>
	</DIV>
</DIV>
