<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div id="sigle-comment">
	<h6>${comment.title }</h6>
	<p>${comment.content }</p>
	<div class="muted">
		| created:
		<fmt:formatDate type="both" pattern="HH:mm, dd-MM" value="${comment.created }" />
		| updated:
		<fmt:formatDate type="both" pattern="HH:mm, dd-MM" value="${comment.updated }" />
	</div>
</div>