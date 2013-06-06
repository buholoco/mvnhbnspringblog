package ar.com.buho.blog.generic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.buho.blog.model.Tag;

@Repository
public class TagDAO extends AbstractHibernateDAO<Tag> implements ITagDAO {

	public TagDAO() {
		setClazz(Tag.class);
	}
	
	public Tag findByTitle(String title) {
		List<Tag> tags = getCurrentSession().createQuery("from Tag t where t.title = :tag_title")
				.setParameter("tag_title", title)
				.list();
		if (tags.isEmpty()) {
			return null;
		} else {
			return tags.get(0);
		}
		
	}
	
	@Override
	public void create(Tag tag) {
		if (findByTitle(tag.getTitle()) == null) {
			super.create(tag);
		}
	}
}
