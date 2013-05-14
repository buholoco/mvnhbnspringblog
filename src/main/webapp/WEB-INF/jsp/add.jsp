<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>${title }</title>
</head>
<body>

<h2>Add a Post</h2>

<form:form method="post" action="add" commandName="post">

    <table>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td> 
        <td><form:errors path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="content">Content</form:label></td>
        <td><form:textarea path="content" /></td>
        <td><form:errors path="content" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add Post"/>
        </td>
    </tr>
</table>    
</form:form>


</body>
</html>