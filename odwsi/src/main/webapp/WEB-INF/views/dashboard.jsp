<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>

<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
</head>


<body>

<jsp:include page="./header.jsp"/>

<h3>Mine:</h3>

<table>
    <c:forEach items="${credentials}" var="credential">
        <c:if test="${credential.owner.equals(username)}">
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <c:out value="${credential.name}"/>
                </td>
                <td>
                    <form method="get" action="${contextPath}/details/${credential.credentialId}">
                        <button>Details</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${contextPath}/details/${credential.credentialId}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" name="_method" value="DELETE"/>
                        <button>Delete</button>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>

<h3>Shared to me:</h3>

<table>
    <c:forEach items="${credentials}" var="credential">
        <c:if test="${!credential.owner.equals(username)}">
            <tr>
                <td>
                    Owner:
                </td>
                <td>
                    <c:out value="${credential.owner}"/>
                </td>
                <td>
                    Name:
                </td>
                <td>
                    <c:out value="${credential.name}"/>
                </td>
                <td>
                    <form method="get" action="${contextPath}/details/${credential.credentialId}">
                        <button>Details</button>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>


<a href="${contextPath}/create">Add credentials</a>
<a href="${contextPath}/access-log">Access log</a>


</body>


</html>