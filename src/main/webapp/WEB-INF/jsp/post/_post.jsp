<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<spring:htmlEscape defaultHtmlEscape="true" /> 

<tiles:useAttribute name="post" ignore="true"/>
<article id="post-${post.id }">
    <div id="sigle-post">
        <h4><a href="<c:url value="/post/${post.id }/show.htm" />"><c:out value="${post.title }" /></a></h4>
        <p><c:out value="${post.content }"/></p>
        <p>Tags:         
	        <c:if test="${!empty post.tags }">
	           <c:forEach items="${post.tags }" var="tag" >
	               <a href="<c:url value="/tag/${tag.id }/" />">${tag.title } | </a> 
	            </c:forEach>
	        </c:if>
        </p>

        <div class="muted">
            | <a href="<c:url value="/post/${post.id }/show.htm#comments" />">Comments ${fn:length(post.comments) } </a>
            | created: <fmt:formatDate type="date" pattern="HH:mm, dd-MM" value="${post.created }" />
            | updated: <fmt:formatDate type="date" pattern="HH:mm, dd-MM" value="${post.updated }" />
            <sec:authorize access="hasRole('ROLE_ADMIN')">
	            | <a href="<c:url value="/post/${post.id }/edit.htm" />">Edit</a> 
	            | <a href="<c:url value="/post/${post.id }/delete.htm" />">Delete</a> 
	        </sec:authorize>
	        <hr/>
        </div>
    </div>
</article>