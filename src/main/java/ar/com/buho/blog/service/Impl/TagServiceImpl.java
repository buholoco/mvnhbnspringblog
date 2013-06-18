package ar.com.buho.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buho.blog.generic.dao.ITagDAO;
import ar.com.buho.blog.model.Tag;
import ar.com.buho.blog.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired(required=true)
	private ITagDAO tagDAO;
	
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

}
