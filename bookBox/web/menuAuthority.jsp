<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.*"%>
<!--************************************* ʵ�����뿪ʼ *************************************-->
<SCRIPT type=text/javascript>
function switchMenu(curr_id, total_num)
{
	for(var i=0; i<total_num; i++)
	{
		//�����˵�
		var el = document.getElementById('menu_sub_'+i);

		if(el!=null)
		{
			if(!el) return;
	
			//һ���˵�ǰ���ͼƬ
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
		�˻�����
	</DIV>
	<DIV class=sub_menu id=menu_sub_0>
		<UL>
			<LI>
				<A title="" href="showAdmin.action?status=admin"
					target="mainFrame">�༭����Ա��Ϣ</A>
			</LI>
			<LI>
				<A title="" href="addStudRecord.jsp"
					target="mainFrame">����ѧ����¼</A>
			</LI>
			<LI>
				<A title="" href="editStudNo.jsp"
					target="mainFrame">�޸�ѧ��ѧ��</A>
			</LI>
			
		</UL>
	</DIV>
	
	
	
	<DIV class=menu_master onClick="javascript:switchMenu(1, 4);">
		<IMG src="image/dot_02.gif" alt=close width="7" height="7"
			id=menu_master_1>
		���ӹ���
	</DIV>
	<DIV class=sub_menu id=menu_sub_1>
		<UL>
			<LI>
				<A title="�趨Ԥ�����������" href="showAdmin.action?status=maxBoxCount"
					target="mainFrame">�趨Ԥ�����������</A>
			</LI>
			<LI>
				<A title="������ɾ��" href="showFloorCateManage.action"
					target="mainFrame">������ɾ��</A>
			</LI>
			<LI>
				<A title="�鿴������״" href="viewBox_back.jsp"
					target="mainFrame">�鿴������״</A>
			</LI>
			<LI>
				<A title="��ӡʣ������" href="showLeftBox.action"
					target="mainFrame">��ӡʣ������</A>
			</LI>
		</UL>
	</DIV>
	
	
	<DIV class=menu_master onClick="javascript:switchMenu(2, 4);">
		<IMG src="image/dot_02.gif" alt=close width="7" height="7"
			id=menu_master_2>
		�շѹ���
	</DIV>
	<DIV class=sub_menu id=menu_sub_2>
		<UL>
			<LI>
				<A title="�趨���Ӽ۸�" href="showPrice.action"
					target="mainFrame">�趨���Ӽ۸�</A>
			</LI>
			<LI>
				<A title="��Լʱ���趨" href="showAdmin.action?status=conTime"
					target="mainFrame">��Լʱ���趨</A>
			</LI>
			<LI>
				<A title="ԤԼʱ���趨" href="showAdmin.action?status=bookTime"
					target="mainFrame">ԤԼʱ���趨</A>
			</LI>
			<LI>
				<A title="�����շѹ���" href="payBoxManage.action"
					target="mainFrame">�����շѹ���</A>
			</LI>
			<LI>
				<A title="�����շѲ�ѯ" href="managePrice.jsp"
					target="mainFrame">�����շѲ�ѯ</A>
			</LI>

		</UL>
	</DIV>
</DIV>
