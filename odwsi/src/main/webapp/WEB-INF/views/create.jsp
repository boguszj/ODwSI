<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
</head>


<body>

<jsp:include page="./header.jsp" />

<h2>Add credentials</h2>

<form:form method="post" modelAttribute="credentialForm">

    <spring:bind path="name">
        <form:input type="text" path="name" placeholder="Name"/>
        <form:errors path="name"/>
    </spring:bind>

    <spring:bind path="principal">
        <form:input type="text" path="principal" placeholder="Principal"/>
        <form:errors path="principal"/>
    </spring:bind>

    <spring:bind path="secret">
        <form:input type="password" path="secret" placeholder="Secret"/>
        <form:errors path="secret"/>
    </spring:bind>

    <spring:bind path="shouldValidate">
        <form:checkbox path="shouldValidate"/>
    </spring:bind>

    <form:button>Submit</form:button>

</form:form>

<a href = "${contextPath}/dashboard">Dashboard</a>

</body>


</html>