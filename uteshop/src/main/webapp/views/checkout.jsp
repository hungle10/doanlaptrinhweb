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
                    <option value="Viettel">Viettel Post</option>
                    <option value="NinjaVan">NinjaVan</option>
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

									<input class="form-control mt-3" type="number"
										placeholder="Enter card number" name="cardno">
									<div class="row gx-5">
										<div class="col mt-3">
											<input class="form-control" type="number"
												placeholder="Enter CVV" name="cvv">
										</div>
										<div class="col mt-3">
											<input class="form-control" type="text"
												placeholder="Valid through i.e '07/23'">
										</div>
									</div>
									<input class="form-control mt-3" type="text"
										placeholder="Enter card holder name" name="name">
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
					<c:if test="${from.trim() == 'cart'}">
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td>${totalProduct}</td>
							</tr>
							<tr>
								<td>Total Price</td>
								 <td>&#8377; <span id="totalPrice"><c:out value="${totalPrice}"/></span></td>
							</tr>
							<tr>
								<td>Delivery Charges</td>
								<td>&#8377; <span id="cost">0.00</span></td>
							</tr>
							<tr>
								<td><h5>Amount Payable :</h5></td>
                                   <td>
                                        <h5>&#8377; <span id="amountPayable"><c:out value="${totalPrice}"/></span></h5>

                                  </td>
							</tr>
						</table>
					</c:if>
						<c:if test="${from.trim() != 'cart'}">
                          <c:set var="pid" value="${sessionScope.pid}" />
                          <c:set var="price" value="${productDao.getProductPriceById(pid)}" />
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td>1</td>
							</tr>
							<tr>
								<td>Total Price</td>
								 <td>&#8377; <span id="totalPrice"><c:out value="${totalPrice}"/></span></td>
							</tr>
							<tr>
								<td>Delivery Charges</td>
								<td>&#8377; <span id="cost">0.00</span></td>
							</tr>
							<tr>
								<td><h5>Amount Payable :</h5></td>
                                   <td>
                                    <h5>&#8377; <span id="amountPayable"><c:out value="${totalPrice}"/></span></h5>

                                  </td>
							</tr>
						</table>
						</c:if>
					</div>
				</div>
			</div>
			<!-- end of column -->
		</div>
	</div>
	<script>
	function calculateShipping(selectElement) {
    const carrier = selectElement.value; // Lấy giá trị của nhà vận chuyển
    const totalPrice = parseFloat(document.getElementById("totalPrice").innerText);

    // Gửi yêu cầu đến servlet
    fetch('/uteshop/user/checkout/shippingcaculator?carrier='+ carrier)
        .then(response => response.json()) // Đọc dữ liệu trả về dưới dạng JSON
        .then(data => {
        	 const deliveryCharge = data.cost;
            // Hiển thị phí vận chuyển
            document.getElementById("cost").innerText = data.cost.toFixed(2);
            // Cập nhật Amount Payable (tổng giá trị sản phẩm + phí vận chuyển)
            const totalAmountPayable = totalPrice + deliveryCharge;
            document.getElementById("amountPayable").innerText = totalAmountPayable.toFixed(2); // Hiển thị tổng tiền phải trả
        })
        .catch(error => {
            console.error("Error fetching shipping cost:", error);
            document.getElementById("cost").innerText = "Error";
        });
       }
	</script>
</body>
</html>