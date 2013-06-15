package ar.com.buho.blog.generic.dao;

import java.util.List;

import ar.com.buho.blog.model.Post;

public interface IPostDAO extends IOperations<Post>{
	public List<Post> findByTitle(String title);
}
