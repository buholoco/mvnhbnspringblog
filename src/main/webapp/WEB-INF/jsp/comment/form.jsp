<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function doAjaxPost() {
		// get the form values
		var title = $('#title').val();
		var content = $('#content').val();

		$.ajax({
			type : "POST",
			url : "/blog/post/${post.id}/comment/add.ajax",
			data : "title=" + title + "&content=" + content,
			success : function(response) {
				// we have the response
				$('#info').html(response);
				$('#title').val('');
				$('#content').val('');
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>
<div id="info" style="color: green;"></div>

<form:form method="post" action="/blog/post/${post.id }/comment/add.htm" commandName="comment">
	<form:label path="title">Title</form:label>
	<form:input path="title" placeholder="Enter title.."
		class="input-block-level" />
	<form:errors path="title" class="text-error" />

    <form:label path="content">Content</form:label>
	<form:textarea path="content" class="input-block-level" />
	<form:errors path="content" class="text-error" />

	<br />
	<input type="submit" value="Add Comment HTML" class="btn" />
	<input type="button" value="Add Comment Ajax" onclick="doAjaxPost()" class="btn" />
</form:form>