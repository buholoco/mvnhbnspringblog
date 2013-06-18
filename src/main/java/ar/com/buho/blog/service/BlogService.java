package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import ar.com.buho.blog.model.Post;


public interface BlogService {
	
	public PagedListHolder<Post> getPagedList(List<Post> list, String orderBy, boolean order);

}
