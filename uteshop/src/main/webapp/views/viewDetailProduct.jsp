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
          <c:remove var="message" scope="session"/>
    </div>
</c:if>
		<div class="row border border-3">
			<div class="col-md-6">
				<div class="container-fluid text-end my-3">
				 <c:if test="${requestScope.product.image != ''}">
                 
                          <c:if test ="${requestScope.product.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${requestScope.product.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${requestScope.product.image.substring(0,5) == 'https' }">
                            <c:url value="${requestScope.product.image}" var="imgUrl"></c:url>
                       </c:if>
                       </c:if>      
					<img src="${imgUrl}"
						class="card-img-top"
						style="max-width: 100%; max-height: 500px; width: auto;">
				</div>
            </div>
          <div class="col-md-6">
				<div class="container-fluid my-5">
					<h4>${requestScope.product.name}</h4>
					<span class="fs-5"><b>Description</b></span><br> <span>${requestScope.product.description}</span><br>
				    <span class="real-price">${requestScope.product.price_after_discount}&#8363;</span>&ensp;
					<span class="product-price">${requestScope.product.price}&#8363;</span>&ensp;
		
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
					</span><br> <span class="fs-5"><b>Category : </b></span> <a href="/uteshop/user/products?category=${requestScope.category.id}">
                                                                     ${requestScope.category.name}
                                                                        </a>
					<br> <span class="fs-5"><b>Shop : </b></span> <a href="/uteshop/user/products?shopid=${requestScope.shop.id}" >
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
        <c:forEach var="comment" items="${requestScope.commentList}">
            <div class="card p-4 mt-2 shadow-sm">
               
                <div class="d-flex justify-content-between align-items-center">
                    <div class="user d-flex align-items-center">
                        <img src="/uteshop/Images/${comment.image_user}" width="40" class="user-img rounded-circle mr-3">
                        <div>
                            <small class="font-weight-bold text-primary">${comment.username}</small>
                            <small class="font-weight-bold d-block">${comment.comment_text}</small>
                        </div>
                    </div>
                    <small class="text-muted">${comment.created_at}"</small>
                </div>
                <div class="action d-flex justify-content-between mt-3 align-items-center">
                    <div class="reply px-4">
        <c:if test="${sessionScope.activeUser.id == comment.user_id}">
    <form action="/uteshop/user/comment/remove?pid=${requestScope.product.id}" method="post" style="display:inline;" enctype="multipart/form-data">
    
        <input type="hidden" name="comment_id" value="${comment.id}">
        <button type="submit" class="btn btn-link text-secondary p-0" style="border: none; background: none;">
            <small>Remove</small>
        </button>
    </form>
     <c:if test="${comment.image != ''}">
                 
                          <c:if test ="${comment.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${comment.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${comment.image.substring(0,5) == 'https' }">
                            <c:url value="${comment.image}" var="imgUrl"></c:url>
                       </c:if>
                       </c:if>
         <c:if test="${comment.video != ''}">
                        
                        <c:if test ="${comment.video.substring(0,5) != 'https' }">
                      <c:url value="/video?fname=${comment.video}" var="videoUrl"></c:url>
                      </c:if>
                      
            
                     <c:if test ="${comment.video.substring(0,5) == 'https' }">
                     <c:url value="${comment.video}" var="videoUrl"></c:url>
                     </c:if>
                     </c:if>
      <!-- modal ở đây nè :vv -->
    <button type="button" class="btn btn-link text-secondary p-0"
									data-bs-toggle="modal" data-bs-target="#exampleModal"
									data-comment-id="${comment.id}"
									data-comment-text="${comment.comment_text}" 
							        data-comment-image="${comment.image }"
							        data-comment-video="${comment.video }"
									
		
									style="border: none; background: none;">
									<small>Chỉnh sửa</small></button>
		 </c:if>
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

                       <c:if test ="${comment.image.substring(0,5) == 'https' }">
                            <c:url value="${comment.image}" var="imgUrl"></c:url>
                       </c:if>
                       
                       <img  src="${imgUrl}" class="comment-media img-fluid rounded mr-2" width="35%" alt="Comment Image" /><br>
                   </c:if>
                       <c:if test="${comment.video != ''}">
                        
                        <c:if test ="${comment.video.substring(0,5) != 'https' }">
                      <c:url value="/video?fname=${comment.video}" var="videoUrl"></c:url>
                      </c:if>
                      
            
                     <c:if test ="${comment.video.substring(0,5) == 'https' }">
                     <c:url value="${comment.video}" var="videoUrl"></c:url>
                     </c:if>
                     
                     <video  class="comment-media" width="35%" controls>
                     <source src="${videoUrl}" type="video/mp4">
                     </video>
                     </c:if>
                   </div>
             
            </div>
                </c:forEach>
     <c:if test="${activeUser != null}">
            <!-- Comment submission area -->
            <div class="card p-4 mt-3 shadow-sm">
                <form action="/uteshop/user/comment/add?pid=${requestScope.product.id}" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="commentInput">Add a comment:</label>
                        <input type="text" class="form-control" id="commentInput" name="comment" placeholder="Write your comment here"  maxlength="50">
                    </div>
               
                    <label for="images">Images:</label><br>
                         <input type="file" id="images" name="fileimage" onchange="chooseFile(this)" ><br>
                        <img id="imagess" src="" width="80" height="70" /><br>
                         

                      <label for="videos">Videos :</label><br>
                      <input type="file" id="videos" name="filevideo" onchange="chooseFileVideo(this)"><br>
                       <video  id="videoElement" width="120" height="100" controls >
                     <source  src= ""  type="video/mp4">
                     </video> 
                           <button type="submit" class="btn btn-primary">Post Comment</button><br>


                </form>
            </div>
            </c:if>
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
            <form action="/uteshop/user/comment/edit?pid=${requestScope.product.id}" method="POST" enctype="multipart/form-data">
                <div class="modal-body">
                    <!-- Hidden Input for Comment ID -->
                    <input type="hidden" name="comment_id" id="modalCommentId">
                    <!-- Comment Content Input -->
                    <div class="form-group">
                        <label for="commentContent">Nội dung bình luận</label>
                         <input type="text" class="form-control" id="modalCommentText" name="comment" placeholder="Write your comment here">
                        <label for="images">Images:</label><br>
                         <input type="file" id="images" name="fileimage" onchange="chooseFile1(this)" ><br>
                        
                         <img id="imagess1" width="80" height="70" /><br>  
                         

                      <label for="videos">Videos :</label><br>
                      <input type="file" id="videos" name="filevideo" onchange="chooseFileVideo1(this)" ><br>


                      
                 <video  id="videoElement1" width="120" height="100" controls >
                     <source  type="video/mp4">
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
function chooseFile1(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            // Update the image source to the uploaded file's data URL
            $('#imagess1').attr('src', e.target.result);
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}
function chooseFileVideo1(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            // Update the video source to the uploaded file's data URL
            $('#videoElement1').attr('src', e.target.result);
            $('#videoElement1')[0].load();  // Reload the video element to play the new file
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}
document.addEventListener('DOMContentLoaded', function () {
    const editCommentModal = document.getElementById('exampleModal');
    editCommentModal.addEventListener('show.bs.modal', function (event) {
        // Lấy nút kích hoạt modal
        const button = event.relatedTarget;

        // Lấy dữ liệu từ data-*
        const commentId = button.getAttribute('data-comment-id');
        const commentText = button.getAttribute('data-comment-text');
        const commentImage = button.getAttribute('data-comment-image');
        const commentVideo = button.getAttribute('data-comment-video');

        // Gán dữ liệu vào modal
        const modalCommentId = editCommentModal.querySelector('#modalCommentId');
        const modalCommentText = editCommentModal.querySelector('#modalCommentText');
        const modalCommentImage = editCommentModal.querySelector('#imagess1');
        const modalCommentVideo = editCommentModal.querySelector('#videoElement1');


        modalCommentId.value = commentId;
        modalCommentText.value = commentText;
        modalCommentImage.src = '/uteshop/Images/'+commentImage;
        modalCommentVideo.src = '/uteshop/Images/'+commentVideo;

        
       
    });
});

function formatPrices(className) {
    var elements = document.querySelectorAll('.' + className);
    elements.forEach(function(element) {
        var value = element.innerText;

        // Loại bỏ ký hiệu tiền tệ (₫) và khoảng trắng
        value = value.replace('₫', '').trim();

     
            value = Number(value).toLocaleString('vi-VN') + '₫';
            element.innerText = value;
    
    });
}

// Gọi hàm formatPrices cho từng loại giá
formatPrices('real-price');
formatPrices('product-price');

</script>


</body>
</body>
</html>