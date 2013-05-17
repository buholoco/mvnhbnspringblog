package ar.com.buho.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "COMMENT")
public class Comment implements Timestampable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "COMMENT_TITLE")
	@Size(max = 50, message = "Title must be at least 5 characters long")
	private String title;
	
	@Column(name = "COMMENT_CONTENT", columnDefinition = "TEXT")
	@Size(min = 5, message = "Content must be at least 5 characters long")
	private String content;
	
	@Column(name = "COMMENT_CREATED")
	private Date created;
	
	@Column(name = "COMMENT_UPDATED")
	private Date updated;
	
	@ManyToOne
	@JoinColumn(name = "POST_ID")
	private Post post;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
