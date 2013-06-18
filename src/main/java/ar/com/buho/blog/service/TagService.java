package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.buho.blog.model.Tag;

@Service
public interface TagService {

	public List<Tag> findTags();
	public Tag findTagById(long id);
	public Tag findTagByTitle(String title);
}
