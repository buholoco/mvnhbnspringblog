package ar.com.buho.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "COMMENT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment implements Timestampable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6579106333838321149L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
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
	
	@JsonBackReference("post-comment")
	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "POST_ID")
	private Post post;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", title=" + title + ", content="
				+ content + ", created=" + created + ", updated=" + updated
				+ "]";
	}
	
	

}
