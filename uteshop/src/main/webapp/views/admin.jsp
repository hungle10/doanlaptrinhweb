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
    <%@ include file="/Components/navbar.jsp" %>
     <!--admin dashboard -->
    <div class="container-fluid py-4 px-3">
      
        <div class="row">
            <div class="container text-center" id="details">
                <img src="/uteshop/Images/admin.png" style="max-width: 180px;" class="img-fluid">
                <h3>
                    Welcome <c:out value="${activeAdmin.username}" />
                </h3>
        
            </div>
        </div>
        <div class="container">
            <div class="row px-3 py-3">
                <div class="col-md-4">
                    <a href=/uteshop/admin/categories>
                        <div class="card text-bg-light mb-3 text-center">
                            <div class="card-body">
                                <img src="/uteshop/Images/categories.png" style="max-width: 80px;" class="img-fluid">
                                <h4 class="card-title mt-3">Category</h4>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="/uteshop/admin/products">
                        <div class="card text-bg-light mb-3 text-center">
                            <div class="card-body">
                                <img src="/uteshop/Images/products.png" style="max-width: 80px;" class="img-fluid">
                                <h4 class="card-title mt-3">Products</h4>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="/uteshop/admin/order">
                        <div class="card text-bg-light mb-3 text-center">
                            <div class="card-body">
                                <img src="/uteshop/Images/order.png" style="max-width: 80px;" class="img-fluid">
                                <h4 class="card-title mt-3">Order</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row px-3 py-3">
                <div class="col-md-4">
                    <a href="/uteshop/admin/users">
                        <div class="card text-bg-light mb-3 text-center">
                            <div class="card-body">
                                <img src="/uteshop/Images/users.png" style="max-width: 80px;" class="img-fluid">
                                <h4 class="card-title mt-3">User's</h4>
                            </div>
                        </div>
                    </a>
                </div>
                 <div class="col-md-4">
                    <a href="/uteshop/admin/shippingunits">
                        <div class="card text-bg-light mb-3 text-center">
                            <div class="card-body">
                                <img src="/uteshop/Images/shipping.png" style="max-width: 80px;" class="img-fluid">
                                <h4 class="card-title mt-3">Shipping Unit</h4>
                            </div>
                        </div>
                    </a>
                  
                </div>
                 <div class="col-md-4">
                    <a href="/uteshop/admin/shops">
                        <div class="card text-bg-light mb-3 text-center">
                            <div class="card-body">
                                <img src="/uteshop/Images/logoshopforapp.png" style="max-width: 80px;" class="img-fluid">
                                <h4 class="card-title mt-3">Shops</h4>
                            </div>
                        </div>
                    </a>
                  
                </div>
               
            </div>
        </div>
    </div>
    <!--end-->
    	<!-- add category modal-->
	<!-- Modal -->
<div class="modal fade" id="add-category" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
 <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="addCategoryModalLabel">Add Category Here</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="/uteshop/admin/category/add" method="post" enctype="multipart/form-data">
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label"><b>Category Name</b></label>
            <input type="text" name="category_name" placeholder="Enter category here" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="formFile" class="form-label"><b>Category Image</b></label>
            <input class="form-control" type="file" name="fileimage" onChange="chooseFile2323(this)" >
            <br>
            <img id="imagess2323" width="80" height="70" />
            <br>
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

	<!-- end of modal -->
		<!-- Modal -->
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

	<!-- end of modal -->
      <script>
    function chooseFile2323(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                // Update the image source to the uploaded file's data URL
                $('#imagess2323').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }
    </script>
</body>
</html>