package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.model.Tag;

public interface BlogService {
	
	public Post findPostById(long id);
	public List<Post> findPostsByTitle(String title);
	public List<Post> findPostsByTagId(long id);
	public void savePost(Post post);
	public void updatePost(Post post);
	public List<Post> findPosts();
	public void removePost(long id);
	
	public Comment findCommentbyId(long id);
	public List<Comment> findLastestComments();
	public void saveComment(Comment comment, long postId);
	public List<Comment> findComments();
	public void removeComment(long id);
	
	public List<Tag> findTags();
	public Tag findTagById(long id);
	public Tag findTagByTitle(String title);
	
	public PagedListHolder<Post> getPagedList(List<Post> list, String orderBy, boolean order);

}
