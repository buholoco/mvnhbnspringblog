package ar.com.buho.blog.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ar.com.buho.blog.model.Tag;
import ar.com.buho.blog.service.TagService;

public class CommaDelimitedStringEditor extends PropertyEditorSupport{
	private TagService tagService;
	
	public CommaDelimitedStringEditor(TagService tagService) {
		this.tagService = tagService;
	}
	
	public void setAsText(String text) {
		Set<Tag> tags = new HashSet<Tag>();
		if (!text.isEmpty()) {
			String[] stringTags = text.split(",");
	        for(String title : stringTags) {
	        	Tag tag = tagService.findTagByTitle(title.trim());
	        	
	        	tags.add(tag);
	        }
        }
        setValue(tags);
    }
	
	@SuppressWarnings("unchecked")
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
