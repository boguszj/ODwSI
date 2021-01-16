<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8">
    <title>Log in</title>
</head>

<body>

<h2>Log in</h2>

<form:form method="post" modelAttribute="loginForm">

    <spring:bind path="username">
        <form:input type="text" path="username" placeholder="Username" autofocus="true"/>
    </spring:bind>

    <spring:bind path="password">
        <form:input type="password" path="password" class="form-control" placeholder="Password"/>
    </spring:bind>

    <form:errors path="error"/>

    <form:button>Log In</form:button>

</form:form>

<a href="${contextPath}/registration">Create an account</a>

</body>


</html>