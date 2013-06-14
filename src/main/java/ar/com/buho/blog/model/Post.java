package ar.com.buho.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
//@FetchProfile(name = "posts-with-deps", fetchOverrides = {
//		   @FetchProfile.FetchOverride(entity = Post.class, association = "comments", mode = FetchMode.JOIN),
//		   @FetchProfile.FetchOverride(entity = Post.class, association = "tags", mode = FetchMode.JOIN)
//		})
@Table(name = "POST")
public class Post implements Timestampable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7125976836669176901L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

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
	
	@JsonManagedReference("post-comment")
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@OrderBy("created")
	private Set<Comment> comments;
	
	@JsonManagedReference("post-tag")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		      name="POST_TAG", 
		      joinColumns={@JoinColumn(name="POST_ID", referencedColumnName="ID", nullable = false, updatable = false)},
		      inverseJoinColumns={@JoinColumn(name="TAG_ID", referencedColumnName="ID", nullable = false, updatable = false)}
		      )
	private Set<Tag> tags = new HashSet<Tag>();

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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content
				+ ", created=" + created + "]";
	}
	
	
}
