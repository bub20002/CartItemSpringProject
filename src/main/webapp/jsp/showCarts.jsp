<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
<head><title>Cart List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<div class="container">
    <h1>List of Carts</h1>
    <a class="btn btn-success pull-right" href="<c:url value='/cart'/>" role="button">Create Cart</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Index</td>
            <td>Id</td>
            <td>Name</td>
            <td>Capacity</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cart" items="${carts}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"/></td>
                <td><c:out value="${cart.id}"/></td>
                <td><c:out value="${cart.name}"/></td>
                <td><c:out value="${cart.capacity}"/></td>
                <td><a class="btn btn-success" href="<c:url value="/items_in_cart/${cart.id}"/>" role="button">Show items in cart</a></td>
                <%--<td><a class="btn btn-warning" href="<c:url value="/update_cart/${cart.id}"/>" role="button">Update</a></td>--%>
                <td><a class="btn btn-danger" href="<c:url value="/delete_cart/${cart.id}"/>" role="button">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="color: red"><c:out value="${error}"/></div>
</div>
</body>
</html>
