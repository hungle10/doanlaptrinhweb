<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>
<body>
<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>
		<div class="container mt-5" style="font-size: 17px;">
		<div class="row">

			<!-- left column -->
			<div class="col-md-8">
				<div class="card">
					<div class="container px-3 py-3">
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Delivery Address</h4>
							</div>
						</div>
						<div class="mt-3 mb-3">
							<h5>${sessionScope.activeUser.username}
								&nbsp;
								${sessionScope.activeUser.phoneNumber}</h5>
							<c:set var="fullAddress">
                                ${sessionScope.activeUser.address}
                            </c:set>
                            <p>${fullAddress}</p>
							<br>
						</div>
						<div class="mt-3 mb-3">
                <label for="shippingOption" class="form-label">Select Shipping Unit:</label>
                <select id="shippingOption" class="form-select" name="shippingCarrier" onchange="calculateShipping(this)" required>
                    <option value="" disabled selected>Select a shipping unit</option>
                   <c:forEach var="shippingunit" items="${shippingUnits}">
                             <option value="${shippingunit.id}">${shippingunit.name}</option>
                  </c:forEach>
                </select>
                     </div>

						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Payment Options</h4>
							</div>
						</div>
						<form action="/uteshop/user/order/add" method="post">
							<div class="form-check mt-2">
								<input class="form-check-input" type="radio" name="payementMode"
									value="cardpayment" required><label class="form-check-label">Credit
									/Debit /ATM card</label><br>
								<div class="mb-3">
								</div>
								<input class="form-check-input" type="radio" name="payementMode"
									value="COD"><label
									class="form-check-label">Cash on Delivery</label>
							</div>
							<div class="text-end">
								<button type="submit"
									class="btn btn-lg btn-outline-primary mt-3">Place
									Order</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- end of column -->

			<!-- right column -->
			<div class="col-md-4">
				<div class="card">
					<div class="container px-3 py-3">
						<h4>Price Details</h4>
						<hr>
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td>${totalProduct}</td>
							</tr>
							<tr>
								<td>Total Price</td>
								 <td> <span id="totalPrice"><c:out value="${totalPrice}"/></span>&#8363;</td>
							</tr>
							<tr>
								<td>Delivery Charges</td>
								<td><span id="cost">0.00</span></td>
							</tr>
							<tr>
								<td><h5>Amount Payable :</h5></td>
                                   <td>
                                        <h5><span id="amountPayable"><c:out value="${totalPrice}"/></span></h5>

                                  </td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<!-- end of column -->
		</div>
	</div>
	<script>
	function calculateShipping(selectElement) {
	    const carrier = selectElement.value; // Lấy giá trị của nhà vận chuyển
	    // Loại bỏ ký tự không phải số và chuyển thành số thực
	    const totalPrice = parseFloat(document.getElementById("totalPrice").innerText.replace(/[^\d]/g, ""));

	    fetch('/uteshop/user/checkout/shippingcaculator?carrier=' + carrier)
	        .then(response => response.json()) // Đọc dữ liệu trả về dưới dạng JSON
	        .then(data => {
	            const deliveryCharge = parseFloat(data.cost); // Chuyển thành số thực
	            // Hiển thị phí vận chuyển
	            document.getElementById("cost").innerText = deliveryCharge.toLocaleString('vi-VN') + '₫';

	            // Tính toán tổng tiền phải trả
	            const totalAmountPayable = totalPrice + deliveryCharge;

	            // Định dạng và hiển thị tổng tiền phải trả
	            document.getElementById("amountPayable").innerText = totalAmountPayable.toLocaleString('vi-VN') + '₫';
	        })
	        .catch(error => {
	            console.error("Error fetching shipping cost:", error);
	            document.getElementById("cost").innerText = "Error";
	        });
	}

	function formatPrice(elementId) {
	    var element = document.getElementById(elementId);
	    var value = element.innerText;

	    // Loại bỏ ký hiệu tiền tệ (₫) và khoảng trắng
	    value = value.replace('₫', '').trim();

	   
	        // Chuyển sang dạng số bình thường và thêm định dạng
	        value = Number(value).toLocaleString('vi-VN');
	        element.innerText = value;

	}

	// Gọi hàm formatPrice cho từng phần tử
	formatPrice('totalPrice');
	formatPrice('cost');
	formatPrice('amountPayable');

	</script>
</body>
</html>