<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="span12">
	<c:if test="${!empty success }">
		<div>
			<p class="text-success">
				<strong>${success }</strong>
			</p>
		</div>
	</c:if>

	<section>
		<c:if test="${!empty postList.getPageList() }">
			<p>
				<small>Showing page ${postList.page + 1 } of ${postList.pageCount }</small>
			</p>
			<c:forEach items="${postList.pageList }" var="post" >
			    <tiles:putAttribute name="post" value="${post }" cascade="true" />
                <tiles:insertAttribute name="_post" />
			</c:forEach>
			<c:if test="${!postList.lastPage }"><a href="?page=next">&lt; ${postList.page + 2 }</a></c:if> ${postList.page + 1 } <c:if test="${!postList.firstPage }"><a href="?page=previous">${postList.page } &gt;</a></c:if>
		</c:if>
	</section>
</div>
<!--/span-->