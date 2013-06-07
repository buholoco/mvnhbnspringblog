package ar.com.buho.blog.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private IPostDAO postDAO;
	
	@Autowired
	private ICommentDAO commentDAO;
	
	@Autowired
	private ITagDAO tagDAO;
	
	@Transactional(readOnly = true)
	public Post findPostById(long id) {
		return postDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Post> findPostsByTagId(long id) {
		List<Post> postList = new ArrayList<Post>();
		postList.addAll(tagDAO.findById(id).getPosts());
		return postList;
	}
	
	@Transactional
	public void savePost(Post post) {
		post = this.setTagsForPost(post);
		postDAO.create(post);
	}
	
	@Transactional
	public void updatePost(Post post) {
		post = this.setTagsForPost(post);
		postDAO.update(post);
	}

	@Transactional(readOnly = true)
	public List<Post> findPosts() {
		return postDAO.findAll();
	}

	@Transactional
	public void removePost(long id) {
		postDAO.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	public Comment findCommentbyId(long id) {
		return commentDAO.findById(id);
	}

	@Transactional
	public void saveComment(Comment comment, long postId) {
		Post post = postDAO.findById(postId);
		comment.setPost(post);
		commentDAO.create(comment);     		
    }

	@Transactional(readOnly = true)
	public List<Comment> findComments() {
		return commentDAO.findAll();
	}
	
	@Transactional
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
	
	private Post setTagsForPost(Post post) {
		Set<Tag> tags = new HashSet<Tag>();
		if (!post.getTags().isEmpty()) {
			for(Tag tag : post.getTags()) {
				Tag tag2 = findTagByTitle(tag.getTitle());
				if (tag2 == null) {
					tag2 = new Tag();
					tag2.setTitle(tag.getTitle());
				}
				tags.add(tag2);
			}
		}
		post.setTags(tags);
		return post;
	}

}
