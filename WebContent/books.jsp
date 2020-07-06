<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<sql:setDataSource driver="com.mysql.jdbc.Driver"
					   url="jdbc:mysql://localhost:3306/librarydb"
					   user="root"
					   password="root"
					   var="con"/>
					   
	<!-- Expression Language (EL) -->
	<sql:query var="rs" dataSource="${con}">
		select * from books
	</sql:query>
				
	<table class="table table-bordered table-striped table-hover">
		<tr>
			<th>Image</th>
			<th>Bookname</th>
			<th>Author</th>
			<th>Price</th>
			<th>Link</th>
		</tr>
		
		<c:forEach items="${rs.rows}" var="row">
			<tr>
				<td><img src="ImageServlet?bookname=${row.bookname}" width="200px" height="250px"/></td>
				<td>${row.bookname}</td>
				<td>${row.author}</td>
				<td>${row.price}</td>
				<td>${row.link}</td>
			</tr>
		</c:forEach>		
	</table> 
					   
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>