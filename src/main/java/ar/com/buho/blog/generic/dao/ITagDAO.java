package ar.com.buho.blog.generic.dao;

import ar.com.buho.blog.model.Tag;

public interface ITagDAO extends IOperations<Tag> {

	public Tag findByTitle(String title);
	
	
}
