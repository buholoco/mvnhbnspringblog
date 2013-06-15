package ar.com.buho.blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buho.blog.generic.dao.ICommentDAO;
import ar.com.buho.blog.generic.dao.IPostDAO;
import ar.com.buho.blog.generic.dao.ITagDAO;
import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.model.Tag;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired(required=true)
	private IPostDAO postDAO;
	
	@Autowired(required=true)
	private ICommentDAO commentDAO;
	
	@Autowired(required=true)
	private ITagDAO tagDAO;
	
	@Transactional(readOnly = true)
	public Post findPostById(long id) {
		return postDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Post> findPostsByTagId(long id) {
		return (List<Post>) tagDAO.findById(id).getPosts();
	}
	
	@Transactional(readOnly=true) 
	public List<Post> findPostsByTitle(String title) {
		return (List<Post>) postDAO.findByTitle(title);
	}
	
	@Transactional(readOnly = false)
	public void savePost(Post post) {
		postDAO.create(post);
//		this.updateTagsWeights();
	}
	
	@Transactional(readOnly = false)
	public void updatePost(Post post) {
		postDAO.update(post);		
//		this.updateTagsWeights();
	}

	@Transactional(readOnly = true)
	public List<Post> findPosts() {
		return postDAO.findAll();
	}

	@Transactional(readOnly = false)
	public void removePost(long id) {
		postDAO.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	public Comment findCommentbyId(long id) {
		return commentDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Comment> findLastestComments() {
		HashMap<String, String> options = new HashMap();
		options.put("orderBy", "created");
		options.put("maxResults", "5");
		return commentDAO.findAll(options);
	}

	@Transactional(readOnly = false)
	public void saveComment(Comment comment, long postId) {
		Post post = postDAO.findById(postId);
		comment.setPost(post);
		commentDAO.create(comment);     		
    }

	@Transactional(readOnly = true)
	public List<Comment> findComments() {
		return commentDAO.findAll();
	}
	
	@Transactional(readOnly = false)
	public void removeComment(long id) {
		commentDAO.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Tag> findTags() {
		return tagDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public Tag findTagById(long id) {
		return tagDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Tag findTagByTitle(String title) {
		return tagDAO.findByTitle(title);
	}
	
	public PagedListHolder<Post> getPagedList(List<Post> list, String orderBy, boolean order) {
		PagedListHolder<Post> postList = new PagedListHolder<Post>(
				list, new MutableSortDefinition(orderBy, true, order));
		postList.resort();
		postList.setPageSize(2);
		return postList;
	}
	
//	private void updateTagsWeights() {
//		List<Tag> tags = tagDAO.findAll();
//		for (Tag tag : tags) {
//			tag.setWeight(tag.getPosts().size());
//			tagDAO.update(tag);
//		}
//	}
	
}
