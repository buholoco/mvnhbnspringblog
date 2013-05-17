package ar.com.buho.blog.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POST")
public class Post implements Timestampable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "POST_TITLE")
	@Size(min = 5, max = 60, message = "Title should have between 5 and 50 characters")
	@NotNull
	private String title;

	@Column(name = "POST_CONTENT", columnDefinition = "TEXT")
	@Size(min = 5, message = "Content should have at least 5 characters")
	@NotNull
	private String content;

	@Column(name = "POST_CREATED")
	private Date created;

	@Column(name = "POST_UPDATED")
	private Date updated;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Comment> comments = new ArrayList<Comment>();

	public long getId() {
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

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
}
