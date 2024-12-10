<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Navbar -->
    <%@ include file="/Components/navbar.jsp" %>
      <!-- Update Category -->
    <div class="container mt-5">
        <div class="row row-cols-1 row-cols-md-1 offset-md-2">
            <div class="col">
                <div class="card w-75">
                    <div class="card-header text-center">
                        <h3>Edit Shipping Unit</h3>
                    </div>
                    <form action="/uteshop/admin/shippingunit/update?sid=${shippingunit.id}" method="post">
                        <div class="card-body">
                            <div class="mb-3">
                                <label class="form-label"><b>Shipping Unit Name</b></label>
                                <input type="text" name="shippingunit_name" value="${shippingunit.name}" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><b>Contact Number</b></label>
                                <input type="number" name="shippingunit_contact_number" value="${shippingunit.contact_number}" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><b>Address</b></label>
                                <input type="text" name="shippingunit_address" value="${shippingunit.address}" class="form-control">
                            </div>
                             <div class="mb-3">
                                <label class="form-label"><b>Email</b></label>
                                <input type="text" name="shippingunit_email" value="${shippingunit.email}" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><b>Delivery Fee</b></label>
                                <input type="number" name="shippingunit_delivery_fee" value="${shippingunit.delivery_fee}" class="form-control">
                            </div>   
                        </div>
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-lg btn-primary me-3">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- End -->
 
</body>
</html>