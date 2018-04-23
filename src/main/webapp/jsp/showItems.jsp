<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
<head><title>Item List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<div class="container">
    <h1>List of Items</h1>
    <a class="btn btn-success pull-right" href="<c:url value='/item'/>" role="button">Create Item</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Index</td>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Price</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"/></td>
                <td><c:out value="${item.id}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.description}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td><a class="btn btn-warning" href="<c:url value="/update_item/${item.id}"/>" role="button">Update</a></td>
                <td><a class="btn btn-danger" href="<c:url value="/delete_item/${item.id}"/>" role="button">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="color: red"><c:out value="${error}"/></div>
</div>
</body>
</html>
