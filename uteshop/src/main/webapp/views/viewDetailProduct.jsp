<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.real-price {
	font-size: 26px !important;
	font-weight: 600;
}

.product-price {
	font-size: 18px !important;
	text-decoration: line-through;
}

.product-discount {
	font-size: 16px !important;
	color: #027a3e;
}
 
</style>
<!-- add các link này vào mới hoàn thiện được phần bình luận-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>
<div class="container mt-5">
			<!-- them cau lenh alert -->
			<c:if test="${not empty message}">
    <div class="message">
        <i class="fas fa-exclamation-circle"></i> ${message}
    </div>
</c:if>
		<div class="row border border-3">
			<div class="col-md-6">
				<div class="container-fluid text-end my-3">
					<img src="<c:url value='/Images/${requestScope.product.image}'/>"
						class="card-img-top"
						style="max-width: 100%; max-height: 500px; width: auto;">
				</div>
            </div>
          <div class="col-md-6">
				<div class="container-fluid my-5">
					<h4>${requestScope.product.name}</h4>
					<span class="fs-5"><b>Description</b></span><br> <span>${requestScope.product.description}</span><br>
					<span class="real-price">&#8363;${requestScope.product.price}</span>&ensp;
					<span class="product-price">&#8363;${requestScope.product.price_after_discount}</span>&ensp;
					<span class="product-discount">${requestScope.product.discount}&#37;off</span><br>
					<span class="fs-5"><b>Status : </b></span> <span id="availability">
						<c:choose>
                             <c:when test="${product.quantity > 0}">
                                    Available
                             </c:when>
                            <c:otherwise>
                                     Currently Out of stock
                             </c:otherwise>
                       </c:choose>
					</span><br> <span class="fs-5"><b>Category : </b></span> <a href="/uteshop/view/product?category=${requestScope.category.name}">
                                                                     ${requestScope.category.name}
                                                                        </a>
					<br> <span class="fs-5"><b>Shop : </b></span> <a href="/uteshop/view/shop?id=${requestScope.shop.id}" >
                                                                     ${requestScope.shop.name}
                                                                        </a>
					<form method="post">
						<div class="container-fluid text-center mt-3">
							<c:if test="${sessionScope.activeUser != null}">
							<button type="submit"
								formaction="/uteshop/user/addcart?uid=${sessionScope.activeUser.id}&pid=${requestScope.product.id}" id="add-cart-btn"
								class="btn btn-primary text-white btn-lg">Add to Cart</button>
							&emsp;
							<a
								href="/uteshop/user/checkout?from=buynow&pid=${requestScope.product.id}" id="buy-now-btn"
								class="btn btn-info text-white btn-lg" role="button"
								aria-disabled="true">Buy Now</a> 
							</c:if>
						</div>
					</form>
				</div>
			</div>
	</div>
	</div>
	<script>
		$(document).ready(function() {
			if ($('#availability').text().trim() == "Currently Out of stock") {
				$('#availability').css('color', 'red');
				$('#buy-now-btn').addClass('disabled');
				$('#add-cart-btn').addClass('disabled');
			}
		});
	</script>
<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-8">
            <div class="card p-4 mt-2 shadow-sm">
               <c:forEach var="comment" items="${requestScope.commentList}">
                <div class="d-flex justify-content-between align-items-center">
                    <div class="user d-flex align-items-center">
                        <img src="https://i.imgur.com/C4egmYM.jpg" width="40" class="user-img rounded-circle mr-3">
                        <div>
                            <small class="font-weight-bold text-primary">"${comment.username}"</small>
                            <small class="font-weight-bold d-block">"${comment.comment_text}"</small>
                        </div>
                    </div>
                    <small class="text-muted">${comment.created_at}"</small>
                </div>
                <div class="action d-flex justify-content-between mt-3 align-items-center">
                    <div class="reply px-4">
    <form action="/uteshop/user/comment/remove" method="POST" style="display:inline;">
        <input type="hidden" name="comment_id" value="${comment.id}">
        <button type="submit" class="btn btn-link text-secondary p-0" style="border: none; background: none;">
            <small>Remove</small>
        </button>
    </form>
    <button type="button" class="btn btn-link text-secondary p-0"
									data-bs-toggle="modal" data-bs-target="#exampleModal" style="border: none; background: none;">
									<small>Chỉnh sửa</small></button>
