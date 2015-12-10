package edu.hljcu.displaytag;

import java.io.UnsupportedEncodingException;

import org.displaytag.decorator.TableDecorator;

import edu.hljcu.hibernate.all.Box;

public class BoxWrapper extends TableDecorator{
	public String editLink;

	public String getEditLink() throws UnsupportedEncodingException {
		Box box = (Box) getCurrentRowObject();
        String id=box.getBoxId().toString();
        return "<a href='showBoxById.action?boxId=" + id + "'  >编辑</a> |"
        		+ "<a href='deleteBox.action?boxId=" + id +"' onclick=\"return confirm('ȷ��与此箱子有关的学生预约记录需会被删除，确定删除?');\">删除</a> ";
		}

	public void setEditLink(String editLink) {
		this.editLink = editLink;
	}
	
}
