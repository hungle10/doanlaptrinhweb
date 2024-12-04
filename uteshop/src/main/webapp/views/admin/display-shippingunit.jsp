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

<!-- Shipping unit -->
<div class="container mt-3">
  
    <table class="table table-hover">
        <tr class="table-primary text-center" style="font-size: 20px;">
       
            <th>Shipping Unit Name</th>
            <th>Contact Number</th>
            <th>Email</th>
            <th>Address</th>
            <th>Delivery Fee</th>
            <th>Create at</th>
            <th>Update at</th>
			<th colspan="2" class="text-center">Action</th>
        </tr>
        <c:forEach var="shippingunit" items="${shippingList}">
            <tr class="text-center">
          
                <td>${shippingunit.name}</td>
                <td>${shippingunit.contact_number}</td>
                <td>${shippingunit.email}</td>
                <td>${shippingunit.address}</td>
                <td>${shippingunit.delivery_fee}</td>
                <td>${shippingunit.createdAt}</td>
                <td>${shippingunit.updatedAt}</td>
                <td>
                    <a href="/uteshop/admin/shippingunit/update?sid=${shippingunit.id}" 
                       role="button" class="btn btn-secondary">Update</a>
                    &emsp;
                   <form action="/uteshop/admin/shippingunit/delete" method="post" style="display: inline;">
                        <input type="hidden" name="sid" value="${shippingunit.id}">
                       <button type="submit" class="btn btn-danger">Delete</button>
                  </form>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>