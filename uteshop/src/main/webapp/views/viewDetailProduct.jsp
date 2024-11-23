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
								formaction="/uteshop/user/addcart?uid=${sessionScope.activeUser.id}&pid=${requestScope.product.id}"
								class="btn btn-primary text-white btn-lg">Add to Cart</button>
							&emsp;
							<a
								href="/uteshop/user/checkout?from=buynow&pid=${requestScope.product.id}" id="buy-btn"
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
				$('.btn').addClass('disabled');
			}
		});
	</script>
<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-8">
            <div class="card p-4 mt-2 shadow-sm">
                <div class="d-flex justify-content-between align-items-center">
                    <div class="user d-flex align-items-center">
                        <img src="https://i.imgur.com/C4egmYM.jpg" width="40" class="user-img rounded-circle mr-3">
                        <div>
                            <small class="font-weight-bold text-primary">olan_sams</small>
                            <small class="font-weight-bold d-block">Loving your work and profile!</small>
                        </div>
                    </div>
                    <small class="text-muted">3 days ago</small>
                </div>
                <div class="action d-flex justify-content-between mt-3 align-items-center">
                    <div class="reply px-4">
                        <small class="text-secondary">Remove</small>
                        <span class="dots">•</span>
                        <small class="text-secondary">Reply</small>
                        <span class="dots">•</span>
                        <small class="text-secondary">Translate</small>
                    </div>
                    <div class="icons align-items-center">
                        <i class="fa fa-check-circle-o check-icon text-primary"></i>
                    </div>
                </div>
            </div>

            <!-- Comment submission area -->
            <div class="card p-4 mt-3 shadow-sm">
                <form>
                    <div class="form-group">
                        <label for="commentInput">Add a comment:</label>
                        <input type="text" class="form-control" id="commentInput" placeholder="Write your comment here">
                    </div>
                    <button type="submit" class="btn btn-primary">Post Comment</button>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</body>
</html>