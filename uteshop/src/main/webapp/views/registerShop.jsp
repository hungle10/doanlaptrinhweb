<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/Components/common_css_js.jsp" %>
    <style>
        label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <!--navbar -->
    <%@ include file="/Components/navbar.jsp" %>

    <div class="container-fluid mt-4">
        <div class="row g-0">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-body px-5">
                        <c:if test="${not empty alert}">
                            <div class="message">
                                <i class="fas fa-exclamation-circle"></i> ${alert}
                            </div>
                        </c:if>

                        <div class="container text-center">
                            <img src="/uteshop/Images/createshop.png" style="max-width: 80px;" class="img-fluid">
                        </div>
                        <h3 class="text-center">Create Shop</h3>

                        <!--registration-form-->
                        <form id="register-form" action="/uteshop/user/registerShop" method="post" enctype="multipart/form-data">
                            <div class="mt-2">
                                <label class="form-label">Shop Name</label>
                                <input type="text" name="shop_name" class="form-control" placeholder="Enter shop name" required>
                            </div>

                            <div class="mt-2">
                                <label class="form-label">Description</label>
                                <textarea name="description" class="form-control" rows="3" placeholder="Enter shop description"></textarea>
                            </div>

                            <div class="mt-2">
                                <label class="form-label">Logo</label>
            
                                <input type="file" id="images" name="fileimage" onchange="chooseFile(this)" ><br>
                                <img id="imagess" src="" width="80" height="70" /><br>
                            </div>

                            <div class="mt-2">
                                <label class="form-label">Address</label>
                                <input type="text" name="address" class="form-control" placeholder="Enter shop address" required>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mt-2">
                                    <label class="form-label">Phone Number</label>
                                    <input type="text" name="phone_number" class="form-control" placeholder="Enter phone number" required>
                                </div>
                                <div class="col-md-6 mt-2">
                                    <label class="form-label">Email</label>
                                    <input type="email" name="email" class="form-control" placeholder="Enter shop email" required>
                                </div>
                            </div>
                            <div id="submit-btn" class="container text-center mt-4">
                                <button type="submit" class="btn btn-outline-primary me-3">Submit</button>
                                <button type="reset" class="btn btn-outline-primary">Reset</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
    function chooseFile(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            // Update the image source to the uploaded file's data URL
            $('#imagess').attr('src', e.target.result);
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}
    </script>
</body>
</html>
