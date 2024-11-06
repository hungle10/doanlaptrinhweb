<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping cart</title>
</head>
<body>
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>
	
<c:if test="${empty requestScope.listOfCart}">
<!-- nếu ng dùng không có listOfCart -->
<div class="container text-center py-5 px-5">
		<img src="/uteshop/Images/empty-cart.png" style="max-width: 250px;"
			class="img-fluid">
		<h4 class="mt-5">Your cart is empty!</h4>
		<p>Add items to it now.</p>
		<a href="products.jsp" class="btn btn-primary btn-lg" role="button"
			aria-disabled="true">Shop Now</a>
	</div>
</c:if>
<!-- nếu ng dùng có listOfCart -->
<c:if test="${not empty requestScope.listOfCart}">
<div class="container mt-5">
		<div class="card px-3 py-3">
			<table class="table table-hover">
				<thead>
					<tr class="table-primary text-center" style="font-size: 18px;">
						<th>Item</th>
						<th>Item Name</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total price</th>
						<th>Remove</th>
					</tr>
				</thead>
		<tbody>
			<c:forEach var="cart" items="${requestScope.listOfCart}">
				<c:forEach var="product" items="${requestScope.listOfProductInCart}">
				 <c:if test="${cart.productId == product.id}">
				    <tr class="text-center">
						<td><img src="/uteshop/Images/${product.image}" style="width: 50px; height: 50px; width: auto;"></td>
						<td class="text-start">${product.name}</td>
						<td>&#8377;${product.price_after_discount}</td>
						<td>
							<a href="CartOperationServlet?cid=${cart.cartId}&opt=1" role="button" class="btn btn-light" style="border-radius: 50%; font-size: 8px;">
								<i class="fa-regular fa-plus fa-2xl"></i>
							</a>
							<div class="qty">${cart.quantity}</div>
							<c:choose>
								<c:when test="${cart.quantity > 1}">
									<a href="CartOperationServlet?cid=${cart.cartId}&opt=2" role="button" class="btn btn-light" id="qtyDesc" style="border-radius: 50%; font-size: 8px;">
										<i class="fa-solid fa-minus fa-2xl"></i>
									</a>
								</c:when>
								<c:otherwise>
									<a href="CartOperationServlet?cid=${cart.cartId}&opt=2" role="button" class="btn btn-light disabled" id="qtyDesc" style="border-radius: 50%; font-size: 8px;">
										<i class="fa-solid fa-minus fa-2xl"></i>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>&#8377;${cart.quantity * product.price_after_discount}</td>
						<td><a href="CartOperationServlet?cid=${cart.cartId}&opt=3" class="btn btn-secondary" role="button">Remove</a></td>
					</tr>
					</c:if>
						</c:forEach>
				</c:forEach>
				<tr>
    <td class="text-end" colspan="8">
        <h4 class='pe-5'>
            Total Amount : &#8377;
            <c:out value="${totalPrice}" />
        </h4>
    </td>
</tr>
</tbody>
</table>
<div class="text-end">
			<a href="products.jsp" class="btn btn-outline-primary" role="button" >Continue Shopping</a>&nbsp; 
			<a href="checkout.jsp" id="checkout-btn" class="btn btn-outline-primary" role="button" >Checkout</a>
</div>
</div>
</div>
</c:if>
</body>
</html>