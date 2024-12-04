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
                        <h3>Edit Category</h3>
                    </div>
                    <form action="/uteshop/admin/category/update?cid=${category.id}" method="post" enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="mb-3">
                                <label class="form-label"><b>Category Name</b></label>
                                <input type="text" name="category_name" value="${category.name}" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><b>Category Image</b></label>
                                <input class="form-control" type="file" name="fileimage" onchange="chooseFile(this)"><br>
                            </div>
                            <div class="mb-3">
                             <c:if test="${category.image != ''}">
                 
                          <c:if test ="${category.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${category.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${category.image.substring(0,5) == 'https' }">
                            <c:url value="${category.image}" var="imgUrl"></c:url>
                       </c:if>
                       </c:if>
        
                                <label class="form-label"><b>Uploaded Image:&nbsp;</b></label>${category.image}&emsp;
                                <img id="imagess" src="${imgUrl}" style="width: 80px; height: 80px; width: auto;">
                
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