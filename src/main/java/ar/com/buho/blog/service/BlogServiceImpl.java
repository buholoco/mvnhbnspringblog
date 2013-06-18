package ar.com.buho.blog.service;

import java.util.List;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import ar.com.buho.blog.model.Post;

@Service
public class BlogServiceImpl implements BlogService {

	public PagedListHolder<Post> getPagedList(List<Post> list, String orderBy, boolean order) {
		PagedListHolder<Post> postList = new PagedListHolder<Post>(
				list, new MutableSortDefinition(orderBy, true, order));
		postList.resort();
		postList.setPageSize(2);
		return postList;
	}
	
//	private void updateTagsWeights() {
//		List<Tag> tags = tagDAO.findAll();
//		for (Tag tag : tags) {
//			tag.setWeight(tag.getPosts().size());
//			tagDAO.update(tag);
//		}
//	}
	
}
