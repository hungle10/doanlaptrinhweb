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
		<table id="table-admin-user" class="table table-hover">
		<thead>
			<tr class="text-center table-primary" style="font-size: 18px;">
				<th>User Name</th>
				<th>Email</th>
				<th>Phone No.</th>
	            
				<th>Address</th>
				<th>Register Date</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
			</thead>
			  <tbody>
			<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.phoneNumber}</td>
			
				<td>${user.address}</td>
				<td>${user.createdAt}</td>
				<td><c:choose>
        <c:when test="${user.isActive}">
            Hoạt động
            <!-- Nút để khóa user -->
            <form action="/uteshop/admin/user/update" method="post" style="display:inline;">
                <input type="hidden" name="uid" value="${user.id}">
                <input type="hidden" name="isActive" value="false">
                <button type="submit" class="btn btn-warning btn-sm">Khóa</button>
            </form>
        </c:when>
        <c:otherwise>
            User này bị khóa
            <!-- Nút để kích hoạt lại user -->
            <form action="/uteshop/admin/user/update" method="post" style="display:inline;">
                <input type="hidden" name="uid" value="${user.id}">
                <input type="hidden" name="isActive" value="true">
                <button type="submit" class="btn btn-success btn-sm">Mở khóa</button>
            </form>
        </c:otherwise>
    </c:choose>
				</td>
				<td>
                    <form action="/uteshop/admin/user/delete" method="post" style="display:inline;">
                        <input type="hidden" name="uid" value="${user.id}">
                        <button type="submit" class="btn btn-danger">Remove</button>
                     </form>
               </td>

			</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
	$(document).ready(function() {
	    $('#table-admin-user').DataTable({
	        "paging": true,        // Bật phân trang
	        "searching": true,    // Bật tìm kiếm
	        "ordering": true,     // Bật sắp xếp cột
	        "info": true,         // Hiển thị thông tin bảng
	        "lengthMenu": [5, 10, 25, 50], // Số hàng mỗi trang
	        "language": {
	            "zeroRecords": "No result found",
	            "infoEmpty": "No result",
	            "infoFiltered": "(lọc từ MAX dòng)",
	            "search": "Search",
	            "paginate": {
	                "first": "First",
	                "last": "Last",
	                "next": "Next",
	                "previous": "Previous"
	            }
	        }
	    });
	});
	</script>
</body>
</html>