</div>
                    <div class="icons align-items-center">
                        <i class="fa fa-check-circle-o check-icon text-primary"></i>
                    </div>
                </div>
                 <div class="d-flex justify-content-start">
                 <c:if test="${comment.image != ''}">
                          <c:if test ="${comment.image.substring(0,5) != 'https' }">
                           <c:url value="/image?fname=${comment.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${cate.images.substring(0,5) == 'https' }">
                            <c:url value="${comment.image}" var="imgUrl"></c:url>
                       </c:if>
                       
                       <img id="imagess"  src="${imgUrl}" class="comment-media img-fluid rounded mr-2" width="35%" alt="Comment Image" /><br>
                   </c:if>
                       <c:if test="${comment.video != ''}">
                        <c:if test ="${comment.video.substring(0,5) != 'https' }">
                      <c:url value="/video?fname=${comment.video}" var="videoUrl"></c:url>
                      </c:if>
                      
            
                     <c:if test ="${comment.video.substring(0,5) == 'https' }">
                     <c:url value="${comment.video}" var="videoUrl"></c:url>
                     </c:if>
                     
                     <video id="videoElement" class="comment-media" width="35%" controls>
                     <source src="${videoUrl}" type="video/mp4">
                     </video>
                     </c:if>
                   </div>
                 </c:forEach>
            </div>

            <!-- Comment submission area -->
            <div class="card p-4 mt-3 shadow-sm">
                <form action="/uteshop/user/comment/add?pid=${requestScope.product.id}" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="commentInput">Add a comment:</label>
                        <input type="text" class="form-control" id="commentInput" name="comment" placeholder="Write your comment here">
                    </div>
               
                    <label for="images">Images:</label><br>
                         <input type="file" id="images" name="fileimage" onchange="chooseFile(this)" value = ${comment.image }><br>
                       
                         

                      <label for="videos">Videos :</label><br>
                      <input type="file" id="videos" name="filevideo" onchange="chooseFileVideo(this)" value = ${comment.video }><br>
                      
                           <button type="submit" class="btn btn-primary">Post Comment</button><br>


                </form>
            </div>
        </div>
    </div>
</div>





<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Chỉnh sửa</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <!-- Modal Form -->
            <form action="/uteshop/user/comment/edit" method="POST" enctype="multipart/form-data">
                <div class="modal-body">
                    <!-- Hidden Input for Comment ID -->
                    <input type="hidden" name="comment_id" value="${comment.comment_id}">
                    <!-- Comment Content Input -->
                    <div class="form-group">
                        <label for="commentContent">Nội dung bình luận</label>
                        
                        <textarea class="form-control" id="commentContent" name="comment_content" rows="3" placeholder="Nhập nội dung mới tại đây">${comment.comment_content}</textarea>
                        <label for="images">Images:</label><br>
                         <input type="file" id="images" name="file" onchange="chooseFile(this)" value = ${comment.image }><br>
                         <c:if test ="${comment.image.substring(0,5) != 'https' }">
                           <c:url value="/image?fname=${comment.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${cate.images.substring(0,5) == 'https' }">
                            <c:url value="${comment.image}" var="imgUrl"></c:url>
                       </c:if>
                       <img id="imagess"  src="${imgUrl}" width="80" height="70" /><br>
                         

                      <label for="videos">Videos :</label><br>
                      <input type="file" id="videos" name="file" onchange="chooseFileVideo(this)" value = ${video.images }><br>


                       <c:if test ="${comment.video.substring(0,5) != 'https' }">
                      <c:url value="/video?fname=${comment.video}" var="videoUrl"></c:url>
                      </c:if>
            
                     <c:if test ="${comment.video.substring(0,5) == 'https' }">
                     <c:url value="${comment.video}" var="videoUrl"></c:url>
                     </c:if>
                     <video id="videoElement" width="80" height="70" controls>
                     <source src="${videoUrl}" type="video/mp4">
                     </video>
                    </div>
                </div>

                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

	<!-- end modal -->
	
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
<script>
function chooseFileVideo(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            // Update the video source to the uploaded file's data URL
            $('#videoElement').attr('src', e.target.result);
            $('#videoElement')[0].load();  // Reload the video element to play the new file
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}
</script>
</body>
</body>
</html>