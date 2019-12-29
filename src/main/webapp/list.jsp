<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1" align="centen" width="60%">
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>年龄</td>
        </tr>

        <c:forEach var="list" items="${list}">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <c:if test="${list.sex==0}">
                <td>男</td>
            </c:if>
            <c:if test="${list.sex==1}">
                <td>女</td>
            </c:if>
            <td>${list.age}</td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
