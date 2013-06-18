package ar.com.buho.blog.controller;

import java.util.Collections;
import java.util.List;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ar.com.buho.blog.model.Tag;
import ar.com.buho.blog.service.CommentService;
import ar.com.buho.blog.service.TagService;

@Controller("NavigationController")
@Scope("request")
public class NavigationController extends ViewPreparerSupport {

	@Autowired(required = true)
	TagService tagService;
	
	@Autowired(required = true)
	CommentService commentService;
	
	@Override
	public void execute(TilesRequestContext tilesContext,
			AttributeContext attributeContext) {
		List<Tag> tags = tagService.findTags();
		Collections.shuffle(tags);
		tilesContext.getRequestScope().put("tagList", tags);
		tilesContext.getRequestScope().put("lastComments", commentService.findLastestComments());
		
	}
	
}
