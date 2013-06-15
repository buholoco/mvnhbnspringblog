<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Blog - ${title }</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">



<script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>

<!-- Styles -->

<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="/resources/js/html5shiv.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value="/resources/ico/apple-touch-icon-144-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value="/resources/ico/apple-touch-icon-114-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value="/resources/ico/apple-touch-icon-72-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value="/resources/ico/apple-touch-icon-57-precomposed.png"/>">
<link rel="shortcut icon"
	href="<c:url value="/resources/ico/favicon.ico"/>">

<link href="<c:url value="/resources/prettify/prettify-desert.css" />" type="text/css" rel="stylesheet" />
<link href="<c:url value="/resources/css/sidebar.css" />" type="text/css" rel="stylesheet" />
</head>

<body onload="prettyPrint()">
	<tiles:importAttribute />

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="<c:url value="/" />">Blog</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
					    <a href="<c:url value="search.htm" />">Search</a> |
						<sec:authorize access="isAuthenticated()"> 
						  Logged in as 
						  <a href="#" class="navbar-link"> <sec:authentication
									property="principal.username" />
							</a> | 
						      <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
						</sec:authorize>
						<sec:authorize access="isAnonymous()">
							<a href="<c:url value="/login.htm" />">Login</a>
						</sec:authorize>

					</p>
					<ul class="nav">
						<li class="active"><a href="<c:url value="/"/>">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="/blog/contact.htm">Contact</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				
						<tiles:insertAttribute name="navigation" />
				
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<div class="row-fluid">
					<h1>${title }</h1>
					<hr />
				</div>
				<div class="row-fluid">
					<tiles:insertAttribute name="body" />
					
				</div>
				<!--/row-->
			</div>
			<!--/span-->
		</div>
		<!--/row-->

		<hr>

		<footer>
			<tiles:insertAttribute name="footer" />
			
		</footer>

	</div>
	<!--/.fluid-container-->

	<!-- Javascripts
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/prettify/prettify.js" />"></script>
    
	
</body>
</html>
