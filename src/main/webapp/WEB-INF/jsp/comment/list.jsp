<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div id="comments">
	<c:if test="${!empty post.comments }">
		<h5>
			<strong>Comments</strong>
		</h5>
		<c:forEach items="${post.comments }" var="comment">
			<!-- @TODO Find a way to include and use this as a partial -->
			<c:set var="comment" value="${comment}" scope="request" />
			<jsp:include page="_comment.jsp" />
        </c:forEach>
	</c:if>
	
</div>