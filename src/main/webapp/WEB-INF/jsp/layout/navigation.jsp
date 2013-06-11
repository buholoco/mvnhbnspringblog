<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Sidebar</li>
        <li class="active"><a href="#">Link</a></li>
        <li><a href="<c:url value="/post/add.htm" />">Add post</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
    </ul>
</div>
<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Tags</li>
        <c:if test="${!empty tagList }">
            <p>
            <c:forEach items="${tagList }" var="tag" >
                <a href="<c:url value="/tag/${tag.id}"/>">${tag.title } (${fn:length(tag.posts) })</a>, 
            </c:forEach>
            </p>
        </c:if>
    </ul>
</div>


