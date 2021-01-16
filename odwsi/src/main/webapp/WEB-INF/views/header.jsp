<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>


<form method="post" action="${contextPath}/logout">
    <button>Logout</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<h2>Welcome ${username} </h2>
