<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:htmlEscape defaultHtmlEscape="true" /> 

<form:form method="post" action="/blog/post/add.htm" commandName="post">
    <form:hidden path="id"/>

    <form:label path="title">Title</form:label>
	<form:input path="title" placeholder="Enter title.." class="input-block-level" />
	<form:errors path="title" class="text-error" />


	<form:label path="content">Content</form:label>
	<form:textarea path="content" class="input-block-level" />
	<form:errors path="content" class="text-error" />

    <form:label path="tags" title="Comma separate values">Tags</form:label>
    <form:input path="tags" title="Comma separate values" class="input-block-level" placeholder="Comma, Separate, Values" />
    <form:errors path="tags" class="text-error" />
    <br/>
	<input type="submit" value="Add Post" class="btn" />


</form:form>