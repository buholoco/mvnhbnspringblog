<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Sidebar</li>
        <li><a href="/blog/">Home</a></li>
        <li><a href="<c:url value="/post/add.htm" />">Add post</a></li>
    </ul>
</div>

<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Tag Cloud</li>
        <c:if test="${!empty tagList }">
            <p>
            <c:forEach items="${tagList }" var="tag" >
                <c:if test="${!(tag.weight eq 0) }" >
                    <a href="<c:url value="/tag/${tag.id}"/>"><span class="weight-${tag.weight }">${tag.title } </span></a>,
                </c:if> 
            </c:forEach>
            </p>
        </c:if>
    </ul>
</div>

<%-- <div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Tags</li>
        <c:if test="${!empty tagList }">
            <p>
            <c:forEach items="${tagList }" var="tag" >
                <a href="<c:url value="/tag/${tag.id}"/>">${tag.title } (${tag.weight })</a>, 
            </c:forEach>
            </p>
        </c:if>
    </ul>
</div> --%>
<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Recent comments</li>
        <c:if test="${!empty lastComments }">
            <li>
            <c:forEach items="${lastComments }" var="comment" >
                <a href="<c:url value="/post/${comment.post.id }/show.htm#comment-${comment.id}"/>">${comment.title } </a>
                <p>${comment.content }</p> 
            </c:forEach>
            </li>
        </c:if>
    </ul>
</div>

