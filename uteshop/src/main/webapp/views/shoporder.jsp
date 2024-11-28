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
            <div class="container-fluid">
                <table id="table-order" class="table  table-hover">
                 <thead>
                    <tr class="table-primary" style="font-size: 18px;">
                        <th class="text-center">Product</th>
                        <th>Order ID</th>
                        <th>Product detail</th>
                        <th>Shipping address</th>
                        <th>Date & Time</th>
                        <th>Status</th>
                        <th>Shipping unit</th>
                        <th>Total money</th>
                        <th>Payment method</th>
                        <th>Payment status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <c:forEach var="orderProduct" items="${order.order_detail_list}">
                            <form action="/uteshop/user/order/update?oid=${order.id}" method="post">
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
                                        <img src="/uteshop/Images/${orderProduct.product.image}" style="width: 50px; height: 50px; width: auto;">
                                    </td>
                                    <td>${order.id}</td>
                                    <td>
                                        ${orderProduct.product.name}<br>
                                        Quantity: ${orderProduct.numberOfProducts}<br>
                                        Total Price: &#8377;<fmt:formatNumber value="${orderProduct.price * orderProduct.numberOfProducts}" 
                  type="number" 
                  maxFractionDigits="2" 
                  groupingUsed="true"/>
                                        

                                    </td>
                                    <td>
                                        ${order.user.username}<br>
                                        Mobile No. ${order.user.phoneNumber}<br>
                                        ${order.shipping_address}
                                    </td>
                                    <td>${order.orderdate}</td>
                                   
                                    <td>${order.status}</td>
                                    <td>${order.shipunit.name }</td>
                                    <td>${order.totalmoney }</td>
                                     <td>${order.payment_method}</td>
                                     <td>${order.payment_status }</td>
                                </tr>
                            </form>
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
	            $('#table-order').DataTable({
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
 </script>
