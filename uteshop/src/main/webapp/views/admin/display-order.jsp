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

	<!-- order details -->
	<div class="container-fluid px-3 py-3">
		<c:choose>
			<c:when test="${orderList == null || orderList.isEmpty()}">
				<div class="container mt-5 mb-5 text-center">
					<img src="/uteshop/Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
					<h4 class="mt-3">Zero Order found</h4>
				</div>
			</c:when>
			<c:otherwise>
				<div class="container-fluid">
					<table id="admin-table-order" class="table table-hover">
					<thead>
						<tr class="table-primary" style="font-size: 18px;">
							<th class="text-center">Product</th>
							<th>Order ID</th>
							<th>Product Details</th>
							<th>Delivery Address</th>
							<th>Date & Time</th>
							<th>Payment Status</th>
							<th>Payment Type</th>
							<th>Status</th>
							<th class="text-center">Action</th>
						</tr>
					   </thead>
					      <tbody>
						<c:forEach var="order" items="${orderList}">
							<c:set var="ordProdList" value="${order.order_detail_list}" />
							<c:forEach var="orderProduct" items="${ordProdList}">
								
									<tr>
										<td class="text-center">
										<c:if test="${orderProduct.product.image != ''}">
                 
                          <c:if test ="${orderProduct.product.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${orderProduct.product.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${orderProduct.product.image.substring(0,5) == 'https' }">
                            <c:url value="${orderProduct.product.image}" var="imgUrl"></c:url>
                       </c:if>
                       </c:if>
											<img src="${imgUrl}" style="width: 50px; height: 50px; width: auto;">
										</td>
										<td>${order.id}</td>
										<td>
											${orderProduct.product.name}<br>
											Quantity: ${orderProduct.numberOfProducts}<br>
											Total Price:<span class="total-price-order-admin"> ${orderProduct.price * orderProduct.numberOfProducts}&#8363;</span>
										</td>
										<td>
											${order.user.username}<br>
											Mobile No. ${order.user.phoneNumber}<br>
											${order.user.address}
										</td>
										<td>${order.orderdate}</td>
										<td>
										${order.payment_status}
										<form action="/uteshop/admin/order/update?oid=${order.id}" method="post">
											<button type="submit" class="btn btn-secondary">Update</button>
											<select name="payment_status" class="form-select">
											
												<option value="paid">Paid</option>
												<option value="unpaid">Unpaid</option>
											</select>
											</form>
										</td>
										<td>${order.payment_method}</td>
										<td>${orderProduct.status}</td>
										<td>
										<form action="/uteshop/admin/order/update?odid=${orderProduct.id}" method="post">
											<button type="submit" class="btn btn-secondary">Update</button>
											<select id="operation" name="status" class="form-select">
											
												<option value="OrderConfirmed">Order Confirmed</option>
												<option value="Pending">Pending</option>
												<option value="Delivered">Delivered</option>
												<option value="Returned">Returned</option>
												<option value="CancelReturned">Cancel Returned</option>
												<option value="Refunded">Refunded</option>
												<option value="Cancel">Cancel</option>
											</select>
										
												</form>
											<form action="/uteshop/admin/order/delete?odid=${orderProduct.id}" method="post">
											   <button type="submit" class="btn btn-secondary">Delete</button>
											</form>
										</td>
										
									</tr>
							
							</c:forEach>
						</c:forEach>
						   <tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
	$(document).ready(function() {
	    $('#admin-table-order').DataTable({
	        "paging": true,        // Bật phân trang
	        "searching": true,    // Bật tìm kiếm
	        "ordering": true,     // Bật sắp xếp cột
	        "info": true,         // Hiển thị thông tin bảng
	        "lengthMenu": [5, 10, 25, 50], // Số hàng mỗi trang
	        "language": {
	            "zeroRecords": "No result found",
	            "infoEmpty": "No result",
	            "infoFiltered": "(lọc từ MAX dòng)",
	            "search": "Search",
	            "paginate": {
	                "first": "First",
	                "last": "Last",
	                "next": "Next",
	                "previous": "Previous"
	            }
	        }
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
	formatPrices('total-price-order-admin');
	</script>
</body>
</html>