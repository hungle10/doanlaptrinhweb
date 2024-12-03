<div class="container-fluid px-3 py-3">
    <c:choose>
        <c:when test="${empty orderList}">
            <div class="container mt-5 mb-5 text-center">
                <img src="/uteshop/Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Order found</h4>
                Looks like you haven't placed any order!
            </div>
        </c:when>
        <c:otherwise>
            <h4>My Order</h4>
            <hr>
            <div class="container">
                <table id="tableOrder" class="table table-hover table-sm small">
                    <thead>
                        <tr class="text-center table-secondary">
                            <th>Product</th>
                            <th>Order ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Total Price</th>
                            <th>Date and Time</th>
                            <th>Payment status</th>
                            <th>Payment Type</th>
                            <th>Status</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <c:forEach var="orderProduct" items="${order.order_detail_list}">
                                <tr class="text-center">
                                    <form action="/uteshop/user/orderdetail/update?odid=${orderProduct.id}&from=user" method="post">
                                        <td>
                                            <img src="/uteshop/Images/${orderProduct.product.image}" style="width: 30px; height: 30px;">
                                        </td>
                                        <td class="text-start">${order.id}</td>
                                        <td class="text-start">${orderProduct.product.name}</td>
                                        <td>${orderProduct.numberOfProducts}</td>
                                        <td><span class="totalmoney11">${order.totalmoney}</span>&#8363;</td>
                                        <td>${order.orderdate}</td>
                                        <td>${order.payment_status}</td>
                                        <td>${order.payment_method}</td>
                                        <td class="fw-semibold">
                                            <c:choose>
                                                <c:when test="${orderProduct.status == 'Pending'}">
                                                    <span style="color: red;">${orderProduct.status}</span>
                                                </c:when>
                                                <c:when test="${orderProduct.status == 'Cancelled'}">
                                                    <span style="color: red;">${orderProduct.status}</span>
                                                </c:when>
                                                <c:when test="${orderProduct.status == 'Returned'}">
                                                    <span style="color: yellow;">${orderProduct.status}</span>
                                                </c:when>
                                                <c:when test="${orderProduct.status == 'Refunded'}">
                                                    <span style="color: green;">${orderProduct.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: green;">${orderProduct.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                             <c:choose>
                                      <c:when test="${orderProduct.status == 'Delivered'}">
                                          <button type="submit" class="btn btn-secondary">Update</button>
                                             <select id="operation" name="status" class="form-select">
							                
						  	                     <option value="Returned">Returned</option>
						                         <option value="CancelReturned">Cancel Returned</option>
					                         </select>
                                      </c:when>
                                       <c:when test="${orderProduct.status == 'Returned'}">
                                          <button type="submit" class="btn btn-secondary">Update</button>
                                           <select id="operation" name="status" class="form-select">
							      
						  	                     <option value="Returned">Returned</option>
						                         <option value="CancelReturned">Cancel Returned</option>
					                         </select>
                                      </c:when>
                                      <c:when test="${orderProduct.status == 'CancelReturned'}">
                                          <button type="submit" class="btn btn-secondary">Update</button>
                                           <select id="operation" name="status" class="form-select">
							            
						  	                     <option value="Returned">Returned</option>
						                         <option value="CancelReturned">Cancel Returned</option>
					                         </select>
                                      </c:when>
                                      <c:when test="${orderProduct.status == 'Cancel'}">
                                          <button type="submit" class="btn btn-secondary">Update</button>
                                      </c:when>
                                      <c:when test="${orderProduct.status == 'Pending'}">
                                          <button type="submit" class="btn btn-secondary">Update</button>
                                          <select id="operation" name="status" class="form-select">
							  
						                  <option value="Cancelled">Cancel Order</option>
							                <!--<option value="Out For Delivery">Out For Delivery</option>
							             <option value="Delivered">Delivered</option> -->
					                 </select>
                                      </c:when>
                                   <c:otherwise>
                                     <button type="submit" class="btn btn-success disabled">Update</button>
                                     
                                  </c:otherwise>
                             </c:choose>
                 
                                        </td>
                                    </form>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script>
function formatPrices(className) {
    var elements = document.querySelectorAll('.' + className);
    elements.forEach(function(element) {
        var value = element.innerText;

        // Loại bỏ ký hiệu tiền tệ (₫) và khoảng trắng
        value = value.replace('₫', '').trim();

     
            value = Number(value).toLocaleString('vi-VN');
            element.innerText = value;
    
    });
}

// Gọi hàm formatPrices cho từng loại giá
formatPrices('totalmoney11');
</script>
