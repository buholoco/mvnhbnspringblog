package ar.com.buho.blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TAG")
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015834688916897788L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="TAG_TITLE")
	@NotBlank
	private String title;
	
	@Transient
	private int weight;
	
	@JsonBackReference("post-tag")
	@ManyToMany(mappedBy="tags")
	private Set<Post> posts = new HashSet<Post>();

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
		this.title = title.toLowerCase();
	}

	public int getWeight() {
		return this.getPosts().size();
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Set<Post> getPosts() {

		return posts;
		
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	
	
	

}
