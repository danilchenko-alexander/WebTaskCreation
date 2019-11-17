<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Task Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <form method="post" action="/filter">
        <div>StartDate: <input type="text" name="startDate" id="sd"></div>
        <div>EndDate: <input type="text" name="endDate" id="ed"></div>
        <div>Assignee: <select name="assignee">
            <option>All assignees</option>
            <c:forEach items="${assignees}" var="assignee">
                <option>${assignee}</option>
            </c:forEach>
        </select></div>
        <div>
            Period: <select id="period" onchange="insertDate()">
            <option>Not chosen</option>
            <option>Last Quarter</option>
            <option>Last Month</option>
            <option>Last Week</option>
            <option>Current Quarter to Date</option>
            <option>Current Month to Date</option>
            <option>Current Week to Date</option>
        </select>
        </div>
        <button type="submit">Find</button>
    </form>
    <div class="message">
        <c:if test="${message!=null}">
            <div>${message}</div>
        </c:if>
    </div>
    <table border="1" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>Summary</th>
            <th>StartDate</th>
            <th>EndDate</th>
            <th>Assignee</th>
        </tr>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>${task.id}</td>
                <td>${task.summary}</td>
                <td>${task.startDate}</td>
                <td>${task.endDate}</td>
                <td>${task.assignee}</td>
            </tr>
        </c:forEach>
    </table>
    <script src="/resources/js/date.js"></script>
</div>
</body>
</html>
