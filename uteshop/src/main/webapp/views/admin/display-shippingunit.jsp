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
<div class="modal fade" id="add-shippingunit" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
 <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="addCategoryModalLabel">Add Shipping Unit Here</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="/uteshop/admin/shippingunit/add" method="post">
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label"><b>Shipping unit name</b></label>
            <input type="text" name="shippingunit_name" placeholder="Enter shipping unit here" class="form-control" required>
          </div>
          <div class="mb-3">
            <label class="form-label"><b>Shipping unit email</b></label>
            <input type="text" name="shippingunit_email" placeholder="Enter shipping unit here" class="form-control" required>
          </div>
          <div class="mb-3">
            <label class="form-label"><b>Shipping unit contact number</b></label>
            <input type="number" name="shippingunit_contact_number" placeholder="Enter shipping unit here" class="form-control" required>
          </div>
          <div class="mb-3">
            <label class="form-label"><b>Shipping unit address</b></label>
            <input type="text" name="shippingunit_address" placeholder="Enter shipping unit here" class="form-control" required>
          </div>
          <div class="mb-3">
            <label class="form-label"><b>Shipping unit fee</b></label>
            <input type="number" name="shippingunit_delivery_fee" placeholder="Enter shipping unit here" class="form-control" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary me-3">Add Category</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>