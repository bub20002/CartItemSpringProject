<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
<head><title>Bank Accounts</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<div class="container">
    <h1>Show all items in cart</h1>
    <a class="btn btn-success pull-right" href="<c:url value="/items_in_cart/${cartId}/add_item"/>" role="button">Add item</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Index</td>
            <td>Item name</td>
            <td></td>
            <td></td>
            <%--<td><fmt:message key = "Balance"/></td>--%>
            <%--<td><fmt:message key = "Status"/></td>--%>
            <%--<td><fmt:message key = "Action"/></td>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td><a class="btn btn-danger" href="<c:url value="/items_in_cart/${cartId}/delete_item/${item.id}"/>" role="button">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
