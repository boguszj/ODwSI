<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Access log</title>
</head>


<body>

<jsp:include page="./header.jsp"/>

<h3>Access log:</h3>

<table>
    <c:forEach items="${accessLogEntries}" var="accessLogEntry">
        <tr>
            <td>
                IP:
            </td>
            <td>
                <c:out value="${accessLogEntry.ip}"/>
            </td>
            <td>
                Login date:
            </td>
            <td>
                <c:out value="${accessLogEntry.loginTime}"/>
            </td>
        </tr>
    </c:forEach>
</table>



<a href="${contextPath}/dashboard">Dashboard</a>

</body>


</html>