package ar.com.buho.blog.controller;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ar.com.buho.blog.service.BlogService;

@Controller("NavigationController")
@Scope("request")
public class NavigationController extends ViewPreparerSupport {

	@Autowired(required=true)
	BlogService blogService;
	
	@Override
	public void execute(TilesRequestContext tilesContext,
			AttributeContext attributeContext) {
		tilesContext.getRequestScope().put("tagList", blogService.findTags());
		
	}
	
}
