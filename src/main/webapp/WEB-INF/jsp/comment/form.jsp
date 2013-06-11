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
				if(response.status == "SUCCESS"){
					$('#info').html(response);
					$('#title').val('');
					$('#content').val('');
					var today = new Date();
					//var monthNames = [ "January", "February", "March", "April", "May", "June",
					//                   "July", "August", "September", "October", "November", "December" ];
					var day   = today.getDate();
				    //var month = monthNames[today.getMonth()];
				    var month = today.getMonth();
					var newComment = '<div id="comment-' + response.result.id + '">';
				        newComment += '<h6>' + response.result.title + '</h6>';
				        newComment += '<p>' + response.result.content + '</p>';
				        newComment += '<div class="muted">| created: a few seconds ago</div>';
	                    newComment += '<hr /></div>';
	                $('#comments').append(newComment);
	                $('#new-comment').hide().fadeIn('slow');
				}else{
					errorInfo = "";
					for(i =0 ; i < response.result.length ; i++){
						errorInfo += "<br>" + (i + 1) +". " + response.result[i].defaultMessage;
						$('#info').html("Please correct following errors: " + errorInfo);
					}

				}
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