<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>

	<div class="container-fluid px-5 py-3">
		<table class="table table-hover">
			<tr class="text-center table-primary" style="font-size: 18px;">
				<th>User Name</th>
				<th>Email</th>
				<th>Phone No.</th>
				<th>Gender</th>
				<th>Address</th>
				<th>Register Date</th>
				<th>Action</th>
			</tr>
			<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.userName}</td>
				<td>${user.userEmail}</td>
				<td>${user.userPhone}</td>
				<td>${user.userGender}</td>
				<td><c:out value="${userDao.getUserAddress(user.userId)}" /></td>
				<td>${user.dateTime}</td>
				<td><a href="UpdateUserServlet?operation=deleteUser&uid=${user.userId}" role="button" class="btn btn-danger">Remove</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>