package ar.com.buho.blog.model;

import java.util.Date;

public interface Timestampable {

	public Date getCreated(); 
	public void setCreated(Date created);
	public Date getUpdated();
	public void setUpdated(Date updated);
}
