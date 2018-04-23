<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
<head>
    <title>Item to Cart</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<div class="container">
    <h1>Put item into a cart</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Index</td>
            <td>Id</td>
            <td>Name</td>
            <td>Price</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"/></td>
                <td><c:out value="${item.id}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td><a class="btn btn-success" href="<c:url value="/items_in_cart/${cart.id}/add_item/${item.id}"/>" role="button">Add item</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="color: red"><c:out value="${error}"/></div>
</div>
</body>
</html>
