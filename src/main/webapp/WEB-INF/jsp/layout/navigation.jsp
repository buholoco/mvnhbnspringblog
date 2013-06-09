<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
        <li><a href="#">Link</a></li>
        <li><a href="<c:url value="/post/add.htm" />">Add post</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
    </ul>
</div>


