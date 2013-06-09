<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty error}">
	<div class="text-error">
		Your login attempt was not successful, try again.
	</div>
</c:if>

<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
	<label for="user">User</label> 
	<input type="text" name="j_username" id="j_username"/>
	<label for="password">Password</label> 
	<input type="password" name="j_password" /> 
	<input type="submit" value="Login" />
</form>

<script type="text/javascript">
    $(document).ready(function() {
         $('#j_username').focus();
    });
    
</script>