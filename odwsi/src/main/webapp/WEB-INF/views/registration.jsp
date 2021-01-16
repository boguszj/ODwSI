<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>

<h2>Create your account</h2>

<form:form method="post" modelAttribute="userForm">

    <spring:bind path="username">
        <form:input type="text" path="username" placeholder="Username" autofocus="true"/>
        <form:errors path="username"/>
    </spring:bind>

    <spring:bind path="password">
        <form:input type="password" path="password" placeholder="Password"/>
        <form:errors path="password"/>
    </spring:bind>

    <spring:bind path="passwordConfirmation">
        <form:input type="password" path="passwordConfirmation" placeholder="Confirm your password"/>
        <form:errors path="passwordConfirmation"/>
    </spring:bind>

    <form:button>Submit</form:button>

</form:form>

<a href="${contextPath}/login">Log in</a>

</body>


</html>