<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>

<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Details</title>
</head>


<body>

<jsp:include page="./header.jsp" />

<table>
    <tr>
        <td>
            Owner:
        </td>
        <td>
            <span>
                <c:out value="${credential.owner}" />
            </span>
        </td>
    </tr>
    <tr>
        <td>
            Name:
        </td>
        <td>
            <span>
                <c:out value="${credential.name}" />
            </span>
        </td>
    </tr>
    <tr>
        <td>
            Principal
        </td>
        <td>
            <span>
                <c:out value="${credential.principal}" />
            </span>
        </td>
    </tr>
    <tr>
        <td>Secret:</td>
        <td>
            <span>
                <c:out value="${credential.secret}" />
            </span>
        </td>
    </tr>
</table>

<c:if test="${credential.owner.equals(username)}">
    <form:form method="post" action="${contextPath}/details/${credential.credentialId.toString()}" modelAttribute="shareCredentialForm">

        <spring:bind path="username">
            <form:input type="text" path="username" placeholder="Username" autofocus="true"/>
            <form:errors path="username"/>
        </spring:bind>

        <form:button>Submit</form:button>

    </form:form>
</c:if>

<a href = "${contextPath}/dashboard">Dashboard</a>

</body>


</html>
