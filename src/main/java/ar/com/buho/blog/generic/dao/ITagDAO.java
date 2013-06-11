package ar.com.buho.blog.generic.dao;

import java.util.Map;
import java.util.Set;

import ar.com.buho.blog.model.Tag;

public interface ITagDAO extends IOperations<Tag> {

	public Tag findByTitle(String title);
}
