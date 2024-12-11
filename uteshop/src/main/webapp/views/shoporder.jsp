<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid px-3 py-3">
    <c:choose>
        <c:when test="${empty orderList}">
            <div class="container mt-5 mb-5 text-center">
                <img src="Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Order found</h4>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                 <table id="tableOrder" class="table table-hover table-sm small">
                 <thead>
                 <tr class="text-center table-secondary"> 
                        <th class="text-center">Product</th>
                        <th>Order ID</th>
                        <th>Product detail</th>
                        <th>Shipping address</th>
                        <th>Date & Time</th>
                        <th>Status</th>
                        <th>Shipping unit</th>
                        <th>Payment method</th>
                        <th>Payment status</th>
                       <th class="text-center">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <c:forEach var="orderProduct" items="${order.order_detail_list}">
                                <tr>
                                <form action="/uteshop/user/orderdetail/update?odid=${orderProduct.id}&shopid=${requestScope.shop.id}" method="post">
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
                                        Total Price:<span class="product-price-shop-owner">${orderProduct.price * orderProduct.numberOfProducts}</span> &#8363;
             
                                        

                                    </td>
                                    <td>
                                        ${order.user.username}<br>
                                        Mobile No. ${order.user.phoneNumber}<br>
                                        ${order.shipping_address}
                                    </td>
                                    <td>${order.orderdate}</td>
                                   
                                    <td>${orderProduct.status}
                                    
                                    </td>
                                    <td>${order.shipunit.name }</td>
                            
                                     <td>${order.payment_method}</td>
                                     <td>${order.payment_status }</td>
                           <td>
                              	<c:choose>
                                      <c:when test="${orderProduct.status == 'Delivered'}">
                                          <button type="submit" class="btn btn-success disabled">Update</button>
                                      </c:when>
                                      <c:when test="${orderProduct.status == 'CancelReturned'}">
                                          <button type="submit" class="btn btn-success disabled">Update</button>
                                      </c:when>
                                   <c:otherwise>
                                     <button type="submit" class="btn btn-secondary">Update</button>
                                  </c:otherwise>
                             </c:choose>
                 
                                <select id="operation" name="status" class="form-select">
							<option value="OrderConfirmed">Order Confirmed</option>
						  	<option value="Pending">Pending</option>
						  	<option value="Refunded">Refunded</option>
							<!--<option value="Out For Delivery">Out For Delivery</option>
							<option value="Delivered">Delivered</option> -->
					        </select>
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

$(document).ready(function() {
    $('#tableOrder').DataTable({
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

	     
	            value = Number(value).toLocaleString('vi-VN');
	            element.innerText = value;
	    
	    });
	}

	// Gọi hàm formatPrices cho từng loại giá
	formatPrices('product-price-shop-owner');
 </script>
