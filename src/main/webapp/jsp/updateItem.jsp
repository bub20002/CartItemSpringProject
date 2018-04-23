<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
<head>
    <title>Update</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<form:form name="updateItem" method="POST">
    <div class="container">
        <h1>Update Item</h1>
        <div type="div-form">

            <h3>ID = <c:out value="${item.id}"/></h3>
            <p><input type="text" name="id" value="<c:out value="${item.id}"/>" readonly hidden/></p>

            <p>Name</p>
            <p><input class = "text-form" type="text" name="name" value="<c:out value="${item.name}"/>"/></p>

            <p>Description</p>
            <p><input class = "text-form" type="text" name="description" value="<c:out value="${item.description}"/>"/></p>

            <p>Price</p>
            <p><input class = "text-form" type="text" name="price" value="<c:out value="${item.price}"/>"/></p>

            <br><input class ="submit-form" type="submit" value="Submit"/>
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>
