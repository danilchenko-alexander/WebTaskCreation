<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <div>
        <form method="post" action="/addnewtask">
            <div>Summary: <input type="text" name="summary"></div>
            <div>Assignee: <input type="text" name="assignee"></div>
            <div>StartDate: <input type="date" name="startDate"></div>
            <div>EndDate: <input type="date" name="endDate"></div>

            <div>
                <button type="submit"> Add new Task</button>
            </div>
        </form>
    </div>
    <div class="message">
        <c:if test="${message!=null}">
            <div>${message}</div>
        </c:if>
    </div>
</div>
</body>
</html>
