<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="post"/>
<article id="post-${post.id }">
	<div id="sigle-post">
		<h4>${post.title }</h4>
		<p>${post.content }</p>
		<div class="muted">
			| Comments ${fn:length(post.comments) } | created:
			<fmt:formatDate type="date" value="${post.created }" />
			| updated:
			<fmt:formatDate type="date" value="${post.updated }" />
			| <a href="<c:url value="/post/edit/${post.id }" />">Edit</a> | <a
				href="<c:url value="/post/delete/${post.id }" />">Delete</a> |
		</div>
	</div>
</article>