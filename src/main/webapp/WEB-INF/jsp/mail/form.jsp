<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:htmlEscape defaultHtmlEscape="true" /> 

<form:form method="post" action="/blog/contact.htm" commandName="contactType">

    <form:label path="from">Email</form:label>
    <form:input path="from" placeholder="Enter your email.." class="input-large"  htmlEscape="true"/>
    <form:errors path="from" class="text-error" />
    
    <form:label path="subject">Subject</form:label>
    <form:input path="subject" placeholder="Enter subject.." class="input-large"  htmlEscape="true"/>
    <form:errors path="subject" class="text-error" />
    
    

    <form:label path="text">Body</form:label>
    <form:textarea path="text" class="input-xxlarge" rows="10" htmlEscape="true"/>
    <form:errors path="text" class="text-error" />
    
    <br />

    <input type="submit" value="Send" class="btn" />


</form:form>