<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>${page_title}</title>
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<a href="<c:url value="/" />">Blog</a>
			</h1>
		</header>
		<div id="content">
			<section>
				<h2>Recent Posts</h2>
				<c:if test="${!empty postList}">
					<p>Mostrando ${fn:length(postList) } Posts</p>
					<c:forEach items="${postList}" var="post">
						<article id="post-${post.id }">
							<div id="sigle-post">
								<h4>${post.title }</h4>
								<p>${post.content }</p>
								<p>created ${post.created }</p>
								<p>updated ${post.updated }</p>
								<form:form method="put" action="/blog/edit/post/${post.id}">
                                    <p class="submit">
                                        <input type="submit" value="Edit post" />
                                    </p>
                                </form:form>
								<form:form method="delete" action="/blog/delete/post/${post.id}">
									<p class="submit">
										<input type="submit" value="Delete post" />
									</p>
								</form:form>
							</div>
						</article>
					</c:forEach>
				</c:if>
			</section>
			<nav>
				<ul>
					<li><a href='<c:url value="/add" />'>Add Post</a>
					</href></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>
