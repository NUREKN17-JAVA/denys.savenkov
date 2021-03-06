<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" class="ua.nure.cs.savenkov.usermanagement.User" scope="session"/>
<html>
<head><title>User management: details</title></head>
<body>
<form action="<%=request.getContextPath()%>/details" method="post">
    <input type="hidden" name="id" value="${user.id}">
    ${user.firstName}<br>
    ${user.lastName}<br>
    ${user.dateOfBirth}<br>
    <input type="submit" name="okButton" value="Ok">
</form>
<c:if test='${requestScope.error != null}'>
    <script type="text/javascript">
        alert('${requestScope.error}');
    </script>
</c:if>
</body>
</html>