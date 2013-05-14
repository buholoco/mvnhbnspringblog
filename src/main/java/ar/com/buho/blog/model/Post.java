package ar.com.buho.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POST")
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name="POST_TITLE")
	@Size(max=50, min=5)
	@NotNull
	private String title;

	@Column(name="POST_CONTENT", columnDefinition="TEXT")
	@Size(min=5)
	@NotNull
	private String content;

	@Column(name="POST_CREATED")
	private Date created;

	@Column(name="POST_UPDATED")
	private Date updated;

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

	@PrePersist
	protected void onCreate() {
		this.created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated = new Date();
	}

}
