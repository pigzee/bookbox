package edu.hljcu.displaytag;

import org.displaytag.decorator.TableDecorator;

import edu.hljcu.hibernate.all.FloorCate;


public class CateWrapper extends TableDecorator{
	public String editLink;

	public String getEditLink() {

		FloorCate fc= (FloorCate) getCurrentRowObject();
        String id=fc.getFloorCateId().toString();
        
        return "<a href='showRowCateManage.action?floorCateId=" + id
	        + "'  >查看排数</a> |<a href='showFloorCateById.action?floorCateId="+ id+"'>编辑</a> |"
            + "<a href='deleteFloorCate.action?floorCateId="+id+ "' onclick=\"return confirm('ȷ��此类下的所有排号和箱子都会被删除，确定删除?');\">删除</a> ";
	}

	public void setEditLink(String editLink) {
		this.editLink = editLink;
	}
	
}
