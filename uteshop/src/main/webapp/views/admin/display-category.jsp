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

<!-- Category -->
<div class="container mt-3">
  
    <table class="table table-hover">
        <tr class="table-primary text-center" style="font-size: 20px;">
            <th>Image</th>
            <th>Category Name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="category" items="${categoryList}">
            <tr class="text-center">
                <td>
                    <img src="/uteshop/Images/${category.image}" 
                         style="width: 60px; height: 60px; width: auto;">
                </td>
                <td>${category.name}</td>
                <td>
                    <a href="/uteshop/admin/category/update?cid=${category.id}" 
                       role="button" class="btn btn-secondary">Update</a>
                    &emsp;
                   <form action="/uteshop/admin/category/delete" method="post" style="display: inline;">
                        <input type="hidden" name="cid" value="${category.id}">
                       <button type="submit" class="btn btn-danger">Delete</button>
                  </form>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>
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
</body>
</html>