package ar.com.buho.blog.generic.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ar.com.buho.blog.generic.dao.ITagDAO;
import ar.com.buho.blog.model.Tag;

@Repository
public class TagDAO extends AbstractHibernateDAO<Tag> implements ITagDAO {
	protected static Logger logger = Logger.getLogger("dao");
	
	public TagDAO() {
		setClazz(Tag.class);
	}
	
	@SuppressWarnings("unchecked")
	public Tag findByTitle(String title) {
//		logger.debug("findByTitle " + title);
		List<Tag> tags = getCurrentSession().createQuery("from Tag t where t.title = :tag_title")
				.setParameter("tag_title", title)
				.list();
		if (tags.isEmpty()) {
			Tag tag = new Tag();
			tag.setId(0);
			tag.setTitle(title);
			return tag;
		} else {
			return tags.get(0);
		}
	}
}
