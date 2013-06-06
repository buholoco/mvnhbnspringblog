package ar.com.buho.blog.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ar.com.buho.blog.model.Tag;

public class CommaDelimitedStringEditor extends PropertyEditorSupport{
	
	public void setAsText(String text) {
		Set<Tag> tags = new HashSet<Tag>();
		if (!text.isEmpty()) {
			String[] stringTags = text.split(",");
	        for(String title : stringTags) {
	        	title = title.trim();
	        	Tag tag = new Tag();
	        	tag.setTitle(title);
	        	
	        	tags.add(tag);
	        }
        }
        setValue(tags);
    }
	
	public String getAsText() {
		Set<Tag> tags = (Set<Tag>) getValue();
		String rTags = new String();
		Iterator<Tag> itr = tags.iterator();
		while (itr.hasNext()) {
			Tag tag = (Tag) itr.next();
			rTags += tag.getTitle();
			if (itr.hasNext()) {
				rTags += ", ";
			}
		}
		return rTags;
	}
}
