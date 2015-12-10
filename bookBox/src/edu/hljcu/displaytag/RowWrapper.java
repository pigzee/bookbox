package edu.hljcu.displaytag;

import org.displaytag.decorator.TableDecorator;

import edu.hljcu.hibernate.all.RowCate;

public class RowWrapper extends TableDecorator{
	public String editLink;
	public String getEditLink() {
		RowCate rc= (RowCate) getCurrentRowObject();
        String id=rc.getRowCateId().toString();
        
        return "<a href='showBoxManage.action?rowCateId=" + id
	        + "'  >查看箱子</a> |<a href='showRowCateById.action?rowCateId="+ id+"'>编辑</a> |"
            + "<a href='deleteRowCate.action?rowCateId="+id+ "' onclick=\"return confirm('ȷ��ȷ��此排下的所有箱子都会被删除，确定删除?');\">删除</a> ";
	}
	public void setEditLink(String editLink) {
		this.editLink = editLink;
	}
}
